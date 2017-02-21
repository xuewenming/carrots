package com.ptteng.carrots.home.service;

import java.util.List;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.ptteng.carrots.home.model.CompanyTags;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.ptteng.common.dao.BaseDaoService;

@Remotable
public interface CompanyTagsService extends BaseDaoService {

	



   		   
		
		public Long insert(CompanyTags companyTags)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<CompanyTags> insertList(List<CompanyTags> companyTagsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(CompanyTags companyTags)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean updateList(List<CompanyTags> companyTagsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public CompanyTags getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<CompanyTags> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		  
    	
	


			
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer  countCompanyTagsIdsByCompanyId(Long companyId)throws ServiceException, ServiceDaoException;
					
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long>  getCompanyTagsIdsByCompanyId(Long companyId,Integer start,Integer limit)throws ServiceException, ServiceDaoException;
		
	
	
	
	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getCompanyTagsIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException;

	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer countCompanyTagsIds() throws ServiceException, ServiceDaoException;
	

}

