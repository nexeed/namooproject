/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.service;

import namoo.board.dom1.entity.Posting;
import namoo.board.dom1.shared.cdo.PostingCdo;
import namoo.dom.sharedtype.namevalue.NameValueList;
import namoo.dom.sharedtype.offset.OffsetList;

public interface PostingService {
    //
    // Posting
    String registerPosting(String boardUsid, PostingCdo postingCdo);
    Posting findPosting(String usid);
    OffsetList<Posting> findPostingsByCondition(String boardUsid, int offset, int limit); 
    void modifyPosting(String usid, NameValueList nameValues);
    void removePosting(String usid);
    
    boolean isValidPassword(String postingUsid, String postingPassword);
}