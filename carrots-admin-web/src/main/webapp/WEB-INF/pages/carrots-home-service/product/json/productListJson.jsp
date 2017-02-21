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
		<c:forEach items="${productList}" var="product">
			<json:object>
					
               
                    <json:property name="id" value="${product.id}"></json:property>
           
           
	   
        			
               
                    <json:property name="companyId" value="${product.companyId}"></json:property>
           
           
	   
        			
               
                    <json:property name="name" value="${product.name}"></json:property>
           
           
	   
        			
               
                    <json:property name="slogan" value="${product.slogan}"></json:property>
           
           
	   
        			
               
                    <json:property name="logo" value="${product.logo}"></json:property>
           
           
	   
        			
               
                    <json:property name="summary" value="${product.summary}"></json:property>
           
           
	   
        			
               
                    <json:property name="createBy" value="${product.createBy}"></json:property>
           
           
	   
        			
               
                    <json:property name="updateBy" value="${product.updateBy}"></json:property>
           
           
	   
        			
               
                    <json:property name="updateAt" value="${product.updateAt}"></json:property>
           
           
	   
        			
               
                    <json:property name="createAt" value="${product.createAt}"></json:property>
           
           
	   
        			</json:object>
		</c:forEach>
	</json:array>
</json:object>


