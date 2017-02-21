package com.ptteng.carrots.home.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.ptteng.carrots.home.model.CompanyTags;
import com.ptteng.carrots.home.service.CompanyTagsService;



import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.dal.dao.Dao;
import com.gemantic.dal.dao.exception.DaoException;
import com.ptteng.common.dao.BaseDaoServiceImpl;


public class CompanyTagsServiceImpl extends BaseDaoServiceImpl implements CompanyTagsService {

 

	private static final Log log = LogFactory.getLog(CompanyTagsServiceImpl.class);



		   
		@Override
		public Long insert(CompanyTags companyTags)throws ServiceException, ServiceDaoException{
		
	
		          
    log.info(" insert data : " + companyTags);

		if (companyTags == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		companyTags.setCreateAt(currentTimeMillis);
		companyTags.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(companyTags);
		} catch (DaoException e) {
			log.error(" insert wrong : " + companyTags);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
    
		log.info(" insert data success : " + result);
    
return result;	
		}	
		  
    	   
		@Override
		public List<CompanyTags> insertList(List<CompanyTags> companyTagsList)throws ServiceException, ServiceDaoException{
		
	
		          	
        log.info(" insert lists : " + (companyTagsList == null ? "null" : companyTagsList.size()));
      
		List<CompanyTags> resultList = null;

		if (CollectionUtils.isEmpty(companyTagsList)) {
			return new ArrayList<CompanyTags>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyTags companyTags : companyTagsList) {
			companyTags.setCreateAt(currentTimeMillis);
			companyTags.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<CompanyTags>) dao.batchSave(companyTagsList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + companyTagsList);
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
			result = dao.delete(CompanyTags.class, id);
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
		public boolean update(CompanyTags companyTags)throws ServiceException, ServiceDaoException{
		
	
		          
	log.info(" update data : " + (companyTags == null ? "null" : companyTags.getId()));

		boolean result = false;

		if (companyTags == null) {
			return true;
		}

		companyTags.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(companyTags);
		} catch (DaoException e) {
			log.error(" update wrong : " + companyTags);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
       if(log.isInfoEnabled()){
		log.info(" update data success : " + companyTags);
       }
		return result;	
		}	
		  
    	   
		@Override
		public boolean updateList(List<CompanyTags> companyTagsList)throws ServiceException, ServiceDaoException{
		
	
		          log.info(" update lists : " + (companyTagsList == null ? "null" : companyTagsList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(companyTagsList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (CompanyTags companyTags : companyTagsList) {
			companyTags.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(companyTagsList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + companyTagsList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     
		log.info(" update lists success : " + companyTagsList.size());
         
		return result;	
		}	
		  
    	   
		@Override
		public CompanyTags getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
	
		              
        log.info(" get data : " + id);
      
		CompanyTags companyTags = null;

		if (id == null) {
			return companyTags;
		}

		try {
			companyTags = (CompanyTags) dao.get(CompanyTags.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     
		log.info(" get data success : " + id);
      
		return companyTags;		
		}	
		  
    	   
		@Override
		public List<CompanyTags> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
	
		          	  
	    log.info(" get lists : " + (ids == null ? "null" : ids));
     
		List<CompanyTags> companyTags = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<CompanyTags>();
		}

		try {
			companyTags = (List<CompanyTags>) dao.getList(CompanyTags.class, ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
    
		log.info(" get data success : " + (companyTags == null ? "null" : companyTags.size()));
    
		return companyTags;	
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
		idList = dao.getIdList("getCompanyTagsIdsByCompanyId", new Object[] { companyId},start,limit, false);

   
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
	public Integer  countCompanyTagsIdsByCompanyId(Long companyId)throws ServiceException, ServiceDaoException{
		
		       if(log.isInfoEnabled()){
      log.info(" count ids by companyId  : " + companyId );
	  }
	 	Integer count=null;
        
       

	try {
	   
		count = dao.count("getCompanyTagsIdsByCompanyId", new Object[] { companyId});

   
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
	public List<Long> getCompanyTagsIds(Integer start, Integer limit)
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
			idList = dao.getIdList("getCompanyTagsIdsAll",new Object[] {},start, limit, false);
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
	public Integer countCompanyTagsIds() throws ServiceException,
			ServiceDaoException {
		Integer count =  0;
		try {
			count = dao.count("getCompanyTagsIdsAll",new Object[] {});
		} catch (DaoException e) {
			log.error(" count by getCompanyTagsIds " ) ;
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

