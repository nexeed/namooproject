/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.service;

import java.util.List;

import namoo.board.dom1.entity.SocialBoard;
import namoo.board.dom1.shared.cdo.SocialBoardCdo;

public interface SocialBoardService {
	//
    // SocialBoard
	String registerSocialBoard(SocialBoardCdo boardCdo);
	SocialBoard findSocialBoard(String usid);
	List<SocialBoard> findAllSocialBoards();
	void modifySocialBoard(String usid, String boardName);
	void removeSocialBoard(String usid);
}