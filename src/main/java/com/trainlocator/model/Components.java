package com.trainlocator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Components {
	
	 	@JsonProperty("ISO_3166-1_alpha-2") 
	    private String alpha2;
	    @JsonProperty("ISO_3166-1_alpha-3") 
	    private String alpha3;
	    private String _category;
	    private String _type;
	    private String continent;
	    private String country;
	    private String country_code;
	    private String county;
	    private String state;
	    private String village;
		public String getAlpha2() {
			return alpha2;
		}
		public void setAlpha2(String alpha2) {
			this.alpha2 = alpha2;
		}
		public String getAlpha3() {
			return alpha3;
		}
		public void setAlpha3(String alpha3) {
			this.alpha3 = alpha3;
		}
		public String get_category() {
			return _category;
		}
		public void set_category(String _category) {
			this._category = _category;
		}
		public String get_type() {
			return _type;
		}
		public void set_type(String _type) {
			this._type = _type;
		}
		public String getContinent() {
			return continent;
		}
		public void setContinent(String continent) {
			this.continent = continent;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getCountry_code() {
			return country_code;
		}
		public void setCountry_code(String country_code) {
			this.country_code = country_code;
		}
		public String getCounty() {
			return county;
		}
		public void setCounty(String county) {
			this.county = county;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getVillage() {
			return village;
		}
		public void setVillage(String village) {
			this.village = village;
		}
	    
	    

}
