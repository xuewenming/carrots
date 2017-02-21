'use strict';
angular.module('carrots_home')
    .controller('companyCtrl', function($state,$scope, $rootScope,$cookies,commonUtil, companyService) {

        var vm = $scope.vm = {};
        
         vm.params = {    
                   	     id: $state.params.id
	                              ,  
                     	     name: $state.params.name
	                              ,  
                     	     province: $state.params.province
	                              ,  
                     	     city: $state.params.city
	                              ,  
                     	     county: $state.params.county
	                              ,  
                     	     financing: $state.params.financing
	                              ,  
                     	     approved: $state.params.approved
	                	     freezed: $state.params.freezed
	                	     slogan: $state.params.slogan
	                	     summary: $state.params.summary
	                	     phone: $state.params.phone
	                	     address: $state.params.address
	                	     mail: $state.params.mail
	                	     createBy: $state.params.createBy
	                	     updateBy: $state.params.updateBy
	                	     updateAt: $state.params.updateAt
	                	     createAt: $state.params.createAt
	                         };


    companyService.getList(vm.params).then(function(res) {
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
        
        $state.go("field.companyList", vm.params,{reload:true});
    };
    });
