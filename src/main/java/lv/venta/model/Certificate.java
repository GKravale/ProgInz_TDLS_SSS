package lv.venta.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
	@Column(name = "CRT_ID")
	@Setter(AccessLevel.NONE)
	private int crtId;

//	@Column(name = "CertificateType")
//	@NotNull
//	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅa-zēūīļķģšāžčņ ]+")
//	private String certificateType;

	@Column(name = "Certificate_Type")
	@NotNull
	@Enumerated(EnumType.STRING)
	private CourseResult result;

	@ManyToOne
	@JoinColumn(name = "C_ID")
	@NotNull
	private Course course;

	@ManyToOne
	@JoinColumn(name = "CP_ID")
	@NotNull
	private CourseParticipant participant;

	@OneToOne(mappedBy = "certificate", cascade = CascadeType.ALL)
	@ToString.Exclude
	private Grade grade;

	@Column(name = "Is_Signed")
	private boolean isSigned = false;

	@Column(name = "Is_Sent")
	private boolean isSent = false;

	@ManyToOne
	@JoinColumn(name = "CD_ID")
	@NotNull
	private CourseDate courseDate;

	@ManyToOne
	@JoinColumn(name = "T_ID")
	@NotNull
	private Template template;

	public Certificate(CourseResult result, Course course, Grade grades, CourseParticipant participant,
			Template template) {
		setResult(result);
		setCourse(course);
		setParticipant(participant);
		setTemplate(template);
	}
}
