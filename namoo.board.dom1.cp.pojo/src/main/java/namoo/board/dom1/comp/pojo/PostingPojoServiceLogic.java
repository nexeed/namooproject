/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.comp.pojo;

import namoo.board.dom1.entity.Posting;
import namoo.board.dom1.lifecycle.BoardStoreLifecycler;
import namoo.board.dom1.logic.PostingServiceLogic;
import namoo.board.dom1.service.PostingService;
import namoo.board.dom1.shared.cdo.PostingCdo;
import namoo.dom.sharedtype.namevalue.NameValueList;
import namoo.dom.sharedtype.offset.OffsetList;

public class PostingPojoServiceLogic implements PostingService {
	//
	private PostingService postingService;

	public PostingPojoServiceLogic(BoardStoreLifecycler boardStoreLifecycler) {
		//
		this.postingService = new PostingServiceLogic(boardStoreLifecycler);
	}

	@Override
	public String registerPosting(String boardUsid, PostingCdo postingCdo) {
		//
		return postingService.registerPosting(boardUsid, postingCdo);
	}

	@Override
	public Posting findPosting(String postingUsid) {
		//
		return postingService.findPosting(postingUsid);
	}

	@Override
	public OffsetList<Posting> findPostingsByCondition(String boardUsid, int offset, int limit) {
		//
		return postingService.findPostingsByCondition(boardUsid, offset, limit);
	}

	@Override
	public void modifyPosting(String postingUsid, NameValueList nameValues) {
		//
		postingService.modifyPosting(postingUsid, nameValues);
	}

	@Override
	public void removePosting(String postingUsid) {
		//
		postingService.removePosting(postingUsid);
	}

	@Override
	public boolean isValidPassword(String postingUsid, String postingPassword) {
		//
		return postingService.isValidPassword(postingUsid, postingPassword);
	}

}
