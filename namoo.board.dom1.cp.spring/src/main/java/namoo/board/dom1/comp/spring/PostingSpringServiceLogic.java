/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 25.
 */

package namoo.board.dom1.comp.spring;

import namoo.board.dom1.entity.Posting;
import namoo.board.dom1.lifecycle.BoardStoreLifecycler;
import namoo.board.dom1.logic.PostingServiceLogic;
import namoo.board.dom1.service.PostingService;
import namoo.board.dom1.shared.cdo.PostingCdo;
import namoo.dom.sharedtype.namevalue.NameValueList;
import namoo.dom.sharedtype.offset.OffsetList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostingSpringServiceLogic implements PostingService {
    //
    private PostingService service;

    @Autowired
    public PostingSpringServiceLogic(BoardStoreLifecycler storeLifecycler) {
        //
        this.service = new PostingServiceLogic(storeLifecycler);
    }

    @Override
    public String registerPosting(String boardUsid, PostingCdo postingCdo) {
        // 
        return service.registerPosting(boardUsid, postingCdo);
    }

    @Override
    public Posting findPosting(String postingUsid) {
        // 
        return service.findPosting(postingUsid);
    }

    @Override
    public OffsetList<Posting> findPostingsByCondition(String boardUsid, int offset, int limit) {
        // 
        return service.findPostingsByCondition(boardUsid, offset, limit);
    }

    @Override
    public void modifyPosting(String postingUsid, NameValueList nameValues) {
        // 
        service.modifyPosting(postingUsid, nameValues);
    }

    @Override
    public void removePosting(String postingUsid) {
        // 
        service.removePosting(postingUsid);
    }

    @Override
    public boolean isValidPassword(String postingUsid, String postingPassword) {
        // 
        return service.isValidPassword(postingUsid, postingPassword);
    }

}
