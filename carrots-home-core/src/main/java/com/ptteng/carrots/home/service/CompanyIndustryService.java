package com.ptteng.carrots.home.service;

import java.util.List;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.ptteng.carrots.home.model.CompanyIndustry;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.ptteng.common.dao.BaseDaoService;

@Remotable
public interface CompanyIndustryService extends BaseDaoService {

	



   		   
		
		public Long insert(CompanyIndustry companyIndustry)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<CompanyIndustry> insertList(List<CompanyIndustry> companyIndustryList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(CompanyIndustry companyIndustry)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean updateList(List<CompanyIndustry> companyIndustryList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public CompanyIndustry getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<CompanyIndustry> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		  
    	
	


			
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer  countCompanyIndustryIdsByCompanyId(Long companyId)throws ServiceException, ServiceDaoException;
					
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long>  getCompanyIndustryIdsByCompanyId(Long companyId,Integer start,Integer limit)throws ServiceException, ServiceDaoException;
		
	
	
	
	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getCompanyIndustryIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException;

	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer countCompanyIndustryIds() throws ServiceException, ServiceDaoException;
	

}

