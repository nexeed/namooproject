/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import namoo.board.dom1.util.namevalue.NameValue;
import namoo.board.dom1.util.namevalue.NameValueList;
import namoo.board.dom1.util.numeral.UsidGen;

public class SocialBoard {
    //
    public static final String MODIFIABLE_NAME = "name";
    public static final String MODIFIABLE_ADMINEMAIL = "adminEmail";

    // 1. generated
    private String usid; // "000"

    // 2. mandatory
    private String name;
    private String adminEmail;
    
    // 3. optional
    private Date createDate;

    // 4. derived
    // 5. associated
    private List<Posting> postings;
    
    //--------------------------------------------------------------------------
    // constructors

    public SocialBoard() {
        // 6. default constructor, don't use directly !!
        this.createDate = new Date();
        this.postings = new ArrayList<Posting>();
    }

    public SocialBoard(String name, String adminEmail) {
        // 7. base constructor 
        this();
        this.name = name;
        this.adminEmail = adminEmail;
    }
    
    //--------------------------------------------------------------------------
    // public methods

    @Override
    public String toString() {
        //
        StringBuilder builder = new StringBuilder();
        builder.append("usid: " + usid);
        builder.append(", name: " + name);
        builder.append(", adminEmail: " + adminEmail);
        builder.append(", createDate: " + createDate);

        return builder.toString();
    }

    public void setValues(NameValueList nameValues) {
        //
        for (NameValue nameValue : nameValues.getNameValues()) {
            //
            if (nameValue.equalsName(MODIFIABLE_NAME)) {
                setName(nameValue.getValue());
            } else if (nameValue.equalsName(MODIFIABLE_ADMINEMAIL)) {
                setAdminEmail(nameValue.getValue());
            }
        }
    }

    public void initUsid(int sequence) {
        // 
        this.usid = UsidGen.getStr36(sequence, 3); 
    }
    
    //--------------------------------------------------------------------------
    // getters and setters
    
    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Posting> getPostings() {
        return postings;
    }

    public void setPostings(List<Posting> postings) {
        this.postings = postings;
    }

    public void addPosting(Posting posting) {
        // 
        postings.add(posting); 
        posting.setBoardUsid(this.getUsid());
    }
    
    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}