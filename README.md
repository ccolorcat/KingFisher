# KingFisher

用于网络请求，以 [NetBird](https://github.com/ccolorcat/NetBird) 为基础，使用方式与 [retrofit](https://github.com/square/retrofit) 类似。实现方式上 retrofit 使用代理，本项目利用注解编译期自动生成代码。支持上传、下载及相关的进度监听等。

## 1. 特性

* 支持同步和异步请求。
* 支持 Android 平台，相关回调均在主线程进行。
* 支持日志、自定义缓存等特性。
* 支持数据解析且可自定义，其中数据解析均在后台线程中执行。
* 支持文件上传、下载及进度的监听。
* 支持对请求和响应进行拦截。

## 2. 模块简介

| 模块名                  | 功能说明                     |
| ----------------------- | ---------------------------- |
| core                    | 核心模块，必须。             |
| annotation              | 提供注解，必须。             |
| processor               | 处理注解，并生成代码，必须。 |
| gson_parser_factory     | 提供 Gson 数据解析支持。     |
| jackson_parser_factory  | 提供 Jackson 数据解析支持。  |
| fastjson-parser-factory | 提供 Fastjson 数据解析支持。 |

## 3. 用法举例

（1）初始化

```java
NetBird netBird = new NetBird.Builder("https://api.github.com/")
    .addTailInterceptor(new JsonLoggingTailInterceptor())
    .enableGzip(true)
    .build();
// 简单初始化无需单独设置 client 但需设置 baseUrl，此处添加了自定义日志模块。
new KingFisher.Builder()
    .client(netBird)
    .addParserFactory(new GsonParserFactory<>(new Gson()))
    .initialize();
```

（2）编写接口

```java
@Api
public interface TestApi {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @BaseUrl("http://www.imooc.com/")
    @GET("api/teacher")
    Call<List<Course>> listCourses(@Param("type") int type, @Param("num") int num);

    @BaseUrl("https://dldir1.qq.com/weixin/android/weixin673android1360.apk")
    @GET
    Call<File> downloadWeChat(@Down DownPack pack);

    @GET
    Call<File> downloadWeChat(@Url String url, @Down File savePath);
}
```

（3）同步请求

```java
TestApi api = new TestApiService();
List<Repo> repos = api.listRepos("ccolorcat").execute();
System.out.println(repos);
```

（4）异步请求

```java
TestApi api = new TestApiService();
api.listRepos("ccolorcat").enqueue(new SimpleCallback<List<Repo>>() {
    @Override
    public void onSuccess(List<Repo> result) {
        System.out.println(result);
    }
});
```

## 4. 使用方法

(1) 在项目的 build.gradle 中配置仓库地址：

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

(2) 添加项目依赖：

java

```groovy
dependencies {
    implementation 'com.github.ccolorcat.NetBird:netbird:v4.3.4'
    implementation 'com.github.ccolorcat.KingFisher:core:v1.0.1'
    implementation 'com.github.ccolorcat.KingFisher:annotation:v1.0.1'
    annotationProcessor 'com.github.ccolorcat.KingFisher:processor:v1.0.1'
    implementation 'com.github.ccolorcat.KingFisher:gson_parser_factory:v1.0.1'
    implementation 'com.github.ccolorcat.KingFisher:jackson-parser_factory:v1.0.1'
    implementation 'com.github.ccolorcat.KingFisher:fastjson-parser_factory:v1.0.1'
}
```

kotlin

```groovy
apply plugin: 'kotlin-kapt'
...
dependencies {
    implementation 'com.github.ccolorcat.NetBird:netbird:v4.3.4'
    implementation 'com.github.ccolorcat.KingFisher:core:v1.0.1'
    implementation 'com.github.ccolorcat.KingFisher:annotation:v1.0.1'
    kapt 'com.github.ccolorcat.KingFisher:processor:v1.0.1'
    implementation 'com.github.ccolorcat.KingFisher:gson_parser_factory:v1.0.1'
    implementation 'com.github.ccolorcat.KingFisher:jackson-parser_factory:v1.0.1'
    implementation 'com.github.ccolorcat.KingFisher:fastjson-parser_factory:v1.0.1'
}
```

安卓平台，需额外添加支持：

```groovy
dependencies {
    implementation 'com.github.ccolorcat.NetBird:netbird-android-support:v4.3.4'
}
```

## 5. 版本历史

v1.0.1

> 更改注解的 Retention

v1.0.0

> 首次发布。
>