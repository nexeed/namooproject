/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hyunohkim@nextree.co.kr">Kim, HyunOh</a>
 * @since 2014. 6. 15.
 */

package namoo.board.dom1.web.controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import namoo.board.dom1.entity.Posting;
import namoo.board.dom1.entity.SocialBoard;
import namoo.board.dom1.service.PostingService;
import namoo.board.dom1.service.SocialBoardService;
import namoo.board.dom1.shared.cdo.PostingCdo;
import namoo.board.dom1.web.util.MessagePage;
import namoo.dom.sharedtype.exception.NamooException;
import namoo.dom.sharedtype.namevalue.NameValueList;
import namoo.dom.sharedtype.offset.OffsetList;
import namoo.util.page.Page;
import namoo.util.page.PageCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PostingController {
	//
	@Autowired
	private SocialBoardService socialBoardService;
	
	@Autowired
	private PostingService postingService;
	
	//--------------------------------------------------------------------------
	// 게시판 메인
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String main(HttpServletRequest req) throws NamooException{
		//
		List<SocialBoard> socialBoards = socialBoardService.findAllSocialBoards();
		req.setAttribute("boardList", socialBoards);
		
		return "posting/main";
	}
	
	//--------------------------------------------------------------------------
	// 게시판 상세보기
	@RequestMapping(value = "board/{boardUsid}/posting/{postingUsid}", method = RequestMethod.GET)
	public String detail(@PathVariable("boardUsid") String boardUsid,
			@PathVariable("postingUsid") String postingUsid,
			HttpServletRequest req) throws NamooException{
		//
		Posting posting = postingService.findPosting(postingUsid);
		SocialBoard socialBoard = socialBoardService.findSocialBoard(boardUsid);
		List<SocialBoard> socialBoards = socialBoardService.findAllSocialBoards();
		
		req.setAttribute("posting", posting);
		req.setAttribute("boardUsid", boardUsid);
		req.setAttribute("boardList", socialBoards);
		req.setAttribute("socialBoard", socialBoard);
		
		return "posting/detail";
	}
	
	//--------------------------------------------------------------------------
	// 게시판 리스트
	@RequestMapping(value = "board/{boardUsid}/postings", method = RequestMethod.GET)
	public String list(@PathVariable("boardUsid") String boardUsid,
			@RequestParam("page") String page,
			HttpServletRequest req) throws NamooException{
		//
		SocialBoard socialBoard = socialBoardService.findSocialBoard(boardUsid);
		
		List<SocialBoard> socialBoards = socialBoardService.findAllSocialBoards();
		
        PageCriteria pageCriteria = new PageCriteria(Integer.parseInt(page), 3);

        OffsetList<Posting> offsetList = postingService.findPostingsByCondition(boardUsid, Integer.valueOf(page), 3);
        
        // Convert OffsetList to Page
        pageCriteria.setTotalItemCount(offsetList.getTotalCount());
        Page<Posting> postings = new Page<Posting>(offsetList, pageCriteria);
		
		req.setAttribute("boardUsid", boardUsid);
		req.setAttribute("boardList", socialBoards);
		req.setAttribute("socialBoard", socialBoard);
		req.setAttribute("postings", postings);
		
		return "posting/list";
	}
	
	//--------------------------------------------------------------------------
	// 포스트 생성
	@RequestMapping(value = "board/{boardUsid}/posting/create", method = RequestMethod.GET)
	public String create(@PathVariable("boardUsid") String boardUsid,
			HttpServletRequest req) throws NamooException{
		//
		List<SocialBoard> socialBoards = socialBoardService.findAllSocialBoards();
		
		req.setAttribute("boardUsid", boardUsid);
		req.setAttribute("boardList", socialBoards);
		
		return "posting/create";
	}
	@RequestMapping(value = "board/{boardUsid}/posting/create", method = RequestMethod.POST)
	public ModelAndView create(
			@PathVariable("boardUsid") String boardUsid,
			@RequestParam("title") String title,
			@RequestParam("contents") String contents,
			@RequestParam("writerEmail") String writerEmail,
			@RequestParam("writerName") String writerName,
			@RequestParam("password") String password,
			HttpServletRequest req) throws NamooException{
		//
		PostingCdo postingCdo = new PostingCdo(title, writerEmail, contents); 
		if (writerEmail != null) postingCdo.setWriterName(writerName);
		if (password != null) postingCdo.setPassword(password);
		
		postingService.registerPosting(boardUsid, postingCdo);
		
		String message = "작성한 글이 저장되었습니다.";
		String linkURL = "board/"+boardUsid+"/postings?page=1";
		
		return MessagePage.information(message, linkURL);
	}
	
	//--------------------------------------------------------------------------
	// 포스트 수정
	@RequestMapping(value = "board/{boardUsid}/posting/{postingUsid}/update", method = RequestMethod.GET)
	public String update(
			@PathVariable("boardUsid") String boardUsid, 
			@PathVariable("postingUsid") String postingUsid, 
			HttpServletRequest req) throws NamooException {
		//
		SocialBoard socialBoard = socialBoardService.findSocialBoard(boardUsid);
		Posting posting = postingService.findPosting(postingUsid);
		List<SocialBoard> socialBoards = socialBoardService.findAllSocialBoards();
		
		req.setAttribute("posting", posting);
		req.setAttribute("boardUsid", boardUsid);
		req.setAttribute("boardList", socialBoards);
		req.setAttribute("socialBoard", socialBoard);
		
		return "posting/update";
	}
	
	@RequestMapping(value = "board/{boardUsid}/posting/{postingUsid}", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam("title") String title,
			@RequestParam("contents") String contents, 
			@RequestParam("postingUsid") String postingUsid, 
			@RequestParam("boardUsid") String boardUsid) throws NamooException {
		// 
		NameValueList nameValues = NameValueList.getInstance();
		nameValues.add(Posting.MODIFIABLE_TITLE, title);
		nameValues.add(Posting.MODIFIABLE_CONTENTS, contents);
		
		postingService.modifyPosting(postingUsid, nameValues);
		
		String message = "게시판이 수정되었습니다.";
		String linkURL = "board/"+boardUsid+"/posting/"+ postingUsid;

		return MessagePage.information(message, linkURL);
	}
	
	//--------------------------------------------------------------------------
	//비밀번호 체크
	@RequestMapping(value = "board/{boardUsid}/posting/{postingUsid}/checkPassword", method = RequestMethod.GET)
	public String checkPassword(
			@PathVariable("boardUsid") String boardUsid,
			@PathVariable("postingUsid") String postingUsid,
			HttpServletRequest req) throws NamooException{
		
		String checked = req.getParameter("checked");
		
		req.setAttribute("boardUsid", boardUsid);
		req.setAttribute("postingUsid", postingUsid);
		req.setAttribute("checked", checked);
		
		return "posting/check_password";
	}
	
	//--------------------------------------------------------------------------
	//비밀번호 체크 후 알림(수정)
	@RequestMapping(value = "board/{boardUsid}/posting/{postingUsid}/update/checkPassword", method = RequestMethod.POST)
	public ModelAndView chekedPasswordAfterUpdate(
			@PathVariable("boardUsid") String boardUsid,
			@PathVariable("postingUsid") String postingUsid,
			@RequestParam("password") String password,
			HttpServletRequest req) throws NamooException{
		
		boolean valid = postingService.isValidPassword(postingUsid, password);
		
		if(valid){
			String message = "비밀번호가 확인되었습니다.";
			String linkURL = "board/" + boardUsid + "/posting/" + postingUsid + "/update";
			
			return MessagePage.information(message, linkURL);
		}
		
		String message = "비밀번호가 틀렸습니다.";
		String linkURL = "board/" + boardUsid + "/posting/" + postingUsid;
		
		return MessagePage.error(message, linkURL);
	}
	
	//--------------------------------------------------------------------------
	//비밀번호 체크 후 알림(삭제)
	@RequestMapping(value = "board/{boardUsid}/posting/{postingUsid}/delete", method = RequestMethod.POST)
	public ModelAndView chekedPasswordAfterDelete(
			@PathVariable("boardUsid") String boardUsid,
			@PathVariable("postingUsid") String postingUsid,
			@RequestParam("password") String password,
			HttpServletRequest req) throws NamooException{
			
		boolean valid = postingService.isValidPassword(postingUsid, password);
		
		if(valid){
			postingService.removePosting(postingUsid);
			
			String message = "게시판이 삭제되었습니다.";
			String linkURL = "board/"+boardUsid + "/postings?page=1";
			return MessagePage.information(message, linkURL);
		}
			
		String message = "비밀번호가 틀렸습니다.";
		String linkURL = "board/" + boardUsid + "/posting/" + postingUsid;
			
		return MessagePage.error(message, linkURL);
	}
}
