package lv.venta.model;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.JoinColumn;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Course_Participant_Table")
@Entity
public class CourseParticipant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CP_ID")
	@Setter(AccessLevel.NONE)
	private int cpId;

	@NotNull
	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")
	@Size(min = 3, max = 20)
	@Column(name = "Name")
	private String name;

	@NotNull
	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")
	@Size(min = 3, max = 25)
	@Column(name = "Surname")
	private String surname;

	@NotNull
	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
	@Size(min = 6, max = 254)
	@Column(name = "Email")
	private String email;

	@NotNull
	@Pattern(regexp = "\\+371[0-9]{8}")
	@Column(name = "Phone_No")
	private String phoneNo;

	@NotNull
	@Pattern(regexp = "^[A-Za-zĀ-ž\\.\\-\\s]+\\s[0-9]{1,4}[A-Za-z]?$")
	@Size(min = 5, max = 100)
	@Column(name = "Street_Or_House_Title_And_Number")
	private String streetOrHouseTitleAndNumber;

	@Column(name = "Apartment_No")
	@Min(1)
	@Max(1000)
	private int apartmentNo;

	@NotNull
	@Column(name = "City")
	private String city;

	@NotNull
	@Pattern(regexp = "^[A-Z]{0,2}[0-9]{3,5}([\\s-]?[0-9A-Z]{1,4})?$")
	@Column(name = "Postal_Code")
	private String postalCode;

	@NotNull
	@Column(name = "Country")
	private String country;

	@ManyToMany
	@JoinTable(name = "Participant_Course", joinColumns = @JoinColumn(name = "cpId"), inverseJoinColumns = @JoinColumn(name = "cdId"))
	private Collection<CourseDate> courseDates;

	public CourseParticipant(String name, String surname, String email, String phoneNo,
			String streetOrHouseTitleAndNumber, int apartmentNo, String city, String postalCode, String country) {
		setName(name);
		setSurname(surname);
		setEmail(email);
		setPhoneNo(phoneNo);
		setStreetOrHouseTitleAndNumber(streetOrHouseTitleAndNumber);
		setApartmentNo(apartmentNo);
		setCity(city);
		setPostalCode(postalCode);
		setCountry(country);
	}

}
