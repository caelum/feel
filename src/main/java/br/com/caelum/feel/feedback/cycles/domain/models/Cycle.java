package br.com.caelum.feel.feedback.cycles.domain.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

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
		List<Question> questions = questionRepository.findAllQuestionsByCycleIdOrderedByDateAsc(this.id,PageRequest.of(0, 1));		
		Assert.state(!questions.isEmpty(),"Deveria ter pelo menos uma questao associada ao ciclo");
		
		
		return questions.get(0).equals(question);
	}
	
	
}
