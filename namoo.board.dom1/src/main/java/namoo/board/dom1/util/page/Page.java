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

    /** �??��결과 */
    private List<T> results;

    /** ?��?���? 조건 */
    private PageCriteria criteria;

    //--------------------------------------------------------------------------
    
    /**
     * ?��?��?��
     *
     * @param results
     *            �??��결과
     * @param criteria
     *            ?��?���? 조건
     */
    public Page(List<T> results, PageCriteria criteria) {
        this.results = results;
        this.criteria = criteria;
    }

    //--------------------------------------------------------------------------
    
    /**
     * @return ?��?�� ?��?���? 번호
     */
    public int getPage() {
        return this.criteria.getPage();
    }

    /**
     * @return ?�� ?��?���??�� ?��?��?�� ?��
     */
    public int getItemLimitOfPage() {
        return this.criteria.getItemLimitOfPage();
    }

    /**
     * @return ?���? ?��?���? ?��
     */
    public int getTotalPageCount() {
        return this.criteria.getTotalPageCount();
    }

    /**
     * ?���? ?��?��?�� ?��
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
     * ?��?�� ?��비게?��?��?�� ?��?���? 목록 처음 ?��?��?��?�� 번호 계산
     * UI ?��?�� ?��?��?��?�� 메소?��
     * @return
     */
    public int getNavigateStartPage() {
        return ((getPage() - 1) / this.criteria.getNavigateLimit()) * this.criteria.getNavigateLimit() + 1;
    }

    /**
     * ?��?�� ?��?���??��?�� ?��?�� ?��비게?��?��?�� ?��?���? ?��?�� 계산
     * UI ?��?�� ?��?��?��?�� 메소?��
     * @return ?��?�� ?��비게?��?��?�� ?��?���? ?��?�� 계산
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
