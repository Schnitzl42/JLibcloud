package org.askalon.jlibcloud.computeCodeGenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to parse the <tt>__init__()</tt> method of a python class
 * 
 * @author root
 * 
 */
public class MethodInit extends Method {

	private Set<String> otherClassNames = new HashSet<String>();
	private String classSuperType;

	public MethodInit(String classSuperType) {
		super();
		this.classSuperType = classSuperType;
	}

	public void setOtherClassNames(Set<String> names) {
		this.otherClassNames.addAll(names);
	}

	@Override
	public void nextLine(String line) {
		super.nextLine(line);
		// __init__ method
		/*
		 * if (line.matches(Regex.pyMethodInit)) { st = State.INIT_METHOD;
		 * System.out.println(line); } else
		 */
		if (line.matches("\\s+self\\.\\w+\\s*=.*")
				&& !(st.equals(State.COMMENT) || st.equals(State.COMMENT_TYPE) || st
						.equals(State.COMMENT_PARAM))) {
			// self.att = att
			String att = line.substring(line.indexOf('.') + 1,
					line.indexOf('=')).trim();
			String type = getInitType(att);
			if (!att.equals("driver") && !hasTypeAlready(att)) {
				addTypeToAtts(att, type);
			}
		}
	}

	/**
	 * 
	 * @param att
	 * @return true if the attribute exists and has a non-string type otherwise
	 *         false
	 */
	private boolean hasTypeAlready(String attName) {
		for (Attribute att : this.atts) {
			if (att.name.equals(attName)) {
				if (att.type != null && !att.type.equals("String")) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * returns the type of an attribute
	 * based on the attributes name
	 * 
	 * @param att the attribute
	 * @return the probably correct type
	 */
	private String getInitType(String att) {
		String[] intTypes = { "memory", "bandwidth", "vcpus", "ram", "disk",
				"cpus" };
		for (String intType : intTypes) {
			if (att.equals(intType)) {
				return "int";
			}
		}
		if(att.contains("port")){
			return "int";
		}
		
		String[] floatTypes = { "price" };
		for (String floatType : floatTypes) {
			if (att.equals(floatType)) {
				return "float";
			}
		}
		String[] mapTypes = { "extra" };
		for (String mapType : mapTypes) {
			if (att.equals(mapType)) {
				jImports.add("import java.util.Map;\n");
				return "Map<String,String>";
			}
		}
		return "String";
	}

	@Override
	public void build() {

		// create py-code
		String tabs = "\t\t";
		this.fullPy = "\tdef __init__(self, obj):\n";
		if (Utils.isInBaseClassNames(this.classSuperType)) {
			// add imports
			this.jImports.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base."
					+ this.classSuperType + ";\n");
			this.pyImports.add("from javaimpl.compute.base." + this.classSuperType
					+ "Impl import " + this.classSuperType + "Impl\n");
			// call super constructor
			fullPy += "\t\t" + this.classSuperType
					+ "Impl.__init__(self, obj)\n";
		} else {
			fullPy += "\t\tself.obj=obj\n";
		}
		for (Attribute att : atts) {
			String attName = att.name;
			String attType = att.type;
			String val = null;
			String noneCheck;
			if (attType.equals("int")) {
				val = "-1";
				noneCheck = "none_check(obj." + attName + ", " + val + ")";
			} else if (attType.equals("float")) {
				val = "-1.0";
				noneCheck = "none_check(obj." + attName + ", " + val + ")";
			} else if (attType.equals("Map<String,String>")) {
				val = "{}";
				noneCheck = "obj." + attName + ", " + val;
			} else if (attType.startsWith("List<")
					&& !(attType.startsWith("List<Map") || 
							attType.startsWith("List<String"))) {
				String listType = attType.replaceAll("List<|>", "");
				noneCheck = "wrap_listing(obj."+attName+", "+listType+"Impl)";
				val = "["+listType+"Impl(None)]";
			} else {
				// check if the attribute is a known class
				//generates the code if true. therefore execute continue;
				if (isClassAttribute(tabs, att, attName)) {
					continue;
				} else if (isBaseClassAttribute(tabs, att, attName)) {
					continue;
				}
				val = "' '";
				noneCheck = "none_check(obj." + attName + ", " + val + ")";
			}
			fullPy += tabs + "if hasattr(obj, '" + attName + "'):\n";
			writeAttCode(tabs, attName, noneCheck, val);
		}
		// add the toString method
		fullPy += tabs + "if hasattr(obj, '__repr__()'):\n";
		fullPy += tabs + "\tself.reprp = obj.__repr__()\n";
		fullPy += tabs + "else:\n";
		fullPy += tabs + "\tself.reprp = str(obj)\n\n";

		// create java-code + py-methods
		this.fullJava = "";
		for (Attribute att : atts) {
			String camelAtt = Utils.capitalizeFirst(Utils
					.getJavaSigNameFrom(att.name));
			fullPy += "\tdef get" + camelAtt + "(self):\n";
			fullPy += "\t\treturn self." + att.name + "p\n\n";

			fullJava += "\tpublic " + att.type + " get" + camelAtt + "();\n\n";
		}
		// ad the toString method
		fullPy += "\tdef toString(self):\n";
		fullPy += "\t\treturn self.reprp\n\n";
	}

	/**
	 * checks if a attribute is a base Libcloud class.
	 * generates the code to pares the class if the attribute
	 * seems to be a class.
	 * @param tabs the tabs to use for indentation
	 * @param att the attribute
	 * @param attName the name of the attribute
	 * @return true if the attribute is a base class from Libcloud
	 */
	private boolean isBaseClassAttribute(String tabs, Attribute att,
			String attName) {
		String val;
		for (String name : Utils.getBaseClassNames()) {
			String shortName = "null";
			if (Utils.countUpperCaseLetters(name) > 1) {
				shortName = Utils.splitAtUpperCase(name)[2];
			}
			String lowerName = name.toLowerCase();
			String lowerAtt = attName.toLowerCase();
			if (lowerAtt.startsWith(lowerName)
					|| lowerAtt.startsWith(shortName.toLowerCase())) {
				fullPy += tabs + "if hasattr(obj, '" + attName + "'):\n";
				String elsep = "";
				this.jImports.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base."
						+ name + ";\n");
				if (attName.endsWith("s")) {
					att.type = "List<" + name + ">";
					this.jImports.add("import java.util.List;\n");
					val = "wrap_listing(obj." + attName + ", " + name + "Impl)";
					elsep = "wrap_listing(None, " + name + "Impl)";
					writeAttCode(tabs, attName, val, elsep);
					return true;
				} else {
					att.type = name;
					val = name + "Impl(obj." + attName + ")";
					elsep = name + "Impl(None)";
					writeAttCode(tabs, attName, val, elsep);
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * guesses if a attribute is a class. generates the code
	 * to parse a class if the attribute seems to be a class.
	 * @param tabs the indendation to use
	 * @param att the attribute
	 * @param attName the attribute name
	 * @return true if the attribute semms to be a class name
	 */
	private boolean isClassAttribute(String tabs, Attribute att,
			String attName) {
		String val;
		for (String name : this.otherClassNames) {
			if (attName.toLowerCase().startsWith(name.toLowerCase())) {
				fullPy += tabs + "if hasattr(obj, '" + attName + "'):\n";
				String elsep = "";
				if (attName.endsWith("s")) {
					att.type = "List<" + name + ">";
					this.jImports.add("import java.util.List;\n");
					val = "wrap_listing(obj." + attName + ", " + name + "Impl)";
					elsep = "wrap_listing(None, " + name + "Impl)";
					writeAttCode(tabs, attName, val, elsep);
					return true;
				} else {
					att.type = name;
					val = name + "Impl(obj." + attName + ")";
					elsep = name + "Impl(None)";
					writeAttCode(tabs, attName, val, elsep);
					return true;
				}
			}
		}
		return false;
	}

	private void writeAttCode(String tabs, String attName, String val,
			String elsep) {
		fullPy += tabs + "\tself." + attName + "p = " + val + "\n";
		fullPy += tabs + "else:\n";
		fullPy += tabs + "\tself." + attName + "p = " + elsep + "\n";
	}
	

	@Override
	public String getPyCode() {
		return super.getPyCode();
	}

	@Override
	public String getJCode() {
		return super.getJCode();
	}

}
