package org.askalon.jlibcloud.computeCodeGenerator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NodeDriver implements ParserModule {

	private String driverName;
	private String className;
	private String superClassName;
	private List<Method> methods = new LinkedList<Method>();
	private Map<String, String> attMap = new HashMap<String, String>();
	private Set<String> pyImports = new HashSet<String>();
	private Method createNodeMethod;
	private String jClassDoc;
	private String pyClassDoc;
	private String createNodeClass = "NodeImpl";
	private String jPackName;
	private String driverFile;
	private String pyCode;
	private String jCode;

	private enum State {
		CREATE_NODE, PARSE_EX, IGNORE, CLASS_DOC, PARSE_DOC
	};

	private State st = State.IGNORE;

	public NodeDriver(String jPackName, String driverFile) {
		this.jPackName = jPackName;
		this.driverFile = driverFile;
		this.pyCode = "";
		this.jCode = "";
		this.jClassDoc = "";
		this.pyClassDoc = "";
	}

	public Set<String> getPyImports() {
		return this.pyImports;
	}

	public String getClassName() {
		return this.className;
	}

	public void setCreateNodeClass(String cnc) {
		this.createNodeClass = cnc;
	}

	@Override
	public void nextLine(String line) {
		if (line.startsWith("class ")) {
			this.driverName = line.replace("class ", "");
			int end = driverName.indexOf("NodeDriver(");
			this.driverName = driverName.substring(0, end).trim();
			this.className = driverName + "ComputeContext";
			this.superClassName = line.replaceAll(".*\\(|\\):|\\s*", "");
			this.st = State.CLASS_DOC;

		} else if (line.matches(Regex.pyCreateNode)) {
			createNodeMethod = new Method();
			createNodeMethod.nextLine(line);
			st = State.CREATE_NODE;

		} else if (line.matches(Regex.pyExMethod)) {
			methods.add(new Method());
			Utils.last(methods).nextLine(line);
			st = State.PARSE_EX;

		} else if (line.matches(Regex.pyMethodDef)
				|| line.matches(Regex.pyPrivateMethodDef)) {
			st = State.IGNORE;

		} else if (st.equals(State.CLASS_DOC)) {
			if (line.contains(Regex.pyCommentDel)) {
				st = State.PARSE_DOC;
			}

		} else if (st.equals(State.PARSE_DOC)) {
			if (line.contains(Regex.pyCommentDel)) {
				this.pyClassDoc += "\t\'\'\'\n";
				st = State.IGNORE;
			} else {
				this.pyClassDoc = line;
				this.jClassDoc = " * " + line.replaceAll("\\s+", "").trim();
			}

		} else if (st.equals(State.CREATE_NODE)) {
			this.createNodeMethod.nextLine(line);

		} else if (st.equals(State.PARSE_EX)) {
			Utils.last(methods).nextLine(line);
		}
	}

	@Override
	public void build() {
		// nothing to generate if no methods are present
		if (methods.size() == 0 && this.createNodeMethod == null) {
			return;
		}
		
		Set<String> jImports = new HashSet<String>();
		String superClass;
		if (this.superClassName.equals("BaseNodeDriver")
				|| this.superClassName.toLowerCase().contains("mixin")
				|| this.superClassName.equals("NodeDriver")) {
			jImports.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.ComputeContext;\n");
			superClass = "ComputeContext";
		} else {
			superClass = this.superClassName.replace("NodeDriver",
					"ComputeContext");
		}
		pyCode += "from org.askalon.jlibcloud.compute.driverSpecific." + jPackName + " import "
				+ driverName + "ComputeContext\n";
		pyCode += "\n";
		// py class declaration
		pyCode += "class " + driverName + "ComputeContextImpl(" + superClass
				+ "Impl, " + driverName + "ComputeContext):\n";
		// add class doc if exists
		if (!this.pyClassDoc.matches("\\s*")) {
			pyCode += "\t\'\'\'\n";
			pyCode += this.pyClassDoc + "\n";
		}
		pyCode += "\tdef __init__(self, builder):\n";
		pyCode += "\t\t"+superClass+"Impl.__init__(self, builder)\n\n";
		// create node
		pyCode += "\tdef createNode(self, node_temp):\n";
		if (this.createNodeMethod != null) {
			pyCode += createNodeMethod.pyDoc;
		}
		pyCode += "\t\ttry:\n";
		pyCode += "\t\t\tkwargs = self._eval_template(node_temp)\n";
		if (this.createNodeMethod != null) {
			pyCode += "\t\t\tkwargs = self._parse_" + driverName.toLowerCase()
					+ "_template(node_temp, kwargs)\n";
		}
		//wrap listing with nod type
		pyCode += "\t\t\treturn wrap_listing(self.conn.create_node(**kwargs), "
				+ createNodeClass + ")\n";
		pyCode += "\t\texcept Exception, ex:\n";
		pyCode += "\t\t\traise wrap_exception(ex)\n\n";
		pyCode += "\n";
		// deploy node
		pyCode += "\tdef deployNode(self, node_temp):\n";
		pyCode += "\t\ttry:\n";
		pyCode += "\t\t\tkwargs = self._eval_template(node_temp)\n";
		pyCode += "\t\t\tkwargs = self._eval_deploy_template(node_temp, kwargs)\n";
		if (this.createNodeMethod != null) {
			pyCode += "\t\t\tkwargs = self._parse_" + driverName.toLowerCase()
					+ "_template(node_temp, kwargs)\n";
		}
		pyCode += "\t\t\treturn wrap_listing(self.conn.deploy_node(**kwargs), "
				+ createNodeClass + ")\n";
		pyCode += "\t\texcept Exception, ex:\n";
		pyCode += "\t\t\traise wrap_exception(ex)\n\n";
		pyCode += "\n";
		// _parse_driver_template
		if (this.createNodeMethod != null) {
			pyCode += "\tdef _parse_" + driverName.toLowerCase()
					+ "_template(self, node_temp, kwargs):\n";
			// parse kwargs code

			for (Attribute att : this.createNodeMethod.atts) {
				if (att.name.contains("kwargs")) {
					continue;
				}
				String camelAtt = Utils.getJavaSigNameFrom(att.name);
				String sigAtt = Utils.capitalizeFirst(camelAtt);
				pyCode += "\t\t" + att.name + " = node_temp.get" + sigAtt
						+ "()\n";

				String lambda = null;
				if (att.type.equals("String")) {
					lambda = "lambda x : x";

				} else if (att.type.equals("int")) {
					lambda = "lambda x : int(x)";

				} else if (att.type.equals("float")) {
					lambda = "lambda x : float(x)";

				} else if (att.type.equals("boolean")) {
					pyCode += "\t\tl = lambda x : True if x == 'true' or x == True else False\n";
					pyCode += "\t\tkwargs = get_property(self, " + att.name
							+ ", '" + att.name + "',\n\t\t\t\t\t kwargs,"
							+ "l)\n";

				} else if (att.type.equals("List<String>")) {
					pyCode += "\t\tkwargs = get_property_list(self, " + att.name
							+ ", '" + att.name + "',\n\t\t\t\t\t kwargs,"
							+ "lambda x : jlist_str_to_pylist(x))\n";
					//set the attribute type for easier usage in templates
					att.type = "String...";

				} else if (att.type.equals("List<Map<String,Arg>>")) {
					pyCode += "\t\tif " + att.name + ":\n" + "\t\t\tkwargs['"
							+ att.name + "'] = jlist_map_to_pylist_map("
							+ att.name + ")\n";

				} else if (att.type.equals("Map<String,Arg>")) {
					pyCode += "\t\tif " + att.name + ":\n" + "\t\t\tkwargs['"
							+ att.name + "'] = jmap_to_pymap(" + att.name
							+ ")\n";
					// object type
				} else if (att.type.startsWith("List<")) {
					pyCode += "\t\tif " + att.name + ":\n" + "\t\t\tkwargs['"
							+ att.name + "'] = jlist_obj_to_pylist(" + att.name
							+ ")\n";

				} else {
					lambda = "lambda x : x.obj";
				}
				if (lambda != null) {
					pyCode += "\t\tkwargs = get_property(self, " + att.name
							+ ", '" + att.name + "',\n\t\t\t\t\t kwargs,"
							+ lambda + ")\n";
				}
			}
			pyCode += "\t\treturn kwargs\n\n";

			// add template method
			pyCode += "\tdef getTemplateBuilder(self):\n";
			pyCode += "\t\treturn "
					+ this.className.replace("ComputeContext", "")
					+ "NodeTemplateImpl.newBuilder()\n\n";

		}
		jCode = Regex.getJDisclaimer(driverFile);
		jCode += "package org.askalon.jlibcloud.compute.driverSpecific." + jPackName + ";\n\n";

		// create java imports
		pyImports.add("from javaimpl.compute.base.NodeImpl import NodeImpl\n");
		if (this.createNodeMethod != null) {
			pyImports.add("from org.askalon.jlibcloud.compute.driverSpecific." + jPackName
					+ " import " + this.className.replace("ComputeContext", "")
					+ "NodeTemplateImpl\n");
			pyImports.addAll(createNodeMethod.getPyImports());
		}
		for (Method m : methods) {
			m.build();
			pyImports.addAll(m.getPyImports());
			jImports.addAll(m.jImports);
		}
		for (String imp : jImports) {
			jCode += imp;
		}
		jCode += "\n";
		if (!this.jClassDoc.equals("")) {
			jCode += "/**\n";
			jCode += jClassDoc;
			jCode += "**/\n";
		}
		jCode += "\npublic interface " + this.className + " extends "
				+ superClass + " {\n\n";

		for (Method m : methods) {
			pyCode += m.getPyCode();
			jCode += m.getJCode();
			jCode += "\n";
		}
		jCode += "}";
	}

	@Override
	public String getPyCode() {
		return this.pyCode;
	}

	@Override
	public String getJCode() {
		return jCode;
	}

	public boolean hasCreateNode() {
		if (this.createNodeMethod == null) {
			return false;
		}
		return !this.createNodeMethod.isEmpty();
	}

	public String getNodeTemplateInterface() {
		StringBuilder nti = new StringBuilder();
		addImportsToNodeTempl(nti, "NodeTemplate");
		nti.append("public interface " + driverName
				+ "NodeTemplate extends NodeTemplate{\n\n");

		for (Attribute att : this.createNodeMethod.atts) {
			if (att.name.contains("kwargs")) {
				continue;
			}
			
			String attType = templateImplTypeCheck(att.type);
			String camelAtt = Utils.getJavaSigNameFrom(att.name);
			String sigAtt = Utils.capitalizeFirst(camelAtt);
			nti.append("\t/**\n" + att.docParam.replace("@param", "@return")
					+ "\t**/\n");
			nti.append("\tpublic " + attType.trim() + " get" + sigAtt
					+ "();\n\n");
			// add to att Map for NodeTemplateImpl
			this.attMap.put(camelAtt, att.type);
		}
		nti.append("}");
		return nti.toString();
	}

	public String getNodeTemplateBuilderInterface() {
		StringBuilder ntbi = new StringBuilder();
		addImportsToNodeTempl(ntbi, "NodeTemplateBuilder");
		ntbi.append("public interface " + driverName
				+ "NodeTemplateBuilder extends NodeTemplateBuilder{\n\n");

		for (Attribute att : this.createNodeMethod.atts) {
			if (att.name.contains("kwargs")) {
				continue;
			}
			String camelAtt = Utils.getJavaSigNameFrom(att.name);
			ntbi.append("\t/**\n" + /*Utils.getJavaSigNameFrom(*/att.docParam//)
					+ "\t**/\n");
			ntbi.append("\tpublic " + driverName + "NodeTemplateBuilder "
					+ camelAtt + "(" + att.type + " " + att.name + ");\n\n");
		}
		ntbi.append("}");
		return ntbi.toString();
	}

	private void addImportsToNodeTempl(StringBuilder nt, String builderType) {
		nt.append("package org.askalon.jlibcloud.compute.driverSpecific." + jPackName + ";\n\n");
		nt.append("import org.askalon.jlibcloud.compute.wrapperInterfaces." + builderType + ";\n");
		//add needed imports, list, map etc...
		for (String imp : this.createNodeMethod.jImports) {
			nt.append(imp);
		}
		nt.append("\n");
	}

	public String getNodeTemplateImpl() {
		StringBuilder nt = new StringBuilder();
		nt.append("package org.askalon.jlibcloud.compute.driverSpecific." + jPackName + ";\n\n");
		nt.append("import org.askalon.jlibcloud.compute.core.Providers;\n");
		nt.append("import org.askalon.jlibcloud.compute.driverSpecific." + jPackName + "."
				+ driverName + "NodeTemplate;\n");
		nt.append("import org.askalon.jlibcloud.compute.driverSpecific." + jPackName + "."
				+ driverName + "NodeTemplateBuilder;\n");
		Set<String> ntImports = new HashSet<String>();
		ntImports.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeImage;\n");
		ntImports
				.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeLocation;\n");
		ntImports.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base.NodeSize;\n");
		//ignore imports for deployment
		/*ntImports.add("import java.util.List;\n");
		ntImports.add("import java.util.LinkedList;\n");
		ntImports
				.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.deployment.Deployment;\n");*/
		nt.append("import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplateBuilder;\n");
		nt.append("import org.askalon.jlibcloud.compute.wrapperInterfaces.NodeTemplate;\n");
		// union of the import sets to avoid duplicate imports
		ntImports.addAll(createNodeMethod.jImports);
		for (String imp : ntImports) {
			nt.append(imp);
		}
		nt.append("\n");
		nt.append("\tpublic class " + driverName
				+ "NodeTemplateImpl implements " + driverName
				+ "NodeTemplate{\n\n");

		// call to fill the att map
		getNodeTemplateInterface();
		// put base atts to att map
		attMap.put("nodeName", "String");
		attMap.put("sizeId", "String");
		attMap.put("size", "NodeSize");
		attMap.put("keyPair", "String");
		attMap.put("imageId", "String");
		attMap.put("image", "NodeImage");
		attMap.put("locationId", "String");
		attMap.put("location", "NodeLocation");
		attMap.put("authPassword", "String");
		attMap.put("authSshKey", "String");
		// deploy attributes
		Map<String, String> deplMap = new HashMap<String, String>();
		deplMap.put("sshUsername", "String");
		deplMap.put("sshAlternateUsernames", "List<String>");
		deplMap.put("sshPort", "int");
		deplMap.put("sshTimeout", "float");
		deplMap.put("sshKey", "String");
		deplMap.put("timeout", "int");
		deplMap.put("maxTries", "int");
		deplMap.put("sshInterface", "String");

		// add private fields
		for (Map.Entry<String, String> entry : attMap.entrySet()) {
			String type = entry.getValue();
			type = templateImplTypeCheck(type);
			nt.append("\tprivate final " + type + " " + entry.getKey() + ";\n");
		}
		// deploy fields
		nt.append("\t//deployment attributes\n");
		nt.append("\t//deployment isn't yet supported\n");
		nt.append("\t/*\n");
		for (Map.Entry<String, String> entry : deplMap.entrySet()) {
			String type = entry.getValue();
			type = templateImplTypeCheck(type);
			nt.append("\tprivate final " + type + " " + entry.getKey() + ";\n");
		}
		nt.append("\tprivate final List<Deployment> deployments;\n");
		nt.append("\t*/\n");
		nt.append("\tprivate final String type = Providers."
				+ jPackName.toUpperCase() + ".name;\n\n");
		

		// add the constructor
		nt.append("\tprivate " + driverName + "NodeTemplateImpl(" + driverName
				+ "NodeTemplateBuilderImpl builder){\n");
		for (Map.Entry<String, String> entry : attMap.entrySet()) {
			nt.append("\t\tthis." + entry.getKey() + " = builder."
					+ entry.getKey() + ";\n");
		}
		// deploy
		nt.append("\t\t//set deployment fields\n");
		nt.append("\t\t//deployment isn't yet supported\n");
		nt.append("\t\t/*\n");
		for (Map.Entry<String, String> entry : deplMap.entrySet()) {
			nt.append("\t\tthis." + entry.getKey() + " = builder."
					+ entry.getKey() + ";\n");
		}
		nt.append("\t\tthis.deployments = builder.deployments;\n");
		nt.append("\t\t*/\n");
		nt.append("\t}\n\n");
		
		
		// add (getter)methods
		nt.append("\tpublic static NodeTemplateBuilder newBuilder() {\n"
				+ "\t\treturn new " + driverName
				+ "NodeTemplateBuilderImpl();\n\t}\n\n");
		nt.append("\t@Override\n" + "\tpublic String getTemplateType() {\n"
				+ "\t\treturn type;\n\t}\n\n");
		for (Map.Entry<String, String> entry : attMap.entrySet()) {
			String sigName = Utils.capitalizeFirst(entry.getKey());
			String returnType = entry.getValue();
			returnType = templateImplTypeCheck(returnType);
			nt.append("\t@Override\n" + "\tpublic " + returnType + " get"
					+ sigName + "() {\n" + "\t\treturn " + entry.getKey()
					+ ";\n\t}\n\n");
		}
		// deployment
		nt.append("\t//DEPLOYMENT GETTERS\n\n");
		nt.append("\t//deployment isn't yet supported\n");
		nt.append("\t/*\n");
		for (Map.Entry<String, String> entry : deplMap.entrySet()) {
			String sigName = Utils.capitalizeFirst(entry.getKey());
			String returnType = entry.getValue();
			returnType = templateImplTypeCheck(returnType);
			nt.append("\t@Override\n" + "\tpublic " + returnType + " get"
					+ sigName + "() {\n" + "\t\treturn " + entry.getKey()
					+ ";\n\t}\n\n");
		}
		nt.append("\t@Override\n" + "\tpublic List<Deployment> getDeploy() {\n"
				+ "\t\treturn this.deployments;\n\t}\n\n");
		nt.append("\t*/\n\n");
		
		// add private builder class
		nt.append("\tprivate static class " + driverName
				+ "NodeTemplateBuilderImpl implements " + driverName
				+ "NodeTemplateBuilder {\n");
		// add attributes
		for (Map.Entry<String, String> entry : attMap.entrySet()) {
			String type = entry.getValue();
			type = templateImplTypeCheck(type);
			nt.append("\t\tprivate " + type + " " + entry.getKey()
					+ " = null;\n");
		}
		// deployment
		nt.append("\t\t//deployment fields\n");
		nt.append("\t\t//deployment isn't yet supported\n");
		nt.append("\t\t/*\n");
		for (Map.Entry<String, String> entry : deplMap.entrySet()) {
			String type = entry.getValue();
			type = templateImplTypeCheck(type);
			nt.append("\t\tprivate " + type + " " + entry.getKey()
					+ " = null;\n");
		}
		nt.append("\t\tprivate List<Deployment> deployments = null;\n");
		nt.append("\t\t*/\n");
		nt.append("\n");

		// add setters
		for (Map.Entry<String, String> entry : attMap.entrySet()) {
			nt.append("\t\t@Override\n" + "\t\tpublic " + driverName
					+ "NodeTemplateBuilderImpl " + entry.getKey() + "("
					+ entry.getValue() + " " + entry.getKey() + ") {\n"
					+ "\t\t\tthis." + entry.getKey() + " = " + entry.getKey()
					+ ";\n");
			nt.append("\t\t\treturn this;\n\t\t}\n\n");
		}
		// deployment
		nt.append("\t\t//DEPLOYMENT SETTERS\n");
		nt.append("\t\t//deployment isn't yet supported\n");
		nt.append("\t\t/*\n");
		for (Map.Entry<String, String> entry : deplMap.entrySet()) {
			nt.append("\t\t@Override\n" + "\t\tpublic " + driverName
					+ "NodeTemplateBuilderImpl " + entry.getKey() + "("
					+ entry.getValue() + " " + entry.getKey() + ") {\n"
					+ "\t\t\tthis." + entry.getKey() + " = " + entry.getKey()
					+ ";\n");
			nt.append("\t\t\treturn this;\n\t\t}\n\n");
		}
		// setDeploy()
		nt.append("\t\t@Override\n"
				+ "\t\tpublic NodeTemplateBuilder deploy(Deployment deploy) {\n"
				+ "\t\t\tif(this.deployments == null){\n"
				+ "\t\t\t\tthis.deployments = new LinkedList<Deployment>();\n"
				+ "\t\t\t}\n" + "\t\t\tthis.deployments.add(deploy);\n"
				+ "\t\t\treturn this;\n\t\t}\n");
		nt.append("\t\t*/\n\n");
		
		// as()
		nt.append("\t\t@Override\n"
				+ "\t\tpublic <T extends NodeTemplateBuilder> T as(Class<T> klass) {\n"
				+ "\t\t\treturn klass.cast(this);\n\t\t}\n\n");
		// build()
		nt.append("\t\t@Override\n" + "\t\tpublic NodeTemplate build() {\n"
				+ "\t\t\treturn new " + driverName
				+ "NodeTemplateImpl(this);\n\t\t}\n");
		nt.append("\t}\n}");
		return nt.toString();
	}

	private String templateImplTypeCheck(String type) {
		if (type.contains("int")) {
			type = "Integer";
		} else if (type.contains("float")) {
			type = "Float";
		} else if (type.contains("bool")) {
			type = "Boolean";
		}else if(type.contains("String...")){
			return "String[]";
		}
		return type;
	}
}
