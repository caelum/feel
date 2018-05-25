package br.com.caelum.feel.infra;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	
	@Async
	public void post(String path,Object... variables) {
		String endpoint = UrlBuilder.buildForLocalhost(path, variables);		
		new RestTemplate().postForLocation(endpoint, null);
	}
}
