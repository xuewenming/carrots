'use strict';
angular.module('carrots_home')

    .factory('pathProject', function (commonUtil) {
        return {
                	     article: function (id) {
                if (!id) {
                    return "ajax/u/article";
                } else {
                    return "ajax/u/article/" + id;
                }
            },
          article_list: "ajax/u/article/search"
          
                                   ,  
                        
          
         
         	     article: function (id) {
                if (!id) {
                    return "ajax/u/article";
                } else {
                    return "ajax/u/article/" + id;
                }
            },
          article_list: "ajax/u/article/search"
          
                                   ,  
                        
          
         
         	     article: function (id) {
                if (!id) {
                    return "ajax/u/article";
                } else {
                    return "ajax/u/article/" + id;
                }
            },
          article_list: "ajax/u/article/search"
          
                                   ,  
                        
          
         
         	     article: function (id) {
                if (!id) {
                    return "ajax/u/article";
                } else {
                    return "ajax/u/article/" + id;
                }
            },
          article_list: "ajax/u/article/search"
          
                                   ,  
                        
          
         
         	     article: function (id) {
                if (!id) {
                    return "ajax/u/article";
                } else {
                    return "ajax/u/article/" + id;
                }
            },
          article_list: "ajax/u/article/search"
          
                                   ,  
                        
          
         
         	     article: function (id) {
                if (!id) {
                    return "ajax/u/article";
                } else {
                    return "ajax/u/article/" + id;
                }
            },
          article_list: "ajax/u/article/search"
          
                                   ,  
                        
          
         
         	     article: function (id) {
                if (!id) {
                    return "ajax/u/article";
                } else {
                    return "ajax/u/article/" + id;
                }
            },
          article_list: "ajax/u/article/search"
          
                        
          
         
         
        }
    })
    
           
	    .factory('companyService', function ($http, pathProject) {
        return {
            add: function (params) {
                return $http.post(pathProject.company(), params);
            },
            update: function (id, params) {
              
                return $http.put(pathProject.company(id), params);
            },
            getList: function (params) {
                return $http.get(pathProject.company_list, {params: params});
            },
            get: function (id) {
                return $http.get(pathProject.company(id));
            },            
            del: function (id) {
                
                return $http.delete(pathProject.company(id));
            }
           
        }
    })
                
	    .factory('companyIndustryService', function ($http, pathProject) {
        return {
            add: function (params) {
                return $http.post(pathProject.companyIndustry(), params);
            },
            update: function (id, params) {
              
                return $http.put(pathProject.companyIndustry(id), params);
            },
            getList: function (params) {
                return $http.get(pathProject.companyIndustry_list, {params: params});
            },
            get: function (id) {
                return $http.get(pathProject.companyIndustry(id));
            },            
            del: function (id) {
                
                return $http.delete(pathProject.companyIndustry(id));
            }
           
        }
    })
                
	    .factory('companyTagsService', function ($http, pathProject) {
        return {
            add: function (params) {
                return $http.post(pathProject.companyTags(), params);
            },
            update: function (id, params) {
              
                return $http.put(pathProject.companyTags(id), params);
            },
            getList: function (params) {
                return $http.get(pathProject.companyTags_list, {params: params});
            },
            get: function (id) {
                return $http.get(pathProject.companyTags(id));
            },            
            del: function (id) {
                
                return $http.delete(pathProject.companyTags(id));
            }
           
        }
    })
                
	    .factory('productService', function ($http, pathProject) {
        return {
            add: function (params) {
                return $http.post(pathProject.product(), params);
            },
            update: function (id, params) {
              
                return $http.put(pathProject.product(id), params);
            },
            getList: function (params) {
                return $http.get(pathProject.product_list, {params: params});
            },
            get: function (id) {
                return $http.get(pathProject.product(id));
            },            
            del: function (id) {
                
                return $http.delete(pathProject.product(id));
            }
           
        }
    })
                
	    .factory('professionService', function ($http, pathProject) {
        return {
            add: function (params) {
                return $http.post(pathProject.profession(), params);
            },
            update: function (id, params) {
              
                return $http.put(pathProject.profession(id), params);
            },
            getList: function (params) {
                return $http.get(pathProject.profession_list, {params: params});
            },
            get: function (id) {
                return $http.get(pathProject.profession(id));
            },            
            del: function (id) {
                
                return $http.delete(pathProject.profession(id));
            }
           
        }
    })
                
	    .factory('professionTagsService', function ($http, pathProject) {
        return {
            add: function (params) {
                return $http.post(pathProject.professionTags(), params);
            },
            update: function (id, params) {
              
                return $http.put(pathProject.professionTags(id), params);
            },
            getList: function (params) {
                return $http.get(pathProject.professionTags_list, {params: params});
            },
            get: function (id) {
                return $http.get(pathProject.professionTags(id));
            },            
            del: function (id) {
                
                return $http.delete(pathProject.professionTags(id));
            }
           
        }
    })
                
	    .factory('articleService', function ($http, pathProject) {
        return {
            add: function (params) {
                return $http.post(pathProject.article(), params);
            },
            update: function (id, params) {
              
                return $http.put(pathProject.article(id), params);
            },
            getList: function (params) {
                return $http.get(pathProject.article_list, {params: params});
            },
            get: function (id) {
                return $http.get(pathProject.article(id));
            },            
            del: function (id) {
                
                return $http.delete(pathProject.article(id));
            }
           
        }
    })
             
   
    ;






