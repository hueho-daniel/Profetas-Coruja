package persistence.dto;

import java.util.GregorianCalendar;

import org.jdto.annotation.DTOCascade;


public class UserAccount implements DTO{
	
	private Long id;
	
	private String name;
	
	@DTOCascade
	private Profile profile;
	
	private String email;
	
	private String password;
	
	private String generatedToken;
	
	private GregorianCalendar tokenDate;
	
	public UserAccount() {
		//Para o JDTO
	}

	public UserAccount(String name, Profile profile, String email,	String password, String generatedToken, GregorianCalendar tokenDate) {
		this.name = name;
		this.profile = profile;
		this.email = email;
		this.password = password;
		this.tokenDate = tokenDate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGeneratedToken() {
		return generatedToken;
	}

	public void setGeneratedToken(String generatedToken) {
		this.generatedToken = generatedToken;
	}

	public GregorianCalendar getTokenDate() {
		return tokenDate;
	}

	public void setTokenDate(GregorianCalendar tokenDate) {
		this.tokenDate = tokenDate;
	}
	
}
