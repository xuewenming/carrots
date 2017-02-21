'use strict';
angular.module('carrots_home')
    .controller('companyIndustryCtrl', function($state,$scope, $rootScope,$cookies,commonUtil, companyIndustryService) {

        var vm = $scope.vm = {};
        
         vm.params = {    
                   	     id: $state.params.id
	                              ,  
                     	     companyId: $state.params.companyId
	                              ,  
                     	     industry: $state.params.industry
	                              ,  
                     	     createBy: $state.params.createBy
	                              ,  
                     	     updateBy: $state.params.updateBy
	                              ,  
                     	     updateAt: $state.params.updateAt
	                              ,  
                     	     createAt: $state.params.createAt
	                         };


    companyIndustryService.getList(vm.params).then(function(res) {
        if (res.data.code === 0) {
            vm.list = res.data.data;
            vm.page = {
                next: res.data.next || 0,
                size: res.data.size || 0,
                page: res.data.page || 0,
                total: res.data.total || 0
            };
        } else {
            $rootScope.alert(res.data.message);
        }
    });
  

    vm.search = function() {
        
        $state.go("field.companyIndustryList", vm.params,{reload:true});
    };
    });
