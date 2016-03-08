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
     * Date ê°ì²´ë¥? yyyy-MM-dd HH:mm:ss ?Œ¨?„´?˜ ë¬¸ì?—´ë¡? ë³??™˜
     * 
     * @param date  Dateê°ì²´
     * @return  {@link String}
     */
    public static String parseStr(Date date) {
        //
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * yyyy-MM-dd HH:mm:ss ?Œ¨?„´?˜ Date ë¬¸ì?—´?„ Date ê°ì²´ë¡? ë³??™˜
     * 
     * @param dateStr   Date ë¬¸ì?—´
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
