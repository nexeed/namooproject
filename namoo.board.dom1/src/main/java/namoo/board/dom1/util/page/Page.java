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

    /** κ²??κ²°κ³Ό */
    private List<T> results;

    /** ??΄μ§? μ‘°κ±΄ */
    private PageCriteria criteria;

    //--------------------------------------------------------------------------
    
    /**
     * ??±?
     *
     * @param results
     *            κ²??κ²°κ³Ό
     * @param criteria
     *            ??΄μ§? μ‘°κ±΄
     */
    public Page(List<T> results, PageCriteria criteria) {
        this.results = results;
        this.criteria = criteria;
    }

    //--------------------------------------------------------------------------
    
    /**
     * @return ??¬ ??΄μ§? λ²νΈ
     */
    public int getPage() {
        return this.criteria.getPage();
    }

    /**
     * @return ? ??΄μ§??Ή ??΄? ?
     */
    public int getItemLimitOfPage() {
        return this.criteria.getItemLimitOfPage();
    }

    /**
     * @return ? μ²? ??΄μ§? ?
     */
    public int getTotalPageCount() {
        return this.criteria.getTotalPageCount();
    }

    /**
     * ? μ²? ??΄? ?
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
     * ??¨ ?€λΉκ²?΄?? ??΄μ§? λͺ©λ‘ μ²μ ???? λ²νΈ κ³μ°
     * UI ?? ?¬?©?? λ©μ?
     * @return
     */
    public int getNavigateStartPage() {
        return ((getPage() - 1) / this.criteria.getNavigateLimit()) * this.criteria.getNavigateLimit() + 1;
    }

    /**
     * ??¬ ??΄μ§??? ??¨ ?€λΉκ²?΄?? ??΄μ§? ?«? κ³μ°
     * UI ?? ?¬?©?? λ©μ?
     * @return ??¨ ?€λΉκ²?΄?? ??΄μ§? ?«? κ³μ°
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
