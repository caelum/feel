package br.com.caelum.feel.feedback.classification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.util.StringUtils;

@Entity
public class CategoryInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String name;
	
	/**
	 * @deprecated
	 */
	public CategoryInfo() {

	}

	public CategoryInfo(@NotBlank String name) {
		super();
		this.name = StringUtils.trimWhitespace(name).replaceAll("\\s+","-");
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getId() {
		return id;
	}
}
