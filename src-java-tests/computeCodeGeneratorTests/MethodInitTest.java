package computeCodeGeneratorTests;

import static org.junit.Assert.*;

import org.askalon.jlibcloud.computeCodeGenerator.MethodInit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MethodInitTest {

	private MethodInit m;
	private final static String line0 = " def __init__(self, id, name, ram, disk, bandwidth, price, driver,";
    private final static String line1 = "             vcpus=None):";
    private final static String line2 = "   super(OpenStackNodeSize, self).__init__(id=id, name=name, ram=ram,";
    private final static String line3 = "                                                disk=disk,";
    private final static String line4 = "                 								 bandwidth=bandwidth,";
    private final static String line5 = "                                            price=price, driver=driver)";
    private final static String line6 = "    self.vcpus = vcpus";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		m = new MethodInit("NodeSize");
	}

	@Test
	public void methodInitTest() {
		m.nextLine(line0);
		m.nextLine(line1);
		m.nextLine(line2);
		m.nextLine(line3);
		m.nextLine(line4);
		m.nextLine(line5);
		m.nextLine(line6);
		m.build();
		String jCode = m.getJCode();
		assertEquals(jCode.trim(), "public int getVcpus();");
		
		String pyCode = m.getPyCode();
		String[] pyCodes = pyCode.split("\n");
		String[] expected = {
				"	def __init__(self, obj):", 
				"		NodeSizeImpl.__init__(self, obj)",
				"		if hasattr(obj, 'vcpus'):", 
				"			self.vcpusp = none_check(obj.vcpus, -1)",
				"		else:",
				"			self.vcpusp = -1",
				"		if hasattr(obj, '__repr__()'):",
				"			self.reprp = obj.__repr__()",
				"		else:",
				"			self.reprp = str(obj)",
				"",
				"	def getVcpus(self):",
				"		return self.vcpusp",
				"",
				"	def toString(self):",
				"		return self.reprp"};
		for(int i=0; i<pyCodes.length; i++){
			pyCodes[i].trim();
			assertEquals(pyCodes[i], expected[i]);
		}
	}

}
