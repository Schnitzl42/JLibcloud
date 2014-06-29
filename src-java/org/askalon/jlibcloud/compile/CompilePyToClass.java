package org.askalon.jlibcloud.compile;

import java.io.File;

import org.python.core.imp;
import org.python.modules._py_compile;

/**
 * this class is used to compile python source files into java class files. This
 * class can be called with a path to a single python file or a path to a
 * directory.
 */
public class CompilePyToClass {

	public static void compile(File pyFile) {
		try {
			String moduleName = _py_compile.getModuleName(pyFile);
			byte[] bytes = imp.compileSource(moduleName, pyFile);
			imp.cacheCompiledSource(pyFile.getAbsolutePath(), null, bytes);
		} catch (Exception e) {
			System.err.println("org.askalon.jlibcloud.compile error at: "
					+ pyFile.getAbsolutePath());
			System.err.println(e);
			// do nothing else to continue compiling
		}
	}

	/**
	 * 
	 * @param rootPath
	 *            a path to a directory or a single .py file
	 */
	public static void walkDir(String rootPath) {
		File root = new File(rootPath);
		File[] list = root.listFiles();

		if (list == null) {
			if (root.getName().endsWith(".py") &&
					root.isFile()) {
				System.out.println("compiling: " + root.getName());
				compile(root);
			}
		} else {
			for (File f : list) {
				if (f.isDirectory()) {
					walkDir(f.getAbsolutePath());
				} else if (f.getName().endsWith(".py")) {
					System.out.println("compiling: " + f.getName());
					compile(f);
				}
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException(
					"Usage $:jython jcompile.py <root-dir>\n"
							+ "compiles every *.py file in <root-dir> to *$py.class");
		}
		walkDir(args[0]);
	}
}
