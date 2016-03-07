package namoo.board.dom1.comp.spring;

import javax.sql.DataSource;

import namoo.board.dom1.comp.spring.shared.SpringServiceTestHelper;
import namoo.board.dom1.service.PostingService;
import namoo.board.dom1.service.PostingServiceBasicTest;
import namoo.board.dom1.service.SocialBoardService;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-test.xml")
public class PostingSpringServiceLogicBasicTest extends PostingServiceBasicTest {

    @Autowired
    private PostingService postingService;
    
    @Autowired
    private SocialBoardService socialBoardService;
    
    @Autowired
    private DataSource dataSource;
    
    @Override
    public void setUp() {
        super.setUp();
        SpringServiceTestHelper.clearData(dataSource);
    }
    
    @Override
    public PostingService initPostingService() {
        //
        return postingService;
    }

    @Override
    public SocialBoardService initSocialBoardService() {
        //
        return socialBoardService;
    }

}
