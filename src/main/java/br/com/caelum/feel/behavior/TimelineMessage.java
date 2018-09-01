package br.com.caelum.feel.behavior;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TimelineMessage {

	String getComment();
	Optional<String> getName();
	LocalDateTime getInstant();
	Integer getId();
}
