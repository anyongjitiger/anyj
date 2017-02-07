package com.emep.zaixian.security.component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 
* @ClassName: SerializeUtils 
* @Description: 配合redis使用的序列化工具
* @author com_emep_mpc
* @date 2016年8月29日 下午2:21:33 
*  
*/
public class SerializeUtils {

	/** 
	* @Title: serialize 
	* @Description: 进行序列化
	* @param @param o
	* @param @return    设定文件 
	* @return byte[]    返回类型 
	* @throws 
	*/
	public static byte[] serialize(Object o) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ObjectOutputStream outo = new ObjectOutputStream(out);
			outo.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return out.toByteArray();
	}

	/** 
	* @Title: deserialize 
	* @Description: 进行反序列化 
	* @param @param b
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	public static Object deserialize(byte[] b) {
		ObjectInputStream oin;
		try {
			oin = new ObjectInputStream(new ByteArrayInputStream(b));
			try {
				return oin.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
