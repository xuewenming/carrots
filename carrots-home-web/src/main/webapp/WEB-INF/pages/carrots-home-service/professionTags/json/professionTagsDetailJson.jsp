<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/includes.jsp"%>
<%@page contentType="text/html;charset=utf-8"%>

<json:object escapeXml="false">
	<json:property name="code" value="${code}"></json:property>
	<json:property name="message">
		<spring:message code="${code}" />
	</json:property>

			<json:object name="data">
		
								
               
                    <json:property name="id" value="${professionTags.id}"></json:property>
           
           
	   
                    			
               
                    <json:property name="professionId" value="${professionTags.professionId}"></json:property>
           
           
	   
                    			
               
                    <json:property name="tag" value="${professionTags.tag}"></json:property>
           
           
	   
                    			
               
                    <json:property name="createBy" value="${professionTags.createBy}"></json:property>
           
           
	   
                    			
               
                    <json:property name="updateBy" value="${professionTags.updateBy}"></json:property>
           
           
	   
                    			
               
                    <json:property name="updateAt" value="${professionTags.updateAt}"></json:property>
           
           
	   
                    			
               
                    <json:property name="createAt" value="${professionTags.createAt}"></json:property>
           
           
	   
                    				
	 
			</json:object>
		
</json:object>


