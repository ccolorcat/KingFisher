package cc.colorcat.kingfisher.sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import cc.colorcat.kingfisher.core.DownPack;
import cc.colorcat.kingfisher.core.KingFisher;
import cc.colorcat.kingfisher.parser.fastjson.FastjsonParserFactory;
import cc.colorcat.kingfisher.parser.gson.GsonParserFactory;
import cc.colorcat.netbird.DownloadListener;
import cc.colorcat.netbird.NetBird;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    static {
        NetBird netBird = new NetBird.Builder("https://www.baidu.com/")
                .addTailInterceptor(new JsonLoggingTailInterceptor())
                .enableGzip(true)
                .build();
        Gson gson = new GsonBuilder().create();
        new KingFisher.Builder()
                .client(netBird)
//                .addParserFactory(new ResultParserFactory(gson))
//                .addParserFactory(new JacksonParserFactory<>(new ObjectMapper()))
                .addParserFactory(new GsonParserFactory<>(gson))
//                .addParserFactory(new FastjsonParserFactory<>())
                .initialize();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testKingFisher() throws IOException {
        TestApi api = new TestApiService();
        System.out.println(api.listRepos("ccolorcat").execute());
//        System.out.println(api.listCourses(4, 30).execute());
    }

    @Test
    public void testDownload() throws IOException {
        TestApi api = new TestApiService();
        File savePath = new File(System.getProperty("user.home"), "weChat.apk");
        DownPack pack = DownPack.create(savePath, new DownloadListener() {
            @Override
            public void onChanged(long finished, long total, int percent) {
                System.out.printf("%d, %d, %d\n", finished, total, percent);
            }
        });
        System.out.println(api.downWeChat(pack).execute());
    }
}