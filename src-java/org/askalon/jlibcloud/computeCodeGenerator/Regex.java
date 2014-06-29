package org.askalon.jlibcloud.computeCodeGenerator;

public final class Regex {

	private Regex() {
	}

	public static final String pyCommentDel = "\\s*(\"\"\"|''')\\s*";
	public static final String pySLComment = pyCommentDel + "[^(\"\"\"|''')"
			 +"]*" + pyCommentDel;
	public static final String pyMethodDef = "\\s+def\\s+[^_]\\w+\\(.*";
	public static final String pyPrivateMethodDef = "\\s+def\\s+_\\w+\\(.*";
	public static final String pyMethodInit = "\\s+def\\s+__init__\\(.*";
	public static final String pyCreateNode = "\\s+def\\s+create_node\\(.*";
	public static final String pyExMethod = "\\s+def\\s+ex_.*";

	// public static final String jDisclaimer =
	// " * Parts or the whole documentation of this class,\n"+
	// " * are copied from the respective python/libcloud modules/classes.\n";
	// public static final String pyDisclaimer =
	// "# Parts or the whole documentation of this module,\n"+
	// "# are copied from the respective modules/classes.\n";

	public static String getJDisclaimer(String driver) {
		return "/**\n"
				+ " * Parts or the whole documentation of this class\n"
				+ " * are copied from the respective module:\n"
				+ " * libcloud/compute/drivers/"
				+ driver
				+ "\n"
				+ " * @see <a href=\"https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/"
				+ driver
				+ "\">"
				+ "https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/"
				+ driver
				+ "</a>\n"
				+ " * \n"
				+ " * Apache Libcloud is licensed under the Apache 2.0 license.\n"
				+ " * For more information, please see LICENSE and NOTICE file or\n"
				+ " * see: <a href=\"http://www.apache.org/licenses/LICENSE-2.0\">http://www.apache.org/licenses/LICENSE-2.0</a>\n"
				+ " **/\n";

	}

	public static String getPyDisclaimer(String driver) {
		return "# Parts or the whole documentation of this module\n"
				+ "# are copied from the respective module:\n"
				+ "# libcloud/compute/drivers/"
				+ driver
				+ "\n"
				+ "# see also:\n"
				+ "# https://github.com/apache/libcloud/tree/trunk/libcloud/compute/drivers/"
				+ driver
				+ "\n"
				+ "#\n"
				+ "# Apache Libcloud is licensed under the Apache 2.0 license.\n"
				+ "# For more information, please see LICENSE and NOTICE file or:\n"
				+ "# http://www.apache.org/licenses/LICENSE-2.0\n";
	}

}
