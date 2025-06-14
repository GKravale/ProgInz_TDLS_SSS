package lv.venta.model;


import java.util.Collection;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Table(name = "Certificate")
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

	@Column(name = "Certificate_Type")
	@NotNull
	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅa-zēūīļķģšāžčņ ]+")
	private String certificateType;

	@ManyToOne
	@JoinColumn(name = "KId")
	@NotNull
	private Course course;

	@OneToMany(mappedBy = "certificate")
	@ToString.Exclude
	private Collection<Grade> grades;

	@ManyToOne
	@JoinColumn(name = "KDId")
	@NotNull
	private CourseParticipant participant;

	public Certificate(String certificateType, Course course, Grade grades, CourseParticipant participant) {
		setCertificateType(certificateType);
		setCourse(course);
		setParticipant(participant);

	}
}
