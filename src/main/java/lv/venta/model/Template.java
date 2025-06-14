package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Template_Table")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Template {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TId")
	@Setter(AccessLevel.NONE)
	private int tId;

	@NotNull
	@Column(name = "Title")
	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅa-zēūīļķģšāžčņ ]+")
	private String title;

	@NotNull
	@Column(name = "Description")
	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅa-zēūīļķģšāžčņ ]+")
	private String description;

	@Lob
	@Column(name = "PDF_Data", columnDefinition = "BLOB")
	private byte[] pdfData;

	public Template(String title, String description, byte[] pdfData) {
		setTitle(title);
		setDescription(description);
		setPdfData(pdfData);
	}

}
