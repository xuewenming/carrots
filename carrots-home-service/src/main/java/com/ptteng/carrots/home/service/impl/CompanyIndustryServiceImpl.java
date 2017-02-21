package com.ptteng.carrots.home.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.ptteng.carrots.home.model.CompanyIndustry;
import com.ptteng.carrots.home.service.CompanyIndustryService;



import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.dal.dao.Dao;
import com.gemantic.dal.dao.exception.DaoException;
import com.ptteng.common.dao.BaseDaoServiceImpl;


public class CompanyIndustryServiceImpl extends BaseDaoServiceImpl implements CompanyIndustryService {

 

	private static final Log log = LogFactory.getLog(CompanyIndustryServiceImpl.class);



		   
		@Override
		public Long insert(CompanyIndustry companyIndustry)throws ServiceException, ServiceDaoException{
		
	
		          
    log.info(" insert data : " + companyIndustry);

		if (companyIndustry == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		companyIndustry.setCreateAt(currentTimeMillis);
		companyIndustry.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(companyIndustry);
		} catch (DaoException e) {
			log.error(" insert wrong : " + companyIndustry);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
    
		log.info(" insert data success : " + result);
    
return result;	
		}	
		  
    	   
		@Override
		public List<CompanyIndustry> insertList(List<CompanyIndustry> companyIndustryList)throws ServiceException, ServiceDaoException{
		
	
		          	
        log.info(" insert lists : " + (companyIndustryList == null ? "null" : companyIndustryList.size()));
      
		List<CompanyIndustry> resultList = null;

		if (CollectionUtils.isEmpty(companyIndustryList)) {
			return new ArrayList<CompanyIndustry>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyIndustry companyIndustry : companyIndustryList) {
			companyIndustry.setCreateAt(currentTimeMillis);
			companyIndustry.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<CompanyIndustry>) dao.batchSave(companyIndustryList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + companyIndustryList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
    
		log.info(" insert lists  success : " + (resultList == null ? "null" : resultList.size()));
    
		return resultList;
		
		
			
		}	
		  
    	   
		@Override
		public boolean delete(Long id)throws ServiceException, ServiceDaoException{
		
	
		            
	    log.info(" delete data : " + id);
 
		boolean result = false;

		if (id == null) {
			return true;
		}

		try {
			result = dao.delete(CompanyIndustry.class, id);
		} catch (DaoException e) {
			log.error(" delete wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
 
		log.info(" delete data success : " + id);
   
		return result;
		
		}	
		  
    	   
		@Override
		public boolean update(CompanyIndustry companyIndustry)throws ServiceException, ServiceDaoException{
		
	
		          
	log.info(" update data : " + (companyIndustry == null ? "null" : companyIndustry.getId()));

		boolean result = false;

		if (companyIndustry == null) {
			return true;
		}

		companyIndustry.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(companyIndustry);
		} catch (DaoException e) {
			log.error(" update wrong : " + companyIndustry);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
       if(log.isInfoEnabled()){
		log.info(" update data success : " + companyIndustry);
       }
		return result;	
		}	
		  
    	   
		@Override
		public boolean updateList(List<CompanyIndustry> companyIndustryList)throws ServiceException, ServiceDaoException{
		
	
		          log.info(" update lists : " + (companyIndustryList == null ? "null" : companyIndustryList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(companyIndustryList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyIndustry companyIndustry : companyIndustryList) {
			companyIndustry.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(companyIndustryList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + companyIndustryList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     
		log.info(" update lists success : " + companyIndustryList.size());
         
		return result;	
		}	
		  
    	   
		@Override
		public CompanyIndustry getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
	
		              
        log.info(" get data : " + id);
      
		CompanyIndustry companyIndustry = null;

		if (id == null) {
			return companyIndustry;
		}

		try {
			companyIndustry = (CompanyIndustry) dao.get(CompanyIndustry.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     
		log.info(" get data success : " + id);
      
		return companyIndustry;		
		}	
		  
    	   
		@Override
		public List<CompanyIndustry> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
	
		          	  
	    log.info(" get lists : " + (ids == null ? "null" : ids));
     
		List<CompanyIndustry> companyIndustry = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<CompanyIndustry>();
		}

		try {
			companyIndustry = (List<CompanyIndustry>) dao.getList(CompanyIndustry.class, ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
    
		log.info(" get data success : " + (companyIndustry == null ? "null" : companyIndustry.size()));
    
		return companyIndustry;	
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
		
		       if(log.isInfoEnabled()){
      log.info(" get ids by companyId,start,limit  : " + companyId+" , "+start+" , "+limit );
	  }
	 	List<Long> idList = null;
        
        // TODO 参数检查!

        if (start == null) {
            start = 0;
        }

        if (limit == null) {
            limit = Integer.MAX_VALUE;
        }

	try {
		idList = dao.getIdList("getCompanyIndustryIdsByCompanyId", new Object[] { companyId},start,limit, false);

   
   } catch (DaoException e) {
			log.error(" get ids  wrong by companyId,start,limit)  : " + companyId+" , "+start+" , "+limit );
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
  if(log.isInfoEnabled()){
   log.info(" get ids success : " + (idList == null ? "null" : idList.size()));
  }
		return idList;
		
	
	
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
		
		       if(log.isInfoEnabled()){
      log.info(" count ids by companyId  : " + companyId );
	  }
	 	Integer count=null;
        
       

	try {
	   
		count = dao.count("getCompanyIndustryIdsByCompanyId", new Object[] { companyId});

   
   } catch (DaoException e) {
			log.error(" count ids  wrong by companyId)  : " + companyId );
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
  if(log.isInfoEnabled()){
    log.info(" count  success : " + count);
  }
		return count;
		
	
	
	}
	
		
	
	
	
		
	@Override
	public List<Long> getCompanyIndustryIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
		
		log.info(" get ids   by start,limit  ================== " + start + " , " + limit);
		List<Long> idList = null;
		
		
		
		if (start == null) {
			start = 0;
		}

		if (limit == null) {
			limit = Integer.MAX_VALUE;
		}
		
		try {
			idList = dao.getIdList("getCompanyIndustryIdsAll",new Object[] {},start, limit, false);
		} catch (DaoException e) {
			log.error(" get ids  wrong by start,limit)  : " + start + " , " + limit);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get ids success == : " + (idList == null ? "null" : idList.size()));
		}
		return idList;
	}
	
	
		@Override
	public Integer countCompanyIndustryIds() throws ServiceException,
			ServiceDaoException {
		Integer count =  0;
		try {
			count = dao.count("getCompanyIndustryIdsAll",new Object[] {});
		} catch (DaoException e) {
			log.error(" count by getCompanyIndustryIds " ) ;
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" count  : " + count);
		}
		return count;
	}

}

