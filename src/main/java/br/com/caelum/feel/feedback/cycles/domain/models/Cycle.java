package br.com.caelum.feel.feedback.cycles.domain.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.respositories.Questions;
import br.com.caelum.feel.infra.ApplicationContextHolder;

@Entity
@SQLDelete(sql="update cycle set deleted_instant = now() where id = ?")
@Where(clause="deleted_instant is null")
@NamedQuery(name="findCycleById",query="select c from Cycle c where c.id = ?1 and deletedInstant is null")
@Loader(namedQuery="findCycleById")
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
	
	private LocalDateTime deletedInstant;
	
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
		List<Question> questions = questionRepository.findByCycleIdOrderByDueDateAsc(this.id,PageRequest.of(0, 1));		
		Assert.state(!questions.isEmpty(),"Deveria ter pelo menos uma questao associada ao ciclo");
		
		
		return questions.get(0).equals(question);
	}
	
	
}
