package com.br.camelProject.route;

import org.apache.camel.builder.RouteBuilder;

public class RouteIntegracaoAWS extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:integracao-transportadora-aws")
                .routeId("integracao-arquivo-transportadora-aws")
                .to("file:{{diretorioSaida}}?fileName=${date:noew:HHmmss}_${file:name}");
    }
}
