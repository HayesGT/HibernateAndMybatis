package com.yxkong.common.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {
	public static String encrypt_algorithm_MD5 = "MD5";
	public static String encrypt_algorithm_SHA = "SHA";
	public static String mac_key_algorithm_HmacMD5 = "HmacMD5";
	public static String mac_key_algorithm_HmacSHA1 = "HmacSHA1";
	public static String mac_key_algorithm_HmacSHA256 = "HmacSHA256";
	public static String mac_key_algorithm_HmacSHA384 = "HmacSHA384";
	public static String mac_key_algorithm_HmacSHA512 = "HmacSHA512";

	/**
	 * DES key size must be equal to 56 
	 * DESede(TripleDES) key size must be equal to 112 or 168 
	 * AES key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available 
	 * Blowfish key size must be multiple of 8, and can only range from 32 to 448 (inclusive) 
	 * RC2 key size must be between 40 and 1024 bits 
	 * RC4(ARCFOUR) key size must be between 40 and 1024 bits
	 */
	public static String des_key_algorithm_DES = "DES";
	public static String des_key_algorithm_DESede = "DESede";
	public static String des_key_algorithm_AES = "AES";
	public static String des_key_algorithm_Blowfish = "Blowfish";
	public static String des_key_algorithm_RC2 = "RC2";
	public static String des_key_algorithm_RC4 = "RC4";

	/**
	 * base64解码
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] base64Decode(String str) throws Exception {
		BASE64Decoder dec = new BASE64Decoder();
		return (dec.decodeBuffer(str));
	}

	public static String base64Encode(byte[] bytes) throws Exception {
		BASE64Encoder encoder = new BASE64Encoder();
		return new String(encoder.encodeBuffer(bytes));
	}

	private static byte[] digest(String value, String algorithm)
			throws Exception {
		byte[] valueBytes = value.getBytes("UTF-8");
		MessageDigest md = null;
		md = MessageDigest.getInstance(algorithm);
		md.reset();
		md.update(valueBytes);
		return (md.digest());
	}

	public static byte[] digestMD5(String value) throws Exception {
		return digest(value, encrypt_algorithm_MD5);
	}

	public static byte[] digestSHA(String value) throws Exception {
		return digest(value, encrypt_algorithm_SHA);
	}

	private static byte[] encryptMac(String value, String key, String algorithm)
			throws Exception {
		byte[] data = value.getBytes("UTF-8");
		SecretKey secretKey = new SecretKeySpec(base64Decode(key), algorithm);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return (mac.doFinal(data));
	}

	public static byte[] encryptMacMD5(String value, String key)
			throws Exception {
		return encryptMac(value, key, mac_key_algorithm_HmacMD5);
	}

	public static String generateMacMD5Key(String seed) throws Exception {
		return generateDESKey(seed,mac_key_algorithm_HmacMD5,56);
	}

	public static String generateDESKeyAll(String seed,String algorithm)throws Exception{
		if(algorithm.equals(des_key_algorithm_DES))
			return generateDESKey(seed);
		else if(algorithm.equals(des_key_algorithm_DESede))
			return generate3DESKey(seed);
		else if(algorithm.equals(des_key_algorithm_AES))
			return generateAESKey(seed);
		else throw new RuntimeException("不支持的加密类型:"+algorithm);
	}
	
	public static String generateDESKey(String seed,String algorithm,int keysize)throws Exception{
		SecureRandom secureRandom = new SecureRandom(seed.getBytes());
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		keyGenerator.init(keysize,secureRandom);
		SecretKey secretKey = keyGenerator.generateKey();
		return base64Encode (secretKey.getEncoded());
	}
	
	public static String encryptDESAll(String data, String key,String algorithm)throws Exception{
		if(algorithm.equals(des_key_algorithm_DES))
			return encryptDES(data,key);
		else if(algorithm.equals(des_key_algorithm_DESede))
			return encrypt3DES(data,key);
		else if(algorithm.equals(des_key_algorithm_AES))
			return encryptAES(data,key);
		else throw new RuntimeException("不支持的加密类型:"+algorithm);
	}
	
	public static String decryptDESAll(String data, String key,String algorithm)throws Exception{
		if(algorithm.equals(des_key_algorithm_DES))
			return decryptDES(data,key);
		else if(algorithm.equals(des_key_algorithm_DESede))
			return decrypt3DES(data,key);
		else if(algorithm.equals(des_key_algorithm_AES))
			return decryptAES(data,key);
		else throw new RuntimeException("不支持的加密类型:"+algorithm);
	}
	
	public static String generateDESKey(String seed)throws Exception{
		return generateDESKey(seed,des_key_algorithm_DES,56);
	}
	
	
	public static String encryptDES(String data, String key)throws Exception{
		return encrypt(data, key,des_key_algorithm_DES);
	}
	
	public static String decryptDES(String src, String key)throws Exception{
		return decrypt(src, key,des_key_algorithm_DES);
	}
	
	public static String generate3DESKey(String seed)throws Exception{
		return generateDESKey(seed,des_key_algorithm_DESede,168);
	}
	public static String encrypt3DES(String src, String key)throws Exception{
		return encrypt(src, key,des_key_algorithm_DESede);
	}
	public static String decrypt3DES(String src, String key)throws Exception{
		return decrypt(src, key,des_key_algorithm_DESede);
	}
	
	
	public static String generateAESKey(String seed)throws Exception{
		return generateDESKey(seed,des_key_algorithm_AES,128);
	}
	public static String encryptAES(String src, String key)throws Exception{
		return encrypt(src, key,des_key_algorithm_AES);
	}
	public static String decryptAES(String src, String key)throws Exception{
		return decrypt(src, key,des_key_algorithm_AES);
	}
	
    /** 
     * 加密 
     *  
     * @param encryptStr 
     * @return 
     */  
    private static byte[] encrypt(byte[] src, String key,String algorithm) throws Exception {  
        Cipher cipher = Cipher.getInstance(algorithm);  
        SecretKeySpec securekey = new SecretKeySpec(base64Decode(key), algorithm);  
        cipher.init(Cipher.ENCRYPT_MODE, securekey);//设置密钥和加密形式  
        return cipher.doFinal(src);  
    }  
  
    /** 
     * 解密 
     *  
     * @param decryptStr 
     * @return 
     * @throws Exception 
     */  
    private static byte[] decrypt(byte[] src, String key,String algorithm)  throws Exception  {  
        Cipher cipher = Cipher.getInstance(algorithm);  
        SecretKeySpec securekey = new SecretKeySpec(base64Decode(key), algorithm);//设置加密Key  
        cipher.init(Cipher.DECRYPT_MODE, securekey);//设置密钥和解密形式  
        return cipher.doFinal(src);  
    }   
      
    /** 
     * 解密 
     * @param data 
     * @return 
     * @throws Exception 
     */  
    private final static String decrypt(String data,String key,String algorithm)throws Exception {  
    	return new String(decrypt(base64Decode(data),  
                key,algorithm));  
    }  
  
    /** 
     * 加密 
     *  
     * @param data 
     * @return 
     * @throws Exception 
     */  
    private final static String encrypt(String data,String key,String algorithm) throws Exception {  
    	return base64Encode(encrypt(data.getBytes(), key,algorithm));  
    }  
      
  
    public static void main(String[] args) throws Exception{  
        String data = "我是谁，你又是谁，到底谁是谁adsf？";  
        String seed = "whoami,youarewho?";
        System.out.println("要加密的数据:"+data);
        System.out.println("密码种子:"+seed);
        System.out.println();
        
        System.out.println("-----------3DES------------");
        String key = generate3DESKey(seed);
        System.out.println("3DES生成的密钥(base64Encode):"+key);
        String idEncrypt = encrypt3DES(data,key);  
        System.out.println("3DES加密后数据(base64Encode):"+idEncrypt); 
        
        key = "BMHBc+9wGd9dsDG/HOp2+HAxgIBzrvE7";
        System.out.println("页面copy的key(base64Encode):"+key);
        String idDecrypt = decrypt3DES(idEncrypt,key);  
        System.out.println("页面key解密后数据:"+idDecrypt);  
        
        System.out.println("-----------DES------------");
        key = generateDESKey(seed);
        System.out.println("DES生成的密钥(base64Encode):"+key);
        idEncrypt = encryptDES(data,key);  
        System.out.println("DES加密后数据(base64Encode):"+idEncrypt);
        key = "BMHBc+9wGd8=";
        System.out.println("页面copy的key(base64Encode):"+key);
        idDecrypt = decryptDES(idEncrypt,key);  
        System.out.println("页面key解密后数据:"+idDecrypt);  
        
        System.out.println("-----------AES------------");
        key = generateAESKey(seed);
        System.out.println("AES生成的密钥(base64Encode):"+key);
        idEncrypt = encryptAES(data,key);  
        System.out.println("AES加密后数据(base64Encode):"+idEncrypt);  
        key = "BMDBcu9xGd5dsTG+Het3+A==";
        System.out.println("页面copy的key(base64Encode):"+key);
        idDecrypt = decryptAES(idEncrypt,key);  
        System.out.println("页面key解密后数据:"+idDecrypt); 
        
        System.out.println("-----------MD5------------");
        System.out.println("MD5摘要(base64Encode):"+base64Encode(digestMD5(data)));
        
        System.out.println("-----------SHA------------");
        System.out.println("SHA摘要((base64Encode))"+base64Encode(digestSHA(data)));
        
        System.out.println("-----------HmacMD5------------");
        key = generateMacMD5Key(seed);
        System.out.println("HmacMD5生成的密钥:"+key);
        idEncrypt = base64Encode(encryptMacMD5(data,key));  
        System.out.println("HmacMD5加密后数据(base64Encode):"+idEncrypt); 
    } 
}
