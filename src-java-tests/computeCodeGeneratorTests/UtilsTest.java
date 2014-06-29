package computeCodeGeneratorTests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.askalon.jlibcloud.computeCodeGenerator.Utils;
import org.junit.Test;

public class UtilsTest {

	@Test
	public void lastTest() {
		List<String> l = new LinkedList<String>();
		l.add("first");
		l.add("second");
		assertEquals(Utils.last(l), "second");
	}

	@Test 
	public void capitalizeFirstTest(){
		assertEquals(Utils.capitalizeFirst("word"),"Word");
		assertEquals(Utils.capitalizeFirst("Word"),"Word");
		assertEquals(Utils.capitalizeFirst("1ord"),"1ord");
	}
	
	@Test
	public void conutUpperCaseLettersTest(){
		assertEquals(Utils.countUpperCaseLetters(""), 0);
		assertEquals(Utils.countUpperCaseLetters("abc"), 0);
		assertEquals(Utils.countUpperCaseLetters("Abc"), 1);
		assertEquals(Utils.countUpperCaseLetters("HelloWorld!"), 2);
	}
	
	@Test
	public void getJavaSigNameFromTest(){
		assertEquals(Utils.getJavaSigNameFrom("ex_list_keyPairs"), "exListKeyPairs");
		assertEquals(Utils.getJavaSigNameFrom("ex_set_server_name"), "exSetServerName");
	}
	
	@Test
	public void splitAtUpperCaseTest(){
		assertEquals(Utils.splitAtUpperCase("NodeImage")[1], "Node");
	}
}
