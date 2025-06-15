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
    @Column(name = "G_ID")
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
    @JoinColumn(name = "CD_ID")
    @NotNull
    private CourseDate courseDate;
	
	@ManyToOne
    @JoinColumn(name = "CRT_ID")
    @NotNull
    private Certificate certificate;
	
	 @ManyToOne
	 @JoinColumn(name = "CD_ID")
	 @NotNull
	 private CourseParticipant participant;
	
	 public Grade(int grade, LocalDate date, CourseDate courseDate, Certificate certificate, CourseParticipant participant) {
		 setGrade(grade);
		 setDate(date);
		 setCourseDate(courseDate);
		 setCertificate(certificate);
		 setParticipant(participant);
	 }
}
