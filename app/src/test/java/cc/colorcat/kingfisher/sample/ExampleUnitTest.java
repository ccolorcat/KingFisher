package cc.colorcat.kingfisher.sample;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cc.colorcat.kingfisher.core.DownPack;
import cc.colorcat.kingfisher.core.KingFisher;
import cc.colorcat.kingfisher.parser.gson.GsonParserFactory;
import cc.colorcat.netbird.DownloadListener;
import cc.colorcat.netbird.GenericPlatform;
import cc.colorcat.netbird.NetBird;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    static {
        NetBird netBird = new NetBird.Builder("https://api.github.com/")
                .platform(new GenericPlatform())
                .addTailInterceptor(new JsonLoggingTailInterceptor())
                .enableGzip(true)
                .build();
        new KingFisher.Builder()
                .client(netBird)
                .addParserFactory(new GsonParserFactory<>(new Gson()))
                .initialize();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testKingFisher() throws IOException {
        TestApi api = new TestApiService();
        List<Repo> repos = api.listRepos("ccolorcat").execute();
        System.out.println(repos);
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