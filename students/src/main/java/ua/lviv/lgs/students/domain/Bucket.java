package ua.lviv.lgs.students.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bucket")
public class Bucket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student student;
	
	@OneToOne
	@JoinColumn(name = "dossier_id", referencedColumnName = "id")
	private Dossier dossier;
	
	@Column(name = "registration_date")
	private Date registrationDate;

	public Bucket() {
	}

	public Bucket(Integer id) {
		this.id = id;
	}

	public Bucket(Student student, Dossier dossier, Date registrationDate) {
		super();
		this.student = student;
		this.dossier = dossier;
		this.registrationDate = registrationDate;
	}

	public Bucket(Integer id, Student student, Dossier dossier, Date registrationDate) {
		super();
		this.id = id;
		this.student = student;
		this.dossier = dossier;
		this.registrationDate = registrationDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Dossier getDossier() {
		return dossier;
	}

	public void setDossier(Dossier dossier) {
		this.dossier = dossier;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((dossier == null) ? 0 : dossier.hashCode());
		result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bucket other = (Bucket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (dossier == null) {
			if (other.dossier != null)
				return false;
		} else if (!dossier.equals(other.dossier))
			return false;
		if (registrationDate == null) {
			if (other.registrationDate != null)
				return false;
		} else if (!registrationDate.equals(other.registrationDate))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bucket [id=" + id + ", user=" + student + ", periodical=" + dossier + ", purchaseDate=" + registrationDate
				+ "]";
	}

}