package com.alejandro.streamtest.functions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class StreamFunctions {

    final Duration delay = Duration.ofSeconds(2L);

    /*
     * producer-out-0
     */
    @Bean
    public Supplier<Flux<Long>> producer() {
        return () -> Flux.interval(delay).log();
    }

    /*
     * squadNumspring.cloud.stream.function.definition'-in-0
     * squadNum-out-0
     */
    @Bean
    public Function<Flux<Long>, Flux<Long>> squadNum() {
        return flx -> flx.map(num -> num * num);
    }

    /*
     * consumer-in-0
     */
    @Bean
    public Consumer<Long> consumer() {
        return num -> log.info("received {} : ", num);
    }


    /*@Bean
    public Function<String, String> capitalizeStream() {
        return input -> input.replace(" ", "-").toLowerCase().concat(".txt");
    }*/

}
