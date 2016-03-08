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

    /** 초기 ?��?���? 번호 */
    private static final int DEFAULT_PAGE_NUM = 1;

    /** ?�� ?��?���??�� 출력?��?�� 기본 Item ?�� */
    public static final int DEFAULT_ITEM_LIMIT_OF_PAGE = 10;

    /** 문자 캐릭?�� 코드 : UTF-8 */
    public static final Charset UTF8 = Charset.forName("UTF-8");

    /** ?��?�� ?��?���? */
    private int page;

    /** ?��체페?���? ?�� */
    private int totalPageCount;

    /** ?���? ?��?��?�� ?�� */
    private int totalItemCount;

    /** ?�� ?��?���??�� ?��?��?�� ?�� */
    private int itemLimitOfPage;

    /** ?��?���? ?��비게?��?�� 바에?�� 보여�? ?��?���? �??�� */
    private int navigateLimit = 10;

    /** �??��조건 ?���? : serialized?��?�� ?��?��?�� 모든 ?��?��미터 ?��본임.*/
    private Map<String, String> params = new HashMap<String, String>();

    //--------------------------------------------------------------------------
    
    /**
     * 기본 ?��?��?��.
     */
    private PageCriteria() {
        //
        this.page = DEFAULT_PAGE_NUM;
        this.itemLimitOfPage = DEFAULT_ITEM_LIMIT_OF_PAGE;
    }

    /**
     * ?��?���? 번호?? ?��?���??�� ?��?��?�� ?��?��?��?���? 초기?��?��?�� ?��?��?��.
     *
     * @param page
     *            ?��?���? 번호
     * @param limit
     *            ?��?���??�� ?��?��?�� ?��?��?�� �??��
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
     * @param defaultItemLimitOfPage ?�� ?��?���??�� ?��?��?�� ?��
     * @param serializedCriteria
     */
    protected void init(int defaultItemLimitOfPage, String serializedCriteria) {
        parseSerializedCriteria(defaultItemLimitOfPage, serializedCriteria);

        try {
            // deserialize();
        } catch (Exception e) {
            // serialize ?��?�� ...
            // Creteria ?��?��?�� ?���? & serializedCriteria�? ?��?��?�� ?��리해?�� ?��?��줘야 ?��.
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    //--------------------------------------------------------------------------

    /**
     * @param page
     *            ?��?��?�� ?��?���?
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @param limit
     *            ?��?��?�� ?�� ?��?���??�� ?��?��?�� ?��
     */
    public void setItemLimitOfPage(int itemLimitOfPage) {
        this.itemLimitOfPage = itemLimitOfPage;
    }

    /**
     * ?���? ?��?��?��?�� ?��?��
     *
     * @param totalItemCount
     *            ?���? ?��?��?��?��
     */
    public void setTotalItemCount(int totalItemCount) {
        if (totalItemCount == 0) {
            return;
        }
        this.totalItemCount = totalItemCount;
        this.totalPageCount = (totalItemCount / this.itemLimitOfPage) + (totalItemCount % this.itemLimitOfPage > 0 ? 1 : 0);
    }

    /**
     * 조건 추�?
     *
     * @param key
     *            조건 ?��
     * @param value
     *            조건 �?
     */
    private void addCondition(String key, String value) {
        //TODO:: �??��조건?�� 목록?���? ?��?��경우�? ?��?��경우 목록 처리 ?��?��?��.
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
                // �? 값의 조건?? 무시
                if (param.length <= 1) continue;
                try {
                    addCondition(param[0], URLDecoder.decode(param[1], UTF8.name()));
                } catch (UnsupportedEncodingException e) {
                    //무시
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
     * @return ?��?�� ?��?��?�� (JPA?��?�� query.setFirstResult?�� ?��?��?��)
     */
    public int getStart() {
        return ((this.page - 1) * this.itemLimitOfPage);
    }

    /**
     * @return ?��?��?��?��?��(mybatis oralce?�� : rownum)
     */
    public int getStartIndex() {
        return getStart() + 1;
    }

    /**
     * @return ?�� ?��?��?��(mybatis oracle?�� : rownum)
     */
    public int getEnd() {
        return this.page * this.itemLimitOfPage;
    }

    /**
     * @return page ?��?�� ?��?���?
     */
    public int getPage() {
        return page;
    }

    /**
     * @return itemLimitOfPage ?�� ?��?���??�� ?��?��?�� ?�� (query.setMaxResults?�� ?��?��로도 ?��?��?�� ?�� ?��?��)
     */
    public int getItemLimitOfPage() {
        return itemLimitOfPage;
    }

    /**
     * @return totalPageCount ?���? ?��?���? ?��
     */
    public int getTotalPageCount() {
        return totalPageCount;
    }

    /**
     * @return totalItemCount ?���? ?��?��?�� ?��
     */
    public int getTotalItemCount() {
        return totalItemCount;
    }

    /**
     * <PRE>
     * ?��?���? 처리?��?�� ?��비게?��?�� 바에 ?���? ?��?�� ?��?���? ?��
     *   << 1,2,3,4,5 ... >>
     * </PRE>
     * @return
     */
    public int getNavigateLimit() {
        return navigateLimit;
    }

    /**
     * <PRE>
     * ?��?���? 처리?��?�� ?��비게?��?�� 바에 ?���? ?��?�� ?��?���? ?��
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
