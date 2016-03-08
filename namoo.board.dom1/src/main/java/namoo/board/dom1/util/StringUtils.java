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
     * ?��?��?�� ?��브젝?���? ?��?���? 빈문?��?��("")?�� 반환?���?
     * ?��?�� ?��?���? ?��브젝?��?�� 문자?��?��  trim?�� 결과�? 반환?��?��.
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
     * @param obj 문자?���? �??��?�� ?��브젝?��
     * @return 빈문?��?��("") ?��?�� trim?�� 문자?��
     */
    public static String trimToEmpty(Object obj) {
        return obj == null ? EMPTY : trimToEmpty(String.valueOf(obj));
    }

    /**
     * ?��?��?�� 문자?��?�� ?�� ?��?�� 빈문?��?��("")?���? ?��?��?�� 기본문자?��?�� 반환?���?
     * 그렇�??��?���? ?��?��?�� 문자?��?�� trim?�� 결과�? 반환?��?��.
     * @param anObject 문자?���? �??��?�� ?��브젝?��
     * @param defaultString 기본문자?��
     * @return 기본문자?�� ?��?�� trim?�� 문자?��
     */
    public static String trimToDefault(String str, String defaultStr) {
        String ts = trim(str);
        return isEmpty(ts) ? defaultStr : ts;
    }

    /**
     * ?��?��?�� ?��브젝?���? ?�� ?��?�� 빈문?��?��("")?���? ?��?��?�� 기본문자?��?�� 반환?���?
     * 그렇�??��?���? ?��브젝?��?�� 문자?��?��  trim?�� 결과�? 반환?��?��.
     * @param obj 문자?���? �??��?�� ?��브젝?��
     * @param defaultStr 기본문자?��
     * @return 기본문자?�� ?��?�� trim?�� 문자?��
     */
    public static String trimToDefault(Object obj, String defaultStr) {
        return obj == null ? defaultStr : trimToDefault(String.valueOf(obj), defaultStr);
    }

    /**
     * 문자?��?�� 치환?��?��.
     * @param srcString ?��본문?��?��
     * @param oldString �?경할 문자?��
     * @param newString ??체할 문자?��
     * @return ??체된 문자?��
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
     * ?��?���? 구성?�� 문자?�� 체크
     * @param str
     * @return ?��?�� 문자?�� ?���?
     */
    public static boolean isDigit(String str) {
        return isNumeric(str);
    }

    /**
     * ?��본문?��?��?�� trim?�� 문자?��?�� 길이보다 �??��?�� 길이�? ?�� 경우 �?족한 만큼?�� ?��공된 문자�?
     * ?��쪽에?���??�� 채워�? 문자?��?�� 반환?��?��.
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
     * @param srcStr ?��본문?��?��
     * @param paddingChar 채울문자
     * @param length 문자�? 채워�? ?��?�� 문자?�� 길이
     * @return ?��공된 문자�? 채워�? 문자?��
     */
    public static String leftPadding(String srcStr, char paddingChar, int length) {
        String resultString = srcStr = (srcStr == null) ? "" : srcStr.trim();

        // ?��본문?��?��보다 ?�� 경우?���? ?��?��?�� ?��.
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
     * ?��?��?�� 값을 �??��?�� �??��?��만큼 ?��쪽에?���??�� 0?�� 채워?�� 반환
     */
    /**
     * ?��본문?��?��?�� trim?�� 문자?��?�� 길이보다 �??��?�� 길이�? ?�� 경우 �?족한 만큼?�� '0'?���?
     * ?��쪽에?���??�� 채워�? 문자?��?�� 반환?��?��.
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
     * @param srcStr ?��본문?��?��
     * @param length '0'?�� ?��?�� 문자?�� 길이
     * @return '0'?���? 채워�? 문자?��
     */
    public static String leftZeroPadding(String srcStr, int length) {
        return leftPadding(srcStr, '0', length);
    }

    /**
     * ?��메일?�� ?��?��?��(?��?��?��)?�� 체크?��?��.
     *
     * @param email
     * @return ?��?��?�� ?��메일?�� 경우 true ?��?���? false�? 리턴
     */
    public static boolean checkEmail(String email) {
        if (StringUtils.isEmpty(email)) return false;
        boolean value = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
        return value;
    }

}
