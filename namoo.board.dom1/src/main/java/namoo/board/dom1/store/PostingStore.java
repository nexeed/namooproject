/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.store;

import namoo.board.dom1.entity.Posting;
import namoo.dom.sharedtype.exception.EmptyResultException;
import namoo.dom.sharedtype.offset.OffsetList;

public interface PostingStore {
    //
    // Posting
    void create(Posting posting); 
    Posting retrieve(String usid) throws EmptyResultException; 
    OffsetList<Posting> retrieveByKey(String boardUsid, int offset, int limit);
    void update(Posting posting); 
    void delete(Posting posting); 
    void deleteByBoard(String boardUsid);

    int nextSequence(String boardUsid);
    void increaseReadCount(String usid);
    boolean exists(String usid);
}
