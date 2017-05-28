package net.huaat.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**  
 * @Description: jar包类加载器
 * @author yzq  
 * @date 2012-4-25 下午01:27:06
 * @version V1.0  
 */
public class JarClassLoader extends ClassLoader {
	
	Log log = LogFactory.getLog(getClass());

	/**
	 * 通过指定的jar包路径名称字符串获取该jar包中的class名称，并在jarClassParentPath路径下创建class文件
	 * @param jarPath
	 * @param jarClassParentPath jar包解压后的存放路径
	 * @return
	 */
	public List<String> getJarClassPathNameList(String jarPath,String jarClassParentPath) {
		List<String> list = new ArrayList<String>();
		
		InputStream is =null;
		FileOutputStream  os=null;
		try{
			JarFile jarFile = new JarFile(new File(jarPath));
			Enumeration<JarEntry> entries=jarFile.entries();
			while(entries.hasMoreElements()){
				JarEntry jarEntry = entries.nextElement();
				if(jarEntry.isDirectory()||jarEntry.getName().contains("MANIFEST.MF")){
					continue;
				}
				String [] strs=jarEntry.getName().split("\\/");
				
				log.info(strs[strs.length-1].split("\\.")[0]);
				log.info(jarEntry.getName());
				list.add(jarEntry.getName());
				
				//同时把jar包中的class解压出来
				if(!jarEntry.getName().endsWith(".class")){
					continue;
				}
				byte data[] = new byte[1024];
				is = jarFile.getInputStream(jarFile.getEntry(jarEntry.getName()));
				
				String parentPath=jarClassParentPath;
				int i=0;
				String [] namesStrings= jarEntry.getName().split("\\/");
				for(String name:namesStrings){
					i++;
					//.class时候不用创建文件夹，下面直接创建文件
					if(namesStrings.length==i){
						break;
					}
					parentPath=parentPath+"/"+name;
					File f1=new File(parentPath); 
					f1.mkdir();
				}
				parentPath=jarClassParentPath;
				os = new FileOutputStream(new File(parentPath+"/"+jarEntry.getName()));
				int len=0;
				while((len=is.read(data))!=-1){
					os.write(data,0,len);  
				}
			}
		}catch(Exception e){
			log.error(e);
			e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	/**
	 * 根据类的二进制名称  返回得到的 Class 对象
	 * @param binaryNames 二进制名称 (API java.lang.ClassLoader定义)  ...huaat/test/User.class
	 */
	@Override
	public Class<?> findClass(String binaryNames) throws ClassNotFoundException {
		byte [] data  =  loadClassData(binaryNames);
		//该方法接受由原始字节组成的数组并把它转换成 Class 对象,原始数组包含如从文件系统或网络装入的数据
		return  defineClass(null, data,  0 , data.length);
	}
	private   byte [] loadClassData(String binaryNames)
    {
        FileInputStream fis  =   null ;
         byte [] data  =   null ;
         try
        {
            fis  =   new  FileInputStream( new  File(binaryNames));
            ByteArrayOutputStream baos  =   new  ByteArrayOutputStream();
             int  ch  =   0 ;
             while  ((ch  =  fis.read())  !=   - 1 )
            {
                baos.write(ch);
            }
            data  =  baos.toByteArray();
        }  catch  (IOException e)
        {
            e.printStackTrace();
        }
        
         return  data;
    }
	
	/**
	 * 通过类的二进制名称获取该class对象所表示类的一个新实例
	 * @param binaryNames
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public Object getInstanceByClassPath(String binaryNames) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return findClass(binaryNames).newInstance();
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		JarClassLoader jarClassLoader = new JarClassLoader();
		//jarClassLoader.getJarClassPathNameList("D:/vo.jar","D:/jarClass");
		Class<?> objClass=jarClassLoader.findClass("D:/jarClass/net/huaat/test/User.class");
		Map<String, Class<?>> map =new HashMap<String, Class<?>>();
		for(Method method:objClass.getDeclaredMethods()){
			System.out.println(method.getName()+" ===  "+method.getParameterTypes().getClass());
			//map.put(key, value)
		}
		for(Field field:objClass.getDeclaredFields()){
			System.out.println(field.getName()+" == "+field.getType());
			map.put(field.getName(), field.getType());
		}
		Object object=objClass.newInstance();
		Method method = objClass.getDeclaredMethod("setAge", map.get("age"));
		method.invoke(object, 233);
		
		Method getMethod = objClass.getDeclaredMethod("getAge");
		System.out.println(getMethod.invoke(object));
	}
	
}

