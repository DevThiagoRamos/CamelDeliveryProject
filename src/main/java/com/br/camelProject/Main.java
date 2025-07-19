package com.br.camelProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	private Main(){
	}
	public static void main(String[] args) throws Exception {
		org.apache.camel.main.Main main = new org.apache.camel.main.Main(Main.class);

		main.run(args);
	}

}
