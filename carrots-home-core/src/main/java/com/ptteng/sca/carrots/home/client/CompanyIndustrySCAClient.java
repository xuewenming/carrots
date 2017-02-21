/**
 * 
 */
package com.ptteng.sca.carrots.home.client;

import java.util.List;
import java.util.Map;

import com.ptteng.carrots.home.model.CompanyIndustry;
import com.ptteng.carrots.home.service.CompanyIndustryService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class CompanyIndustrySCAClient implements CompanyIndustryService {

    private CompanyIndustryService companyIndustryService;

	public CompanyIndustryService getCompanyIndustryService() {
		return companyIndustryService;
	}
	
	
	public void setCompanyIndustryService(CompanyIndustryService companyIndustryService) {
		this.companyIndustryService =companyIndustryService;
	}
	
	
			   
		@Override
		public Long insert(CompanyIndustry companyIndustry)throws ServiceException, ServiceDaoException{
		
		return companyIndustryService.insert(companyIndustry);
		          
		
		}	
		  
    	   
		@Override
		public List<CompanyIndustry> insertList(List<CompanyIndustry> companyIndustryList)throws ServiceException, ServiceDaoException{
		
		return companyIndustryService.insertList(companyIndustryList);
		          
		
		}	
		  
    	   
		@Override
		public boolean delete(Long id)throws ServiceException, ServiceDaoException{
		
		return companyIndustryService.delete(id);
		          
		
		}	
		  
    	   
		@Override
		public boolean update(CompanyIndustry companyIndustry)throws ServiceException, ServiceDaoException{
		
		return companyIndustryService.update(companyIndustry);
		          
		
		}	
		  
    	   
		@Override
		public boolean updateList(List<CompanyIndustry> companyIndustryList)throws ServiceException, ServiceDaoException{
		
		return companyIndustryService.updateList(companyIndustryList);
		          
		
		}	
		  
    	   
		@Override
		public CompanyIndustry getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
		return companyIndustryService.getObjectById(id);
		          
		
		}	
		  
    	   
		@Override
		public List<CompanyIndustry> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
		return companyIndustryService.getObjectsByIds(ids);
		          
		
		}	
		  
    	
	
	
	
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public List<Long>  getCompanyIndustryIdsByCompanyId(Long companyId,Integer start,Integer limit)throws ServiceException, ServiceDaoException{
		
		return companyIndustryService.getCompanyIndustryIdsByCompanyId(companyId,start,limit);
	
	
	}
	
		
	
	
    			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public Integer  countCompanyIndustryIdsByCompanyId(Long companyId)throws ServiceException, ServiceDaoException{
		
		return companyIndustryService.countCompanyIndustryIdsByCompanyId(companyId);
	
	
	}
	
		
	
		@Override
	public List<Long> getCompanyIndustryIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return companyIndustryService.getCompanyIndustryIds(start, limit);
	}

	@Override
	public Integer countCompanyIndustryIds() throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return companyIndustryService.countCompanyIndustryIds();
	}
	
	
	@Override
	public List<Long> getIdsByDynamicCondition(Class clz, Map<String, Object> conditions, Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return companyIndustryService.getIdsByDynamicCondition(clz, conditions, start, limit);
	}


	@Override
	public boolean fakeDelete(Class clz, Long id) throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return companyIndustryService.fakeDelete(clz, id);
	}
	
	
	
	@Override
	public void deleteList(Class clz, List<Long> ids) throws ServiceException, ServiceDaoException {
		   companyIndustryService.deleteList(clz, ids);
		
	}
	
/*	@Override
	public Object getObjectByDynamicCondition(Class clz,
			Map<String, Object> conditions, Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
	
		return this.companyIndustryService.getObjectByDynamicCondition(clz, conditions, start, limit);
	}
*/

 
}

