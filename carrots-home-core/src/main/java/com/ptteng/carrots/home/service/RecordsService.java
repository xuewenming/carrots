package com.ptteng.carrots.home.service;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.ptteng.common.dao.BaseDaoService;
import com.ptteng.carrots.home.model.Records;

import org.osoa.sca.annotations.Remotable;

import java.util.List;

@Remotable
public interface RecordsService extends BaseDaoService {

	



   		   
		
		public Long insert(Records records)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<Records> insertList(List<Records> recordsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(Records records)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean updateList(List<Records> recordsList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public Records getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<Records> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		  
    	
	


				
	
	
	
	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getRecordsIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException;

	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer countRecordsIds() throws ServiceException, ServiceDaoException;
	

}

