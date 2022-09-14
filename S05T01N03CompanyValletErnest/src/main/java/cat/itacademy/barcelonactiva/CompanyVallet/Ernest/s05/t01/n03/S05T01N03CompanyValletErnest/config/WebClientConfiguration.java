package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n03.S05T01N03CompanyValletErnest.config;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfiguration implements WebFluxConfigurer {

    private static final String BASE_URL = "http://localhost:9001/flor";

    @Bean
    public WebClient webClient() {

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .responseTimeout(Duration.ofSeconds(10));

        return WebClient.builder()
                .baseUrl(BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
