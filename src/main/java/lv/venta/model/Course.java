package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.model.enums.CourseLevel;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Course_Table")
@Entity
public class Course {
	
	// Nosaukums, stundas, limenis... pasniedzejs fk, sakums beigas fk
	
	@Setter(value = AccessLevel.NONE)
	@Id
	@Column(name = "CId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	
	@NotNull
	@Column(name = "Title")
	@Size(min = 3, max = 30)
	private String title;
	
	@Column(name = "Description")
	@Size(max = 500)
	private String description;
	
	@NotNull
	@Column(name = "Hours")
	@Min(2)
	@Max(30)
	private int hours;
	
	@NotNull
	@Column(name = "Level")
	private CourseLevel courseLevel;
	
	
	// pasniedzejs fk
	
	// sakuma datums un beigu datums
	
	// viens pasniedzejs vairakiem kursiem
	
	// public Course(String title, String description, int hours, CourseLevel courseLevel,)
	

}
