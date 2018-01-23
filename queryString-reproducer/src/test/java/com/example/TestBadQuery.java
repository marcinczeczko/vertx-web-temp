package com.example;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.WebTestBase;
import java.net.URLEncoder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class TestBadQuery extends WebTestBase {

    @Rule
    public TestName name = new TestName();

    private String value = "~!@\\||$%^&*()_=-%22;;%27%22:%3C%3E/?]}{";

    @Test
    public void testQueryEncoded() throws Exception {
        router.route().handler(rc -> rc.response().end());
        System.out.println(URLEncoder.encode(value, "UTF-8"));
        testRequest(HttpMethod.GET, "/path?q=" + URLEncoder.encode(value, "UTF-8"), 200,
            "OK");
    }

    @Test
    public void testQueryAsIs() throws Exception {
        router.route().handler(rc -> rc.response().end());
        testRequest(HttpMethod.GET, "/path?q=" + value, 200,
            "OK");
    }
}
