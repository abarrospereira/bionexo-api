package br.bionexo.api.ubs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Ubs {

	@Id
	@SequenceGenerator(name = "seq_ubs", sequenceName = "seq_ubs", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ubs")
	private Long id;

	@NotBlank
	@Size(min = 1, max = 50)
	private String name;
	
	@NotBlank
	@Size(min = 1, max = 50)
	private String address;
	
	@NotBlank
	@Size(min = 1, max = 50)
	private String city;
	
	@NotBlank
	@Size(min = 1, max = 50)
	private String phone;
	
	@NotNull
	private Double geocodeLate;
	
	@NotNull
	private Double geocodeLong;
	
	@Min(1)
	@Max(3)
	private Long scoresSize;

	@Min(1)
	@Max(3)
	private Long scoresAdaptationSeniors;
	
	@Min(1)
	@Max(3)
	private Long scoresMedicalEquipment;
	
	@Min(1)
	@Max(3)
	private Long scoresMedicine;
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Long getScoresSize() {
		return scoresSize;
	}

	public void setScoresSize(Long scoresSize) {
		this.scoresSize = scoresSize;
	}

	public Double getGeocodeLate() {
		return geocodeLate;
	}

	public void setGeocodeLate(Double geocodeLate) {
		this.geocodeLate = geocodeLate;
	}

	public Double getGeocodeLong() {
		return geocodeLong;
	}

	public void setGeocodeLong(Double geocodeLong) {
		this.geocodeLong = geocodeLong;
	}

	public Long getScoresAdaptationSeniors() {
		return scoresAdaptationSeniors;
	}

	public void setScoresAdaptationSeniors(Long scoresAdaptationSeniors) {
		this.scoresAdaptationSeniors = scoresAdaptationSeniors;
	}

	public Long getScoresMedicalEquipment() {
		return scoresMedicalEquipment;
	}

	public void setScoresMedicalEquipment(Long scoresMedicalEquipment) {
		this.scoresMedicalEquipment = scoresMedicalEquipment;
	}

	public Long getScoresMedicine() {
		return scoresMedicine;
	}

	public void setScoresMedicine(Long scoresMedicine) {
		this.scoresMedicine = scoresMedicine;
	}

	
	
	

}
