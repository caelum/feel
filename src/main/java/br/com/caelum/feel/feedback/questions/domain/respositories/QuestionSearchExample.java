package br.com.caelum.feel.feedback.questions.domain.respositories;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import static org.springframework.util.ReflectionUtils.*;

import java.lang.reflect.Field;

import br.com.caelum.feel.feedback.cycles.domain.models.Cycle;
import br.com.caelum.feel.feedback.questions.domain.models.CategoryType;
import br.com.caelum.feel.feedback.questions.domain.models.Question;
import br.com.caelum.feel.feedback.questions.domain.models.vo.Affirmation;

/**
 * Essa classe deve ser usada para buscar questões baseadas em filtros da classe {@link Question}. Não é bonita, mas acho que faz o trabalhod dela. Se tiver 
 * uma ideia melhor, você é bem vindo em mexer :). 
 * @author alberto
 *
 */
public class QuestionSearchExample {

	@SuppressWarnings("unused")
	private Cycle cycle;
	@SuppressWarnings("unused")
	private Affirmation affirmation;

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.affirmation = new Affirmation("ignored", "ignored", "ignored", categoryType);
	}
	
	public Example<Question> build() {		
		Field cycleField = findField(Question.class, "cycle");
		makeAccessible(cycleField);
		Field affirmationField = findField(Question.class, "affirmation");
		makeAccessible(affirmationField);
		
		Question probe = new Question();
		setField(cycleField, probe, cycle);
		setField(affirmationField, probe, affirmation);
		
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("affirmation.statement",
				"affirmation.descriptionOfLowerValue", "affirmation.descriptionOfHighestValue","lastOne");
		return Example.of(probe,matcher);
	}

}
