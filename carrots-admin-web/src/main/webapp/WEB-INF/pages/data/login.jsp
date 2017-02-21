<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../includes/includes.jsp"%>
<%@ page contentType="text/html;charset=utf-8"%>

<json:object escapeXml="false">
  <json:property name="code" value="${code}"></json:property>
  <json:property name="message" >
    <spring:message code="${code}"/>
  </json:property>
  <json:object name="data">
    <json:property name="result" value="${result}"></json:property>
    <json:property name="size" value="${size}"></json:property>
    <json:property name="total" value="${total}"></json:property>\\



    <json:object name="manager">


     <json:property name="id" value="${manager.id}"></json:property>


    <json:property name="name" value="${manager.name}"></json:property>


    <json:property name="nick" value="${manager.nick}"></json:property>



    <%--<json:property name="roleId" value="${manager.roleId}"></json:property>--%>



    <json:property name="status" value="${manager.status}"></json:property>


    <json:property name="createBy" value="${manager.createBy}"></json:property>



    <json:property name="updateBy" value="${manager.updateBy}"></json:property>





    <json:property name="updateAt" value="${manager.updateAt}"></json:property>





    <json:property name="createAt" value="${manager.createAt}"></json:property>





  </json:object>



    <json:object name="role">



      <json:property name="id" value="${role.id}"></json:property>





      <json:property name="name" value="${role.name}"></json:property>





      <json:property name="status" value="${role.status}"></json:property>





      <json:property name="createBy" value="${role.createBy}"></json:property>





      <json:property name="updateBy" value="${role.updateBy}"></json:property>





      <json:property name="updateAt" value="${role.updateAt}"></json:property>





      <json:property name="createAt" value="${role.createAt}"></json:property>




    </json:object>





  </json:object>
</json:object>