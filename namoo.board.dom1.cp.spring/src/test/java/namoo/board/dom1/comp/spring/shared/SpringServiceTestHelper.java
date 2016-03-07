/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hyunohkim@nextree.co.kr">Kim, Hyunoh</a>
 * @since 2015. 3. 14.
 */

package namoo.board.dom1.comp.spring.shared;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.RunScript;

public final class SpringServiceTestHelper {
    //
    public static void clearData(DataSource dataSource) {
        //
        try {
            Connection connnnection = dataSource.getConnection();
            InputStream inputStream = SpringServiceTestHelper.class.getResourceAsStream("/initData.sql");
            Reader reader = new InputStreamReader(inputStream);
            RunScript.execute(connnnection, reader);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("error to clear data");
        }
    }
}
