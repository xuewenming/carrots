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
@Table(name = "profession_tags")
public class ProfessionTags implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1828014175899096064L;	
	
		
   	 
    private  Long id;
	
  	 
    private  Long companyId;
	
  	 
    private  Long professionId;
	
  	 
    private  String tag;
	
  	 
    private  Long createBy;
	
  	 
    private  Long updateBy;
	
  	 
    private  Long updateAt;
	
  	 
    private  Long createAt;
	
  
	
		 	
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
		 	@Column(name = "profession_id")
	public Long getProfessionId() {
		return professionId;
	}
	
	
	public void setProfessionId(Long professionId) {
		this.professionId = professionId;
	}
		 	@Column(name = "tag")
	public String getTag() {
		return tag;
	}
	
	
	public void setTag(String tag) {
		this.tag = tag;
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

