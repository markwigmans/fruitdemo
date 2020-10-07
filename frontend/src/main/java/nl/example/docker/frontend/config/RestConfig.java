package nl.example.docker.frontend.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class RestConfig {

    @Bean
    RestTemplate restTemplate() {
        // creat an HTTP/2 client
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectionPool(new ConnectionPool(50, 30, TimeUnit.SECONDS));
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(false);

        // embed the created okhttp client to a spring rest template
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory(builder.build()));
    }
}
