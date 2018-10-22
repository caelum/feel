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

}
