'use strict';
angular.module('carrots_home')
    .controller('productCtrl', function($state,$scope, $rootScope,$cookies,commonUtil, productService) {

        var vm = $scope.vm = {};
        
         vm.params = {    
                   	     id: $state.params.id
	                              ,  
                     	     companyId: $state.params.companyId
	                              ,  
                     	     name: $state.params.name
	                              ,  
                     	     slogan: $state.params.slogan
	                              ,  
                     	     logo: $state.params.logo
	                              ,  
                     	     summary: $state.params.summary
	                              ,  
                     	     createBy: $state.params.createBy
	                	     updateBy: $state.params.updateBy
	                	     updateAt: $state.params.updateAt
	                	     createAt: $state.params.createAt
	                         };


    productService.getList(vm.params).then(function(res) {
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
        
        $state.go("field.productList", vm.params,{reload:true});
    };
    });
