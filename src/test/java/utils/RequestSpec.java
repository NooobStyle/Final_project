package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static config.ConfigUrl.URL;

public class RequestSpec {
    private static final RequestSpecification spec;

    static {
        spec = new RequestSpecBuilder()
                .setBaseUri(URL)
                .build();
    }

    public static RequestSpecification getSpec() {
        return spec;
    }
}
