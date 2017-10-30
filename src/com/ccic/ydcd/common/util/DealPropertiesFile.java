package com.ccic.ydcd.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class DealPropertiesFile {
	public static Properties readPropertiesFile(String filename) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(filename);
            properties.load(inputStream);
			inputStream.close(); // 关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
//		String username = properties.getProperty("username");
//		String passsword = properties.getProperty("password");
//		String chinese = properties.getProperty("chinese");
//		try {
//			chinese = new String(chinese.getBytes("ISO-8859-1"), "GBK"); // 处理中文乱码
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		return properties;
	}
	
	//写资源文件，含中文  
    public static void writePropertiesFile(String filename,Properties properties)  
    {
        try  
        {  
            OutputStream outputStream = new FileOutputStream(filename);
            properties.store(outputStream, "author: baico@163.com");
            outputStream.close();  
        }
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  

}
