package br.com.caelum.feel.infra;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class TransactionalRunner {

	@Transactional
	public void run(Runnable runnable) {
		runnable.run();
	}
}
