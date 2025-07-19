package com.br.camelProject.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.builder.Namespaces;

public class RouteIntegrationFile extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        var ns = new Namespaces("ns", "http://www.portalfiscal.inf.br/nfe");

        from("file:{{diretorioEntrada}}?delay=500")
                .routeId("integration-file")
                .process( exchange -> {
                    String body = exchange.getMessage().getBody(String.class);
                    System.out.println("Arquivo recebido: \n" + body);
                })
                .log("Processando Infos: ${file:name}")

                .setProperty("CNPJ", xpath("{{xpathCnpjTransportadora}}", ns))
                .choice()
                    .when(exchangeProperty("CNPJ").isEqualTo('1'))
                    .to("{direct:integracao-transportadora-aws}")
                    .when(exchangeProperty("CNPJ").isEqualTo("2"))
                    .to("{direct:integracao-trasnportadora-olx}")
                    .otherwise()
                    .log("Transportadora não encotrada.")
                .end();
    }
}
