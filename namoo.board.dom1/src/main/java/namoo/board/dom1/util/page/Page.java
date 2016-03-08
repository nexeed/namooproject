/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */

package namoo.board.dom1.util.page;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    //
    private static final long serialVersionUID = 5594559667804809774L;

    /** Í≤??ÉâÍ≤∞Í≥º */
    private List<T> results;

    /** ?éò?ù¥Ïß? Ï°∞Í±¥ */
    private PageCriteria criteria;

    //--------------------------------------------------------------------------
    
    /**
     * ?Éù?Ñ±?ûê
     *
     * @param results
     *            Í≤??ÉâÍ≤∞Í≥º
     * @param criteria
     *            ?éò?ù¥Ïß? Ï°∞Í±¥
     */
    public Page(List<T> results, PageCriteria criteria) {
        this.results = results;
        this.criteria = criteria;
    }

    //--------------------------------------------------------------------------
    
    /**
     * @return ?òÑ?û¨ ?éò?ù¥Ïß? Î≤àÌò∏
     */
    public int getPage() {
        return this.criteria.getPage();
    }

    /**
     * @return ?ïú ?éò?ù¥Ïß??ãπ ?ïÑ?ù¥?Öú ?àò
     */
    public int getItemLimitOfPage() {
        return this.criteria.getItemLimitOfPage();
    }

    /**
     * @return ?†ÑÏ≤? ?éò?ù¥Ïß? ?àò
     */
    public int getTotalPageCount() {
        return this.criteria.getTotalPageCount();
    }

    /**
     * ?†ÑÏ≤? ?ïÑ?ù¥?Öú ?àò
     * @return
     */
    public int getTotalItemCount() {
        return this.criteria.getTotalItemCount();
    }
    
    public boolean isEmptyResult() {
        if (this.results == null || this.results.isEmpty()) {
            return true;
        }
        return false;
    }
    
    /**
     * ?ïò?ã® ?Ñ§ÎπÑÍ≤å?ù¥?Öò?ùò ?éò?ù¥Ïß? Î™©Î°ù Ï≤òÏùå ?ãú?ûë?ïò?äî Î≤àÌò∏ Í≥ÑÏÇ∞
     * UI ?óê?Ñú ?Ç¨?ö©?ïò?äî Î©îÏÜå?ìú
     * @return
     */
    public int getNavigateStartPage() {
        return ((getPage() - 1) / this.criteria.getNavigateLimit()) * this.criteria.getNavigateLimit() + 1;
    }

    /**
     * ?òÑ?û¨ ?éò?ù¥Ïß??óê?Ñú ?ïò?ã® ?Ñ§ÎπÑÍ≤å?ù¥?Öò?ùò ?éò?ù¥Ïß? ?à´?ûê Í≥ÑÏÇ∞
     * UI ?óê?Ñú ?Ç¨?ö©?ïò?äî Î©îÏÜå?ìú
     * @return ?ïò?ã® ?Ñ§ÎπÑÍ≤å?ù¥?Öò?ùò ?éò?ù¥Ïß? ?à´?ûê Í≥ÑÏÇ∞
     */
    public int getNavigatePageCount() {
        return (getTotalPageCount() - getNavigateStartPage() + 1) > this.criteria.getNavigateLimit() ?
                this.criteria.getNavigateLimit() : (getTotalPageCount() - getNavigateStartPage() + 1);
    }

    public List<T> getResults() {
        return results;
    }

    public PageCriteria getCriteria() {
        return criteria;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
    
}
