/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.shared.cdo;

import java.io.Serializable;

import namoo.board.dom1.entity.Posting;

public class PostingCdo implements Serializable {
    //
    private static final long serialVersionUID = 8595404513241495631L;
    
	// 1. mandatory
	private String title;
	private String writerEmail;
	private String contents;

	// 2. optional
	private String writerName;
	private String password;
	
	//--------------------------------------------------------------------------
	// constructors

	public PostingCdo() {
	    // default constructor, don't use directly !!
	}
	
	public PostingCdo(String title, String writerEmail, String contents) {
		// 3. base constructor 
		this.title = title; 
		this.writerEmail = writerEmail; 
		this.contents = contents; 
	}
	
	//--------------------------------------------------------------------------
	// public methods

	public Posting createDomain() {
		// 4. create domain
		Posting posting = new Posting(title, writerEmail, contents); 
		if (writerName != null) posting.setWriterName(writerName);
		if (password != null) posting.setPassword(password);
		
		return posting; 
	}
	
	//--------------------------------------------------------------------------
	// getters and setters
	
	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public String getWriterEmail() {
		return writerEmail;
	}

	public String getContents() {
		return contents;
	}

}
