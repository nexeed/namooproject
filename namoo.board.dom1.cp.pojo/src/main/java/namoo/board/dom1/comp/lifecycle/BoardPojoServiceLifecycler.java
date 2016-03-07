/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.comp.lifecycle;

import namoo.board.dom1.comp.pojo.PostingPojoServiceLogic;
import namoo.board.dom1.comp.pojo.SocialBoardPojoServiceLogic;
import namoo.board.dom1.lifecycle.BoardServiceLifecycler;
import namoo.board.dom1.lifecycle.BoardStoreLifecycler;
import namoo.board.dom1.service.PostingService;
import namoo.board.dom1.service.SocialBoardService;

public class BoardPojoServiceLifecycler implements BoardServiceLifecycler {
	//
    private static BoardServiceLifecycler serviceLifecycler;
    private BoardStoreLifecycler boardStoreLifecycler; 
    
    private BoardPojoServiceLifecycler(BoardStoreLifecycler boardStoreLifecycler) {
        // 
        this.boardStoreLifecycler = boardStoreLifecycler; 
    }
    
    public static BoardServiceLifecycler getInstance(BoardStoreLifecycler boardStoreLifecycler) {
        //
        if(serviceLifecycler == null) {
            serviceLifecycler = new BoardPojoServiceLifecycler(boardStoreLifecycler);
        }
        
        return serviceLifecycler; 
    }

    @Override
    public SocialBoardService createSocialBoardService() {
        // 
        return new SocialBoardPojoServiceLogic(boardStoreLifecycler);
    }

    @Override
    public PostingService createPostingService() {
        //
        return new PostingPojoServiceLogic(boardStoreLifecycler);
    }
}
