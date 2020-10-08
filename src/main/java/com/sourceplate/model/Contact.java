package com.sourceplate.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "contacts")
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false, unique = false)
	@NotEmpty
	private String contactname;
	
	@Column(nullable = false, unique = false)
	@NotEmpty
	private String phone;
	
	@Column(nullable = true, unique = false)
	private String email;
	
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "contact_tag", joinColumns = {
			@JoinColumn(name = "contact_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "tag_id", referencedColumnName = "id") })
    private List<Tag> tags;

	public Contact() {
	}
	
	
	public Contact(String contactname,String phone, String email, List<Tag> tags) {
		this.contactname = contactname;
		this.phone = phone;
		this.email = email;
		this.tags = tags;
	}


	public Contact(String contactname,String phone, String email) {
		super();
		this.contactname = contactname;
		this.phone = phone;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}


	
	
	
	
	
	
	

}
