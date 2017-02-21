package com.ptteng.carrots.home.service;

import java.util.List;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.ptteng.carrots.home.model.Company;
import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.ptteng.common.dao.BaseDaoService;

@Remotable
public interface CompanyService extends BaseDaoService {

	



   		   
		
		public Long insert(Company company)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<Company> insertList(List<Company> companyList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(Company company)throws ServiceException, ServiceDaoException;
		  
		
    	//在不更新公司的updateAt的前提下,更新公司的professionUpdateAt和professionNum   
		public boolean updateWithoutChangeUpdateAt(Company company) throws ServiceException, ServiceDaoException;
		
		
		public boolean updateList(List<Company> companyList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public Company getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<Company> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		  
    	
	


				
	
	
	
	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getCompanyIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException;

	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer countCompanyIds() throws ServiceException, ServiceDaoException;
	

}

