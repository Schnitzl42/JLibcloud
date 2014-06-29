package org.askalon.jlibcloud.computeCodeGenerator;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Klass implements ParserModule {

	private String className;
	private String classSuperType;
	private Set<String> otherClassNames;
	public List<Method> methods;
	private Set<String> pyImports = new HashSet<String>();
	private MethodInit initMethod = null;
	private String pyClassImport;
	private String packName;
	private String driverFile;
	private String jCode;
	private String pyCode;
	private String pyClassDoc;
	private String jClassDoc;

	private enum State {
		INIT_METHOD, METHOD, COMMENT, COMMENT_PARSING, ERROR
	};

	private State st = State.ERROR;

	public Klass(String packName, String driverFile) {
		this.packName = packName;
		this.driverFile = driverFile;
		this.otherClassNames = new HashSet<String>();
		methods = new LinkedList<Method>();
		this.jCode = "";
		this.pyClassImport = "from org.askalon.jlibcloud.compute.driverSpecific."
				+ packName + " import ";
		this.pyCode = "";
		this.pyClassDoc = "";
		this.jClassDoc = "";
	}

	public Set<String> getPyImports() {
		return this.pyImports;
	}

	public String getClassName() {
		return this.className;
	}

	public void setOtherClassNames(Set<String> names) {
		this.otherClassNames.addAll(names);
	}

	@Override
	public void nextLine(String line) {
		if (line.startsWith("class ")) {
			this.className = line.replace("class ", "").replaceAll("\\(.*", "");
			this.classSuperType = line.replaceAll(".*\\(|\\):", "");
			st = State.COMMENT;

		} else if (line.matches(Regex.pyMethodDef)) {
			methods.add(new Method());
			Utils.last(methods).nextLine(line);
			st = State.METHOD;

		} else if (line.matches(Regex.pyMethodInit)) {
			initMethod = new MethodInit(this.classSuperType);
			initMethod.nextLine(line);
			st = State.INIT_METHOD;
			// ignore _methods
		} else if (line.matches("\\s+def\\s+_.*")) {
			st = State.ERROR;

		} else if (st.equals(State.METHOD)) {
			Utils.last(methods).nextLine(line);

		} else if (st.equals(State.INIT_METHOD)) {
			initMethod.nextLine(line);

		} else if (st.equals(State.COMMENT)) {
			// single line comment
			if (line.matches(Regex.pySLComment)) {
				this.pyClassDoc = line;
				this.jClassDoc = " * "
						+ line.replaceAll(Regex.pyCommentDel, "").trim() + "\n";

				// mulit line comment start
			} else if (line.matches(Regex.pyCommentDel)) {
				this.pyClassDoc = "\t\'\'\'\n";
				st = State.COMMENT_PARSING;
			}
		} else if (st.equals(State.COMMENT_PARSING)) {
			// mlti line comment end
			if (line.matches(Regex.pyCommentDel)) {
				this.pyClassDoc += "\t\'\'\'\n";
				st = State.ERROR;
			} else {
				pyClassDoc += line + "\n";
				jClassDoc += " * " + line.replaceAll("\\s+", " ").trim() + "\n";
			}
		} else {
			st = State.ERROR;
		}
	}

	@Override
	public void build() {
		// nothing to generate if no methods are present
		if (methods.size() == 0 && initMethod == null) {
			return;
		}
		
		Set<String> jImports = new HashSet<String>();
		
		// build java interface
		String jInitCode = "";
		String pyInitCode = "";
		if (initMethod != null) {
			initMethod.setOtherClassNames(otherClassNames);
			initMethod.build();
			jInitCode += initMethod.getJCode();
			pyInitCode += initMethod.getPyCode();
		}
		jCode += Regex.getJDisclaimer(this.driverFile);
		// add imports from method
		jCode += "package org.askalon.jlibcloud.compute.driverSpecific."
				+ packName + ";\n";
		jCode += "\n";
		pyCode += "\n";
		
		// class declaration
		String classDecl;
		if (Utils.isInBaseClassNames(this.classSuperType)) {
			
			classDecl = "public interface " + this.className + " extends "
					+ this.classSuperType + " {\n\n";
			jImports.add("import org.askalon.jlibcloud.compute.wrapperInterfaces.base."+this.classSuperType+";\n");
		} else {
			classDecl = "public interface " + this.className + " {\n\n";
		}

		
		if (initMethod != null) {
			jImports.addAll(initMethod.jImports);
			this.pyImports.addAll(initMethod.pyImports);
		}
		for (Method m : methods) {
			m.build();
			this.pyImports.addAll(m.getPyImports());
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

		jCode += classDecl;

		pyCode += pyClassImport + className + "\n\n";
		if (Utils.isInBaseClassNames(this.classSuperType)) {
			pyCode += "class " + this.className + "Impl(" + this.classSuperType
					+ "Impl, " + className + "):\n";
		} else {
			pyCode += "class " + this.className + "Impl(" + className + "):\n";
		}
		pyCode += pyClassDoc + "\n";

		jCode += jInitCode;
		pyCode += pyInitCode;

		for (Method m : this.methods) {
			jCode += m.getJCode();
			pyCode += m.getPyCode().replaceAll("self\\.conn\\.", "self.obj.");
		}
		jCode += "}";

	}

	@Override
	public String getPyCode() {
		return this.pyCode;
	}

	@Override
	public String getJCode() {
		return this.jCode;
	}
}
