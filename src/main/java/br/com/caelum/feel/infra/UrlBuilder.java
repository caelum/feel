package br.com.caelum.feel.infra;

import org.springframework.core.env.Environment;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UrlBuilder {

	public static String buildForLocalhost(String path,Object... variables) {
		Environment env = ApplicationContextHolder.getInstance().getBean(Environment.class);	
		
		return ServletUriComponentsBuilder.fromPath(path)
		.host("localhost").port(Integer.parseInt(env.getProperty("server.port"))).scheme("http").build(variables).toString();		
	}
}
