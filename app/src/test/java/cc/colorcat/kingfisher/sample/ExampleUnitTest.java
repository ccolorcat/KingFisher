package cc.colorcat.kingfisher.sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.IOException;

import cc.colorcat.kingfisher.core.KingFisher;
import cc.colorcat.kingfisher.core.StringParserFactory;
import cc.colorcat.kingfisher.parser.gson.GsonParserFactory;
import cc.colorcat.netbird.NetBird;

import static org.junit.Assert.assertEquals;

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
        NetBird netBird = new NetBird.Builder("https://www.baidu.com/")
                .addTailInterceptor(new JsonLoggingTailInterceptor())
                .enableGzip(true)
                .build();
        Gson gson = new GsonBuilder().create();
        new KingFisher.Builder()
                .client(netBird)
                .addParserFactory(new StringParserFactory())
//                .addParserFactory(new ResultParserFactory(gson))
                .addParserFactory(new GsonParserFactory(gson))
                .initialize();
        TestApi api = new TestApiService();
        System.out.println(api.listRepos("ccolorcat").execute());
//        System.out.println(api.listCourses(4, 30).execute());
    }
}