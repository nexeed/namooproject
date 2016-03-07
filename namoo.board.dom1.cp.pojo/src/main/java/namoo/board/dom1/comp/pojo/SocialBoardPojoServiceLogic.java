/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.comp.pojo;

import java.util.List;

import namoo.board.dom1.entity.SocialBoard;
import namoo.board.dom1.lifecycle.BoardStoreLifecycler;
import namoo.board.dom1.logic.SocialBoardServiceLogic;
import namoo.board.dom1.service.SocialBoardService;
import namoo.board.dom1.shared.cdo.SocialBoardCdo;

public class SocialBoardPojoServiceLogic implements SocialBoardService {
	//
	private SocialBoardService boardService;

	public SocialBoardPojoServiceLogic(BoardStoreLifecycler boardStoreLifecycler) {
		//
		this.boardService = new SocialBoardServiceLogic(boardStoreLifecycler);
	}

	@Override
	public String registerSocialBoard(SocialBoardCdo boardCdo) {
		//
		return boardService.registerSocialBoard(boardCdo);
	}

	@Override
	public SocialBoard findSocialBoard(String boardUsid) {
		//
		return boardService.findSocialBoard(boardUsid);
	}

	@Override
	public List<SocialBoard> findAllSocialBoards() {
		//
		return boardService.findAllSocialBoards();
	}

	@Override
	public void modifySocialBoard(String boardUsid, String boardName) {
		//
		boardService.modifySocialBoard(boardUsid, boardName);
	}

	@Override
	public void removeSocialBoard(String boardUsid) {
		//
		boardService.removeSocialBoard(boardUsid);
	}

}
