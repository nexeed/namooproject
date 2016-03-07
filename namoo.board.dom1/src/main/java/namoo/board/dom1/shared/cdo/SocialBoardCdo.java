/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.shared.cdo;

import java.io.Serializable;

import namoo.board.dom1.entity.SocialBoard;

public class SocialBoardCdo implements Serializable {
    //
    private static final long serialVersionUID = 1437618687111246101L;
    
	private String name;
	private String adminEmail;

	//--------------------------------------------------------------------------
	// constructors
	
	public SocialBoardCdo() {
	    // default constructor, don't use directly !!
	}

	public SocialBoardCdo(String name, String adminEmail) {
		//
		this.name = name;
		this.adminEmail = adminEmail;
	}

	//--------------------------------------------------------------------------
	// public methods
	
	public SocialBoard createDomain() {
		//
		SocialBoard board = new SocialBoard(name, adminEmail);

		return board;
	}
	
	//--------------------------------------------------------------------------
	// getters and setters

	public String getName() {
		return name;
	}

	public String getAdminEmail() {
		return adminEmail;
	}
}
