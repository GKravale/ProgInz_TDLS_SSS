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

@Entity
@Table(name = "Course_Table")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "C_Id")
    @Setter(AccessLevel.NONE)
    private int cId;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "Title")
    private String title;

    @Size(max = 500)
    @Column(name = "Description")
    private String description;

    @NotNull
    @Min(2)
    @Max(30)
    @Column(name = "Hours")
    private int hours;

    @NotNull
    @Column(name = "Course_Level")
    private CourseLevel courseLevel;

    @ManyToOne
    @JoinColumn(name = "lId")
    private Lecturer lecturer;

    @OneToMany(mappedBy = "course")
    private Collection<CourseDate> courseDates;
    
    
    public Course(String title, String description, int hours, CourseLevel courseLevel, Lecturer lecturer) {
    	setTitle(title);
    	setDescription(description);
    	setHours(hours);
    	setCourseLevel(courseLevel);
    	setLecturer(lecturer);
    }

    
}

