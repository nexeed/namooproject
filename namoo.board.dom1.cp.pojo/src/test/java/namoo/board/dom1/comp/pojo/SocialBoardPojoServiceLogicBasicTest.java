package namoo.board.dom1.comp.pojo;

import namoo.board.dom1.comp.lifecycle.BoardPojoServiceLifecycler;
import namoo.board.dom1.da.lifecycler.BoardMemStoreLifecycler;
import namoo.board.dom1.da.mem.maprepo.PostingRepository;
import namoo.board.dom1.da.mem.maprepo.SocialBoardRepository;
import namoo.board.dom1.da.mem.maprepo.UsidSeqRepository;
import namoo.board.dom1.service.SocialBoardService;
import namoo.board.dom1.service.SocialBoardServiceBasicTest;

public class SocialBoardPojoServiceLogicBasicTest extends SocialBoardServiceBasicTest {

    @Override
    public void setUp() {
        // 
        super.setUp();
    }
    
    @Override
    public void tearDown() {
        //
        super.tearDown();
        
        PostingRepository.getInstance().clear();
        SocialBoardRepository.getInstance().clear();
        UsidSeqRepository.getInstance().clear();
    }

    @Override
    public SocialBoardService initSocialBoardService() {
        // 
        BoardMemStoreLifecycler boardStoreLifecycler = BoardMemStoreLifecycler.getInstance();
        return BoardPojoServiceLifecycler.getInstance(boardStoreLifecycler).createSocialBoardService();
    }

}
