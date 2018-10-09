package cc.colorcat.kingfisher.sample;

import org.junit.Test;

import java.io.IOException;

import cc.colorcat.kingfisher.core.KingFisher;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testKingFisher() throws IOException {
        new KingFisher.Builder().baseUrl("https://www.baidu.com/").initialize();
        TestApi api = new TestApiService();
//        System.out.println(api.listRepos("ccolorcat").execute());
        System.out.println(api.listCourses(4, 30).execute());
    }
}