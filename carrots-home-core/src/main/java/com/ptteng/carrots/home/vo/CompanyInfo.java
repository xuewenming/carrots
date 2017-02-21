package com.ptteng.carrots.home.vo;

import java.util.List;

import com.ptteng.carrots.home.model.Company;
import com.ptteng.carrots.home.model.CompanyIndustry;
import com.ptteng.carrots.home.model.CompanyTags;
import com.ptteng.carrots.home.model.Product;

public class CompanyInfo {

	/**
	 * Created by linfeng on 2016/09/19
	 * Function: add Company/CompanyIndustry/CompanyTags/Product table record
	 */
    private Company company;
    
    private List<CompanyIndustry> industryList;
	
    private List<CompanyTags> tagList;	
   	
    private List<Product> productList;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<CompanyIndustry> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<CompanyIndustry> industryList) {
		this.industryList = industryList;
	}

	public List<CompanyTags> getTagList() {
		return tagList;
	}

	public void setTagList(List<CompanyTags> tagList) {
		this.tagList = tagList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}    

}

