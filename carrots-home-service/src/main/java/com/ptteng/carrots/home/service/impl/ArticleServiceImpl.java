package com.ptteng.carrots.home.service.impl;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.gemantic.dal.dao.exception.DaoException;
import com.ptteng.common.dao.BaseDaoServiceImpl;
import com.ptteng.carrots.home.model.Article;
import com.ptteng.carrots.home.service.ArticleService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class ArticleServiceImpl extends BaseDaoServiceImpl implements
		ArticleService {

	private static final Log log = LogFactory.getLog(ArticleServiceImpl.class);

	@Override
	public Long insert(Article article) throws ServiceException,
			ServiceDaoException {

		log.info(" insert data : " + article);

		if (article == null) {
			return null;
		}

		long currentTimeMillis = System.currentTimeMillis();
		article.setCreateAt(currentTimeMillis);
		article.setUpdateAt(currentTimeMillis);

		Long result = null;
		try {
			result = (Long) dao.save(article);
		} catch (DaoException e) {
			log.error(" insert wrong : " + article);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}

		log.info(" insert data success : " + result);

		return result;
	}

	@Override
	public List<Article> insertList(List<Article> articleList)
			throws ServiceException, ServiceDaoException {

		log.info(" insert lists : "
				+ (articleList == null ? "null" : articleList.size()));

		List<Article> resultList = null;

		if (CollectionUtils.isEmpty(articleList)) {
			return new ArrayList<Article>();
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (Article article : articleList) {
			article.setCreateAt(currentTimeMillis);
			article.setUpdateAt(currentTimeMillis);
		}

		try {
			resultList = (List<Article>) dao.batchSave(articleList);
		} catch (DaoException e) {
			log.error(" insert list wrong : " + articleList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}

		log.info(" insert lists  success : "
				+ (resultList == null ? "null" : resultList.size()));

		return resultList;

	}

	@Override
	public boolean delete(Long id) throws ServiceException, ServiceDaoException {

		log.info(" delete data : " + id);

		boolean result = false;

		if (id == null) {
			return true;
		}

		try {
			result = dao.delete(Article.class, id);
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
	public boolean update(Article article) throws ServiceException,
			ServiceDaoException {

		log.info(" update data : "
				+ (article == null ? "null" : article.getId()));

		boolean result = false;

		if (article == null) {
			return true;
		}

		article.setUpdateAt(System.currentTimeMillis());

		try {
			result = dao.update(article);
		} catch (DaoException e) {
			log.error(" update wrong : " + article);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" update data success : " + article);
		}
		return result;
	}

	@Override
	public boolean updateList(List<Article> articleList)
			throws ServiceException, ServiceDaoException {

		log.info(" update lists : "
				+ (articleList == null ? "null" : articleList.size()));

		boolean result = false;

		if (CollectionUtils.isEmpty(articleList)) {
			return true;
		}

		long currentTimeMillis = System.currentTimeMillis();
		for (Article article : articleList) {
			article.setUpdateAt(currentTimeMillis);
		}

		try {
			result = dao.batchUpdate(articleList);
		} catch (DaoException e) {
			log.error(" update list wrong : " + articleList);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}

		log.info(" update lists success : " + articleList.size());

		return result;
	}

	@Override
	public Article getObjectById(Long id) throws ServiceException,
			ServiceDaoException {

		log.info(" get data : " + id);

		Article article = null;

		if (id == null) {
			return article;
		}

		try {
			article = (Article) dao.get(Article.class, id);
		} catch (DaoException e) {
			log.error(" get wrong : " + id);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}

		log.info(" get data success : " + id);

		return article;
	}

	@Override
	public List<Article> getObjectsByIds(List<Long> ids)
			throws ServiceException, ServiceDaoException {

		log.info(" get lists : " + (ids == null ? "null" : ids));

		List<Article> article = null;

		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<Article>();
		}

		try {
			article = (List<Article>) dao.getList(Article.class, ids);
		} catch (DaoException e) {
			log.error(" get wrong : " + ids);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}

		log.info(" get data success : "
				+ (article == null ? "null" : article.size()));

		return article;
	}

	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	@Override
	public List<Long> getArticleIdsByStatusAndSourceOrderByPublishat(
			Integer status, String source, Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get ids by status,source,start,limit  : " + status
					+ " , " + source + " , " + start + " , " + limit);
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
			idList = dao.getIdList(
					"getArticleIdsByStatusAndSourceOrderByPublishat",
					new Object[] { status, source }, start, limit, false);

		} catch (DaoException e) {
			log.error(" get ids  wrong by status,source,start,limit)  : "
					+ status + " , " + source + " , " + start + " , " + limit);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get ids success : "
					+ (idList == null ? "null" : idList.size()));
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
	public List<Long> getArticleIdsBySourceOrderByPublishat(String source,
															Integer start, Integer limit) throws ServiceException,
			ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get ids by source,start,limit  : " + source + " , "
					+ start + " , " + limit);
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
			idList = dao.getIdList("getArticleIdsBySourceOrderByPublishat",
					new Object[] { source }, start, limit, false);

		} catch (DaoException e) {
			log.error(" get ids  wrong by source,start,limit)  : " + source
					+ " , " + start + " , " + limit);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get ids success : "
					+ (idList == null ? "null" : idList.size()));
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
	public List<Long> getArticleIdsByTypeAndStatusOrderByPublishat(
			Integer type, Integer status, Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" get ids by type,start,limit  : " + type + " , " + start
					+ " , " + limit);
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
			if (type == 2) {
				idList = dao.getIdList(
						"getArticleIdsByTypeAndStatusOrderByPublishatAscs",
						new Object[] { type, status }, start, limit, false);

			} else {
				idList = dao.getIdList(
						"getArticleIdsByTypeAndStatusOrderByPublishatDesc",
						new Object[] { type, status }, start, limit, false);

			}

		} catch (DaoException e) {
			log.error(" get ids  wrong by type,status,start,limit)  : " + type
					+ " , " + status + " ," + start + " , " + limit);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get ids success : "
					+ (idList == null ? "null" : idList.size()));
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
	public Integer countArticleIdsByStatusAndSourceOrderByPublishat(
			Integer status, String source) throws ServiceException,
			ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" count ids by status,source  : " + status + " , "
					+ source);
		}
		Integer count = null;

		try {

			count = dao.count("getArticleIdsByStatusAndSourceOrderByPublishat",
					new Object[] { status, source });

		} catch (DaoException e) {
			log.error(" count ids  wrong by status,source)  : " + status
					+ " , " + source);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
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
	public Integer countArticleIdsBySourceOrderByPublishat(String source)
			throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" count ids by source  : " + source);
		}
		Integer count = null;

		try {

			count = dao.count("getArticleIdsBySourceOrderByPublishat",
					new Object[] { source });

		} catch (DaoException e) {
			log.error(" count ids  wrong by source)  : " + source);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
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
	public Integer countArticleIdsByTypeAndStatusOrderByPublishat(Integer type,
																  Integer status) throws ServiceException, ServiceDaoException {

		if (log.isInfoEnabled()) {
			log.info(" count ids by type  : " + type);
		}
		Integer count = null;

		try {

			count = dao.count("getArticleIdsByTypeAndStatusOrderByPublishat",
					new Object[] { type, status });

		} catch (DaoException e) {
			log.error(" count ids  wrong by type,status)  : " + type + " , "
					+ status);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" count  success : " + count);
		}
		return count;

	}

	@Override
	public List<Long> getArticleIds(Integer start, Integer limit)
			throws ServiceException, ServiceDaoException {

		log.info(" get ids   by start,limit  ================== " + start
				+ " , " + limit);
		List<Long> idList = null;

		if (start == null) {
			start = 0;
		}

		if (limit == null) {
			limit = Integer.MAX_VALUE;
		}

		try {
			idList = dao.getIdList("getArticleIdsAll", new Object[] {}, start,
					limit, false);
		} catch (DaoException e) {
			log.error(" get ids  wrong by start,limit)  : " + start + " , "
					+ limit);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get ids success == : "
					+ (idList == null ? "null" : idList.size()));
		}
		return idList;
	}

	@Override
	public Integer countArticleIds() throws ServiceException,
			ServiceDaoException {
		Integer count = 0;
		try {
			count = dao.count("getArticleIdsAll", new Object[] {});
		} catch (DaoException e) {
			log.error(" count by getArticleIds ");
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" count  : " + count);
		}
		return count;
	}

	
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
		
		       if(log.isInfoEnabled()){
     log.info(" get ids by type,industry,start,limit  : " + type+" , "+industry+" , "+start+" , "+limit );
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
		idList = dao.getIdList("getArticleIdsByTypeAndIndustry", new Object[] { type,industry},start,limit, false);

  } catch (DaoException e) {
			log.error(" get ids  wrong by type,industry,start,limit)  : " + type+" , "+industry+" , "+start+" , "+limit );
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
 if(log.isInfoEnabled()){
  log.info(" get ids success : " + (idList == null ? "null" : idList.size()));
 }
		return idList;
		
	
	
	}

	@Override
	public List<Long> getArticleIdsByTypeAndIndustryAndStatus(Integer type, Long industry, Integer status, Integer start, Integer limit) throws ServiceException, ServiceDaoException {
		if(log.isInfoEnabled()){
			log.info(" get ids by type,industry, status, start,limit  : " + type+" , "+industry+" , "+status+" , "+start+" , "+limit );
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
			//添加逻辑，必须上线状态status=1，图片id才返回

			idList = dao.getIdList("getArticleIdsByTypeAndIndustryAndStatus", new Object[] { type,industry,status},start,limit, false);


		} catch (DaoException e) {
			log.error(" get ids  wrong by type,industry,start,limit)  : " + type+" , "+industry+" , "+start+" , "+limit );
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if(log.isInfoEnabled()){
			log.info(" get ids success : " + (idList == null ? "null" : idList.size()));
		}
		return idList;
	}

	@Override
	public List<Long> getArticleIdsOrderBySort(Integer start, Integer limit) throws ServiceException, ServiceDaoException {

		log.info(" get ids   by start,limit  ================== " + start
				+ " , " + limit);
		List<Long> idList = null;

		if (start == null) {
			start = 0;
		}

		if (limit == null) {
			limit = Integer.MAX_VALUE;
		}

		try {
			idList = dao.getIdList("getArticleIdsOrderBySort", new Object[] {}, start,
					limit, false);
		} catch (DaoException e) {
			log.error(" get ids  wrong by start,limit)  : " + start + " , "
					+ limit);
			log.error(e);
			e.printStackTrace();
			throw new ServiceDaoException(e);
		}
		if (log.isInfoEnabled()) {
			log.info(" get ids success == : "
					+ (idList == null ? "null" : idList.size()));
		}
		return idList;
	}

}
