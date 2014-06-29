package org.askalon.jlibcloud.computeCodeGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Generator {
	//compute/drivers
	public static String currentProvider = "vpsnet.py";
	public static String pyDriverSourcePath = "src-py/libcloud-trunk/libcloud/compute/drivers";
	public static String pyWrapperTargetPath = "src-py/libcloud-trunk/javaimpl/compute/drivers/";
	public static String javaWrapperTargetPath = "src-java/org/askalon/jlibcloud/compute/driverSpecific";
	
	private static enum State {
		INIT, NODE_DRIVER, CLASS
	};

	private static List<Klass> classes = new LinkedList<Klass>();
	private static List<NodeDriver> nodeDrivers = new LinkedList<NodeDriver>();

	// MAIN METHOD
	public static void main(String[] args) {
		Generator.runGenerator();
	}
	
	private static void runGenerator(){
		File f = new File(pyDriverSourcePath);

		for (File file : f.listFiles()) {
			if (file.getName().equals(currentProvider)) {
				parseFile(file);
				try {
					writeFiles(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void parseFile(File f) {
		BufferedReader reader;
		State state = State.INIT;
		String jPackName = f.getName().replaceAll(".py", "");
		try {
			reader = new BufferedReader(new FileReader(f));
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				if (line.matches("class.*NodeDriver\\(.*")) {
					state = State.NODE_DRIVER;
					System.out.println("NODE_DRIVER: " + line);
					nodeDrivers.add(new NodeDriver(jPackName, f.getName()));
					nodeDriversLast().nextLine(line);

				} else if (line.startsWith("class ")) {
					if (line.matches(".*Connection.*|"
							+ ".*Exception.*|.*Response.*|.*Component.*")) {
						state = State.INIT;
					} else {
						System.out.println("CLASS: " + line);
						state = State.CLASS;
						classes.add(new Klass(jPackName, f.getName()));
						classesLast().nextLine(line);
					}

				} else if (state.equals(State.CLASS)) {
					classesLast().nextLine(line);

				} else if (state.equals(State.NODE_DRIVER)) {
					nodeDriversLast().nextLine(line);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Klass classesLast() {
		return classes.get(classes.size() - 1);
	}

	private static NodeDriver nodeDriversLast() {
		return nodeDrivers.get(nodeDrivers.size() - 1);
	}

	private static void writeFiles(File srcFile) throws IOException {

		String driverFileName = srcFile.getName().replaceAll(".py", "");
		// create the java package
		File jpack = new File(javaWrapperTargetPath, driverFileName);
		jpack.mkdir();

		// create the py file
		File pyFile = new File(pyWrapperTargetPath,
				Utils.capitalizeFirst(driverFileName) + "ComputeContext.py");
		pyFile.createNewFile();


		Set<String> pyImports = new HashSet<String>();
		Set<String> allClassNames = new HashSet<String>();
		String pyCode = "";
		String nodeClass = "NodeImpl";
		List<String> pyClassList = new LinkedList<String>();
		List<String> pyDriverList = new LinkedList<String>();
		pyCode = Regex.getPyDisclaimer(srcFile.getName());
		//add the general imports
		pyCode += "from javaimpl.compute.ComputeContextImpl import ComputeContextImpl\n";
		pyCode += "from javaimpl.compute.utils import none_check, wrap_listing, wrap_exception, jlist_str_to_pylist\n";
		pyCode += "from javaimpl.compute.utils import jlist_obj_to_pylist, get_property, get_property_list\n";
		pyCode += "from javaimpl.compute.utils import jmap_to_pymap, jlist_map_to_pylist_map\n";
		
		// build and write
		for(Klass klass : classes){
			allClassNames.add(klass.getClassName());
		}
		for (Klass klass : classes) {
			klass.setOtherClassNames(allClassNames);
			klass.build();
			pyImports.addAll(klass.getPyImports());
			if(klass.getClassName().matches(".*NodeImpl")){
				nodeClass = klass.getClassName();
			}
			// write the java files if klass is not empty
			if (!klass.getJCode().equals("")) {
				File jFile = new File(jpack.getAbsolutePath(), klass.getClassName()
						+ ".java");
				jFile.createNewFile();
				BufferedWriter jWriter = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(jFile)));
				jWriter.write(klass.getJCode());
				jWriter.close();
			}
			// get the py code
			pyClassList.add(klass.getPyCode());
		}
		
		for (NodeDriver nodeDriver : nodeDrivers) {
			nodeDriver.setCreateNodeClass(nodeClass);
			nodeDriver.build();
			pyImports.addAll(nodeDriver.getPyImports());
			pyDriverList.add(nodeDriver.getPyCode());

			// write the java files if klass is not empty
			if (!nodeDriver.getJCode().equals("")) {
				//create class file
				File jFile = new File(jpack.getAbsolutePath(),
						nodeDriver.getClassName() + ".java");
				jFile.createNewFile();
				//write class file
				BufferedWriter jWriter = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(jFile)));
				jWriter.write(nodeDriver.getJCode());
				jWriter.close();

				// write the nodeTemplateInterface
				// if createNode not empty
				if (nodeDriver.hasCreateNode()) {
					generateTemplates(driverFileName, jpack, nodeDriver);
				}
			}
		}

		// write the py code
		BufferedWriter pyWriter = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(pyFile)));
		pyWriter.write(pyCode);
		for(String imp : pyImports){
			pyWriter.write(imp);
		}
		for (String driver : pyDriverList) {
			pyWriter.write(driver);
		}
		for (String klass : pyClassList) {
			pyWriter.write(klass);
		}
		pyWriter.close();
	}

	private static void generateTemplates(String driverFileName, File jpack,
			NodeDriver nodeDriver) throws IOException, FileNotFoundException {
		String fileName = nodeDriver.getClassName().replaceAll("ComputeContext", "");
		// create node Template Interface file
		File ntF = new File(jpack.getAbsolutePath(), fileName
				+ "NodeTemplate.java");
		ntF.createNewFile();

		// create node Template BUILDER Interface file
		File ntbF = new File(jpack.getAbsolutePath(),
				fileName + "NodeTemplateBuilder.java");
		ntbF.createNewFile();

		// create nodeTemplateImpl file
		File ntiF = new File(jpack.getAbsolutePath(),
				fileName + "NodeTemplateImpl.java");
		ntiF.createNewFile();
		// write the nodeTemplateInterface
		BufferedWriter ntWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(ntF)));
		ntWriter.write(nodeDriver.getNodeTemplateInterface());
		ntWriter.close();
		// write the nodeTemplateBuilderInterface
		BufferedWriter ntbWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(ntbF)));
		ntbWriter.write(nodeDriver
				.getNodeTemplateBuilderInterface());
		ntbWriter.close();
		// write the nodeTemplateImpl
		BufferedWriter ntiWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(ntiF)));
		ntiWriter.write(nodeDriver.getNodeTemplateImpl());
		ntiWriter.close();
	}
}
