<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/includes.jsp"%>
<%@page contentType="text/html;charset=utf-8"%>

<json:object escapeXml="false">
	<json:property name="code" value="${code}"></json:property>
	<json:property name="message">
		<spring:message code="${code}" />
	</json:property>
	<json:property name="page" value="${page}"></json:property>
	<json:property name="size" value="${size}"></json:property>
	<json:property name="total" value="${total}"></json:property>
	<json:array name="tags">
		<c:forEach items="${companyTagsList}" var="companyTags">
			<json:object>


				<%--                     <json:property name="id" value="${companyTags.id}"></json:property> --%>





				<%--                     <json:property name="companyId" value="${companyTags.companyId}"></json:property> --%>





				<json:property name="tag" value="${companyTags.tag}"></json:property>





				<%--                     <json:property name="createBy" value="${companyTags.createBy}"></json:property> --%>





				<%--                     <json:property name="updateBy" value="${companyTags.updateBy}"></json:property> --%>





				<%--                     <json:property name="updateAt" value="${companyTags.updateAt}"></json:property> --%>





				<%--                     <json:property name="createAt" value="${companyTags.createAt}"></json:property> --%>



			</json:object>
		</c:forEach>
	</json:array>
</json:object>


