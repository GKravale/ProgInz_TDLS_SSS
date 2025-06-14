package lv.venta.model;

import java.util.Collection;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.model.enums.CourseResult;

@Entity
@Table(name = "Certificate_Table")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SId")
	@Setter(AccessLevel.NONE)
	private int sId;

//	@Column(name = "CertificateType")
//	@NotNull
//	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅa-zēūīļķģšāžčņ ]+")
//	private String certificateType;
	
	@Column(name = "Certificate_Type")
	@NotNull
	@Enumerated(EnumType.STRING)
	private CourseResult result;

	@ManyToOne
	@JoinColumn(name = "KId")
	@NotNull
	private Course course;

	@ManyToOne
	@JoinColumn(name = "KDId")
	@NotNull
	private CourseParticipant participant;

	@OneToMany(mappedBy = "certificate")
	@ToString.Exclude
	private Collection<Grade> grades;

	public Certificate(CourseResult result, Course course, Grade grades, CourseParticipant participant) {
		setResult(result);
		setCourse(course);
		setParticipant(participant);
	}

}
