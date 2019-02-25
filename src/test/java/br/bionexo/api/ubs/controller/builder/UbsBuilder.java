package br.bionexo.api.ubs.controller.builder;

import br.bionexo.api.ubs.domain.Ubs;

public class UbsBuilder {

	private Long id;

	private String name;
	
	private String address;
	
	private String city;
	
	private String phone;
	
	private Double geocodeLate;
	
	private Double geocodeLong;
	
	private Long scoresSize;

	private Long scoresAdaptationSeniors;
	
	private Long scoresMedicalEquipment;
	
	private Long scoresMedicine;
	
	public static UbsBuilder init() {
		return new UbsBuilder();
	}
	
	private UbsBuilder() {}
	
	public UbsBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	
	public UbsBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public UbsBuilder withAddress(String address) {
		this.address = address;
		return this;
	}
	
	public UbsBuilder withCity(String city) {
		this.city = city;
		return this;
	}
	
	public UbsBuilder withPhone(String phone) {
		this.phone = phone;
		return this;
	}
	
	public UbsBuilder withGeoCodeLate(double geocodeLate) {
		this.geocodeLate = geocodeLate;
		return this;
	}
	
	public UbsBuilder withGeoCodeLong(double geocodeLong) {
		this.geocodeLong = geocodeLong;
		return this;
	}
	
	public UbsBuilder withScoresSize(Long scoresSize) {
		this.scoresSize = scoresSize;
		return this;
	}
	
	public UbsBuilder withScoresAdaptationSeniors(Long scoresAdaptationSeniors) {
		this.scoresAdaptationSeniors = scoresAdaptationSeniors;
		return this;
	}
	
	public UbsBuilder withscoresMedicalEquipment(Long scoresMedicalEquipment) {
		this.scoresMedicalEquipment = scoresMedicalEquipment;
		return this;
	}
	
	public UbsBuilder withscoresScoresMedicine(Long scoresMedicine) {
		this.scoresMedicine = scoresMedicine;
		return this;
	}
	
	
	public Ubs build() {
		Ubs ubs = new Ubs();
		
		ubs.setId(id);
		ubs.setName(name);
		ubs.setAddress(address);
		ubs.setCity(city);
		ubs.setGeocodeLate(geocodeLate);
		ubs.setGeocodeLong(geocodeLong);
		ubs.setPhone(phone);
		ubs.setScoresAdaptationSeniors(scoresAdaptationSeniors);
		ubs.setScoresMedicalEquipment(scoresMedicalEquipment);
		ubs.setScoresMedicine(scoresMedicine);
		ubs.setScoresSize(scoresSize);
		
		return ubs;
	}
}
