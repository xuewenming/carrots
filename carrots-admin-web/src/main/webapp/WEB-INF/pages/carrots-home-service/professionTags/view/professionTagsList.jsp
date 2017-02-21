<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/includes.jsp"%>

<input type="hidden" value="professionTags" id="current_nav">
<div id="professionTagsApp" ng-app="professionTagsApp">
	<div ng-controller="professionTagsController">

		<div class="portlet">
		    <div class="portlet-title">
		        <div class="caption"><i class="fa fa-cogs"></i>ProfessionTags管理</div>
		    </div>
		    <span class="btn green fileinput-button">
		        <i class="fa fa-plus"></i>
		        <span ng-click="addProfessionTags(professionTags)">新增</span>
		    </span>
	
			<paging url="/web/a/professionTags">
				<table class="table table-striped table-bordered table-hover">
					<tr>
									                    <td>自增长ID</td>
			        				                    <td>职位ID</td>
			        				                    <td>公司标签</td>
			        				                    <td>创建人ID</td>
			        				                    <td>更新人ID</td>
			        				                    <td>数据更新时间</td>
			        				                    <td>数据创建时间</td>
			        							<th>操作</th>
					</tr>
					<tr ng-repeat="professionTags in data">				
									                    <td ng-bind="professionTags.id" ></td>
			        				                    <td ng-bind="professionTags.professionId" ></td>
			        				                    <td ng-bind="professionTags.tag" ></td>
			        				                    <td ng-bind="professionTags.createBy" ></td>
			        				                    <td ng-bind="professionTags.updateBy" ></td>
			        				                    <td ng-bind="professionTags.updateAt" ></td>
			        				                    <td ng-bind="professionTags.createAt" ></td>
			        								
						<td>
							<input type="button" value="修改" ng-click="updateProfessionTags(professionTags.id)" /> 
							<input type="button" value="删除" ng-click="deleteProfessionTags(professionTags.id)" />
						</td>
					</tr>
				</table>		
			</paging>
	
		</div>
	</div>
</div>

<!-- Angular JS -->
<script src="/r/js-src/web/carrots-home-service/professionTags/professionTagsList.js"></script>
