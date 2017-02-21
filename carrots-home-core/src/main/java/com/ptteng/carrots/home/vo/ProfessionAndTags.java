package com.ptteng.carrots.home.vo;

import java.util.List;

import com.ptteng.carrots.home.model.CompanyIndustry;
import com.ptteng.carrots.home.model.Profession;
import com.ptteng.carrots.home.model.ProfessionTags;

public class ProfessionAndTags {
	
	
	private String logo;
	
	private Profession profession;
    
	private List<CompanyIndustry> industryList;
	
    private List<ProfessionTags> tags;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public List<CompanyIndustry> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<CompanyIndustry> industryList) {
		this.industryList = industryList;
	}

	public List<ProfessionTags> getTags() {
		return tags;
	}

	public void setTags(List<ProfessionTags> tags) {
		this.tags = tags;
	}


	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	
}
