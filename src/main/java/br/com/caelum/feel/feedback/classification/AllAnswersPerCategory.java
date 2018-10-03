package br.com.caelum.feel.feedback.classification;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.caelum.feel.feedback.cycles.domain.models.Cycle;
import br.com.caelum.feel.feedback.questions.domain.models.FeedbackAnswer;

public class AllAnswersPerCategory {

	Map<Cycle, Set<FeedbackAnswer>> cycles = new LinkedHashMap<>();

	public AllAnswersPerCategory(List<CategorizedInfo> infos) {

		infos.stream().forEach(info -> {
			if (!cycles.containsKey(info.getCycle())) {
				cycles.put(info.getCycle(), new LinkedHashSet<>());
			}

			cycles.get(info.getCycle()).add(info.getFeedbackAnswer());
		});

	}

	public Collection<Cycle> getCycles() {
		return cycles.keySet();
	}
	
	public Collection<FeedbackAnswer> find(Cycle cycle) {
		return cycles.get(cycle);
	}

}
