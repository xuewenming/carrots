/**
 * 
 */
package com.ptteng.sca.carrots.home.client;

import java.util.List;
import java.util.Map;

import com.ptteng.carrots.home.model.CompanyTags;
import com.ptteng.carrots.home.service.CompanyTagsService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class CompanyTagsSCAClient implements CompanyTagsService {

    private CompanyTagsService companyTagsService;

	public CompanyTagsService getCompanyTagsService() {
		return companyTagsService;
	}
	
	
	public void setCompanyTagsService(CompanyTagsService companyTagsService) {
		this.companyTagsService =companyTagsService;
	}
	
	
			   
		@Override
		public Long insert(CompanyTags companyTags)throws ServiceException, ServiceDaoException{
		
		return companyTagsService.insert(companyTags);
		          
		
		}	
		  
    	   
		@Override
		public List<CompanyTags> insertList(List<CompanyTags> companyTagsList)throws ServiceException, ServiceDaoException{
		
		return companyTagsService.insertList(companyTagsList);
		          
		
		}	
		  
    	   
		@Override
		public boolean delete(Long id)throws ServiceException, ServiceDaoException{
		
		return companyTagsService.delete(id);
		          
		
		}	
		  
    	   
		@Override
		public boolean update(CompanyTags companyTags)throws ServiceException, ServiceDaoException{
		
		return companyTagsService.update(companyTags);
		          
		
		}	
		  
    	   
		@Override
		public boolean updateList(List<CompanyTags> companyTagsList)throws ServiceException, ServiceDaoException{
		
		return companyTagsService.updateList(companyTagsList);
		          
		
		}	
		  
    	   
		@Override
		public CompanyTags getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
		return companyTagsService.getObjectById(id);
		          
		
		}	
		  
    	   
		@Override
		public List<CompanyTags> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
		return companyTagsService.getObjectsByIds(ids);
		          
		
		}	
		  
    	
	
	
	
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public List<Long>  getCompanyTagsIdsByCompanyId(Long companyId,Integer start,Integer limit)throws ServiceException, ServiceDaoException{
		
		return companyTagsService.getCompanyTagsIdsByCompanyId(companyId,start,limit);
	
	
	}
	
		
	
	
    			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public Integer  countCompanyTagsIdsByCompanyId(Long companyId)throws ServiceException, ServiceDaoException{
		
		return companyTagsService.countCompanyTagsIdsByCompanyId(companyId);
	
	
	}
	
		
	
		@Override
	public List<Long> getCompanyTagsIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return companyTagsService.getCompanyTagsIds(start, limit);
	}

	@Override
	public Integer countCompanyTagsIds() throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return companyTagsService.countCompanyTagsIds();
	}
	
	
	@Override
	public List<Long> getIdsByDynamicCondition(Class clz, Map<String, Object> conditions, Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return companyTagsService.getIdsByDynamicCondition(clz, conditions, start, limit);
	}


	@Override
	public boolean fakeDelete(Class clz, Long id) throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return companyTagsService.fakeDelete(clz, id);
	}
	
	
	
	@Override
	public void deleteList(Class clz, List<Long> ids) throws ServiceException, ServiceDaoException {
		   companyTagsService.deleteList(clz, ids);
		
	}
	
/*	@Override
	public Object getObjectByDynamicCondition(Class clz,
			Map<String, Object> conditions, Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
	
		return this.companyTagsService.getObjectByDynamicCondition(clz, conditions, start, limit);
	}*/


 
}

