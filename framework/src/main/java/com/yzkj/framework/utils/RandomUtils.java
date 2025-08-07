package com.yzkj.framework.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 获取随机数
 * 
 * @author wangchaochao
 *
 */
@SuppressWarnings("unused")
public class RandomUtils {
	
	private static final String BASE_NUMBER = "0123456789";
	
	private static final String BASE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	/**
	 * 根据种子参数,获取length长度的数字字符串
	 * 
	 * @param length 长度
	 * @return 随机字符串
	 */
	public static String getRandomNumber(int length,String Numer){
		Random random = ThreadLocalRandom.current();   
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(Numer.length());   
	        sb.append(Numer.charAt(number));   
	    }   
	    return sb.toString();  
	}
	
	/**
	 * 根据种子参数,获取length长度的字符串
	 * 
	 * @param length  需要的长度
	 * @return 字符串
	 */
	public static String getRandomString(int length,String str) {
	    Random random = ThreadLocalRandom.current();   
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(str.length());   
	        sb.append(str.charAt(number));   
	    }   
	    return sb.toString();   
	 }
	
	/**
	 * 获取length长度的数字字符串
	 * 
	 * @param length 长度
	 * @return 字符串
	 */
	public static String getRandomNumber(int length){
		Random random = ThreadLocalRandom.current();   
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(BASE_NUMBER.length());   
	        sb.append(BASE_NUMBER.charAt(number));   
	    }   
	    return sb.toString();  
	}
	
	/**
	 * 获取length长度的字符串
	 * 
	 * @param length 长度
	 * @return 字符串
	 */
	public static String getRandomString(int length) {
	    Random random = ThreadLocalRandom.current();   
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(BASE_STRING.length());   
	        sb.append(BASE_STRING.charAt(number));   
	    }   
	    return sb.toString();   
	 }
	
	/**
	 * [0, range] 生成的随机数范围 0-range
	 * @param range 范围值
	 * @return 随机数
	 */
	public static int getRandomInt(int range){
		int bound =  range + 1;
		Random random = ThreadLocalRandom.current();
		return random.nextInt(bound);
	}
	
	/**
	 * [start, end] 生成的随机数范围 start-end
	 * @param start 开始值
	 * @param end 结束值
	 * @return 随机数
	 */
	public static int getRandomInt(int start, int end){
		int bound =  end - start + 1;
		Random random = ThreadLocalRandom.current();
		return random.nextInt(bound) + start;
	}

}
