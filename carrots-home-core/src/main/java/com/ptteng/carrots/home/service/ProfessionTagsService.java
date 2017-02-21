package com.ptteng.carrots.home.service;

import java.util.List;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.ptteng.carrots.home.model.ProfessionTags;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.ptteng.common.dao.BaseDaoService;

@Remotable
public interface ProfessionTagsService extends BaseDaoService {

	



   		   
		
		public Long insert(ProfessionTags professionTags)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<ProfessionTags> insertList(List<ProfessionTags> professionTagsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(ProfessionTags professionTags)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean updateList(List<ProfessionTags> professionTagsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public ProfessionTags getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<ProfessionTags> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		  
    	
	


			
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer  countProfessionTagsIdsByCompanyId(Long companyId)throws ServiceException, ServiceDaoException;
			
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer  countProfessionTagsIdsByProfessionId(Long professionId)throws ServiceException, ServiceDaoException;
					
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long>  getProfessionTagsIdsByCompanyId(Long companyId,Integer start,Integer limit)throws ServiceException, ServiceDaoException;
			
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long>  getProfessionTagsIdsByProfessionId(Long professionId,Integer start,Integer limit)throws ServiceException, ServiceDaoException;
		
	
	
	
	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getProfessionTagsIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException;

	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer countProfessionTagsIds() throws ServiceException, ServiceDaoException;
	

}

