/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hyunohkim@nextree.co.kr">Kim, HyunOh</a>
 * @since 2014. 6. 15.
 */

package namoo.board.dom1.web.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import namoo.board.dom1.entity.SocialBoard;
import namoo.board.dom1.service.SocialBoardService;
import namoo.board.dom1.shared.cdo.SocialBoardCdo;
import namoo.board.dom1.web.util.MessagePage;
import namoo.dom.sharedtype.exception.NamooException;
import namoo.dom.sharedtype.namevalue.NameValueList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class BoardController {
	//
	@Autowired
	private SocialBoardService socialBoardService;
	
	//--------------------------------------------------------------------------
	// 보드리스트
	@RequestMapping(value = "boards", method = RequestMethod.GET)
	public String main(HttpServletRequest req) throws NamooException{
		//
		List<SocialBoard> socialBoards = socialBoardService.findAllSocialBoards();
		req.setAttribute("boardList", socialBoards);
		
		return "/board/list";
	}
	
	//--------------------------------------------------------------------------
	// 보드개설
	@RequestMapping(value = "board/create", method = RequestMethod.GET)
	public String create(HttpServletRequest req) throws NamooException{
		List<SocialBoard> socialBoards = socialBoardService.findAllSocialBoards();
		
		req.setAttribute("boardList", socialBoards);
		
		return "board/create";
	}
	
	@RequestMapping(value = "board/create", method = RequestMethod.POST)
	public ModelAndView create(
			@RequestParam("boardName") String boardName, 
			@RequestParam("adminEmail") String adminEmail 
			) throws NamooException {
		// 
		socialBoardService.registerSocialBoard(new SocialBoardCdo(boardName,adminEmail));
		
		String message = "게시판 생성이 완료되었습니다.";
		String linkURL = "boards";

		return MessagePage.information(message, linkURL);
	}
	
	//--------------------------------------------------------------------------
	// 보드 수정
	@RequestMapping(value = "board/{boardUsid}", method = RequestMethod.GET)
	public String update(@PathVariable("boardUsid") String boardUsid,
			HttpServletRequest req) throws NamooException{
		//
		SocialBoard socialBoard = socialBoardService.findSocialBoard(boardUsid);
		List<SocialBoard> socialBoards = socialBoardService.findAllSocialBoards();
		
		req.setAttribute("boardList", socialBoards);
		req.setAttribute("socialBoard", socialBoard);
		
		return "board/update";
	}
	
	@RequestMapping(value = "board/{boardUsid}", method = RequestMethod.POST)
	public ModelAndView update(
			@PathVariable("boardUsid") String boardUsid, 
			@RequestParam("boardName") String boardName) throws NamooException {
		// 
		NameValueList nameValues = NameValueList.getInstance();			
		nameValues.add("name", boardName);
	
		socialBoardService.modifySocialBoard(boardUsid, boardName);
		
		String message = "게시판이 수정되었습니다.";
		String linkURL = "boards";

		return MessagePage.information(message, linkURL);
	}
	
	//--------------------------------------------------------------------------
	// 보드 삭제
	@RequestMapping(value = "board/{boardUsid}/delete")
	public ModelAndView delete(
			@PathVariable("boardUsid") String boardUsid) throws NamooException{
		//
		socialBoardService.removeSocialBoard(boardUsid);
		
		String message = "게시판이 삭제되었습니다.";
		String linkURL = "boards";
		return MessagePage.information(message, linkURL);
	}
}
