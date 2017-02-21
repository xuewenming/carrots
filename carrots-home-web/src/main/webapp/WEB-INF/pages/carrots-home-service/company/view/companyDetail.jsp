<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/includes.jsp"%>
    <input type="hidden" value="company" id="current_nav">
	<div id="companyDetailApp" ng-app="companyDetailApp">
		<div ng-controller="companyDetailController">
			
			<div class="portlet">
			    <div class="portlet-title">
			        <div class="caption"><i class="fa fa-cogs"></i>
											  	<c:choose>
					   		<c:when test="${id > 0}">编辑</c:when> 
					   	    <c:otherwise>新增</c:otherwise>
				   	    </c:choose>		
					</div>
			    </div>
		
		
			    <form class="form-horizontal" role="form" name="companyForm">
			   	 	
				  
				 				 
				 				      <input type="hidden" name="id" id="companyId" ng-model="company.id" ng-bind="company.id" value="${id}">
			      				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="name" class="col-sm-2 control-label">公司名称</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="name" ng-model="company.name" ng-bind="company.name" placeholder="请输入公司名称" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="province" class="col-sm-2 control-label">省编码</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="province" ng-model="company.province" ng-bind="company.province" placeholder="请输入省编码" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="city" class="col-sm-2 control-label">市编码</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="city" ng-model="company.city" ng-bind="company.city" placeholder="请输入市编码" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="county" class="col-sm-2 control-label">县编码</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="county" ng-model="company.county" ng-bind="company.county" placeholder="请输入县编码" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="financing" class="col-sm-2 control-label">融资规模</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="financing" ng-model="company.financing" ng-bind="company.financing" placeholder="请输入融资规模" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="approved" class="col-sm-2 control-label">认证状态</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="approved" ng-model="company.approved" ng-bind="company.approved" placeholder="请输入认证状态" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="freezed" class="col-sm-2 control-label">冻结状态</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="freezed" ng-model="company.freezed" ng-bind="company.freezed" placeholder="请输入冻结状态" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="slogan" class="col-sm-2 control-label">公司标语</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="slogan" ng-model="company.slogan" ng-bind="company.slogan" placeholder="请输入公司标语" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="summary" class="col-sm-2 control-label">公司介绍</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="summary" ng-model="company.summary" ng-bind="company.summary" placeholder="请输入公司介绍" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="phone" class="col-sm-2 control-label">手机</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="phone" ng-model="company.phone" ng-bind="company.phone" placeholder="请输入手机" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="address" class="col-sm-2 control-label">详细地址</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="address" ng-model="company.address" ng-bind="company.address" placeholder="请输入详细地址" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="mail" class="col-sm-2 control-label">邮箱</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="mail" ng-model="company.mail" ng-bind="company.mail" placeholder="请输入邮箱" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="createBy" class="col-sm-2 control-label">创建人ID</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="createBy" ng-model="company.createBy" ng-bind="company.createBy" placeholder="请输入创建人ID" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       	           
	                <div class="form-group">
			        <label for="updateBy" class="col-sm-2 control-label">更新人ID</label>
			        <div class="col-sm-6">
			          <input type="text" class="form-control" name="updateBy" ng-model="company.updateBy" ng-bind="company.updateBy" placeholder="请输入更新人ID" required>
			        </div>
			        </div>
	           
		   
	               			      
				  				  
				    
				 
				
		   
	            				 
				 			       				     
				     
				    
			        			      
				  				  
				    
				 
				
		   
	            				 
				 			       				     
				     
				    
			        			      
				  				  
				    
				 
				
		   
	            					  <div class="form-group">
			        <div class="col-sm-offset-2 col-sm-10">
			          <button class="btn btn-primary col-sm-2" type="submit" ng-click="updateCompany(company)">保存</button>
			          <a class="btn btn-primary col-sm-2 col-sm-offset-2" ng-href="/web/c/company">取消</a>
			        </div>
			      </div>
			     
			    </form>
			 </div>
		 </div>
	 </div>
	 
	 <script src="/r/js-src/web/carrots-home-service/company/companyDetail.js"></script>
