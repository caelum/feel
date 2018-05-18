package br.com.caelum.feel.feedback.cycles.domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;
import br.com.caelum.feel.infra.ApplicationContextHolder;

@Entity
public class Cycle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String name;
	@NotBlank
	@Lob
	private String welcomeText;
	@NotBlank
	@Lob
	private String closingText;
	
	@Autowired
	private transient Questions questionRepository;
	
	/**
	 * @deprecated frameworks only
	 */
	@Deprecated(since = "1.0.0")
	Cycle() {}

	public Cycle(String name, String welcomeText, String closingText) {
		this.name = name;
		this.welcomeText = welcomeText;
		this.closingText = closingText;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	
	public String getWelcomeText() {
		return welcomeText;
	}
	
	public String getClosingText() {
		return closingText;
	}

	public void update(Cycle updatedCycle) {
		this.name = updatedCycle.name;
		this.welcomeText = updatedCycle.welcomeText;
		this.closingText = updatedCycle.closingText;
	}

	public boolean isFirstQuestion(Question question) {
		ApplicationContextHolder.autorwire(this);		
		Question first = questionRepository.listQuestions(this.id,PageRequest.of(0, 1)).get(0);
		
		return first.equals(question);
	}
	
	
}
