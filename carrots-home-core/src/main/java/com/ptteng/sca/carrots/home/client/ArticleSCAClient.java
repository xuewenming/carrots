/**
 * 
 */
package com.ptteng.sca.carrots.home.client;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.ptteng.carrots.home.model.Article;
import com.ptteng.carrots.home.service.ArticleService;

import java.util.List;
import java.util.Map;

public class ArticleSCAClient implements ArticleService {

    private ArticleService articleService;

	public ArticleService getArticleService() {
		return articleService;
	}
	
	
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	
			   
		@Override
		public Long insert(Article article)throws ServiceException, ServiceDaoException{
		
		return articleService.insert(article);
		          
		
		}	
		  
    	   
		@Override
		public List<Article> insertList(List<Article> articleList)throws ServiceException, ServiceDaoException{
		
		return articleService.insertList(articleList);
		          
		
		}	
		  
    	   
		@Override
		public boolean delete(Long id)throws ServiceException, ServiceDaoException{
		
		return articleService.delete(id);
		          
		
		}	
		  
    	   
		@Override
		public boolean update(Article article)throws ServiceException, ServiceDaoException{
		
		return articleService.update(article);
		          
		
		}	
		  
    	   
		@Override
		public boolean updateList(List<Article> articleList)throws ServiceException, ServiceDaoException{
		
		return articleService.updateList(articleList);
		          
		
		}	
		  
    	   
		@Override
		public Article getObjectById(Long id)throws ServiceException, ServiceDaoException{
		
		return articleService.getObjectById(id);
		          
		
		}	
		  
    	   
		@Override
		public List<Article> getObjectsByIds(List<Long> ids)throws ServiceException, ServiceDaoException{
		
		return articleService.getObjectsByIds(ids);
		          
		
		}	
		  
    	
	
	
	
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public List<Long> getArticleIdsByStatusAndSourceOrderByPublishat(Integer status, String source, Integer start, Integer limit)throws ServiceException, ServiceDaoException{
		
		return articleService.getArticleIdsByStatusAndSourceOrderByPublishat(status,source,start,limit);
	
	
	}
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public List<Long> getArticleIdsBySourceOrderByPublishat(String source, Integer start, Integer limit)throws ServiceException, ServiceDaoException{
		
		return articleService.getArticleIdsBySourceOrderByPublishat(source, start, limit);
	
	
	}
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public List<Long> getArticleIdsByTypeAndStatusOrderByPublishat(Integer type, Integer status, Integer start, Integer limit)throws ServiceException, ServiceDaoException{
		
		return articleService.getArticleIdsByTypeAndStatusOrderByPublishat(type, status, start, limit);
	
	
	}
	
		
	
	
    			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public Integer countArticleIdsByStatusAndSourceOrderByPublishat(Integer status, String source)throws ServiceException, ServiceDaoException{
		
		return articleService.countArticleIdsByStatusAndSourceOrderByPublishat(status, source);
	
	
	}
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public Integer countArticleIdsBySourceOrderByPublishat(String source)throws ServiceException, ServiceDaoException{
		
		return articleService.countArticleIdsBySourceOrderByPublishat(source);
	
	
	}
	
			
			
		/**
	 * 
	 * @param 
	 * @return 
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	 @Override
	public Integer countArticleIdsByTypeAndStatusOrderByPublishat(Integer type, Integer status)throws ServiceException, ServiceDaoException{
		
		return articleService.countArticleIdsByTypeAndStatusOrderByPublishat(type, status);
	
	
	}
	
		
	
		@Override
	public List<Long> getArticleIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return articleService.getArticleIds(start, limit);
	}

	@Override
	public Integer countArticleIds() throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return articleService.countArticleIds();
	}
	
	
	@Override
	public List<Long> getIdsByDynamicCondition(Class clz, Map<String, Object> conditions, Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return articleService.getIdsByDynamicCondition(clz, conditions, start, limit);
	}


	@Override
	public boolean fakeDelete(Class clz, Long id) throws ServiceException, ServiceDaoException {
		// TODO Auto-generated method stub
		return articleService.fakeDelete(clz, id);
	}
	
	
	
	@Override
	public void deleteList(Class clz, List<Long> ids) throws ServiceException, ServiceDaoException {
		   articleService.deleteList(clz, ids);
		
	}

//
//	@Override
//	public Object getObjectByDynamicCondition(Class clz,
//											  Map<String, Object> conditions, Integer start, Integer limit)
//			throws ServiceException, ServiceDaoException {
//		// TODO Auto-generated method stub
//		return this.articleService.getObjectByDynamicCondition(clz, conditions, start, limit);
//	}
//

	/**
	 * 
	 * @param 
	 * 		type 为3 行业大图
	 * 		Integer 行业编号 
	 * @return  行业大图
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	 @Override
      public List<Long>  getArticleIdsByTypeAndIndustry(Integer type,Long industry,Integer start,Integer limit)throws ServiceException, ServiceDaoException{
		
		return articleService.getArticleIdsByTypeAndIndustry(type,industry,start,limit);
	
	
	}

	//上面一个方法的替代，多判断图片上下线状态
	@Override
	public List<Long> getArticleIdsByTypeAndIndustryAndStatus(Integer type, Long industry, Integer status, Integer start, Integer limit) throws ServiceException, ServiceDaoException {
		return articleService.getArticleIdsByTypeAndIndustryAndStatus(type,industry,status,start,limit);
	}

	@Override
	public List<Long> getArticleIdsOrderBySort(Integer start, Integer limit) throws ServiceException, ServiceDaoException {
		return articleService.getArticleIdsOrderBySort(start,limit);
	}


}

