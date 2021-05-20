
  package com.neosoft.gk.model;
  
  import java.io.Serializable; 
  import java.util.ArrayList; 
  import java.util.List;
  
  import javax.persistence.CascadeType; 
  import javax.persistence.Column; 
  import javax.persistence.Entity; 
  import javax.persistence.GeneratedValue; 
  import javax.persistence.GenerationType; 
  import javax.persistence.Id; 
  import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany; 
  import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter; 
  import lombok.NoArgsConstructor; 
  import lombok.Setter; 
  import lombok.experimental.Accessors;
  

  @Data
  @Entity
  public class Student {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY) 
  private Integer studentId;
  
  @Column(length=45,nullable=false)
  private String firstName;
  
  @Column(length=45,nullable=false)
  private String lastName;
  
  @Column(length=45,nullable=false)
  private String email;
  
  @Column(length=45,nullable=false)
  private String contact;
  

  @OneToMany
  @JoinColumn(name="student_id")
  private List<Project> projects=new ArrayList<>();
  
  @Column(nullable = true, length = 64)
  private String photos;
  
  @Transient
  public String getPhotosImagePath() {
      if (photos == null || studentId == null) return null;
       
      return "/student-photos/" + studentId + "/" + photos;
  }
	public String getPhotos() {
	return photos;
}

public void setPhotos(String photos) {
	this.photos = photos;
}

/*
	 * @Lob
	 * 
	 * @Column(columnDefinition = "MEDIUMBLOB") private String image;
	 */

public String getFirstName() {
	return firstName;
}

public Integer getStudentId() {
	return studentId;
}
public void setStudentId(Integer studentId) {
	this.studentId = studentId;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getContact() {
	return contact;
}

public void setContact(String contact) {
	this.contact = contact;
}

public List<Project> getProjects() {
	return projects;
}

public void setProjects(List<Project> projects) {
	this.projects = projects;
}
}
 