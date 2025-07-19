package com.br.camelProject.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class RouteIntegracaoOLX extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:integracao-trasnportadora-olx")
                .routeId("integracao-arquivo-transportadora-olx")
                .throttle(1).timePeriodMillis(5000).asyncDelayed()
                    .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                    .setHeader(Exchange.HTTP_URI, constant("{{urlApiTransportadoraOLX}}"))
                    .setHeader(Exchange.HTTP_PATH, constant("{{nfes}}"))
                    .setHeader(Exchange.CONTENT_TYPE, constant("{aplication/xml}"))
                    .to("http:servidorTransportadora")
                .end();

    }
}
