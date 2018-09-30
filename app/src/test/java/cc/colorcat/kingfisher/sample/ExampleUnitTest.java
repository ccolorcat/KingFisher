package cc.colorcat.kingfisher.sample;

import org.junit.Test;

import java.io.IOException;

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
        GithubApi api = new GithubApiService();
        System.out.println(api.listRepos("ccolorcat").execute());
    }
}