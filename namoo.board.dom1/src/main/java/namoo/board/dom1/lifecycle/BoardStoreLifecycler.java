/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.lifecycle;

import namoo.board.dom1.store.PostingStore;
import namoo.board.dom1.store.SocialBoardStore;

public interface BoardStoreLifecycler { 
    //
    SocialBoardStore createSocialBoardStore(); 
    PostingStore createPostingStore(); 
}