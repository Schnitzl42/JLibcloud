package org.askalon.jlibcloud.computeCodeGenerator;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import java.util.Set;

/**
 * parse python methods: -pythons init method -public methods
 * 
 * @author root
 * 
 */
public class Method implements ParserModule {

	String pyName;
	String jName;
	List<Attribute> atts = new LinkedList<Attribute>();
	Set<String> jImports = new HashSet<String>();
	Set<String> pyImports = new HashSet<String>();
	String pyDoc = "";
	String jDoc = "";
	// private String pyReturn;
	String jRetType = "String";
	String fullPySig = "";
	String fullJava;
	String fullPy;
	private String param = "";
	private String jDocReturn = "";
	// boolean to check if a keyword description has ended
	private boolean descrEnd = false;
	private String shortFullJava = "";
	// string for parsing the type of a keyword
	private String typeString = "";

	enum State {
		SIGNATURE, ATTRIBUTES, COMMENT, ERROR, INIT_METHOD, COMMENT_PARAM, COMMENT_TYPE
	};

	State st = State.ERROR;

	public Method() {
		this.fullJava = "";
		this.fullPy = "";
	}

	public boolean isEmpty() {
		if (atts.size() == 0) {
			return true;
		}
		return false;
	}

	public Set<String> getPyImports() {
		return this.pyImports;
	}

	public String getShortJavaSig() {
		return this.shortFullJava;
	}

	// superNextLine) ... parse new
	@Override
	public void nextLine(String line) {
		// start of signature: def method(att1, att2,....
		if (line.matches(Regex.pyMethodDef)) {
			this.pyName = line.replaceAll("\\s+def\\s+", "").replaceAll(
					"\\(.*", "");
			this.jName = Utils.getJavaSigNameFrom(pyName);
			// check for multi line signature
			if (!line.contains("):")) {
				st = State.ATTRIBUTES;
			}
			parseAttsFrom(line);
			this.fullPySig = line + "\n";

			// some more attributes to parse
		} else if (st.equals(State.ATTRIBUTES)) {
			if (line.contains("):")) {
				st = State.ERROR;
			}
			parseAttsFrom(line);
			this.fullPySig += line + "\n";

			// single line comment
		} else if (line.matches(Regex.pySLComment)) {
			pyDoc = line + "\n";
			jDoc = "\t/**\n\t" + line.replaceAll(Regex.pyCommentDel, "")
					+ "\n\t**/\n";
			return;

			// multi line comment start
		} else if (line.matches("\\s+" + Regex.pyCommentDel + ".*")
				&& !(st.equals(State.COMMENT) || st.equals(State.COMMENT_PARAM) || st
						.equals(State.COMMENT_TYPE))) {
			this.pyDoc = "\t\t" + line.replaceAll("\\s+", " ").trim() + "\n";
			jDoc = "\t/**\n";
			st = State.COMMENT;

			// multi line comment end
		} else if (line.matches(".*" + Regex.pyCommentDel + ".*")
				&& (st.equals(State.COMMENT) || st.equals(State.COMMENT_PARAM) || st
						.equals(State.COMMENT_TYPE))) {
			this.pyDoc += line + "\n";
			if (st.equals(State.COMMENT_TYPE)) {
				parseType(this.typeString);
			}
			// add javadocs @param
			for (Attribute att : atts) {
				this.jDoc += att.docParam;
			}
			if (this.jDocReturn.contains("None")
					&& !this.jDocReturn.contains(" or ")) {
				this.jDoc += this.jDocReturn.replace("@return", "<p><p>return");

			} else {
				this.jDoc += this.jDocReturn;
			}
			this.jDoc += line.replaceAll(Regex.pyCommentDel, "") + "\t**/\n";
			this.st = State.ERROR;

			// if in comment parsing
		} else if (st.equals(State.COMMENT) || st.equals(State.COMMENT_PARAM)
				|| st.equals(State.COMMENT_TYPE)) {

			if (line.contains(":type")) {
				this.typeString = line;
				this.descrEnd = false;
				// parseType(this.typeString);
				this.st = State.COMMENT_TYPE;

			} else if (line.contains(":param") || line.contains(":keyword")) {
				this.descrEnd = true;
				if (st.equals(State.COMMENT_TYPE)) {
					parseType(this.typeString);
				}
				this.param = getAttribute(line);
				if (!addTypeToAtts(param, "String")) {
					String msg = "Unexpected Attriubte: " + param + " in: "
							+ this.fullPySig.trim();
					tryPrintNameError(msg);
				}
				findAttWith(this.param).docParam = "\t * @param "
						+ this.param
						+ " "
						+ line.substring(line.lastIndexOf(':') + 1)
								.replaceAll("\\s+", " ").trim() + "\n";

				st = State.COMMENT_PARAM;

				// if doc only contains :return
			} else if (line.contains("rtype")
					|| (line.contains(":return") && !getJavaType(line, false)
							.equals(""))) {
				if (st.equals(State.COMMENT_TYPE)) {
					parseType(this.typeString);
				}
				this.jRetType = getJavaType(line, true);
				if (this.jDocReturn.equals("")) {
					this.jDocReturn = "\t * @return "
							+ line.substring(line.lastIndexOf(':') + 1).trim()
							+ "\n";
					st = State.COMMENT_PARAM;
				} else {
					this.jDocReturn += "\t * \t\t\t("
							+ line.replaceAll("\\s+", " ").trim() + ")\n";
				}
				st = State.COMMENT;

			} else if (line.contains(":return")) {
				if (st.equals(State.COMMENT_TYPE)) {
					parseType(this.typeString);
				}
				this.jDocReturn = "\t * @return "
						+ line.substring(line.lastIndexOf(':') + 1).trim()
						+ "\n";
				st = State.COMMENT_PARAM;

			} else if (st.equals(State.COMMENT_PARAM)) {
				findAttWith(this.param).docParam += "\t * \t\t\t"
						+ line.replaceAll("\\s+", " ").trim() + "\n";

			} else if (!descrEnd && !line.matches("\\s*")) {
				if (st.equals(State.COMMENT_TYPE)) {
					this.typeString += line;
				}
				this.jDoc += "\t * " + line.replaceAll("\\s+|@", " ").trim()
						+ "\n";
			} else if (st.equals(State.COMMENT_TYPE)) {
				this.typeString += line;
			}
			this.pyDoc += line + "\n";
		}
	}

