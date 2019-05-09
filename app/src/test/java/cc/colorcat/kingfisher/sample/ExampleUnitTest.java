package cc.colorcat.kingfisher.sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cc.colorcat.kingfisher.core.Call;
import cc.colorcat.kingfisher.core.DownPack;
import cc.colorcat.kingfisher.core.KingFisher;
import cc.colorcat.kingfisher.parser.gson.GsonParserFactory;
import cc.colorcat.netbird.DownloadListener;
import cc.colorcat.netbird.GenericPlatform;
import cc.colorcat.netbird.NetBird;
import cc.colorcat.netbird.Parser;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final Gson GSON;
    private static final TestApi SERVICE;

    static {
        GSON = new GsonBuilder().create();

        NetBird defaultClient = new NetBird.Builder("http://www.imooc.com/")
                .platform(new GenericPlatform())
                .addTailInterceptor(new JsonLoggingTailInterceptor(true))
                .enableGzip(true)
                .build();
        NetBird gitHubClient = new NetBird.Builder("https://api.github.com/")
                .platform(new GenericPlatform())
                .addTailInterceptor(new JsonLoggingTailInterceptor())
                .enableGzip(true)
                .build();

        new KingFisher.Builder()
                .client(defaultClient)
                .registerClient("github", gitHubClient)
                .addParserFactory(new GsonParserFactory<>(GSON))
                .initialize();
        SERVICE = new TestApiService();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGitHub() throws IOException {
        List<Repo> repos = SERVICE.listRepos("ccolorcat").execute();
        System.out.println(repos);
    }

    @Test
    public void testMooc() throws IOException {
        Parser<List<Course>> parser = new ResultParser<List<Course>>(new Gson()) {};
        List<Course> result = SERVICE.listCourses(4, 30).parser(parser).execute();
        System.out.println(result);
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
        System.out.println(api.downloadWeChat(pack).execute());
    }
}