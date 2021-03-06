/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 12.
 */
package namoo.board.dom1.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    //
    /**
     * Date κ°μ²΄λ₯? yyyy-MM-dd HH:mm:ss ?¨?΄? λ¬Έμ?΄λ‘? λ³??
     * 
     * @param date  Dateκ°μ²΄
     * @return  {@link String}
     */
    public static String parseStr(Date date) {
        //
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * yyyy-MM-dd HH:mm:ss ?¨?΄? Date λ¬Έμ?΄? Date κ°μ²΄λ‘? λ³??
     * 
     * @param dateStr   Date λ¬Έμ?΄
     * @return  {@link java.util.Date}  
     */
    public static Date parseDate(String dateStr) {
        //
        try {
            return DateUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
