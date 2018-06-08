package br.com.caelum.feel.infra;

import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class TransactionalRunner {

	@Transactional
	public <T> T run(Supplier<T> supplier) {
		return supplier.get();
	}
}
