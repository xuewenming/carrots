<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/includes.jsp"%>

<input type="hidden" value="companyIndustry" id="current_nav">
<div id="companyIndustryApp" ng-app="companyIndustryApp">
	<div ng-controller="companyIndustryController">

		<div class="portlet">
		    <div class="portlet-title">
		        <div class="caption"><i class="fa fa-cogs"></i>CompanyIndustry管理</div>
		    </div>
		    <span class="btn green fileinput-button">
		        <i class="fa fa-plus"></i>
		        <span ng-click="addCompanyIndustry(companyIndustry)">新增</span>
		    </span>
	
			<paging url="/web/a/companyIndustry">
				<table class="table table-striped table-bordered table-hover">
					<tr>
									                    <td>自增长ID</td>
			        				                    <td>公司ID</td>
			        				                    <td>公司行业</td>
			        				                    <td>创建人ID</td>
			        				                    <td>更新人ID</td>
			        				                    <td>数据更新时间</td>
			        				                    <td>数据创建时间</td>
			        							<th>操作</th>
					</tr>
					<tr ng-repeat="companyIndustry in data">				
									                    <td ng-bind="companyIndustry.id" ></td>
			        				                    <td ng-bind="companyIndustry.companyId" ></td>
			        				                    <td ng-bind="companyIndustry.industry" ></td>
			        				                    <td ng-bind="companyIndustry.createBy" ></td>
			        				                    <td ng-bind="companyIndustry.updateBy" ></td>
			        				                    <td ng-bind="companyIndustry.updateAt" ></td>
			        				                    <td ng-bind="companyIndustry.createAt" ></td>
			        								
						<td>
							<input type="button" value="修改" ng-click="updateCompanyIndustry(companyIndustry.id)" /> 
							<input type="button" value="删除" ng-click="deleteCompanyIndustry(companyIndustry.id)" />
						</td>
					</tr>
				</table>		
			</paging>
	
		</div>
	</div>
</div>

<!-- Angular JS -->
<script src="/r/js-src/web/carrots-home-service/companyIndustry/companyIndustryList.js"></script>
