package org.askalon.jlibcloud.computeCodeGenerator;

public interface ParserModule {

	/**
	 * feed all the code that should be parsed by the parser module to this
	 * method.
	 * 
	 * @param line
	 *            the line that should be parsed by the module
	 */
	public void nextLine(String line);

	/**
	 * call this method to generate the java and pyton code, after all lines
	 * were feed to <tt>public void nextLine(String line)</tt>.
	 */
	public void build();

	/**
	 * call after <tt>build()</tt> to receive the builded python module
	 * 
	 * @return the python code of the module
	 */
	public String getPyCode();

	/**
	 * call after <tt>build()</tt> to receive the builded java module
	 * 
	 * @return the java Code of the module
	 */
	public String getJCode();
}
