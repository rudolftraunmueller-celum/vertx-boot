package io.vertx.sample.hello;

import io.vertx.core.AbstractVerticle;

import java.lang.management.ManagementFactory;
import java.util.Date;

public class HelloVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    String name = ManagementFactory.getRuntimeMXBean().getName();
    vertx.createHttpServer().requestHandler(request -> {
      request.response().end("Hello Java world !");
    }).listen(8080);
    vertx.setPeriodic(1000, id -> {
      vertx.eventBus().send("ping", "ping", reply -> {
        if (reply.succeeded()) {
          System.out.println("Reply: " + reply.result().body() + " " + new Date());
        }
      });
    });
    vertx.eventBus().consumer("ping", message -> {
      message.reply(message.body() + " from " + name);
    });
  }
}