package computeCodeGeneratorTests;

import static org.junit.Assert.*;

import org.askalon.jlibcloud.computeCodeGenerator.Regex;

import org.junit.Test;

public class RegexTest {

	private final static String initLine = "    def __init__(self, id, name, driver, allocation_model=None, cpu=None,";
	private final static String slComment = "       \"\"\"reboot a running node\"\"\"";
	private final static String methodDef = "    def request(self, *args, **kwargs):";
	
	@Test
	public void pyMethodInittest() {
		assertTrue(initLine.matches(Regex.pyMethodInit));
	}

	@Test
	public void pyMethodDefTest(){
		assertTrue(methodDef.matches(Regex.pyMethodDef));
		assertFalse(initLine.matches(Regex.pyMethodDef));
	}
	
	@Test
	public void pySLCommentTest(){
		assertTrue(slComment.matches(Regex.pySLComment));
	}
}
