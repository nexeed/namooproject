/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.logic;

import java.util.List;

import namoo.board.dom1.entity.SocialBoard;
import namoo.board.dom1.lifecycle.BoardStoreLifecycler;
import namoo.board.dom1.service.SocialBoardService;
import namoo.board.dom1.shared.cdo.SocialBoardCdo;
import namoo.board.dom1.store.PostingStore;
import namoo.board.dom1.store.SocialBoardStore;
import namoo.dom.sharedtype.exception.EmptyResultException;
import namoo.dom.sharedtype.exception.NamooException;

public class SocialBoardServiceLogic implements SocialBoardService {
    //
    private SocialBoardStore boardStore;
    private PostingStore postingStore;

    public SocialBoardServiceLogic(BoardStoreLifecycler lifecycler) {
        //
        this.boardStore = lifecycler.createSocialBoardStore();
        this.postingStore = lifecycler.createPostingStore();
    }

    // --------------------------------------------------------------------------

    @Override
    public String registerSocialBoard(SocialBoardCdo boardCdo) {
        //
        if (boardStore.existsByName(boardCdo.getName())) {
            throw new NamooException("Already exists. --> " + boardCdo.getName());
        }

        SocialBoard board = boardCdo.createDomain();
        board.initUsid(boardStore.nextSequence());

        boardStore.create(board);
        return board.getUsid();
    }

    @Override
    public SocialBoard findSocialBoard(String usid) {
        //
        try {
            return boardStore.retrieve(usid);
            
        } catch (EmptyResultException e) {
            throw new NamooException("No such a board. --> " + usid);
        }
    }

    @Override
    public List<SocialBoard> findAllSocialBoards() {
        //
        return boardStore.retrieveAll();
    }

    @Override
    public void modifySocialBoard(String usid, String boardName) {
        //
        try {
            SocialBoard board = boardStore.retrieve(usid);
            board.setName(boardName);
            boardStore.update(board);
            
        } catch (EmptyResultException e) {
            throw new NamooException("No such a board. --> " + usid);
        }
    }

    @Override
    public void removeSocialBoard(String usid) {
        //
        try {
            SocialBoard board = boardStore.retrieve(usid);
            postingStore.deleteByBoard(usid);
            boardStore.delete(board);
        } catch (EmptyResultException e) {
            throw new NamooException("No such a board. --> " + usid);
        }
    }
}