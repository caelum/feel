package br.com.caelum.feel.infra;

import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.LongType;

public class CustomDialect extends org.hibernate.dialect.MySQL5Dialect{

	public CustomDialect() {
		registerFunction("median", new StandardSQLFunction("median", LongType.INSTANCE));
	}
}
