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
@Table(name = "profession")
public class Profession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2124729459897656320L;

	private Long id;

	private Long companyId;

	private String companyName;

	private String name;

	private String province;

	private String city;

	private String county;

	private Long category;

	private Long subCategory;

	private Long education;

	private Long experience;

	private Long recommend;

	private Long compensation;

	private String responsibility;

	private String requisite;

	private String boon;

	private Long status;

	private Long createBy;

	private Long updateBy;

	private Long updateAt;

	private Long createAt;

	// 临时记录职位标签列表
	private List<ProfessionTags> professionTagsList;

	@Transient
	public List<ProfessionTags> getProfessionTagsList() {
		return professionTagsList;
	}

	public void setProfessionTagsList(List<ProfessionTags> professionTagsList) {
		this.professionTagsList = professionTagsList;
	}
	
	@Column(name = "company_name")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	@Column(name = "company_id")
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

	@Column(name = "category")
	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	@Column(name = "sub_category")
	public Long getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Long subCategory) {
		this.subCategory = subCategory;
	}

	@Column(name = "education")
	public Long getEducation() {
		return education;
	}

	public void setEducation(Long education) {
		this.education = education;
	}

	@Column(name = "experience")
	public Long getExperience() {
		return experience;
	}

	public void setExperience(Long experience) {
		this.experience = experience;
	}

	@Column(name = "recommend")
	public Long getRecommend() {
		return recommend;
	}

	public void setRecommend(Long recommend) {
		this.recommend = recommend;
	}

	@Column(name = "compensation")
	public Long getCompensation() {
		return compensation;
	}

	public void setCompensation(Long compensation) {
		this.compensation = compensation;
	}

	@Column(name = "responsibility")
	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	@Column(name = "requisite")
	public String getRequisite() {
		return requisite;
	}

	public void setRequisite(String requisite) {
		this.requisite = requisite;
	}

	@Column(name = "boon")
	public String getBoon() {
		return boon;
	}

	public void setBoon(String boon) {
		this.boon = boon;
	}

	@Column(name = "status")
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
