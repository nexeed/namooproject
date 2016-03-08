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
     * ?…? ¥?œ ?˜¤ë¸Œì ?Š¸ê°? ?„?´ë©? ë¹ˆë¬¸??—´("")?„ ë°˜í™˜?•˜ê³?
     * ?„?´ ?•„?‹ˆë©? ?˜¤ë¸Œì ?Š¸?˜ ë¬¸ì?—´?˜  trim?•œ ê²°ê³¼ë¥? ë°˜í™˜?•œ?‹¤.
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
     * @param obj ë¬¸ì?—´ë¡? ë³??™˜?•  ?˜¤ë¸Œì ?Š¸
     * @return ë¹ˆë¬¸??—´("") ?˜?Š” trim?œ ë¬¸ì?—´
     */
    public static String trimToEmpty(Object obj) {
        return obj == null ? EMPTY : trimToEmpty(String.valueOf(obj));
    }

    /**
     * ?…? ¥?œ ë¬¸ì?—´?´ ?„ ?˜?Š” ë¹ˆë¬¸??—´("")?´ë©? ?…? ¥?œ ê¸°ë³¸ë¬¸ì?—´?„ ë°˜í™˜?•˜ê³?
     * ê·¸ë ‡ì§??•Š?œ¼ë©? ?…? ¥?œ ë¬¸ì?—´?˜ trim?•œ ê²°ê³¼ë¥? ë°˜í™˜?•œ?‹¤.
     * @param anObject ë¬¸ì?—´ë¡? ë³??™˜?•  ?˜¤ë¸Œì ?Š¸
     * @param defaultString ê¸°ë³¸ë¬¸ì?—´
     * @return ê¸°ë³¸ë¬¸ì?—´ ?˜?Š” trim?œ ë¬¸ì?—´
     */
    public static String trimToDefault(String str, String defaultStr) {
        String ts = trim(str);
        return isEmpty(ts) ? defaultStr : ts;
    }

    /**
     * ?…? ¥?œ ?˜¤ë¸Œì ?Š¸ê°? ?„ ?˜?Š” ë¹ˆë¬¸??—´("")?´ë©? ?…? ¥?œ ê¸°ë³¸ë¬¸ì?—´?„ ë°˜í™˜?•˜ê³?
     * ê·¸ë ‡ì§??•Š?œ¼ë©? ?˜¤ë¸Œì ?Š¸?˜ ë¬¸ì?—´?˜  trim?•œ ê²°ê³¼ë¥? ë°˜í™˜?•œ?‹¤.
     * @param obj ë¬¸ì?—´ë¡? ë³??™˜?•  ?˜¤ë¸Œì ?Š¸
     * @param defaultStr ê¸°ë³¸ë¬¸ì?—´
     * @return ê¸°ë³¸ë¬¸ì?—´ ?˜?Š” trim?œ ë¬¸ì?—´
     */
    public static String trimToDefault(Object obj, String defaultStr) {
        return obj == null ? defaultStr : trimToDefault(String.valueOf(obj), defaultStr);
    }

    /**
     * ë¬¸ì?—´?„ ì¹˜í™˜?•œ?‹¤.
     * @param srcString ?›ë³¸ë¬¸??—´
     * @param oldString ë³?ê²½í•  ë¬¸ì?—´
     * @param newString ??ì²´í•  ë¬¸ì?—´
     * @return ??ì²´ëœ ë¬¸ì?—´
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
     * ?ˆ«?ë¡? êµ¬ì„±?œ ë¬¸ì?—´ ì²´í¬
     * @param str
     * @return ?ˆ«? ë¬¸ì?—´ ?—¬ë¶?
     */
    public static boolean isDigit(String str) {
        return isNumeric(str);
    }

    /**
     * ?›ë³¸ë¬¸??—´?˜ trim?œ ë¬¸ì?—´?˜ ê¸¸ì´ë³´ë‹¤ ì§?? •?œ ê¸¸ì´ê°? ?´ ê²½ìš° ë¶?ì¡±í•œ ë§Œí¼?„ ? œê³µëœ ë¬¸ìë¡?
     * ?™¼ìª½ì—?„œë¶??„° ì±„ì›Œì§? ë¬¸ì?—´?„ ë°˜í™˜?•œ?‹¤.
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
     * @param srcStr ?›ë³¸ë¬¸??—´
     * @param paddingChar ì±„ìš¸ë¬¸ì
     * @param length ë¬¸ìê°? ì±„ì›Œì§? ?›„?˜ ë¬¸ì?—´ ê¸¸ì´
     * @return ? œê³µëœ ë¬¸ìë¡? ì±„ì›Œì§? ë¬¸ì?—´
     */
    public static String leftPadding(String srcStr, char paddingChar, int length) {
        String resultString = srcStr = (srcStr == null) ? "" : srcStr.trim();

        // ?›ë³¸ë¬¸??—´ë³´ë‹¤ ?´ ê²½ìš°?—ë§? ?Œ¨?”©?„ ?•¨.
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
     * ?…? ¥?œ ê°’ì„ ì§?? •?œ ê¸???ˆ˜ë§Œí¼ ?™¼ìª½ì—?„œë¶??„° 0?„ ì±„ì›Œ?„œ ë°˜í™˜
     */
    /**
     * ?›ë³¸ë¬¸??—´?˜ trim?œ ë¬¸ì?—´?˜ ê¸¸ì´ë³´ë‹¤ ì§?? •?œ ê¸¸ì´ê°? ?´ ê²½ìš° ë¶?ì¡±í•œ ë§Œí¼?„ '0'?œ¼ë¡?
     * ?™¼ìª½ì—?„œë¶??„° ì±„ì›Œì§? ë¬¸ì?—´?„ ë°˜í™˜?•œ?‹¤.
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
     * @param srcStr ?›ë³¸ë¬¸??—´
     * @param length '0'?´ ?›„?˜ ë¬¸ì?—´ ê¸¸ì´
     * @return '0'?œ¼ë¡? ì±„ì›Œì§? ë¬¸ì?—´
     */
    public static String leftZeroPadding(String srcStr, int length) {
        return leftPadding(srcStr, '0', length);
    }

    /**
     * ?´ë©”ì¼?˜ ?œ ?š¨?„±(? •?•©?„±)?„ ì²´í¬?•œ?‹¤.
     *
     * @param email
     * @return ?œ ?š¨?•œ ?´ë©”ì¼?¸ ê²½ìš° true ?•„?‹ˆë©? falseë¥? ë¦¬í„´
     */
    public static boolean checkEmail(String email) {
        if (StringUtils.isEmpty(email)) return false;
        boolean value = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
        return value;
    }

}
