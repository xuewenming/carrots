package com.ptteng.carrots.home.service;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.ptteng.common.dao.BaseDaoService;
import com.ptteng.carrots.home.model.Article;

import org.osoa.sca.annotations.Remotable;

import java.util.List;

@Remotable
public interface ArticleService extends BaseDaoService {

	



   		   
		
		public Long insert(Article article)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<Article> insertList(List<Article> articleList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean delete(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean update(Article article)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public boolean updateList(List<Article> articleList)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public Article getObjectById(Long id)throws ServiceException, ServiceDaoException;
		  
    	   
		
		public List<Article> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException;
		  
    	
	


			
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer countArticleIdsByStatusAndSourceOrderByPublishat(Integer status, String source)throws ServiceException, ServiceDaoException;
			
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer countArticleIdsBySourceOrderByPublishat(String source)throws ServiceException, ServiceDaoException;
			
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer countArticleIdsByTypeAndStatusOrderByPublishat(Integer type, Integer status)throws ServiceException, ServiceDaoException;
					
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getArticleIdsByStatusAndSourceOrderByPublishat(Integer status, String source, Integer start, Integer limit)throws ServiceException, ServiceDaoException;
			
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getArticleIdsBySourceOrderByPublishat(String source, Integer start, Integer limit)throws ServiceException, ServiceDaoException;
			
			
	/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getArticleIdsByTypeAndStatusOrderByPublishat(Integer type, Integer status, Integer start, Integer limit)throws ServiceException, ServiceDaoException;
		
	
	
	
	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long> getArticleIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException;

	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public Integer countArticleIds() throws ServiceException, ServiceDaoException;
	

	/**
	 * 
	 * @param 
	 * 		type 为3 行业大图
	 * 		Integer 行业编号 
	 * @return  行业大图
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	public List<Long>  getArticleIdsByTypeAndIndustry(Integer type,Long industry,Integer start,Integer limit)throws ServiceException, ServiceDaoException;

	public List<Long>  getArticleIdsByTypeAndIndustryAndStatus(Integer type,Long industry,Integer status,Integer start,Integer limit)throws ServiceException, ServiceDaoException;

	public List<Long> getArticleIdsOrderBySort(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException;
}

