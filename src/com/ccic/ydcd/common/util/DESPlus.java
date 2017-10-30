package com.ccic.ydcd.common.util;


import java.security.*;
import javax.crypto.*;

public class DESPlus {
    private static String strDefaultKey = "national";
    private static Cipher encryptCipher = null;
    private static Cipher decryptCipher = null;
    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程
     * 
     * @param arrB
     *            需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception
     *             本方法不处理任何异常，所有异常全部抛出
     */
    public static String byteArr2HexStr(byte[] arrB) {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程
     * 
     * @param strIn
     *            需要转换的字符串
     * @return 转换后的byte数组
     */
    public static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    /**
     * 默认构造方法，使用默认密钥
     * 
     * @throws Exception
     */
    public DESPlus() throws Exception {
        this(strDefaultKey);
    }

    private static Key key;
    /**
     * 指定密钥构造方法
     * 
     * @param strKey
     *            指定的密钥
     * @throws Exception
     */
    public DESPlus(String strKey) {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        
        try {
            key = getKey(strKey.getBytes());
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void setKey(String strKey) {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        try {
            key = getKey(strKey.getBytes());
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 加密字节数组
     */
    public static byte[] encrypt(byte[] arrB){
        byte[] s = null ;
        try {
            s = encryptCipher.doFinal(arrB);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return s ;
    }

    /**
     * 将启动码strIn加密，返回加密后的启动码
     */
    public static String encrypt(String strIn) {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    /**
     * 解密字节数组
     */
    public static byte[] decrypt(byte[] arrB)  {
        byte[] s = null ;
        try {
            s = decryptCipher.doFinal(arrB);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return s ;
    }
    /**
     * 解密 strIn 
     */
    public static String decrypt(String strIn){
    	//System.out.println(""+hexStr2ByteArr(strIn).length);
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }
    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     * 
     * @param arrBTmp
     *            构成该字符串的字节数组
     * @return 生成的密钥
     * @throws java.lang.Exception
     */
    public static Key getKey(byte[] arrBTmp) {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];
        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }

	/*每8位做一次异或运算*/
	public static byte[] getByteYH(byte [] by) {
		byte[] newBy = null;
		//DESPlus des = new DESPlus("CCIC");   //自定义密钥 
		if ((by.length % 8) != 0) {
			newBy = new byte[by.length + (8-(by.length % 8))];
			for (int k = 0;k < by.length;k++) {
				newBy[k] = by[k];
			}
			for (int k = by.length; k < newBy.length;k++) {
				newBy[k] = 0x00;//不足的补0x00
			}
		} else {
			newBy = by;
		}
		int i = newBy.length/8;
		byte aa[] = new byte[8];
		aa[0] = newBy[0];
		aa[1] = newBy[1];
		aa[2] = newBy[2];
		aa[3] = newBy[3];
		aa[4] = newBy[4];
		aa[5] = newBy[5];
		aa[6] = newBy[6];
		aa[7] = newBy[7];
		
		byte bb[] = new byte[8];
		
		for (int k = 1;k < i ;k++) {
			for (int j = 0;j < 8;j++) {
				bb[k] = newBy[k*8 + j];
			}
			aa[0] = (byte)(aa[0]^bb[0]);
			aa[1] = (byte)(aa[1]^bb[1]);
			aa[2] = (byte)(aa[2]^bb[2]);
			aa[3] = (byte)(aa[3]^bb[3]);
			aa[4] = (byte)(aa[4]^bb[4]);
			aa[5] = (byte)(aa[5]^bb[5]);
			aa[6] = (byte)(aa[6]^bb[6]);
			aa[7] = (byte)(aa[7]^bb[7]);
			String test = new String(aa);
			aa = DESPlus.encrypt(test.trim()).getBytes();
			
		}
		
        return aa;

	}


	static String str = ""; 
	
    public static void main(String[] args) {

    }

 }

