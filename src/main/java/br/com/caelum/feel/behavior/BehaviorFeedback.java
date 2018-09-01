package br.com.caelum.feel.behavior;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.springframework.util.StringUtils;

@Entity
public class BehaviorFeedback implements TimelineMessage{

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
	@OneToMany(mappedBy="root")
	private List<BehaviorReply> messages = new ArrayList<>();

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

	public Integer getId() {
		return id;
	}

	public Optional<String> getName() {
		return StringUtils.hasLength(name) ? Optional.of(name) : Optional.empty();
	}

	public String getComment() {
		return comment;
	}
	
	public String partOfComment(int size) {
		if(size < this.comment.length()) {
			return comment.substring(0, size).concat("...");
		}
		
		return comment;
	}

	public LocalDateTime getInstant() {
		return instant;
	}
	
	public List<TimelineMessage> asList() {
		ArrayList<TimelineMessage> list = new ArrayList<>();
		list.add(this);
		list.addAll(messages);
		return list;
	}

	

}
