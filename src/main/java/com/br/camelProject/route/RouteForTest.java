package com.br.camelProject.route;

import org.apache.camel.builder.RouteBuilder;

public class RouteForTest extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("direct:routeTestBegin")
      .routeId("nomeDaRotaHeheh")
      .log("esse é o retono da rota")
            .process(exchange -> {
              String body = exchange.getIn().getBody(String.class);
              System.out.println("rotinha");
            })
          .choice()
            .when(simple("Arquivo ${file:ext}"))
            .to("direct:rotaFinal")
          .otherwise()
            .to("direct:forForRoute")
    .end();
  }
}
