package lv.venta.model;

import java.time.LocalDate;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "course")
@Table(name = "Course_Date_Table")
@Entity
public class CourseDate {
	
	@Setter(value = AccessLevel.NONE)
	@Id
	@Column(name = "CD_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cdId;
	
	@NotNull
	@Column(name = "Starting_Date")
	private LocalDate startingDate;
	
	@NotNull
	@Column(name = "Ending_Date")
	private LocalDate endingDate;
	
	@ManyToOne
	@JoinColumn(name = "C_ID")
	@NotNull
	private Course course;
	
	@ManyToMany(mappedBy = "courseDate")
	private Collection<CourseParticipant> participants;
	
	public CourseDate(LocalDate startingDate, LocalDate endingDate, Course course) {
		setStartingDate(startingDate);
		setEndingDate(endingDate);
		setCourse(course);
	}

}
