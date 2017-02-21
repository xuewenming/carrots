/**
 * 
 */
package com.ptteng.sca.carrots.home.client;

import java.util.List;
import java.util.Map;

import com.ptteng.carrots.home.model.ProfessionTags;
import com.ptteng.carrots.home.service.ProfessionTagsService;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;

public class ProfessionTagsSCAClient implements ProfessionTagsService {

    private ProfessionTagsService professionTagsService;

	public ProfessionTagsService getProfessionTagsService() {
		return professionTagsService;
	}
	
	
	public void setProfessionTagsService(ProfessionTagsService professionTagsService) {
		this.professionTagsService =professionTagsService;
	}
	
	
			   
		@Override
		public Long insert(ProfessionTags professionTags)throws ServiceException, ServiceDaoException{
		
		return professionTagsService.insert(professionTags);
		          
		
		}	
		  
    	   
		@Override
		public List<ProfessionTags> insertList(List<ProfessionTags> professionTagsList)throws ServiceException, ServiceDaoException{
		
		return professionTagsService.insertList(professionTagsList);
		          
		
		}	
		  
    	   
		@Override
		public boolean delete(Long id)throws ServiceException, ServiceDaoException{
		
		return professionTagsService.delete(id);
		          
		
		}	
		  
    	   
		@Override
		public boolean update(ProfessionTags professionTags)throws ServiceException, ServiceDaoException{
		
		return professionTagsService.update(professionTags);
		          
		
		}	
		  
    	   
		@Override
		public boolean updateList(List<ProfessionTags> professionTagsList)throws ServiceException, ServiceDaoException{
		
		return professionTagsService.updateList(professionTagsList);
		          
		
		}	
		  
    	   
		@Override
		public ProfessionTags getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
		return professionTagsService.getObjectById(id);
		          
		
		}	
		  
    	   
		@Override
		public List<ProfessionTags> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
		return professionTagsService.getObjectsByIds(ids);
		          
		
		}	
		  
    	
	
	
	
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public List<Long>  getProfessionTagsIdsByCompanyId(Long companyId,Integer start,Integer limit)throws ServiceException, ServiceDaoException{
		
		return professionTagsService.getProfessionTagsIdsByCompanyId(companyId,start,limit);
	
	
	}
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public List<Long>  getProfessionTagsIdsByProfessionId(Long professionId,Integer start,Integer limit)throws ServiceException, ServiceDaoException{
		
		return professionTagsService.getProfessionTagsIdsByProfessionId(professionId,start,limit);
	
	
	}
	
		
	
	
    			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public Integer  countProfessionTagsIdsByCompanyId(Long companyId)throws ServiceException, ServiceDaoException{
		
		return professionTagsService.countProfessionTagsIdsByCompanyId(companyId);
	
	
	}
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public Integer  countProfessionTagsIdsByProfessionId(Long professionId)throws ServiceException, ServiceDaoException{
		
		return professionTagsService.countProfessionTagsIdsByProfessionId(professionId);
	
	
	}
	
		
	
		@Override
	public List<Long> getProfessionTagsIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return professionTagsService.getProfessionTagsIds(start, limit);
	}

	@Override
	public Integer countProfessionTagsIds() throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return professionTagsService.countProfessionTagsIds();
	}
	
	
	@Override
	public List<Long> getIdsByDynamicCondition(Class clz, Map<String, Object> conditions, Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return professionTagsService.getIdsByDynamicCondition(clz, conditions, start, limit);
	}


	@Override
	public boolean fakeDelete(Class clz, Long id) throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return professionTagsService.fakeDelete(clz, id);
	}
	
	
	
	@Override
	public void deleteList(Class clz, List<Long> ids) throws ServiceException, ServiceDaoException {
		   professionTagsService.deleteList(clz, ids);
		
	}
	
/*	@Override
	public Object getObjectByDynamicCondition(Class clz,
			Map<String, Object> conditions, Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
	
		return this.professionTagsService.getObjectByDynamicCondition(clz, conditions, start, limit);
	}
*/

 
}

