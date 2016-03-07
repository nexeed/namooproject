/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.logic;

import namoo.board.dom1.entity.Posting;
import namoo.board.dom1.lifecycle.BoardStoreLifecycler;
import namoo.board.dom1.service.PostingService;
import namoo.board.dom1.shared.cdo.PostingCdo;
import namoo.board.dom1.store.PostingStore;
import namoo.board.dom1.store.SocialBoardStore;
import namoo.dom.sharedtype.exception.EmptyResultException;
import namoo.dom.sharedtype.exception.NamooException;
import namoo.dom.sharedtype.namevalue.NameValueList;
import namoo.dom.sharedtype.offset.OffsetList;

public class PostingServiceLogic implements PostingService {
    //
    private PostingStore postingStore;
    private SocialBoardStore boardStore;
    
    public PostingServiceLogic(BoardStoreLifecycler lifecycler) {
        // 
        this.postingStore = lifecycler.createPostingStore();
        boardStore = lifecycler.createSocialBoardStore();
    }
    
    //--------------------------------------------------------------------------

    @Override
    public String registerPosting(String boardUsid, PostingCdo postingCdo) {
        //
        if (!boardStore.exists(boardUsid)) {
            throw new NamooException("No such a board. --> " + boardUsid);
        }
    
        Posting posting = postingCdo.createDomain();  
        int postingSequence = postingStore.nextSequence(boardUsid);
        posting.initUsid(boardUsid, postingSequence); 
        posting.setBoardUsid(boardUsid);
        
        postingStore.create(posting); 
        
        return posting.getUsid();
    }
    
    @Override
    public Posting findPosting(String usid) {
        // 
        try {
            Posting posting = postingStore.retrieve(usid);
            postingStore.increaseReadCount(usid);
            
            return posting; 
            
        } catch (EmptyResultException e) {
            throw new NamooException("No such a posting. --> " + usid);
        }
    }

    @Override
    public OffsetList<Posting> findPostingsByCondition(String boardUsid, int offset, int limit) {
        // 
        return postingStore.retrieveByKey(boardUsid, offset, limit);
    }

    @Override
    public void modifyPosting(String usid, NameValueList nameValues) {
        // 
        try {
            Posting posting = postingStore.retrieve(usid);
            posting.setValues(nameValues);
            postingStore.update(posting);
            
        } catch(EmptyResultException e) {
            throw new NamooException("No such a posting. --> " + usid);
        }
    }

    @Override
    public void removePosting(String usid) {
        //
        try {
            Posting posting = postingStore.retrieve(usid);
            postingStore.delete(posting);
            
        } catch (EmptyResultException e) {
            throw new NamooException("No such a posting. --> " + usid);
        } 
    }
    
    @Override
    public boolean isValidPassword(String postingUsid, String postingPassword) {
        // 
        try {
            Posting posting = postingStore.retrieve(postingUsid);
            if (posting.getPassword().equals(postingPassword)) {
                return true;
            }
        } catch (EmptyResultException e) {
            // Nothing to do
        }
        
        return false;
    }
}
