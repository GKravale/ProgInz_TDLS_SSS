package lv.venta.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "Grade_Table")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GId")
    @Setter(AccessLevel.NONE)
	private int gId;
	
	
	@Column(name = "Grade")
	@NotNull
	@Min(1)
	@Max(10)
	private int grade;
	
	@Column(name = "Date")
	@NotNull
	private LocalDate date;
	
	@ManyToOne
    @JoinColumn(name = "KDId")
    @NotNull
    private CourseDate courseDates;
	
	@ManyToOne
    @JoinColumn(name = "SId")
    @NotNull
    private Sertificate certificate;
	
	 @ManyToOne
	 @JoinColumn(name = "KDat_ID")
	 @NotNull
	 private CourseParticipant participant;
	
	 public Grade(int grade, LocalDate date, CourseDate courseDates, Sertificate certificate, CourseParticipant participant) {
		 setGrade(grade);
		 setDate(date);
		 setCourseDates(courseDates);
		 setCertificate(certificate);
		 setParticipant(participant);
	 }
}
