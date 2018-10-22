package net.huaat.estate.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class test {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//	  test ab=new test();
//	//	ab.getpath();
//		String aaa=getProjectPath();
//		System.out.println(aaa);
//	  System.out.println(geta());
//		String py_parameter="python2 /once_work/git/ssp/hive/hive_propy/hive_process.py 2017-07-20";
//		Runtime.getRuntime().exec(py_parameter);
		//runprocess();
		rupython();
	}
	
	static void rupython(){
		 try {
	            Process pr = Runtime.getRuntime().exec("python2 /once_work/git/ssp/hive/hive_propy/hive_process.py 2017-07-07");
	            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	              //  line = decodeUnicode(line);
	                System.out.println(line);
	            }
	            in.close();
	            pr.waitFor();
	            System.out.println("end");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	 
	 static void runprocess() throws IOException, InterruptedException {
        // ProcessBuilder p = new ProcessBuilder("cmd /c dir c:\\", "/b");
        String[] args = new String[] {"python2","/c","dir","c:\\", "/b" };
        Process process = Runtime.getRuntime().exec(args);
        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        try {
                while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                }
        } catch (IOException e) {
                e.printStackTrace();
        }

}
	
	
	public   void getpath(){
		// String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String path =this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	    System.out.println(path);
		 
	}
	public static String geta(){
		String filePath_properties = System.getProperty("user.dir") ;
		return filePath_properties;
	}
	 public static String getProjectPath() {

		 

	       java.net.URL url = test.class .getProtectionDomain().getCodeSource().getLocation();

	       String filePath = null ;

	       try {

	           filePath = java.net.URLDecoder.decode (url.getPath(), "utf-8");

	       } catch (Exception e) {

	           e.printStackTrace();

	       }

	    if (filePath.endsWith(".jar"))

	       filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);

	    java.io.File file = new java.io.File(filePath);

	    filePath = file.getAbsolutePath();

	    return filePath;

	}

}
