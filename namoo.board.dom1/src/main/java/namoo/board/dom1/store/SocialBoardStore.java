/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 2. 24.
 */

package namoo.board.dom1.store;

import java.util.List;

import namoo.board.dom1.entity.SocialBoard;
import namoo.dom.sharedtype.exception.EmptyResultException;

public interface SocialBoardStore {
    //
    // SocialBoard
    public String create(SocialBoard board); 
    public SocialBoard retrieve(String usid) throws EmptyResultException; 
    public SocialBoard retrieveByName(String name);
    public List<SocialBoard> retrieveAll(); 
    public void update(SocialBoard board); 
    public void delete(SocialBoard board); 
    
    public int nextSequence();
    public boolean exists(String usid);
    public boolean existsByName(String name);
}
