package com.cms.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Contact {
	@Id
	private int id;
	
	private String name;
	private String address;
	@NotNull(message = "Phone number should not be null")
	@Size(min = 10, max = 10,message = "Please enter 10 digit phone number")
	private String phone;
	@Email(message = "Enter valid email")
	private String email;
	

}
