package com.ptteng.carrots.home.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.ptteng.carrots.home.model.ProfessionTags;
import com.ptteng.carrots.home.service.ProfessionTagsService;



import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.dal.dao.Dao;
import com.gemantic.dal.dao.exception.DaoException;
import com.ptteng.common.dao.BaseDaoServiceImpl;


public class ProfessionTagsServiceImpl extends BaseDaoServiceImpl implements ProfessionTagsService {

 

	private static final Log log = LogFactory.getLog(ProfessionTagsServiceImpl.class);



		   
		@Override
		public Long insert(ProfessionTags professionTags)throws ServiceException, ServiceDaoException{
		
	
		          
    log.info(" insert data : " + professionTags);

		if (professionTags == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		professionTags.setCreateAt(currentTimeMillis);
		professionTags.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(professionTags);
		} catch (DaoException e) {
			log.error(" insert wrong : " + professionTags);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
    
		log.info(" insert data success : " + result);
    
return result;	
		}	
		  
    	   
		@Override
		public List<ProfessionTags> insertList(List<ProfessionTags> professionTagsList)throws ServiceException, ServiceDaoException{
		
	
		          	
        log.info(" insert lists : " + (professionTagsList == null ? "null" : professionTagsList.size()));
      
		List<ProfessionTags> resultList = null;

		if (CollectionUtils.isEmpty(professionTagsList)) {
			return new ArrayList<ProfessionTags>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (ProfessionTags professionTags : professionTagsList) {
			professionTags.setCreateAt(currentTimeMillis);
			professionTags.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<ProfessionTags>) dao.batchSave(professionTagsList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + professionTagsList);
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
			result = dao.delete(ProfessionTags.class, id);
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
		public boolean update(ProfessionTags professionTags)throws ServiceException, ServiceDaoException{
		
	
		          
	log.info(" update data : " + (professionTags == null ? "null" : professionTags.getId()));

		boolean result = false;

		if (professionTags == null) {
			return true;
		}

		professionTags.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(professionTags);
		} catch (DaoException e) {
			log.error(" update wrong : " + professionTags);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
       if(log.isInfoEnabled()){
		log.info(" update data success : " + professionTags);
       }
		return result;	
		}	
		  
    	   
		@Override
		public boolean updateList(List<ProfessionTags> professionTagsList)throws ServiceException, ServiceDaoException{
		
	
		          log.info(" update lists : " + (professionTagsList == null ? "null" : professionTagsList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(professionTagsList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (ProfessionTags professionTags : professionTagsList) {
			professionTags.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(professionTagsList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + professionTagsList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     
		log.info(" update lists success : " + professionTagsList.size());
         
		return result;	
		}	
		  
    	   
		@Override
		public ProfessionTags getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
	
		              
        log.info(" get data : " + id);
      
		ProfessionTags professionTags = null;

		if (id == null) {
			return professionTags;
		}

		try {
			professionTags = (ProfessionTags) dao.get(ProfessionTags.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     
		log.info(" get data success : " + id);
      
		return professionTags;		
		}	
		  
    	   
		@Override
		public List<ProfessionTags> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
	
		          	  
	    log.info(" get lists : " + (ids == null ? "null" : ids));
     
		List<ProfessionTags> professionTags = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<ProfessionTags>();
		}

		try {
			professionTags = (List<ProfessionTags>) dao.getList(ProfessionTags.class, ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
    
		log.info(" get data success : " + (professionTags == null ? "null" : professionTags.size()));
    
		return professionTags;	
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
		idList = dao.getIdList("getProfessionTagsIdsByCompanyId", new Object[] { companyId},start,limit, false);

   
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
	public List<Long>  getProfessionTagsIdsByProfessionId(Long professionId,Integer start,Integer limit)throws ServiceException, ServiceDaoException{
		
		       if(log.isInfoEnabled()){
      log.info(" get ids by professionId,start,limit  : " + professionId+" , "+start+" , "+limit );
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
		idList = dao.getIdList("getProfessionTagsIdsByProfessionId", new Object[] { professionId},start,limit, false);

   
   } catch (DaoException e) {
			log.error(" get ids  wrong by professionId,start,limit)  : " + professionId+" , "+start+" , "+limit );
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
	public Integer  countProfessionTagsIdsByCompanyId(Long companyId)throws ServiceException, ServiceDaoException{
		
		       if(log.isInfoEnabled()){
      log.info(" count ids by companyId  : " + companyId );
	  }
	 	Integer count=null;
        
       

	try {
	   
		count = dao.count("getProfessionTagsIdsByCompanyId", new Object[] { companyId});

   
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
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public Integer  countProfessionTagsIdsByProfessionId(Long professionId)throws ServiceException, ServiceDaoException{
		
		       if(log.isInfoEnabled()){
      log.info(" count ids by professionId  : " + professionId );
	  }
	 	Integer count=null;
        
       

	try {
	   
		count = dao.count("getProfessionTagsIdsByProfessionId", new Object[] { professionId});

   
   } catch (DaoException e) {
			log.error(" count ids  wrong by professionId)  : " + professionId );
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
	public List<Long> getProfessionTagsIds(Integer start, Integer limit)
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
			idList = dao.getIdList("getProfessionTagsIdsAll",new Object[] {},start, limit, false);
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
	public Integer countProfessionTagsIds() throws ServiceException,
			ServiceDaoException {
		Integer count =  0;
		try {
			count = dao.count("getProfessionTagsIdsAll",new Object[] {});
		} catch (DaoException e) {
			log.error(" count by getProfessionTagsIds " ) ;
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

