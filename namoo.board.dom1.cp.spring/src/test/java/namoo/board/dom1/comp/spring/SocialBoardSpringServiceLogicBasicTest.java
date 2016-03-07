package namoo.board.dom1.comp.spring;

import javax.sql.DataSource;

import namoo.board.dom1.comp.spring.shared.SpringServiceTestHelper;
import namoo.board.dom1.service.SocialBoardService;
import namoo.board.dom1.service.SocialBoardServiceBasicTest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-test.xml")
public class SocialBoardSpringServiceLogicBasicTest extends SocialBoardServiceBasicTest {
    //
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
    public SocialBoardService initSocialBoardService() {
        //
        return socialBoardService;
    }
}
