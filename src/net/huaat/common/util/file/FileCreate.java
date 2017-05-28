package net.huaat.common.util.file;

import java.io.File;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-10-25 下午3:21:53
 * @version V1.0  
 */
public class FileCreate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String paths[] = "a/a2".split("/");
		System.out.println(File.separator);
	}
	/**
	 * 从rootPath开始，根据path按照"/"分割的字符串来构建文件夹,如：net/huaat/hdsm
	 * @param rootPath 根路径
	 * @param path
	 * @return
	 */
	public static boolean makeDirFile(String rootPath,String path){
		System.out.println("start make directory file..."+path);
		String paths[] = path.split("\\/");
		for(String str:paths){
			rootPath = rootPath+File.separator+str;
			File file = new File(rootPath);
			if (!file.exists()) { 
				file.mkdir();
				System.out.println("success make directory..."+rootPath);
			}
		}
		return true;
	}

}

