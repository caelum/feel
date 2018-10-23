package br.com.caelum.feel.feedback.questions.domain.models;

//#todo a nota tem que deixar de ser nota e virar uma enum. Ela agora tem significado, as cores. 
public enum HealthColor {

	EXCELENT("#53f442"), VERY_GOOD("#83e299"), GOOD("#fff002"), WARNING("#ffa016"), DANGER(
			"#f73232");

	private String hexa;

	private HealthColor(String hexa) {
		this.hexa = hexa;
	}

	public String getHexa() {
		return hexa;
	}

	public static HealthColor bestGuessToAnoymousFeebackValue(double currentValue) {
		// #warning vou começar a implementaçao deixando esse número aqui. É a
		// melhor nota dentro do nosso feedback.
		double bestValue = 5;
		double distanceBetweenCurrentAndBest = bestValue - currentValue;

		if (distanceBetweenCurrentAndBest <= 0) {
			return EXCELENT;
		} else {
			if (distanceBetweenCurrentAndBest <= 1) {
				return VERY_GOOD;
			} else {
				if (distanceBetweenCurrentAndBest <= 2) {
					return GOOD;
				} else {
					if (distanceBetweenCurrentAndBest <= 3) {
						return WARNING;
					}

					return DANGER;
				}
			}
		}
	}

	public static HealthColor bestGuessToGlobalCountValuesPerQuestion(int count) {
		int bestValue = 80;
		int distanceBetweenCurrentAndBest = bestValue - count;
		
		if (distanceBetweenCurrentAndBest <= 10) {
			return EXCELENT;
		} else {
			if (distanceBetweenCurrentAndBest <= 20) {
				return VERY_GOOD;
			} else {
				if (distanceBetweenCurrentAndBest <= 30) {
					return GOOD;
				} else {
					if (distanceBetweenCurrentAndBest <= 40) {
						return WARNING;
					}

					return DANGER;
				}
			}
		}		
	}

	public static HealthColor bestGuessToCountValuesPerTeamPerQuestion(int value) {
		switch (value) {
		case 1:			
			return HealthColor.DANGER;
		case 2:			
			return HealthColor.WARNING;
		case 3:			
			return HealthColor.GOOD;
		case 4:			
			return HealthColor.VERY_GOOD;
		case 5:			
			return HealthColor.EXCELENT;
		default:
			throw new IllegalArgumentException("O número passado deve estar entre 1 e 5");
		}
	}

}
