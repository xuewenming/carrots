'use strict';
angular.module('carrots_home')
    .controller('professionCtrl', function($state,$scope, $rootScope,$cookies,commonUtil, professionService) {

        var vm = $scope.vm = {};
        
         vm.params = {    
                   	     id: $state.params.id
	                              ,  
                     	     companyId: $state.params.companyId
	                              ,  
                     	     name: $state.params.name
	                              ,  
                     	     category: $state.params.category
	                              ,  
                     	     subCategory: $state.params.subCategory
	                              ,  
                     	     education: $state.params.education
	                              ,  
                     	     experience: $state.params.experience
	                	     recommend: $state.params.recommend
	                	     compensation: $state.params.compensation
	                	     responsibility: $state.params.responsibility
	                	     requisite: $state.params.requisite
	                	     boon: $state.params.boon
	                	     status: $state.params.status
	                	     createBy: $state.params.createBy
	                	     updateBy: $state.params.updateBy
	                	     updateAt: $state.params.updateAt
	                	     createAt: $state.params.createAt
	                         };


    professionService.getList(vm.params).then(function(res) {
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
        
        $state.go("field.professionList", vm.params,{reload:true});
    };
    });
