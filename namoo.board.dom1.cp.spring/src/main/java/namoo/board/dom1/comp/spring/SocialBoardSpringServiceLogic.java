/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 25.
 */

package namoo.board.dom1.comp.spring;

import java.util.List;

import namoo.board.dom1.entity.SocialBoard;
import namoo.board.dom1.lifecycle.BoardStoreLifecycler;
import namoo.board.dom1.logic.SocialBoardServiceLogic;
import namoo.board.dom1.service.SocialBoardService;
import namoo.board.dom1.shared.cdo.SocialBoardCdo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialBoardSpringServiceLogic implements SocialBoardService {
	//
	private SocialBoardService service;

	@Autowired
	public SocialBoardSpringServiceLogic(BoardStoreLifecycler storeLifecycler) {
		//
		this.service = new SocialBoardServiceLogic(storeLifecycler);
	}

	@Override
	public String registerSocialBoard(SocialBoardCdo boardCdo) {
		//
		return service.registerSocialBoard(boardCdo);
	}

	@Override
	public SocialBoard findSocialBoard(String boardUsid) {
		//
		return service.findSocialBoard(boardUsid);
	}

	@Override
	public List<SocialBoard> findAllSocialBoards() {
		//
		return service.findAllSocialBoards();
	}

	@Override
	public void modifySocialBoard(String boardUsid, String boardName) {
		//
		service.modifySocialBoard(boardUsid, boardName);
	}

	@Override
	public void removeSocialBoard(String boardUsid) {
		//
		service.removeSocialBoard(boardUsid);
	}
}
