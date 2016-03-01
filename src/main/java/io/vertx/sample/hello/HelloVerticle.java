package io.vertx.sample.hello;

import io.vertx.core.AbstractVerticle;

import java.util.Date;
import java.util.UUID;

public class HelloVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    String name = UUID.randomUUID().toString();
    vertx.setPeriodic(1000, id -> {
      vertx.eventBus().publish("ping", "ping from " + name + " " + new Date());
    });
    vertx.eventBus().consumer("ping", message -> {
      System.out.println(message.body());
    });
  }
}