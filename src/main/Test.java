package main;
import java.io.*;
import java.util.Scanner;

import parser.Parser;
import parser.SyntaxException;


public class Test {

	public static void main(String[] args)  {
		File source;
		Scanner scanner=new Scanner(System.in);
		if(args.length!=2){
			System.out.println("Enter a valid (full) path for the Funwith@p soucre code");
			String path=scanner.nextLine();
			source=new File(path);
			if(!source.exists()){
				System.err.println("invalid path for the input file, running default tests");
				source=new File("tests/test2.txt");
			}
		}else{
			source=new File(args[1].toString());
			if(!source.exists()){
				System.err.println("invalid path for the input file, running default tests");
				//source=new File("C:/Users/samy/Desktop/test2.txt");
				source=new File("tests/test2.txt");
			}
		}
		
		try {
		Reader r=new BufferedReader(new FileReader(source));
		Parser parser=new Parser();
		String targetCode=parser.parse(r).toString();
		//System.out.println(targetCode);
		
		r.close();
		//System.out.println("Parsed");
		String fileName="Generated.java",directory="Output";
		
		File file=new File(directory,fileName);
		int i=0;
		
		while(file.exists()){
			directory+=i++;
			file=new File(directory,fileName);
		}
		
		
		file.getParentFile().mkdirs();
		file.createNewFile();
		
		PrintWriter writer=new PrintWriter(file,"UTF-8");
		writer.write(targetCode);
		writer.close();
		
		
		//copyFile(new File("Imports","Async.java"),new File(directory,"Async.java"));
		copyFile(new File("Imports","Function.java"),new File(directory,"Function.java"));
		copyFile(new File("Imports","RemoteExecuter.java"),new File(directory,"RemoteExecuter.java"));
		copyFile(new File("Imports","RemoteInterface.java"),new File(directory,"RemoteInterface.java"));
		copyFile(new File("Imports","RMIServer.java"),new File(directory,"RMIServer.java"));
		System.out.println("files generated, could be found in "+file.getAbsolutePath());
		}catch(SyntaxException se){
			System.err.println(se.getMessage());
			se.printStackTrace();
			
		}catch(IOException ioe){
			System.err.println("File Error");
			ioe.printStackTrace();
		}catch(Exception E){
			E.printStackTrace();
		}finally{
			scanner.close();
		}

	}
	
	public static void copyFile(File source, File dest) throws IOException {
		 if(!dest.exists()) {
		  dest.createNewFile();
		 }
		 InputStream in = null;
		 OutputStream out = null;
		 try {
		  in = new  FileInputStream(source);
		  out = new FileOutputStream(dest);
		    
		  // Transfer bytes from in to out
		  byte[] buf = new byte[1024];
		  int len;
		  while ((len = in.read(buf)) > 0) {
		   out.write(buf, 0, len);
		  }
		 }
		 finally {
		  if(in != null) {
		   in.close();
		  }
		  if(out != null) {
		   out.close();
		  }
		 }
		}

}
