package br.com.caelum.feel.behavior;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class BehaviorFeedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@NotBlank
	@Column(columnDefinition = "text")
	private String comment;
	@NotBlank
	private String hash = UUID.randomUUID().toString();
	private LocalDateTime instant = LocalDateTime.now();

	/**
	 * @deprecated
	 */
	public BehaviorFeedback() {

	}

	public BehaviorFeedback(String name, @NotBlank String comment) {
		super();
		this.name = name;
		this.comment = comment;
	}

	public String getHash() {
		return hash;
	}

}