	/**
	 * parses the type from the typeString variable. updates the attribute with
	 * a type
	 * 
	 * @param typeString
	 */
	private void parseType(String typeString) {
		String attName = getAttribute(typeString);
		String type = getJavaType(typeString, false);

		if (addTypeToAtts(attName, type)) {
			findAttWith(attName).docParam += "\t * \t\t\t("
					+ typeString.replaceAll("\\s+", " ").trim() + ")\n";
		} else {
			if (!this.fullPySig.trim().equals("")) {
				// don't print warning in __init__ method -> fullPySig == ""
				String msg = "Unexpected Attriubte: " + param + " in: "
						+ this.fullPySig.trim();
				tryPrintNameError(msg);
			}

			findAttWith(attName).docParam = "\t * @param "
					+ attName
					+ " "
					+ typeString
							.substring(
									typeString.indexOf(attName)
											+ attName.length() + 1)
							.replaceAll("\\s+", " ").trim() + "\n";

		}
		this.st = State.COMMENT;
	}

	/**
	 * print an error msg if an attribute name wasn't expected to system.out if
	 * the list of attributes doesn't contain kwarg as argument.
	 * 
	 * @param msg
	 */
	private boolean tryPrintNameError(String msg) {
		boolean isKwarg = false;
		for (Attribute att : this.atts) {
			if (att.name.contains("kwargs") || att.name.contains("*")) {
				isKwarg = true;
				break;
			}
		}
		if (!isKwarg) {
			System.err.println(msg);
			return true;
		}
		return false;
	}

