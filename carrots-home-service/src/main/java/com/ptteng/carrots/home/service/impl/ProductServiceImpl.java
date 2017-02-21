package com.ptteng.carrots.home.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.osoa.sca.annotations.Remotable;

import com.ptteng.carrots.home.model.Product;
import com.ptteng.carrots.home.service.ProductService;



import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.dal.dao.Dao;
import com.gemantic.dal.dao.exception.DaoException;
import com.ptteng.common.dao.BaseDaoServiceImpl;


public class ProductServiceImpl extends BaseDaoServiceImpl implements ProductService {

 

	private static final Log log = LogFactory.getLog(ProductServiceImpl.class);



		   
		@Override
		public Long insert(Product product)throws ServiceException, ServiceDaoException{
		
	
		          
    log.info(" insert data : " + product);

		if (product == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		product.setCreateAt(currentTimeMillis);
		product.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(product);
		} catch (DaoException e) {
			log.error(" insert wrong : " + product);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
    
		log.info(" insert data success : " + result);
    
return result;	
		}	
		  
    	   
		@Override
		public List<Product> insertList(List<Product> productList)throws ServiceException, ServiceDaoException{
		
	
		          	
        log.info(" insert lists : " + (productList == null ? "null" : productList.size()));
      
		List<Product> resultList = null;

		if (CollectionUtils.isEmpty(productList)) {
			return new ArrayList<Product>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (Product product : productList) {
			product.setCreateAt(currentTimeMillis);
			product.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<Product>) dao.batchSave(productList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + productList);
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
			result = dao.delete(Product.class, id);
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
		public boolean update(Product product)throws ServiceException, ServiceDaoException{
		
	
		          
	log.info(" update data : " + (product == null ? "null" : product.getId()));

		boolean result = false;

		if (product == null) {
			return true;
		}

		product.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(product);
		} catch (DaoException e) {
			log.error(" update wrong : " + product);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
       if(log.isInfoEnabled()){
		log.info(" update data success : " + product);
       }
		return result;	
		}	
		  
    	   
		@Override
		public boolean updateList(List<Product> productList)throws ServiceException, ServiceDaoException{
		
	
		          log.info(" update lists : " + (productList == null ? "null" : productList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(productList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (Product product : productList) {
			product.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(productList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + productList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     
		log.info(" update lists success : " + productList.size());
         
		return result;	
		}	
		  
    	   
		@Override
		public Product getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
	
		              
        log.info(" get data : " + id);
      
		Product product = null;

		if (id == null) {
			return product;
		}

		try {
			product = (Product) dao.get(Product.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
     
		log.info(" get data success : " + id);
      
		return product;		
		}	
		  
    	   
		@Override
		public List<Product> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
	
		          	  
	    log.info(" get lists : " + (ids == null ? "null" : ids));
     
		List<Product> product = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<Product>();
		}

		try {
			product = (List<Product>) dao.getList(Product.class, ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
    
		log.info(" get data success : " + (product == null ? "null" : product.size()));
    
		return product;	
		}	
		  
    	
		
	
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public List<Long>  getProductIdsByCompanyId(Long companyId,Integer start,Integer limit)throws ServiceException, ServiceDaoException{
		
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
		idList = dao.getIdList("getProductIdsByCompanyId", new Object[] { companyId},start,limit, false);

   
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
	public Integer  countProductIdsByCompanyId(Long companyId)throws ServiceException, ServiceDaoException{
		
		       if(log.isInfoEnabled()){
      log.info(" count ids by companyId  : " + companyId );
	  }
	 	Integer count=null;
        
       

	try {
	   
		count = dao.count("getProductIdsByCompanyId", new Object[] { companyId});

   
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
	public List<Long> getProductIds(Integer start, Integer limit)
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
			idList = dao.getIdList("getProductIdsAll",new Object[] {},start, limit, false);
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
	public Integer countProductIds() throws ServiceException,
			ServiceDaoException {
		Integer count =  0;
		try {
			count = dao.count("getProductIdsAll",new Object[] {});
		} catch (DaoException e) {
			log.error(" count by getProductIds " ) ;
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

