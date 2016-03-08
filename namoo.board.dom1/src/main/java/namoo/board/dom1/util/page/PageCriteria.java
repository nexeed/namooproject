/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom1.util.page;


import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class PageCriteria implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -1297251309921049014L;

    /** ì´ˆê¸° ?˜?´ì§? ë²ˆí˜¸ */
    private static final int DEFAULT_PAGE_NUM = 1;

    /** ?•œ ?˜?´ì§??— ì¶œë ¥?˜?Š” ê¸°ë³¸ Item ?ˆ˜ */
    public static final int DEFAULT_ITEM_LIMIT_OF_PAGE = 10;

    /** ë¬¸ì ìºë¦­?„° ì½”ë“œ : UTF-8 */
    public static final Charset UTF8 = Charset.forName("UTF-8");

    /** ?˜„?¬ ?˜?´ì§? */
    private int page;

    /** ? „ì²´í˜?´ì§? ?ˆ˜ */
    private int totalPageCount;

    /** ? „ì²? ?•„?´?…œ ?ˆ˜ */
    private int totalItemCount;

    /** ?•œ ?˜?´ì§??‹¹ ?•„?´?…œ ?ˆ˜ */
    private int itemLimitOfPage;

    /** ?˜?´ì§? ?„¤ë¹„ê²Œ?´?…˜ ë°”ì—?„œ ë³´ì—¬ì§? ?˜?´ì§? ê°??ˆ˜ */
    private int navigateLimit = 10;

    /** ê²??ƒ‰ì¡°ê±´ ?›ë³? : serialized?˜?–´ ?„˜?–´?˜¨ ëª¨ë“  ?ŒŒ?¼ë¯¸í„° ?›ë³¸ì„.*/
    private Map<String, String> params = new HashMap<String, String>();

    //--------------------------------------------------------------------------
    
    /**
     * ê¸°ë³¸ ?ƒ?„±?.
     */
    private PageCriteria() {
        //
        this.page = DEFAULT_PAGE_NUM;
        this.itemLimitOfPage = DEFAULT_ITEM_LIMIT_OF_PAGE;
    }

    /**
     * ?˜?´ì§? ë²ˆí˜¸?? ?˜?´ì§??‹¹ ?‘œ?‹œ?•  ?•„?´?…œ?ˆ˜ë¥? ì´ˆê¸°?™”?•˜?Š” ?ƒ?„±?.
     *
     * @param page
     *            ?˜?´ì§? ë²ˆí˜¸
     * @param limit
     *            ?˜?´ì§??‹¹ ?‘œ?‹œ?•  ?•„?´?…œ ê°??ˆ˜
     */
    public PageCriteria(int page, int itemLimitOfPage) {
        //
        this();
        if (page > 0 && itemLimitOfPage > 0) {
            this.page = page;
            this.itemLimitOfPage = itemLimitOfPage;
        }
    }

    /**
     *
     * @param defaultItemLimitOfPage ?•œ ?˜?´ì§??‹¹ ?•„?´?…œ ?ˆ˜
     * @param serializedCriteria
     */
    protected void init(int defaultItemLimitOfPage, String serializedCriteria) {
        parseSerializedCriteria(defaultItemLimitOfPage, serializedCriteria);

        try {
            // deserialize();
        } catch (Exception e) {
            // serialize ?—?Ÿ¬ ...
            // Creteria ?´?˜?Š¤ ? •ë³? & serializedCriteriaë¥? ?˜ˆ?™¸?— ? •ë¦¬í•´?„œ ?•Œ? ¤ì¤˜ì•¼ ?•¨.
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    //--------------------------------------------------------------------------

    /**
     * @param page
     *            ?„¤? •?•  ?˜?´ì§?
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @param limit
     *            ?„¤? •?•  ?•œ ?˜?´ì§??‹¹ ?•„?´?…œ ?ˆ˜
     */
    public void setItemLimitOfPage(int itemLimitOfPage) {
        this.itemLimitOfPage = itemLimitOfPage;
    }

    /**
     * ? „ì²? ?•„?´?…œ?ˆ˜ ?„¤? •
     *
     * @param totalItemCount
     *            ? „ì²? ?•„?´?…œ?ˆ˜
     */
    public void setTotalItemCount(int totalItemCount) {
        if (totalItemCount == 0) {
            return;
        }
        this.totalItemCount = totalItemCount;
        this.totalPageCount = (totalItemCount / this.itemLimitOfPage) + (totalItemCount % this.itemLimitOfPage > 0 ? 1 : 0);
    }

    /**
     * ì¡°ê±´ ì¶”ê?
     *
     * @param key
     *            ì¡°ê±´ ?‚¤
     * @param value
     *            ì¡°ê±´ ê°?
     */
    private void addCondition(String key, String value) {
        //TODO:: ê²??ƒ‰ì¡°ê±´?´ ëª©ë¡?œ¼ë¡? ?˜¤?Š”ê²½ìš°ê°? ?ˆ?„ê²½ìš° ëª©ë¡ ì²˜ë¦¬ ?•´?•¼?•¨.
        this.params.put(key, value);
    }

    /**
     *
     * @param defaultItemLimitOfPage
     * @param serializedCriteria
     */
    private void parseSerializedCriteria(int defaultItemLimitOfPage, String serializedCriteria) {
        if (serializedCriteria != null && serializedCriteria.length() > 0) {
            String[] splitedCondition = serializedCriteria.split("&");
            for (String keyValue : splitedCondition) {
                String[] param = keyValue.split("=");
                // ë¹? ê°’ì˜ ì¡°ê±´?? ë¬´ì‹œ
                if (param.length <= 1) continue;
                try {
                    addCondition(param[0], URLDecoder.decode(param[1], UTF8.name()));
                } catch (UnsupportedEncodingException e) {
                    //ë¬´ì‹œ
                }
            }
        }

        setCurrentPageAndItemLimitOfPage(defaultItemLimitOfPage);
    }

    /**
     *
     * @param defaultItemLimitOfPage
     */
    private void setCurrentPageAndItemLimitOfPage(int defaultItemLimitOfPage) {
        String page = getParamValue("page");
        if (page != null) {
            setPage(Integer.parseInt(page));
        } else {
            setPage(DEFAULT_PAGE_NUM);
        }

        String itemLimitOfPage = getParamValue("limit");
        if (itemLimitOfPage != null) {
            setItemLimitOfPage(Integer.parseInt(itemLimitOfPage));
        } else {
            setItemLimitOfPage(defaultItemLimitOfPage);
        }
    }

    /**
     *
     * @param key
     * @return
     */
    private String getParamValue(String key) {
        return this.params.get(key);
    }

    /**
     * @return ?‹œ?‘ ?¸?±?Š¤ (JPA?—?„œ query.setFirstResult?˜ ?š©?„?„)
     */
    public int getStart() {
        return ((this.page - 1) * this.itemLimitOfPage);
    }

    /**
     * @return ?‹œ?‘?¸?±?Š¤(mybatis oralce?š© : rownum)
     */
    public int getStartIndex() {
        return getStart() + 1;
    }

    /**
     * @return ? ?¸?±?Š¤(mybatis oracle?š© : rownum)
     */
    public int getEnd() {
        return this.page * this.itemLimitOfPage;
    }

    /**
     * @return page ?˜„?¬ ?˜?´ì§?
     */
    public int getPage() {
        return page;
    }

    /**
     * @return itemLimitOfPage ?•œ ?˜?´ì§??‹¹ ?•„?´?…œ ?ˆ˜ (query.setMaxResults?˜ ?š©?„ë¡œë„ ?‚¬?š©?•  ?ˆ˜ ?ˆ?Œ)
     */
    public int getItemLimitOfPage() {
        return itemLimitOfPage;
    }

    /**
     * @return totalPageCount ? „ì²? ?˜?´ì§? ?ˆ˜
     */
    public int getTotalPageCount() {
        return totalPageCount;
    }

    /**
     * @return totalItemCount ? „ì²? ?•„?´?…œ ?ˆ˜
     */
    public int getTotalItemCount() {
        return totalItemCount;
    }

    /**
     * <PRE>
     * ?˜?´ì§? ì²˜ë¦¬?˜?Š” ?„¤ë¹„ê²Œ?´?…˜ ë°”ì— ?‘œê¸? ?˜?Š” ?˜?´ì§? ?ˆ˜
     *   << 1,2,3,4,5 ... >>
     * </PRE>
     * @return
     */
    public int getNavigateLimit() {
        return navigateLimit;
    }

    /**
     * <PRE>
     * ?˜?´ì§? ì²˜ë¦¬?˜?Š” ?„¤ë¹„ê²Œ?´?…˜ ë°”ì— ?‘œê¸? ?˜?Š” ?˜?´ì§? ?ˆ˜
     *   << 1,2,3,4,5 ... >>
     * </PRE>
     * @param navigateLimit
     */
    public void setNavigateLimit(int navigateLimit) {
        this.navigateLimit = navigateLimit;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    
}
