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

    /** μ΄κΈ° ??΄μ§? λ²νΈ */
    private static final int DEFAULT_PAGE_NUM = 1;

    /** ? ??΄μ§?? μΆλ ₯?? κΈ°λ³Έ Item ? */
    public static final int DEFAULT_ITEM_LIMIT_OF_PAGE = 10;

    /** λ¬Έμ μΊλ¦­?° μ½λ : UTF-8 */
    public static final Charset UTF8 = Charset.forName("UTF-8");

    /** ??¬ ??΄μ§? */
    private int page;

    /** ? μ²΄ν?΄μ§? ? */
    private int totalPageCount;

    /** ? μ²? ??΄? ? */
    private int totalItemCount;

    /** ? ??΄μ§??Ή ??΄? ? */
    private int itemLimitOfPage;

    /** ??΄μ§? ?€λΉκ²?΄? λ°μ? λ³΄μ¬μ§? ??΄μ§? κ°?? */
    private int navigateLimit = 10;

    /** κ²??μ‘°κ±΄ ?λ³? : serialized??΄ ??΄?¨ λͺ¨λ  ??Όλ―Έν° ?λ³Έμ.*/
    private Map<String, String> params = new HashMap<String, String>();

    //--------------------------------------------------------------------------
    
    /**
     * κΈ°λ³Έ ??±?.
     */
    private PageCriteria() {
        //
        this.page = DEFAULT_PAGE_NUM;
        this.itemLimitOfPage = DEFAULT_ITEM_LIMIT_OF_PAGE;
    }

    /**
     * ??΄μ§? λ²νΈ?? ??΄μ§??Ή ???  ??΄??λ₯? μ΄κΈ°??? ??±?.
     *
     * @param page
     *            ??΄μ§? λ²νΈ
     * @param limit
     *            ??΄μ§??Ή ???  ??΄? κ°??
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
     * @param defaultItemLimitOfPage ? ??΄μ§??Ή ??΄? ?
     * @param serializedCriteria
     */
    protected void init(int defaultItemLimitOfPage, String serializedCriteria) {
        parseSerializedCriteria(defaultItemLimitOfPage, serializedCriteria);

        try {
            // deserialize();
        } catch (Exception e) {
            // serialize ??¬ ...
            // Creteria ?΄??€ ? λ³? & serializedCriteriaλ₯? ??Έ? ? λ¦¬ν΄? ?? €μ€μΌ ?¨.
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    //--------------------------------------------------------------------------

    /**
     * @param page
     *            ?€? ?  ??΄μ§?
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @param limit
     *            ?€? ?  ? ??΄μ§??Ή ??΄? ?
     */
    public void setItemLimitOfPage(int itemLimitOfPage) {
        this.itemLimitOfPage = itemLimitOfPage;
    }

    /**
     * ? μ²? ??΄?? ?€? 
     *
     * @param totalItemCount
     *            ? μ²? ??΄??
     */
    public void setTotalItemCount(int totalItemCount) {
        if (totalItemCount == 0) {
            return;
        }
        this.totalItemCount = totalItemCount;
        this.totalPageCount = (totalItemCount / this.itemLimitOfPage) + (totalItemCount % this.itemLimitOfPage > 0 ? 1 : 0);
    }

    /**
     * μ‘°κ±΄ μΆκ?
     *
     * @param key
     *            μ‘°κ±΄ ?€
     * @param value
     *            μ‘°κ±΄ κ°?
     */
    private void addCondition(String key, String value) {
        //TODO:: κ²??μ‘°κ±΄?΄ λͺ©λ‘?Όλ‘? ?€?κ²½μ°κ°? ??κ²½μ° λͺ©λ‘ μ²λ¦¬ ?΄?Ό?¨.
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
                // λΉ? κ°μ μ‘°κ±΄?? λ¬΄μ
                if (param.length <= 1) continue;
                try {
                    addCondition(param[0], URLDecoder.decode(param[1], UTF8.name()));
                } catch (UnsupportedEncodingException e) {
                    //λ¬΄μ
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
     * @return ?? ?Έ?±?€ (JPA?? query.setFirstResult? ?©??)
     */
    public int getStart() {
        return ((this.page - 1) * this.itemLimitOfPage);
    }

    /**
     * @return ???Έ?±?€(mybatis oralce?© : rownum)
     */
    public int getStartIndex() {
        return getStart() + 1;
    }

    /**
     * @return ? ?Έ?±?€(mybatis oracle?© : rownum)
     */
    public int getEnd() {
        return this.page * this.itemLimitOfPage;
    }

    /**
     * @return page ??¬ ??΄μ§?
     */
    public int getPage() {
        return page;
    }

    /**
     * @return itemLimitOfPage ? ??΄μ§??Ή ??΄? ? (query.setMaxResults? ?©?λ‘λ ?¬?©?  ? ??)
     */
    public int getItemLimitOfPage() {
        return itemLimitOfPage;
    }

    /**
     * @return totalPageCount ? μ²? ??΄μ§? ?
     */
    public int getTotalPageCount() {
        return totalPageCount;
    }

    /**
     * @return totalItemCount ? μ²? ??΄? ?
     */
    public int getTotalItemCount() {
        return totalItemCount;
    }

    /**
     * <PRE>
     * ??΄μ§? μ²λ¦¬?? ?€λΉκ²?΄? λ°μ ?κΈ? ?? ??΄μ§? ?
     *   << 1,2,3,4,5 ... >>
     * </PRE>
     * @return
     */
    public int getNavigateLimit() {
        return navigateLimit;
    }

    /**
     * <PRE>
     * ??΄μ§? μ²λ¦¬?? ?€λΉκ²?΄? λ°μ ?κΈ? ?? ??΄μ§? ?
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
