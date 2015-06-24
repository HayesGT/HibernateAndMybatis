package com.yxkong.common.utils;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class DESUtil {
	private static final String KEY="platform";
    /**
     * DES加密密码
     * @param data 要加密的字符串
     * @param key  DES加密的私钥，必须是8位长的字符串
     * @return
     * @throws Exception
     */
	public static String desEncode(String data,String key) throws Exception {
		DESKeySpec keySpec = new DESKeySpec(key.getBytes());// 设置密钥参数
		AlgorithmParameterSpec iv = new IvParameterSpec(key.getBytes());// 设置向量
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
	    Key desKey= keyFactory.generateSecret(keySpec);// 得到密钥对象;
		Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
		enCipher.init(Cipher.ENCRYPT_MODE, desKey, iv);// 设置工作模式为加密模式，给出密钥和向量
		byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
		BASE64Encoder base64Encoder = new BASE64Encoder();
		return base64Encoder.encode(pasByte);
	}
    /**
     * des 解密字符串
     * @param data  加密后的值
     * @param key   DES加密的私钥，必须是8位长的字符串
     * @return
     * @throws Exception
     */
	public static String desDecode(String data,String key) throws Exception {
		DESKeySpec keySpec = new DESKeySpec(key.getBytes());// 设置密钥参数
		AlgorithmParameterSpec iv = new IvParameterSpec(key.getBytes());// 设置向量
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
	    Key desKey= keyFactory.generateSecret(keySpec);// 得到密钥对象;
		Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		deCipher.init(Cipher.DECRYPT_MODE, desKey, iv);
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
		return new String(pasByte, "UTF-8");
	}
	public static void main(String[] args) throws Exception {

		System.out.println("加密:" + desEncode("123456",KEY));
		System.out.println("解密:" + desDecode(desEncode("123456",KEY),KEY));
	}
}