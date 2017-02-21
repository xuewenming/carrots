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
		<c:forEach items="${professionAndTagsList}" var="professionAndTags">
			<json:object>


				<json:property name="id" value="${professionAndTags.profession.id}"></json:property>

				<json:property name="companyId" value="${professionAndTags.profession.companyId}"></json:property>


				<json:property name="companyName" value="${professionAndTags.profession.companyName}"></json:property>


				<json:property name="name" value="${professionAndTags.profession.name}"></json:property>




				<json:property name="province" value="${professionAndTags.profession.province}"></json:property>

				<json:property name="city" value="${professionAndTags.profession.city}"></json:property>

				<json:property name="county" value="${professionAndTags.profession.county}"></json:property>


				<json:property name="category" value="${professionAndTags.profession.category}"></json:property>





				<json:property name="subCategory" value="${professionAndTags.profession.subCategory}"></json:property>





				<json:property name="education" value="${professionAndTags.profession.education}"></json:property>





				<json:property name="experience" value="${professionAndTags.profession.experience}"></json:property>





				<json:property name="recommend" value="${professionAndTags.profession.recommend}"></json:property>





				<json:property name="compensation"
					value="${professionAndTags.profession.compensation}"></json:property>





				<json:property name="responsibility"
					value="${professionAndTags.profession.responsibility}"></json:property>





				<json:property name="requisite" value="${professionAndTags.profession.requisite}"></json:property>





				<json:property name="boon" value="${professionAndTags.profession.boon}"></json:property>





<%-- 				<json:property name="status" value="${profession.status}"></json:property> --%>





				<%-- 				<json:property name="createBy" value="${profession.createBy}"></json:property> --%>





				<%-- 				<json:property name="updateBy" value="${profession.updateBy}"></json:property> --%>





								<json:property name="updateAt" value="${professionAndTags.profession.updateAt}"></json:property>





				<%-- 				<json:property name="createAt" value="${profession.createAt}"></json:property> --%>



				<json:property name="logo" value="${professionAndTags.logo}"></json:property>


				<json:array name="industryList">
					<c:forEach items="${professionAndTags.industryList}"
						var="companyIndustry">
						<json:object>
							<json:property name="industry" value="${companyIndustry.industry}"></json:property>
						</json:object>
					</c:forEach>
				</json:array>

				<json:array name="tags">
					<c:forEach items="${professionAndTags.tags}"
						var="professionTags">
						<json:object>
							<json:property name="tag" value="${professionTags.tag}"></json:property>
						</json:object>
					</c:forEach>
				</json:array>

			</json:object>



		</c:forEach>
	</json:array>
</json:object>


