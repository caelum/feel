package br.com.caelum.feel.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/*
 * Execução assincrona ainda não é uma super verdade quando lidamos com nossos frameworks do dia a dia. Muita coisa é thread bound,
 * exemplo Session do Hibernate, proxy dinâmico etc... Acho que o melhor é realmente fazer um request e deixar o fluxo natural
 */
/**
 * Executa request para endpoints dentro do próprio localhost
 * @author alberto
 *
 */
@Service
public class AsyncLocalEndpointExecutor {
	
	@Autowired
	private Environment env;

	@Async
	public void post(String uri,Object... variables) {
		//TODO a porta tem que pegar da configuração
		String endpoint = ServletUriComponentsBuilder.fromPath(uri)
				.host("localhost").port(Integer.parseInt(env.getProperty("server.port"))).scheme("http").build(variables).toString();
		
		new RestTemplate().postForLocation(endpoint, null);
	}
}
