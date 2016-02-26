package io.vertx.sample.hello;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.annotation.PostConstruct;

/**
 * @author rtm
 * @since 26.02.2016
 */
@SpringBootApplication
public class App {

  public static void main(String[] args) {
    System.setProperty("spring.config.name", "application");
    new SpringApplicationBuilder(App.class).web(false).run(args);
  }

  @PostConstruct
  public void deployVerticle() {
    Vertx.clusteredVertx(new VertxOptions(), vertxAsyncResult -> vertxAsyncResult.result().deployVerticle(new HelloVerticle()));
  }
}