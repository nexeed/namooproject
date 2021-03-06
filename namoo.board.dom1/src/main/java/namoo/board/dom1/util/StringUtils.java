/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom1.util;

import java.util.regex.Pattern;

public abstract class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * ?? ₯? ?€λΈμ ?Έκ°? ??΄λ©? λΉλ¬Έ??΄("")? λ°ν?κ³?
     * ??΄ ??λ©? ?€λΈμ ?Έ? λ¬Έμ?΄?  trim? κ²°κ³Όλ₯? λ°ν??€.
     * <pre>
     * [null] --> [""]
     * [""] --> [""]
     * ["     "] --> [""]
     * [" aaa"] --> ["aaa"]
     * ["aaa "] --> ["aaa"]
     * [" aaa "] --> ["aaa"]
     * [" aa aaa "] --> ["aa aaa"]
     * [" aa aaa aa"] --> ["aa aaa aa"]
     * </pre>
     * @param obj λ¬Έμ?΄λ‘? λ³???  ?€λΈμ ?Έ
     * @return λΉλ¬Έ??΄("") ?? trim? λ¬Έμ?΄
     */
    public static String trimToEmpty(Object obj) {
        return obj == null ? EMPTY : trimToEmpty(String.valueOf(obj));
    }

    /**
     * ?? ₯? λ¬Έμ?΄?΄ ? ?? λΉλ¬Έ??΄("")?΄λ©? ?? ₯? κΈ°λ³Έλ¬Έμ?΄? λ°ν?κ³?
     * κ·Έλ μ§???Όλ©? ?? ₯? λ¬Έμ?΄? trim? κ²°κ³Όλ₯? λ°ν??€.
     * @param anObject λ¬Έμ?΄λ‘? λ³???  ?€λΈμ ?Έ
     * @param defaultString κΈ°λ³Έλ¬Έμ?΄
     * @return κΈ°λ³Έλ¬Έμ?΄ ?? trim? λ¬Έμ?΄
     */
    public static String trimToDefault(String str, String defaultStr) {
        String ts = trim(str);
        return isEmpty(ts) ? defaultStr : ts;
    }

    /**
     * ?? ₯? ?€λΈμ ?Έκ°? ? ?? λΉλ¬Έ??΄("")?΄λ©? ?? ₯? κΈ°λ³Έλ¬Έμ?΄? λ°ν?κ³?
     * κ·Έλ μ§???Όλ©? ?€λΈμ ?Έ? λ¬Έμ?΄?  trim? κ²°κ³Όλ₯? λ°ν??€.
     * @param obj λ¬Έμ?΄λ‘? λ³???  ?€λΈμ ?Έ
     * @param defaultStr κΈ°λ³Έλ¬Έμ?΄
     * @return κΈ°λ³Έλ¬Έμ?΄ ?? trim? λ¬Έμ?΄
     */
    public static String trimToDefault(Object obj, String defaultStr) {
        return obj == null ? defaultStr : trimToDefault(String.valueOf(obj), defaultStr);
    }

    /**
     * λ¬Έμ?΄? μΉν??€.
     * @param srcString ?λ³Έλ¬Έ??΄
     * @param oldString λ³?κ²½ν  λ¬Έμ?΄
     * @param newString ??μ²΄ν  λ¬Έμ?΄
     * @return ??μ²΄λ λ¬Έμ?΄
     */
    public static String replace(String srcString, String oldString, String newString) {
        if (srcString == null) {
            return null;
        }
        StringBuilder destStr = new StringBuilder(srcString.length());
        int len = oldString.length();
        int srclen = srcString.length();
        int pos = 0;
        int oldpos = 0;

        while ((pos = srcString.indexOf(oldString, oldpos)) >= 0) {
            destStr.append(srcString.substring(oldpos, pos));
            destStr.append(newString);
            oldpos = pos + len;
        }

        if (oldpos < srclen) {
            destStr.append(srcString.substring(oldpos, srclen));
        }
        return destStr.toString();
    }

    /**
     * ?«?λ‘? κ΅¬μ±? λ¬Έμ?΄ μ²΄ν¬
     * @param str
     * @return ?«? λ¬Έμ?΄ ?¬λΆ?
     */
    public static boolean isDigit(String str) {
        return isNumeric(str);
    }

    /**
     * ?λ³Έλ¬Έ??΄? trim? λ¬Έμ?΄? κΈΈμ΄λ³΄λ€ μ§?? ? κΈΈμ΄κ°? ?΄ κ²½μ° λΆ?μ‘±ν λ§νΌ? ? κ³΅λ λ¬Έμλ‘?
     * ?Όμͺ½μ?λΆ??° μ±μμ§? λ¬Έμ?΄? λ°ν??€.
     * <p>
     * <pre>
     * [null, 'A', 3] --> ["AAA"]
     * ["1", 'A', 3] --> ["AA2"]
     * ["12", 'A', 3] --> ["A12"]
     * ["123", 'A', 3] --> ["123"]
     * ["1234", 'A', 3] --> ["1234"]
     * ["1234  ", 'A', 3] --> ["1234"]
     *
     * ["12 ", 'A', 3] --> ["A12"]
     * [" 12", 'A', 3] --> ["A12"]
     * </pre>
     *
     * @param srcStr ?λ³Έλ¬Έ??΄
     * @param paddingChar μ±μΈλ¬Έμ
     * @param length λ¬Έμκ°? μ±μμ§? ?? λ¬Έμ?΄ κΈΈμ΄
     * @return ? κ³΅λ λ¬Έμλ‘? μ±μμ§? λ¬Έμ?΄
     */
    public static String leftPadding(String srcStr, char paddingChar, int length) {
        String resultString = srcStr = (srcStr == null) ? "" : srcStr.trim();

        // ?λ³Έλ¬Έ??΄λ³΄λ€ ?΄ κ²½μ°?λ§? ?¨?©? ?¨.
        if (length > srcStr.length()) {
            StringBuilder buff = new StringBuilder(length);
            int paddingSize = length - srcStr.length();
            for (int i = 0; i < paddingSize; i++) {
                buff.append(paddingChar);
            }
            resultString = buff.append(srcStr).toString();
        }

        return resultString;
    }

    /**
     * ?? ₯? κ°μ μ§?? ? κΈ???λ§νΌ ?Όμͺ½μ?λΆ??° 0? μ±μ? λ°ν
     */
    /**
     * ?λ³Έλ¬Έ??΄? trim? λ¬Έμ?΄? κΈΈμ΄λ³΄λ€ μ§?? ? κΈΈμ΄κ°? ?΄ κ²½μ° λΆ?μ‘±ν λ§νΌ? '0'?Όλ‘?
     * ?Όμͺ½μ?λΆ??° μ±μμ§? λ¬Έμ?΄? λ°ν??€.
     * <p>
     * <pre>
     * [null, 3] --> ["000"]
     * ["1", 3] --> ["002"]
     * ["12", 3] --> ["012"]
     * ["123", 3] --> ["123"]
     * ["1234", 3] --> ["1234"]
     * ["1234  ", 3] --> ["1234"]
     *
     * ["12 ", 3] --> ["012"]
     * [" 12", 3] --> ["012"]
     * </pre>
     *
     * @param srcStr ?λ³Έλ¬Έ??΄
     * @param length '0'?΄ ?? λ¬Έμ?΄ κΈΈμ΄
     * @return '0'?Όλ‘? μ±μμ§? λ¬Έμ?΄
     */
    public static String leftZeroPadding(String srcStr, int length) {
        return leftPadding(srcStr, '0', length);
    }

    /**
     * ?΄λ©μΌ? ? ?¨?±(? ?©?±)? μ²΄ν¬??€.
     *
     * @param email
     * @return ? ?¨? ?΄λ©μΌ?Έ κ²½μ° true ??λ©? falseλ₯? λ¦¬ν΄
     */
    public static boolean checkEmail(String email) {
        if (StringUtils.isEmpty(email)) return false;
        boolean value = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
        return value;
    }

}