	@Override
	public void build() {
		// build

		// fullPySig
		// String attNames = this.fullPySig.substring(fullPySig.indexOf("("));
		String attNames = getPySignatureAtts(this.atts);

		this.fullPy = "\tdef " + this.jName + "(" + attNames + "):\n"
				+ this.pyDoc;
		// build the py return
		String ret = "\t\ttry:\n";
		ret += getPyReturnStmt(this.fullPySig, this.jRetType) + "\n";
		ret += "\t\texcept Exception, ex:\n";
		ret += "\t\t\traise wrap_exception(ex)\n\n";
		this.fullPy += ret;
		this.fullPy.replaceAll("\\s*\\n+", "\n");

		// fullJavaSig
		boolean canBeOverloaded = false;
		this.fullJava = jDoc;
		this.fullJava += "\tpublic " + this.jRetType + " " + jName + "(";
		for (int i = 0; i < atts.size(); i++) {
			Attribute att = this.atts.get(i);
			if (i == atts.size() - 1) {
				fullJava += att.type + " " + att.name;
			} else {
				fullJava += att.type + " " + att.name + ", ";
				if (i != 0 && i % 4 == 0) {
					fullJava += "\n\t\t\t";
				}
			}
			if (att.value != null) {
				canBeOverloaded = true;
			}
		}
		fullJava += ");\n";

		if (canBeOverloaded) {
			String shortJdoc = this.jDoc;
			List<Attribute> shortAtts = new LinkedList<Attribute>();
			for (Attribute att : this.atts) {
				if (att.value == null) {
					shortAtts.add(att);
				} else {
					// remove unused jdoc param
					shortJdoc = shortJdoc.replaceAll("@param " + att.name
							+ " [^@<]*", "");
				}
			}
			if (!shortJdoc.endsWith("**/\n")) {
				System.err.println("cut of docstring end in: " + jName);
				shortJdoc += "\n\t**/\n";
			}
			this.shortFullJava += shortJdoc;
			this.shortFullJava += "\tpublic " + jRetType + " " + jName + "(";
			// create list with only mandatory attributes

			for (int i = 0; i < shortAtts.size(); i++) {
				Attribute att = shortAtts.get(i);
				if (i == shortAtts.size() - 1) {
					shortFullJava += att.type + " " + att.name;
				} else {
					shortFullJava += att.type + " " + att.name + ", ";
					if (i != 0 && i % 4 == 0) {
						fullJava += "\n\t\t\t";
					}
				}

			}
			shortFullJava += ");\n";
		}
		if (!this.shortFullJava.equals(fullJava)) {
			this.fullJava += "\n\n" + shortFullJava;
		}
	}

	/**
	 * builds a string containing the attributtes for the python signature
	 * @param atts list of Attribute objects
	 * @return the string containing attribute names
	 */
	private String getPySignatureAtts(List<Attribute> atts) {
		List<Attribute> removeList = new LinkedList<Attribute>();
		String attNames = "self, ";
		for (int i = 0; i < atts.size(); i++) {
			Attribute att = atts.get(i);
			if (att.name.equals("kwargs") || att.name.contains("*")) {
				removeList.add(att);
				continue;
			}
			attNames += att.name;
			if (att.value != null) {
				attNames += "=" + att.value;
			}
			attNames += ", ";
			//newline after 4 attributes to avoid overlong singnature
			if (i % 4 == 0 && i > 0) {
				attNames += "\n\t\t\t\t\t";
			}
		}
		int in = attNames.lastIndexOf(',');
		if (in > 0) {
			attNames = attNames.substring(0, in);
		}
		for (Attribute att : removeList) {
			atts.remove(att);
		}
		return attNames;
	}

	/**
	 * extract python attributes form a signature
	 * 
	 * @param line
	 *            part of a python signature
	 */
	public void parseAttsFrom(String line) {
		// parse attributes
		// def ex_create_group(self, name, location=None):
		String l = line.replaceAll("\\):", "");
		int pos = l.indexOf('(');
		if (pos < 0) {
			pos = 1;
		}
		l = l.substring(pos + 1);
		String[] atts = l.split(",");
		for (String att : atts) {
			att = att.trim();
			if (att.equals("self")) {
				continue;
			}
			Attribute tmp = new Attribute(att);
			String type;
			if (att.startsWith("image")) {
				type = "NodeImage";
				this.jImports
						.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;\n");
			} else if (att.startsWith("size")) {
				type = "NodeSize";
				this.jImports
						.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;\n");
			} else if (att.startsWith("node")) {
				type = "Node";
				this.jImports
						.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base.Node;\n");
			} else if (att.startsWith("location")) {
				type = "NodeLocation";
				this.jImports
						.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;\n");
			} else {
				type = "String";
			}
			tmp.type = type;
			this.atts.add(tmp);
		}
	}

	public static String getAttribute(String line) {
		// :type name: :class:`NodeGroup`

		return line.replaceAll("\\s*\\w*:\\w+\\s+", "").replaceAll(":(.|\n)*", "");
	}

	/**
	 * 
	 * 
	 * @param attName
	 * @param attType
	 * @return true if the attribute was already known false if a new attribute
	 *         was created
	 */
	public boolean addTypeToAtts(String attName, String attType) {
		for (Attribute att : atts) {
			if (att.name.equals(attName)) {
				att.type = attType;
				return true;
			}
		}
		Attribute att = new Attribute(attName, attType, true);
		// att.value = "None";
		atts.add(att);
		return false;
	}

