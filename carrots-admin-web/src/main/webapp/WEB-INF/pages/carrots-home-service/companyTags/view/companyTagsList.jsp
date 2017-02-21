<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/includes.jsp"%>

<input type="hidden" value="companyTags" id="current_nav">
<div id="companyTagsApp" ng-app="companyTagsApp">
	<div ng-controller="companyTagsController">

		<div class="portlet">
		    <div class="portlet-title">
		        <div class="caption"><i class="fa fa-cogs"></i>CompanyTags管理</div>
		    </div>
		    <span class="btn green fileinput-button">
		        <i class="fa fa-plus"></i>
		        <span ng-click="addCompanyTags(companyTags)">新增</span>
		    </span>
	
			<paging url="/web/a/companyTags">
				<table class="table table-striped table-bordered table-hover">
					<tr>
									                    <td>自增长ID</td>
			        				                    <td>公司ID</td>
			        				                    <td>公司标签</td>
			        				                    <td>创建人ID</td>
			        				                    <td>更新人ID</td>
			        				                    <td>数据更新时间</td>
			        				                    <td>数据创建时间</td>
			        							<th>操作</th>
					</tr>
					<tr ng-repeat="companyTags in data">				
									                    <td ng-bind="companyTags.id" ></td>
			        				                    <td ng-bind="companyTags.companyId" ></td>
			        				                    <td ng-bind="companyTags.tag" ></td>
			        				                    <td ng-bind="companyTags.createBy" ></td>
			        				                    <td ng-bind="companyTags.updateBy" ></td>
			        				                    <td ng-bind="companyTags.updateAt" ></td>
			        				                    <td ng-bind="companyTags.createAt" ></td>
			        								
						<td>
							<input type="button" value="修改" ng-click="updateCompanyTags(companyTags.id)" /> 
							<input type="button" value="删除" ng-click="deleteCompanyTags(companyTags.id)" />
						</td>
					</tr>
				</table>		
			</paging>
	
		</div>
	</div>
</div>

<!-- Angular JS -->
<script src="/r/js-src/web/carrots-home-service/companyTags/companyTagsList.js"></script>
