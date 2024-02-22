package dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import helper.AES;
import lombok.Data;

@Entity
@Data
public class TodoUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	private LocalDate dob;
	private Long number;
	private String gender;
	
	
	public String getPassword() {
		return AES.decrypt(password, "123");
	}

	public void setPassword(String password) {
		this.password = AES.encrypt(password, "123");
	}

}
