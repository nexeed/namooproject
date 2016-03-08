/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.entity;

import java.util.Date;

import namoo.board.dom1.util.namevalue.NameValue;
import namoo.board.dom1.util.namevalue.NameValueList;
import namoo.board.dom1.util.numeral.UsidGen;


public class Posting {
    //
	public static final String MODIFIABLE_TITLE = "title";
	public static final String MODIFIABLE_CONTENTS = "contents";

	// 1. generated
	private String usid;		// 001-0001
	
	// 2. mandatory
	private String title;
	private String writerEmail;
	private String contents;
	
	// 3. optional
	private String writerName;
	private Date registerDate;
	private String password;

	// 4. derived
	private int readCount;
	
	// 5. associated
	public String boardUsid; 
	
	public Posting() {
		// 6. default constructor, don't use directly !!
		this.readCount = 0; 
		this.registerDate = new Date(); 
	}

	public Posting(String title, String writerEmail, String contents) {
		// 7. base constructor
		this(); 
		this.title = title; 
		this.writerEmail = writerEmail; 
		this.contents = contents; 
	}

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		builder.append("usid: " + usid);
		builder.append(", title: " + title);
		builder.append(", writerEmail: " + writerEmail);
		builder.append(", writerName: " + writerName);
		builder.append(", readCount: " + readCount);
		builder.append(", registerDate: " + registerDate);
		builder.append(", contents: " + contents);
		
		return builder.toString();
	}

	public void setValues(NameValueList nameValues) {
		//
		for (NameValue nameValue : nameValues.getNameValues()) {
			//
			if (nameValue.equalsName(MODIFIABLE_TITLE)) {
				setTitle(nameValue.getValue());
			} else if (nameValue.equalsName(MODIFIABLE_CONTENTS)) {
				setContents(nameValue.getValue());
			}
		}
	}

	public void initUsid(String boardUsid, int postingSequence) {
		// 
		this.usid = UsidGen.getStr36(boardUsid, postingSequence, 4);
	}
	
	//--------------------------------------------------------------------------
	// getters and setters
	
	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriterEmail() {
		return writerEmail;
	}

	public void setWriterEmail(String writerEmail) {
		this.writerEmail = writerEmail;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getBoardUsid() {
        return boardUsid;
    }

    public void setBoardUsid(String boardUsid) {
        this.boardUsid = boardUsid;
    }
}