package computeCodeGeneratorTests;

import static org.junit.Assert.assertEquals;

import org.askalon.jlibcloud.computeCodeGenerator.Method;
import org.junit.Before;
import org.junit.Test;

public class MethodTest {

	private Method m;
	private final static String typeDoc1 = ":type sort_desc: ``str``";
	private final static String typeDoc2 = ":type    networks: :class:`OpenNebulaNetwork` or\n"
			+ "``list`` of :class:`OpenNebulaNetwork`";
	private final static String typeDoc3 = ":type: ``list`` of :class:`Node` or None";
	private final static String typeDoc4 = ":type: None or ``list`` of :class:`Node`";
 	private final static String rTypeDoc1 = ":rtype:                :class:`Node`";
	private final static String rTypeDoc2 = ":rtype: ``list`` of :class:`Node`";
	private final static String rTypeDoc3 = ":rtype: :class:`Node` or ``None``";
	private final static String rTypeDoc4 = ":rtype:   ``list`` of ``dict``";
	private final static String rTypeDoc5 = " :rtype: ``dict`` or none";
	
	@Before
	public void setUp() throws Exception {
		m = new Method();
	}

	@Test
	public void getRawPythonTypetest() {
		assertEquals(m.getRawPyType(typeDoc1), "str");
		assertEquals(m.getRawPyType(typeDoc2), "OpenNebulaNetworklist");
		assertEquals(m.getRawPyType(typeDoc3), "listNode");
		assertEquals(m.getRawPyType(typeDoc4), "listNode");
		
		assertEquals(m.getRawPyType(rTypeDoc1), "Node");
		assertEquals(m.getRawPyType(rTypeDoc2), "Node");
		assertEquals(m.getRawPyType(rTypeDoc3), "Node");
		assertEquals(m.getRawPyType(rTypeDoc4), "dict");
		assertEquals(m.getRawPyType(rTypeDoc5), "dict");
	}

	@Test
	public void getJavaTypeTest(){
		
		assertEquals(m.getJavaType(typeDoc1, false), "String");
		assertEquals(m.getJavaType(typeDoc2, false), "List<OpenNebulaNetwork>");
		assertEquals(m.getJavaType(typeDoc3, false), "List<Node>");
		assertEquals(m.getJavaType(typeDoc4, false), "List<Node>");
		
		assertEquals(m.getJavaType(rTypeDoc1, false), "Node");
		assertEquals(m.getJavaType(rTypeDoc2, false), "List<Node>");
		assertEquals(m.getJavaType(rTypeDoc3, false), "Node");
		assertEquals(m.getJavaType(rTypeDoc4, true), "List<Map<String,String>>");
		assertEquals(m.getJavaType(rTypeDoc5, true), "Map<String,String>");
	}
		
	@Test
	public void getAttributeTest(){
		assertEquals(Method.getAttribute(":type name: :class:`NodeGroup`"), "name");
		assertEquals(Method.getAttribute(typeDoc1), "sort_desc");
		assertEquals(Method.getAttribute(typeDoc2), "networks");
	}
	
}
