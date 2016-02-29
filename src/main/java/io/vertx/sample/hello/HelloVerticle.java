package io.vertx.sample.hello;

import io.vertx.core.AbstractVerticle;

import java.lang.management.ManagementFactory;
import java.util.Date;

public class HelloVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    String name = ManagementFactory.getRuntimeMXBean().getName();
    vertx.setPeriodic(1000, id -> {
      vertx.eventBus().publish("ping", "ping from " + name + " " + new Date());
    });
    vertx.eventBus().consumer("ping", message -> {
      System.out.println(message.body());
    });
  }
}