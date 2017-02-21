package com.ptteng.carrots.home.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Entity
@Table(name = "company")
public class Company implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 845213511977511936L;	
	
	private static final long None = 0L;
   	 
    private  Long id;
	
  	 
    private  String name;
	
  	 
    private  String province;
	
  	 
    private  String city;
	
  	 
    private  String county;
	
  	 
    private  Long financing;
	
  	 
    private  Long approved;
	
  	 
    private  Long freezed;
	
  	 
    private  String logo;
	
  	 
    private  String slogan;
	
  	 
    private  Long totalNum;
	
  	 
    private  String summary;
	
  	 
    private  String phone;
	
  	 
    private  String address;
	
  	 
    private  String map;
	
  	 
    private  String mail;
	
    private  String product;
    
    private  Long professionUpdateAt;
    
    private  Long professionNum = None;
    
    private  Long createBy;
	
  	 
    private  Long updateBy;
	
  	 
    private  Long updateAt;
	
  	 
    private  Long createAt;
    
    private List<CompanyIndustry> companyIndustryList;
    
    private List<Profession> professionList;

    
    
    @Transient
	public List<Profession> getProfessionList() {
		return professionList;
	}


	public void setProfessionList(List<Profession> professionList) {
		this.professionList = professionList;
	}


	@Transient	 	
    public List<CompanyIndustry> getCompanyIndustryList() {
		return companyIndustryList;
	}


	public void setCompanyIndustryList(List<CompanyIndustry> companyIndustryList) {
		this.companyIndustryList = companyIndustryList;
	}


				@Id
     	   @GeneratedValue(strategy = GenerationType.AUTO)
              	@Column(name = "id")
	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
		 	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
		 	@Column(name = "province")
	public String getProvince() {
		return province;
	}
	
	
	public void setProvince(String province) {
		this.province = province;
	}
		 	@Column(name = "city")
	public String getCity() {
		return city;
	}
	
	
	public void setCity(String city) {
		this.city = city;
	}
		 	@Column(name = "county")
	public String getCounty() {
		return county;
	}
	
	
	public void setCounty(String county) {
		this.county = county;
	}
		 	@Column(name = "financing")
	public Long getFinancing() {
		return financing;
	}
	
	
	public void setFinancing(Long financing) {
		this.financing = financing;
	}
		 	@Column(name = "approved")
	public Long getApproved() {
		return approved;
	}
	
	
	public void setApproved(Long approved) {
		this.approved = approved;
	}
		 	@Column(name = "freezed")
	public Long getFreezed() {
		return freezed;
	}
	
	
	public void setFreezed(Long freezed) {
		this.freezed = freezed;
	}
		 	@Column(name = "logo")
	public String getLogo() {
		return logo;
	}
	
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
		 	@Column(name = "slogan")
	public String getSlogan() {
		return slogan;
	}
	
	
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
		 	@Column(name = "total_num")
	public Long getTotalNum() {
		return totalNum;
	}
	
	
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
		 	@Column(name = "summary")
	public String getSummary() {
		return summary;
	}
	
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
		 	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
		 	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	
	
	public void setAddress(String address) {
		this.address = address;
	}
		 	@Column(name = "map")
	public String getMap() {
		return map;
	}
	
	
	public void setMap(String map) {
		this.map = map;
	}
		 	@Column(name = "mail")
	public String getMail() {
		return mail;
	}
	
	
	public void setMail(String mail) {
		this.mail = mail;
	}
		 	@Column(name = "profession_num")
	public Long getProfessionNum() {
		return professionNum;
	}
	
	
	public void setProfessionNum(Long professionNum) {
		this.professionNum = professionNum;
	}
		 	@Column(name = "create_by")
	public Long getCreateBy() {
		return createBy;
	}
	
	
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
		 	@Column(name = "update_by")
	public Long getUpdateBy() {
		return updateBy;
	}
	
	
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
		 	@Column(name = "update_at")
	public Long getUpdateAt() {
		return updateAt;
	}
	
	
	public void setUpdateAt(Long updateAt) {
		this.updateAt = updateAt;
	}
		 	@Column(name = "create_at")
	public Long getCreateAt() {
		return createAt;
	}
	
	
	public void setCreateAt(Long createAt) {
		this.createAt = createAt;
	}
	
			@Column(name = "product")
    public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}

			@Column(name = "profession_update_at")
	public Long getProfessionUpdateAt() {
		return professionUpdateAt;
	}


	public void setProfessionUpdateAt(Long professionUpdateAt) {
		this.professionUpdateAt = professionUpdateAt;
	}
		
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}

