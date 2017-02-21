<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/includes.jsp"%>
<%@page contentType="text/html;charset=utf-8"%>

<json:object escapeXml="false">
<json:property name="code" value="${code}"></json:property>
	<json:property name="message">
		<spring:message code="${code}" />
	</json:property>
	<json:property name="size" value="${size}"></json:property>
	<json:property name="total" value="${total}"></json:property>
	<json:array name="data">
		<c:forEach items="${companyList}" var="company">
			<json:object>
					
               
                    <json:property name="id" value="${company.id}"></json:property>
           
           
	   
        			
               
                    <json:property name="name" value="${company.name}"></json:property>
           
           
	   
        			
               
                    <json:property name="province" value="${company.province}"></json:property>
           
           
	   
        			
               
                    <json:property name="city" value="${company.city}"></json:property>
           
           
	   
        			
               
                    <json:property name="county" value="${company.county}"></json:property>
           
           
	   
        			
               
                    <json:property name="financing" value="${company.financing}"></json:property>
           
           
	   				<json:property name="slogan" value="${company.slogan}"></json:property>
	   				
	   				<json:property name="logo" value="${company.logo}"></json:property>
        			
               
                    <json:property name="professionNum" value="${company.professionNum}"></json:property>
           
           
					<json:array name="industryList">
	           			<c:forEach items="${company.companyIndustryList}" var="companyIndustry">
           					           
                	    		<json:property name="industry" value="${companyIndustry.industry}"></json:property>
                	    				
           				</c:forEach>
           			</json:array>           
	   
        			
               
<%--                     <json:property name="summary" value="${company.summary}"></json:property>
           
           
	   
        			
               
                    <json:property name="phone" value="${company.phone}"></json:property>
           
           
	   
        			
               
                    <json:property name="address" value="${company.address}"></json:property>
           
           
	   
        			
               
                    <json:property name="mail" value="${company.mail}"></json:property>
           
           
	   
        			
               
                    <json:property name="createBy" value="${company.createBy}"></json:property>
           
           
	   
        			
               
                    <json:property name="updateBy" value="${company.updateBy}"></json:property>
           
           
	   
        			
               
                    <json:property name="updateAt" value="${company.updateAt}"></json:property>
           
           
	   
        			
               
                    <json:property name="createAt" value="${company.createAt}"></json:property> --%>
           
           
	   
        			</json:object>
		</c:forEach>
	</json:array>
</json:object>


