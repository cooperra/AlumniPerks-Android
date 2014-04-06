package edu.rosehulman.roseperks;

public class Perk {
	private long id;
	private String company_name;
	private String company_address;
	private String company_phone;
	private String perk_description;
	private String perk_image;
	private String perk_website;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return company_name;
	}

	public void setCompanyName(String company_name) {
		this.company_name = company_name;
	}

	public String getCompanyAddress() {
		return company_address;
	}

	public void setCompanyAddress(String company_address) {
		this.company_address = company_address;
	}

	public String getCompanyPhone() {
		return company_phone;
	}

	public void setCompanyPhone(String company_phone) {
		this.company_phone = company_phone;
	}

	public String getPerkDescription() {
		return perk_description;
	}

	public void setPerkDescription(String perk_description) {
		this.perk_description = perk_description;
	}

	public String getPerkImage() {
		return perk_image;
	}

	public void setPerkImage(String perk_image) {
		this.perk_image = perk_image;
	}

	public String getPerkWebsite() {
		return perk_website;
	}

	public void setPerkWebsite(String perk_website) {
		this.perk_website = perk_website;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return company_name + ": " + perk_description;
	}
}
