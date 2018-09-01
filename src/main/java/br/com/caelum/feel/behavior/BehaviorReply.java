package br.com.caelum.feel.behavior;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

@Entity
public class BehaviorReply implements TimelineMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String comment;
	private String name;
	@ManyToOne
	@NotNull
	private BehaviorFeedback root;
	@NotNull
	private LocalDateTime instant = LocalDateTime.now();
	
	/**
	 * @deprecated
	 */
	public BehaviorReply() {

	}

	public BehaviorReply(@NotBlank String comment, String name, @NotNull BehaviorFeedback root) {
		super();
		this.comment = comment;
		this.name = name;
		this.root = root;
	}
	
	public Optional<String> getName() {
		return StringUtils.hasLength(name) ? Optional.of(name) : Optional.empty();
	}
	
	@Override
	public String getComment() {
		return comment;
	}
	
	@Override
	public LocalDateTime getInstant() {
		return instant;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
}
