package com.yzkj.framework.utils;

import com.yzkj.framework.constant.FrameworkMessageConstant;
import com.yzkj.framework.constant.ResultConstant;
import com.yzkj.framework.exception.YzkjException;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 金额从string转为int
     * @param amount string
     * @return integer
     */
    public static Integer amount2Int(String amount) {
        try {
            BigDecimal realAmount = new BigDecimal(amount);
            BigDecimal result = realAmount.multiply(new BigDecimal(100));
            return Integer.parseInt(result.stripTrailingZeros().toPlainString());
        } catch (Exception e) {
            Integer code = ResultConstant.RESULT_ERROR_CODE;
            String message = MessageUtils.getMessages(FrameworkMessageConstant.INVALID_AMOUNT_ERROR, new Object[]{amount});
            throw new YzkjException(code, message, e);
        }
    }

    /**
     * 生成随机字符串
     * @return 生成的随机串
     */
    public static String generateStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取中文首字母大写
     * 如:张三 -> ZS
     *    张 -> Z
     *    java -> J
     * @param chinese 中文
     * @return 中文首字母大写
     */
    public static String getChineseFirstWordToUpperCase(String chinese){
        StringBuilder initials = new StringBuilder();
        for (char c : chinese.toCharArray()) {
            // 获取当前字符的拼音列表
            List<Pinyin> pinyinList = HanLP.convertToPinyinList(String.valueOf(c));
            if (!pinyinList.isEmpty()) {
                if(!StringUtils.equals(pinyinList.get(0).toString(),Pinyin.none5.toString())){
                    // 如果是汉字，提取拼音首字母
                    initials.append(pinyinList.get(0).getShengmu().toString().toUpperCase().charAt(0));
                }else{
                    initials.append(String.valueOf(c).toUpperCase());
                }
            } else {
                // 其他字符
                initials.append(String.valueOf(c).toUpperCase());
            }
        }
       return initials.toString();
    }

    /**
     * 获取汉字的中文拼音,中间的非中文字符串直接过滤
     *  如:
     *      张三 -> ZhāngSān
     *      java ->
     *      zhang张三 -> ZhāngSān
     * @param chinese 中文字符串
     * @return 带音标的中文拼音
     */
    public static String getChinesePinyin(String chinese){
        if(StringUtils.isBlank(chinese)){
            return "";
        }
        StringBuilder pinyinWithToneAndUpperCase = new StringBuilder();
        List<Pinyin> pinyinList = HanLP.convertToPinyinList(chinese);
        if (!pinyinList.isEmpty()) {
            // 如果是汉字，提取带音标的拼音
            for(Pinyin pinyin : pinyinList){
                if (!pinyin.toString().equals(Pinyin.none5.toString())) {
                    // 获取带音标的拼音，并将首字母大写
                    String pinyinWithTone = pinyin.getPinyinWithToneMark();
                    pinyinWithToneAndUpperCase.append(Character.toUpperCase(pinyinWithTone.charAt(0)))
                            .append(pinyinWithTone.substring(1));
                }
            }
        }
        return pinyinWithToneAndUpperCase.toString();
    }
}