	/**
	 * parses the type out of a line with :type or :rtype: or :return:
	 * 
	 * @param line containing a keyword
	 * @return the java type of the keyword from line
	 */
	public String getJavaType(String line, boolean isReturnType) {
		/*
		 * parser ex: :rtype: ``list`` of :class:`Node` :rtype: :class:`Node` or
		 * ``None`` :type sort_desc: ``str``
		 */
		String type;
		String rawType = getRawPyType(line);

		if (rawType.contains("str")) {
			type = "String";
		} else if (rawType.contains("bool")) {
			type = "boolean";
		} else if (rawType.contains("dict") || line.contains("dict")) {
			if (isReturnType) {
				type = "Map<String,String>";
			} else {
				type = "Map<String,Arg>";
				jImports.add("import org.askalon.jlibcloud.compute.types.Arg;\n");
			}
			this.jImports.add("import java.util.Map;\n");

			// Node/NodeSize/NodeImage/NodeLocation
		} else if (rawType.startsWith("libcloudcomputebaseNode")) {
			type = rawType.replace("libcloudcomputebase", "");
			this.jImports
					.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base."
							+ type + ";\n");
			this.pyImports.add("from javaimpl.compute.base." + rawType
					+ "Impl import " + type + "Impl\n");
		} else if (Utils.isInBaseClassNames(rawType)) {
			type = rawType;
			this.jImports
					.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base."
							+ rawType + ";\n");
			this.pyImports.add("from javaimpl.compute.base." + rawType
					+ "Impl import " + rawType + "Impl\n");
			// check if type is return and void
		} else if ((line.contains("rtype") || line.contains("return"))
				&& rawType.toLowerCase().equals("none")) {
			type = "void";
		} else {
			type = rawType;
		}
		// check for listing
		if (line.contains("list") && !line.matches(".*list\\w+.*")) {
			if (rawType.matches(".*list.*str.*") || rawType.equals("list")) {
				type = "String";

			} else if (rawType.matches(".*list.*")) {
				type = rawType.replaceAll("list", "");
			}
			type = "List<" + type + "> ";
			this.jImports.add("import java.util.List;\n");
		}
		return type.trim();
	}

	public String getRawPyType(String line) {
		// case: :rtype: :class:`Node` or ``None``
		// :type group_name: c{str}
		// if the line is only a text line with
		if (!line.contains("`") && !line.contains("{")) {
			return "";
		}
		String word = null;
		if (line.contains(" or ")) {
			word = " or ";
		} else if (line.contains("|")) {
			word = "|";
		} else if (line.contains(" of ")) {
			word = " of ";
		}
		if (word != null) {
			int index = line.indexOf(word);
			String part0 = line.substring(0, index);
			String part1 = line.substring(index, line.length());
			if(line.matches(".*"+word+".*(None|none).*")){
				line = part0;
			}else if (line.matches(".*(None|none).*"+word+".*")){
				line = part1;
			}else if (part0.matches(".*list.*|.*dict.*|.*None.*|.*none.*")) {
				line = part1;
			} else {
				line = part0;
			}
		}
		return line.replaceAll(":\\w*\\s*\\w+:?|:?\\w*\\s*\\w+:", "")
				.replaceAll("\\s\\w+|\\w+\\s", "")
				.replaceAll("'|`|\\W|\\s*", "");
	}

	public String getPyReturnStmt(String signature, String jReturnType) {
		/*
		 * String signatureFormated = signature
		 * .replaceAll(":|=\\w+|self, |self|\\s+def\\s", "") .replaceAll("\n",
		 * "\n\t\t").trim();
		 */
		String sigName = signature.substring(0, signature.indexOf("("))
				.replaceAll("\\s+def\\s+|\\s+", "");
		String attList = "";
		String listExtractor = "";
		boolean addKwarg = false;
		for (Attribute att : this.atts) {
			if (att.isKwarg()) {
				listExtractor = "\t\t\tkwargs = {}\n";
				addKwarg = true;
				break;
			}
		}
		// build correct calls e.g. node.node, vdc.obj
		for (Attribute att : this.atts) {
			String tmp = null;
			/*
			 * if (att.type.equals("Node")) { tmp = "node"; } else if
			 * (att.type.startsWith("Node")) { //not needed anymore tmp =
			 * att.type.replace("Node", "").toLowerCase();
			 */
			if (!Utils.isJavaBaseType(att.type)) {
				// if driver specific class or list of classes or map
				if (att.type.startsWith("List<")) {
					String listType = att.type.replaceAll("List<|>", "");
					if (listType.contains("Map<")) {
						listExtractor += "\t\t\t" + att.name
								+ " = jlist_map_to_pylist_map(" + att.name
								+ ")\n";

					} else if (listType.equals("String")) {
						listExtractor += "\t\t\t" + att.name
								+ " = jlist_str_to_pylist(" + att.name + ")\n";

					} else {
						listExtractor += "\t\t\t" + att.name
								+ " = jlist_obj_to_pylist(" + att.name + ")\n";
					}

					// if type != (baseType,Map,List)
				} else if (!att.type.contains("Map<")) {
					tmp = "obj";

				} else if (att.type.equals("Map<String,Arg>")) {
					listExtractor += "\t\t\t" + att.name + " = jmap_to_pymap("
							+ att.name + ")\n";
				}
			}

			// check if the att is in kwargs
			if (att.isKwarg()) {
				listExtractor += "\t\t\tif " + att.name + ":\n";
				listExtractor += "\t\t\t\tkwargs['" + att.name + "'] = "
						+ att.name + "\n";
				// only add the att to the signature if it isnt in kwargs
			} else {
				// handle default parameters
				if (att.value != null) {
					String ifStr = "\t\t\tif not " + att.name + ":\n";
					ifStr += "\t\t\t\t" + att.name + " = " + att.value + "\n";
					listExtractor = ifStr + listExtractor;
				}
				String comma = ", ";
				if (att.equals(atts.get(0))) {
					// if first iteration
					comma = "";
				}
				// if is object type
				if (tmp != null) {
					/*
					 * // check if attribute can be None if (att.value != null
					 * && att.value.equals("None")) {
					 */
					String ifStr = "\t\t\tif " + att.name + ":\n";
					ifStr += "\t\t\t\t" + att.name + " = " + att.name + "."
							+ tmp + "\n";
					listExtractor = ifStr + listExtractor;
					attList += comma + att.name;
				} else {
					attList += comma + att.name;
				}
			}
		}
		if (addKwarg) {
			if (attList.equals("")) {
				attList += "**kwargs";
			} else {
				attList += ", **kwargs";
			}
		}
		String signatureFormated = sigName + "(" + attList + ")";
		String lstType = jReturnType.replaceAll("List<|>", "");
		if ((signature.contains("list") || jReturnType.startsWith("List<"))
				&& !Utils.isJavaBaseType(lstType)
				&& !jReturnType.contains("Map")) {
			return listExtractor + "\t\t\treturn wrap_listing(self.conn."
					+ signatureFormated + ", " + lstType.trim() + "Impl)";
		} else if ((Utils.countUpperCaseLetters(jReturnType) > 1 || jReturnType
				.equals("Node"))
				&& !jReturnType.contains("Map")
				&& !(signature.contains("list") || jReturnType.contains("List"))) {
			return listExtractor + "\t\t\treturn " + jReturnType.trim()
					+ "Impl(self.conn." + signatureFormated + ")";
		} else if (jReturnType.toLowerCase().equals("void")) {
			return listExtractor + "\t\t\tself.conn." + signatureFormated;
		} else {
			return listExtractor + "\t\t\treturn self.conn."
					+ signatureFormated;
		}
	}

	public Attribute findAttWith(String attName) {
		for (Attribute att : this.atts) {
			if (att.name.equals(attName)) {
				return att;
			}
		}
		return null;
	}

	@Override
	public String getPyCode() {
		return this.fullPy;
	}

	@Override
	public String getJCode() {
		return this.fullJava;
	}

	// a little bit of test code
	public static void main(String[] args) {

		String methodStr = "	def ex_destroy_node_and_drives(self, node):\n"
				+ "		\"\"\"\n"
				+ "		Destroy a node and all the drives associated with it.\n"
				+ "		\n" + "		:param      node: Node which should be used\n"
				+ "		:type       node: :class:`libcloud.compute.base.Node`\n"
				+ "		\n" + "		:rtype: ``bool``\n" + "		\"\"\"\n"
				+ "		node = self._get_node_info(node)\n" + "		\n"
				+ "		drive_uuids = []\n";
		Method m = new Method();
		for (String string : methodStr.split("\n")) {
			m.nextLine(string);
		}
		m.build();

		System.out.println(m.getJavaType(
				":type    networks: :class:`OpenNebulaNetwork` or\n"
						+ "``list`` of :class:`OpenNebulaNetwork`", false));
	}

}
