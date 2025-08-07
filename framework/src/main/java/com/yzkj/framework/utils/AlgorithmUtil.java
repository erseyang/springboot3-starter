package com.yzkj.framework.utils;

import com.yzkj.framework.enums.ErrorCodeEnum;
import com.yzkj.framework.exception.YzkjException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 加密工具类,使用与旧系统相同的加密算法规则,以保证历史数据的可用
 *
 */
@Slf4j
public class AlgorithmUtil {
    /**
     * ALGORITHM 算法 <br>
     * 可替换为以下任意一种算法，同时key值的size相应改变。
     * <p/>
     * <pre>
     * DES                  key size must be equal to 56
     * DESede(TripleDES)    key size must be equal to 112 or 168
     * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
     * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
     * RC2                  key size must be between 40 and 1024 bits
     * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
     * </pre>
     * <p/>
     * 在Key toKey(byte[] key)方法中使用下述代码
     * <p>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</p> 替换
     * <p>
     * DESKeySpec dks = new DESKeySpec(key);<br>
     * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);<br>
     * SecretKey secretKey = keyFactory.generateSecret(dks);<br>
     * </p>
     */
    public static final String ALGORITHM = "DES";
    private static final String parentKey = "E8450B38C7DD622CEB5A369F2D7F10A4AC80F53A";
    private static final byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8};
    private static final  String hexStr = "0123456789ABCDEF";

    /**
     * 加密算法
     * @param source 需要加密的对象
     * @param key 加密使用的Key
     * @return 加密后的数据
     */
    public static String encrypt(String source,String key){
        try {
            byte[] bs = source.getBytes(StandardCharsets.UTF_8);
            bs = parseByte2HexStr(bs).getBytes();
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            Key k = toKey(parentKey + "_" + key);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, k, zeroIv);
            byte[] d = cipher.doFinal(bs);
            return encodeBASE64(d);
        } catch (Exception e) {
            log.error("encrypt error:", e);
            throw new YzkjException(ErrorCodeEnum.getException(ErrorCodeEnum.ENCRYPT_ERROR));
        }
    }
    /**
     * 加密算法
     * @param source 需要加密的字节数组
     * @param key 加密使用的Key
     * @return 加密后的数据
     */
    public static byte[] encrypt(byte[] source,String key){
        try {
            byte[] bs = parseByte2HexStr(source).getBytes();
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            Key k = toKey(parentKey + "_" + key);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, k, zeroIv);
            byte[] d = cipher.doFinal(bs);
            return encodeBASE64(d).getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("encrypt error:", e);
            throw new YzkjException(ErrorCodeEnum.getException(ErrorCodeEnum.ENCRYPT_ERROR));
        }
    }

    /**
     * 解密算法
     * @param source 需要解密的对象
     * @param key 加密使用的Key
     * @return 解密后的数据
     */
    public static String decode(String source,String key){
        try {
            byte[] result = decodeBASE64(source);
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            Key k = toKey(parentKey + "_" + key);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, k, zeroIv);
            byte[] d = cipher.doFinal(result);
            d = parseHexStr2Byte(new String(d));
            if(d != null){
                return new String(d, StandardCharsets.UTF_8);
            }
            return "";
        } catch (Exception e) {
            log.error("decode error:", e);
            throw new YzkjException(ErrorCodeEnum.getException(ErrorCodeEnum.DECODE_ERROR));
        }
    }
    /**
     * Encode a string using algorithm specified in web.xml and return the
     * resulting encrypted password. If exception, the plain credentials string
     * is returned
     *
     * @param password
     *            Password or other credentials to use in authenticating this
     *            username
     * @param algorithm
     *            Algorithm used to do the digest
     * @return encypted password based on the algorithm.
     */
    public static String encodePassword(String password, String algorithm) {
        byte[] unEncodedPassword = password.getBytes();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            log.error("encodePassword:",e);
            return password;
        }
        md.reset();
        md.update(unEncodedPassword);
        byte[] encodedPassword = md.digest();
        StringBuilder buf = new StringBuilder();
        for (byte b : encodedPassword) {
            if ((b & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(b & 0xff, 16));
        }
        return buf.toString();
    }
    public static String EncodeSHA256(String message){
        return AlgorithmUtil.encodePassword(message,"SHA-256");
    }

    public static String EncodeSHA256WithMD5(String message){
        return AlgorithmUtil.encodePassword(md5crypt(message),"SHA-256");
    }
    public static String generateUserToken(String key) {
        String preKey =  key + ThreadLocalRandom.current().nextLong() + System.currentTimeMillis();
        return md5crypt(preKey);
    }
    public static String generateEncodeKey() {
        String preKey = hexStr + ThreadLocalRandom.current().nextLong() + System.currentTimeMillis();
        return md5crypt(preKey.getBytes());
    }

    /**
     * md5 加密
     * @param input 字节数组
     * @return 加密后的字符串
     */
    public static String md5crypt(byte[] input){
        try {
            char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7',
                    '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(input);
            input = md5.digest();
            StringBuilder sb = new StringBuilder(input.length * 2);
            for (byte b : input) {
                sb.append(hexChar[(b & 0xf0) >>> 4]);
                sb.append(hexChar[b & 0x0f]);
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("md5crypt exception:", e);
            throw new YzkjException(ErrorCodeEnum.ENCRYPT_ERROR.getMessage(), ErrorCodeEnum.ENCRYPT_ERROR.getCode());
        }
    }
    /**
     * One-stop md5 string encrypting.
     */
    public static String md5crypt(String input) {
        return AlgorithmUtil.md5crypt(input.getBytes());
    }

    /**
     * 转换密钥<br>
     *
     * @param key 密钥
     * @return 转换后的秘钥
     * @throws Exception 异常信息
     */
    private static Key toKey(String key) throws Exception {
        DESKeySpec dks = new DESKeySpec(md5crypt(key).getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(dks);
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf 二进制字节数组
     * @return 16进制字符串
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuilder result = new StringBuilder();
        String hex = "";
        for (byte b : buf) {
            // 字节高4位
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            // 字节低4位
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result.append(hex);
        }
        return result.toString();
    }

    /**
     * BASE64加密
     *
     * @param key 待加密的字节数组
     * @return 加密后的字符串
     */
    public static String encodeBASE64(byte[] key) {
        return Base64.getEncoder().encodeToString(key);
    }

    /**
     * BASE64解密
     *
     * @param key 待解密的字符串
     * @return 解密后的字节数组
     */
    public static byte[] decodeBASE64(String key)  {
        return Base64.getDecoder().decode(key);
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr 16进制的字符串
     * @return 二进制数组
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.isEmpty())
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
