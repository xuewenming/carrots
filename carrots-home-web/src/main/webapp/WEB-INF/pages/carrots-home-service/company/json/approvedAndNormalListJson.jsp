<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/includes.jsp"%>
<%@page contentType="text/html;charset=utf-8"%>

<json:object escapeXml="false">
<json:property name="code" value="${code}"></json:property>
	<json:property name="message">
		<spring:message code="${code}" />
	</json:property>
<%-- 	<json:property name="size" value="${size}"></json:property>
	<json:property name="total" value="${total}"></json:property> --%>


               <json:object name="industryImg">
                    <json:property name="id" value="${company.id}"></json:property>
           
           			<json:property name="name" value="${company.name}"></json:property>
           
           			<json:property name="slogan" value="${company.slogan}"></json:property>
           			
           			<json:property name="financing" value="${company.financing}"></json:property>
           			
           			<json:property name="province" value="${company.province}"></json:property>
           			
           			<json:property name="city" value="${company.city}"></json:property>
           			
           			<json:property name="county" value="${company.county}"></json:property>
	   
        			<json:property name="industryId" value="${industry.industry}"></json:property>
               
                    <json:property name="industryImg" value="${article.img}"></json:property>
				</json:object>
				
				<json:array name="normalCompanyList">
					<c:forEach items="${normalCompanyList}" var="company">
							<json:object>
					
               
                    				<json:property name="id" value="${company.id}"></json:property>
           
           
	   				
	   								<json:property name="logo" value="${company.logo}"></json:property>
        							
           
	   
        					</json:object>
					</c:forEach>
				</json:array>
		

	   			<json:array name="approvedCompanyList">
						<c:forEach items="${approvedCompanyList}" var="company">
								<json:object>
					
               
                    				<json:property name="id" value="${company.id}"></json:property>
           
           							<json:property name="name" value="${company.name}"></json:property>
	   				
	   								<json:property name="logo" value="${company.logo}"></json:property>
	   								
	   								<json:property name="slogan" value="${company.slogan}"></json:property>
        			
           							<json:array name="industryList">
	           							<c:forEach items="${company.companyIndustryList}" var="companyIndustry">
           					           
                	    					<json:property name="industry" value="${companyIndustry.industry}"></json:property>
                	    				
           								</c:forEach>
           							</json:array> 
	   								
           							<json:array name="professionList">
	           							<c:forEach items="${company.professionList}" var="profession">
           					           
                	    					<json:property name="professionName" value="${profession.name}"></json:property>
                	    				
           								</c:forEach>
           							</json:array> 
        						</json:object>
						</c:forEach>
				</json:array>

</json:object>


