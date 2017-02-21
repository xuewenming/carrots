<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/includes.jsp"%>
    <input type="hidden" value="companyIndustry" id="current_nav">
	<div id="companyIndustryDetailApp" ng-app="companyIndustryDetailApp">
		<div ng-controller="companyIndustryDetailController">
			
			<div class="portlet">
			    <div class="portlet-title">
			        <div class="caption"><i class="fa fa-cogs"></i>
											  	<c:choose>
					   		<c:when test="${id > 0}">编辑</c:when> 
					   	    <c:otherwise>新增</c:otherwise>
				   	    </c:choose>		
					</div>
			    </div>
		
		
			    <form class="form-horizontal" role="form" name="companyIndustryForm">
			   	 	
				  
				 				 
				 				      <input type="hidden" name="id" id="companyIndustryId" ng-model="companyIndustry.id" ng-bind="companyIndustry.id" value="${id}">
			      				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="companyId" class="col-sm-2 control-label">公司ID</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="companyId" ng-model="companyIndustry.companyId" ng-bind="companyIndustry.companyId" placeholder="请输入公司ID" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="industry" class="col-sm-2 control-label">公司行业</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="industry" ng-model="companyIndustry.industry" ng-bind="companyIndustry.industry" placeholder="请输入公司行业" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="createBy" class="col-sm-2 control-label">创建人ID</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="createBy" ng-model="companyIndustry.createBy" ng-bind="companyIndustry.createBy" placeholder="请输入创建人ID" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="updateBy" class="col-sm-2 control-label">更新人ID</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="updateBy" ng-model="companyIndustry.updateBy" ng-bind="companyIndustry.updateBy" placeholder="请输入更新人ID" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       				     
				     
				    
			        			      
				  				  
				    
				 
				
		   
	            				 
				 			       				     
				     
				    
			        			      
				  				  
				    
				 
				
		   
	            					  <div class="form-group">
			        <div class="col-sm-offset-2 col-sm-10">
			          <button class="btn btn-primary col-sm-2" type="submit" ng-click="updateCompanyIndustry(companyIndustry)">保存</button>
			          <a class="btn btn-primary col-sm-2 col-sm-offset-2" ng-href="/web/c/companyIndustry">取消</a>
			        </div>
			      </div>
			     
			    </form>
			 </div>
		 </div>
	 </div>
	 
	 <script src="/r/js-src/web/carrots-home-service/companyIndustry/companyIndustryDetail.js"></script>
