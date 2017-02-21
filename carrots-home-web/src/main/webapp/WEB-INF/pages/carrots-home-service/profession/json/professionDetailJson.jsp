<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/includes.jsp"%>
<%@page contentType="text/html;charset=utf-8"%>

<json:object escapeXml="false">
	<json:property name="code" value="${code}"></json:property>
	<json:property name="message">
		<spring:message code="${code}" />
	</json:property>

			<json:object name="data">
		
								
               
                    <json:property name="id" value="${profession.id}"></json:property>
           
           
	   
                    			
               
                    <json:property name="name" value="${profession.name}"></json:property>
           
           
                    <json:property name="companyId" value="${profession.companyId}"></json:property>
         
           
           			<json:property name="companyName" value="${company.name}"></json:property>	   
                    
                    <json:property name="province" value="${profession.province}"></json:property>
                    
                    <json:property name="city" value="${profession.city}"></json:property>	
                    
                    <json:property name="county" value="${profession.county}"></json:property>		
                    			
               		<json:property name="logo" value="${company.logo}"></json:property>
               
              	    <json:property name="totalNum" value="${company.totalNum}"></json:property>
               
               		<json:property name="productId" value="${product.id}"></json:property>
               		
               		<json:property name="productName" value="${product.name}"></json:property>
               
                    <json:property name="logo" value="${company.logo}"></json:property>
                   
                    <json:property name="category" value="${profession.category}"></json:property>
           
           
	   
                    			
               
                    <json:property name="subCategory" value="${profession.subCategory}"></json:property>
           
           
	   
                    			
               
                    <json:property name="education" value="${profession.education}"></json:property>
           
           
	   
                    			
               
                    <json:property name="experience" value="${profession.experience}"></json:property>
           
           
	   
                    			
               
                    <json:property name="recommend" value="${profession.recommend}"></json:property>
           
           
	   
                    			
               
                    <json:property name="compensation" value="${profession.compensation}"></json:property>
           
           
	   
                    			
               
                    <json:property name="responsibility" value="${profession.responsibility}"></json:property>
           
           
	   
                    			
               
                    <json:property name="requisite" value="${profession.requisite}"></json:property>
           
           
	   
                    			
               
                    <json:property name="boon" value="${profession.boon}"></json:property>
           
           
	   
                    			
               
<%--                     <json:property name="status" value="${profession.status}"></json:property> --%>
           
           
	   
                    			
               
<%--                     <json:property name="createBy" value="${profession.createBy}"></json:property> --%>
           
           
	   
                    			
               
<%--                     <json:property name="updateBy" value="${profession.updateBy}"></json:property> --%>
           
           
	   
                    			
               
                    <json:property name="updateAt" value="${profession.updateAt}"></json:property>
           
           
	   
                    			
               
<%--                     <json:property name="createAt" value="${profession.createAt}"></json:property> --%>
       
       
          <json:array name="industryList">
           		<c:forEach items="${companyIndustryList}" var="industry">
           			<json:object>
           				<json:property name="industry" value="${industry.industry}"></json:property>
           			</json:object>
           		</c:forEach>
	   	  </json:array>
           
           
          <json:array name="companyTags">
           		<c:forEach items="${companyTags}" var="companyTag">
           			<json:object>
           				<json:property name="companyTag" value="${companyTag.tag}"></json:property>
           			</json:object>
           		</c:forEach>
	   	  </json:array>
           
           <json:array name="tags">
           		<c:forEach items="${tags}" var="professionTags">
           			<json:object>
           				<json:property name="tag" value="${professionTags.tag}"></json:property>
           			</json:object>
           		</c:forEach>
	   	  </json:array>
                    				
	 
			</json:object>
		
</json:object>


