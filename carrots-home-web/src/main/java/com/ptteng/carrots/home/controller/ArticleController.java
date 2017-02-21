package com.ptteng.carrots.home.controller;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.ptteng.common.storage.util.ImgStorageUtil;
import com.ptteng.carrots.home.model.Article;
import com.ptteng.carrots.home.model.Manager;
import com.ptteng.carrots.home.service.ArticleService;
import com.ptteng.carrots.home.service.ManagerService;
import com.qding.common.util.DataUtils;
import com.qding.common.util.FileUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import util.DynamicUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.*;

/**
 * Article crud
 *
 * @author magenm
 * @Date 2014-4-16 13:43
 *
 */
@Controller
public class ArticleController {
	private static final Log log = LogFactory.getLog(ArticleController.class);

	private final String Img_Module_Common = "common";
	@Autowired
	private ArticleService articleService;

	@Autowired
	private ImgStorageUtil imgStorageUtil;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private com.qding.common.util.http.cookie.CookieUtil cookieUtil;

	/**
	 * 批量获取文章详细信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/a/article/search", method = RequestMethod.GET)
	public String getMultiArticleJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String title,
			String author, Long startAt, Long endAt, Long type, Long status,
			Long industry, Integer page, Integer size) throws Exception {

		if (page == null || page <= 0) {
			page = 1;
		}

		if (size == null || size <= 0) {
			size = 10;
		}

		int start = (page - 1) * size;
		if (start < 0) {
			start = 0;
		}
		List<Article> articleList = null;
		try {
			// 前台只能查上线banner
			status = 2L;
			Map<String, Object> param = DynamicUtil.getArticleList(title,
					author, startAt, endAt, type, status, industry);
			List<Long> ids = articleService.getIdsByDynamicCondition(
					Article.class, param, start, size);

			articleList = articleService.getObjectsByIds(ids);
			model.addAttribute("code", 0);
			model.addAttribute("size", size);
			model.addAttribute("total", ids.size());
			model.addAttribute("articleList", articleList);
		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get article error");
			model.addAttribute("code", -100000);
		}

		return "/admin/article/json/articleMultiJson";
	}

	/**
	 * 单个查询展示内容
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param aid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/a/article/{aid}", method = RequestMethod.GET)
	public String getArticleJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long aid)
			throws Exception {

		log.info("get data : id= " + aid);
		try {
			if (aid == null) {
				model.addAttribute("code", -1004);
				return "/data/json";
			} else {

				Article article = articleService.getObjectById(aid);
				if (article == null) {
					model.addAttribute("code", -9003);
					return "/data/json";
				} else {
					log.info("get article data is " + article);
					model.addAttribute("code", 0);
					model.addAttribute("article", article);
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("get article error,id is  " + aid);
			model.addAttribute("code", -100000);
		}

		return "/admin/article/json/articleDetailJson";
	}

	// // 图片上传
	// @RequestMapping(value = "/a/u/img/{module}", method = RequestMethod.POST)
	// public String uploadFile(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model,
	// @RequestParam MultipartFile file, @PathVariable String module)
	// throws Exception {
	// log.info("hello upload " + module);
	// if (org.apache.commons.lang3.StringUtils.isBlank(module)) {
	// module = "common";
	// }
	// int code = 0;
	// log.info(file.getOriginalFilename());
	//
	// Long uid = cookieUtil.getID(request, response);
	// try {
	// String type = file.getContentType();
	// long size = file.getSize();
	// log.info("  size is =" + size / 1048576);
	// String extend = FileUtil.getFileExtension(file
	// .getOriginalFilename());
	//
	// String fileName = UUID.randomUUID().toString() + "." + extend;
	// log.info("new name is " + fileName);
	// // /data/temp/
	// String filePath = "/data/temp/" + fileName;
	//
	// File tempPic = new File(filePath);
	// file.transferTo(tempPic);
	// String url = this.imgStorageUtil.imgStorage(null, module + "/"
	// + fileName, filePath);
	// log.info(module + " upload success ,and file name is " + fileName
	// + "temp path is " + filePath + " access url is " + url);
	// tempPic.delete();
	// log.info(file.getOriginalFilename() + " delete success ");
	// model.addAttribute("url", url);
	// model.addAttribute("code", 0);
	// return "/common/img";
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// return "/common/success";
	// }
	//
	// }

	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	// @RequestMapping(value = "/index", method = RequestMethod.GET)
	// public String index(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model) throws Exception {
	//
	// log.info("welcome !");
	// List<Article> articleList1 = this.getArticleListByType(0,3,3);
	//
	// log.info("list1 :"+articleList1);
	//
	// model.addAttribute("articleList1", articleList1);
	//
	//
	// model.addAttribute("index", "active");
	// return "/web/index";
	//
	//
	// }

	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */
	// @RequestMapping(value = "/a/test", method = RequestMethod.GET)
	// public String testJsp(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model ) throws Exception {
	//
	// log.info("show the test page~");
	//
	// return "/admin/link";
	//
	// }
	// @RequestMapping(value = "/web/c/article", method = RequestMethod.GET)
	// public String getArticleList(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model) throws Exception {
	//
	//
	//
	// log.info("/web/article  to /article/view/articleList");
	//
	// return "/admin/article/view/articleList";
	// }

	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	// @RequestMapping(value = "/web/c/article1 ", method = RequestMethod.GET)
	// public String
	// getArticleIdsByStatusAndSourceOrderByPublishatList1(HttpServletRequest
	// request,
	// HttpServletResponse response, ModelMap model, Long status, String source)
	// throws Exception {
	//
	// log.info("/web/article  to /article/view/ArticleList");
	// Long uid = Long.valueOf(cookieUtil.getKeyIdentity(request,
	// com.qding.common.util.http.cookie.CookieUtil.USER_ID));
	// log.info(uid);
	// return "/admin/article/view/ArticleList";
	// }

	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	// @RequestMapping(value = "/web/c/article2 ", method = RequestMethod.GET)
	// public String
	// getArticleIdsBySourceOrderByPublishatList2(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model, String source) throws
	// Exception {
	//
	// log.info("/web/Article  to /Article/view/ArticleList");
	//
	// return "/admin/Article/view/ArticleList";
	// }

	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	// @RequestMapping(value = "/web/c/Article3 ", method = RequestMethod.GET)
	// public String getArticleIdsByTypeOrderByPublishatList3(HttpServletRequest
	// request,
	// HttpServletResponse response, ModelMap model, Integer type) throws
	// Exception {
	//
	// log.info("/web/Article  to /Article/view/ArticleList");
	//
	// return "/admin/Article/view/ArticleList";
	// }

	//
	// @RequestMapping(value = "/web/c/Article/{id}", method =
	// RequestMethod.GET)
	// public String getArticle(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model, @PathVariable Long id)
	// throws Exception {
	//
	// log.info("/web/Article/" + id + "  to /Article/view/ArticleDeail");
	// if(null != id){
	// model.addAttribute("id", id);
	// }else{
	// model.addAttribute("id", 0);
	// }
	//
	// return "/admin/Article/view/ArticleDetail";
	// }

	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	// @RequestMapping(value = "/web/a/Article1 ", method = RequestMethod.GET)
	// public String
	// getArticleIdsByStatusAndSourceOrderByPublishatJsonList(HttpServletRequest
	// request,
	// HttpServletResponse response, ModelMap model, Integer page,
	// Integer size, Integer status, String source) throws Exception {
	//
	//
	// if (page == null) {
	// page = 1;
	// }
	// if (size == null) {
	// size = 10;
	// }
	// int start = (page - 1) * size;
	// if (start < 0) {
	// start = 0;
	// }
	//
	// log.info("pageList : page= " + start + " , size=" + size);
	//
	// try {
	//
	// List<Long> ids=
	// articleService.getArticleIdsByStatusAndSourceOrderByPublishat(status,
	// source, start, size);
	// log.info("get countArticlenIdsByStatusAndSourceOrderByPublishat size is "
	// + ids.size());
	//
	// List<Article> articleList = articleService.getObjectsByIds(ids);
	// log.info("get Article data is " + articleList.size());
	//
	// Integer total = articleService.
	// countArticleIdsByStatusAndSourceOrderByPublishat(status, source);
	// log.info("get Article count is " + total);
	//
	// model.addAttribute("code", 0);
	// model.addAttribute("page", page);
	// int totalPage = 0;
	// if (total > 0) {
	// totalPage = total / size + 1;
	// }
	// model.addAttribute("size",total);
	// model.addAttribute("total",totalPage);
	// model.addAttribute("articleList", articleList);
	//
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("get Article list error,page is  " + start + " , size "
	// + size);
	// // for test
	// model.addAttribute("code", -100000);
	// }
	//
	// return "/admin/article/json/articleListJson";
	// }

	/**
	 *
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	// @RequestMapping(value = "/web/a/article2 ", method = RequestMethod.GET)
	// public String
	// getArticleIdsBySourceOrderByPublishatJsonList(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model, Integer page,
	// Integer size, String source) throws Exception {
	//
	//
	// if (page == null) {
	// page = 1;
	// }
	// if (size == null) {
	// size = 10;
	// }
	// int start = (page - 1) * size;
	// if (start < 0) {
	// start = 0;
	// }
	//
	// log.info("pageList : page= " + start + " , size=" + size);
	//
	// try {
	//
	// List<Long> ids=
	// articleService.getArticleIdsBySourceOrderByPublishat(source, start,
	// size);
	// log.info("get countarticleIdsBySourceOrderByPublishat size is " +
	// ids.size());
	//
	// List<Article> articleList = articleService.getObjectsByIds(ids);
	// log.info("get article data is " + articleList.size());
	//
	// Integer total = articleService.
	// countArticleIdsBySourceOrderByPublishat(source);
	// log.info("get article count is " + total);
	//
	// model.addAttribute("code", 0);
	// model.addAttribute("page", page);
	// int totalPage = 0;
	// if (total > 0) {
	// totalPage = total / size + 1;
	// }
	// model.addAttribute("size",total);
	// model.addAttribute("total",totalPage);
	//
	// model.addAttribute("articleList", articleList);
	//
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("get article list error,page is  " + start + " , size "
	// + size);
	// // for test
	// model.addAttribute("code", -100000);
	// }
	//
	// return "/admin/article/json/articleListJson";
	// }

	/**
	 * 根据类型发布状体查找内容
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	// @RequestMapping(value = "/web/a/article/{type} ", method =
	// RequestMethod.GET)
	// public String
	// getArticleIdsByTypeOrderByPublishatJsonList(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model, Integer page,
	// Integer size, @PathVariable Integer type) throws Exception {
	//
	//
	// if (page == null) {
	// page = 1;
	// }
	// if (size == null) {
	// size = 10;
	// }
	// int start = (page - 1) * size;
	// if (start < 0) {
	// start = 0;
	// }
	//
	// log.info("pageList : page= " + start + " , size=" + size);
	//
	// try {
	//
	// List<Long> ids=
	// articleService.getArticleIdsByTypeAndStatusOrderByPublishat(type,
	// Article.Status_Unpublished, start, size);
	// log.info("get countArticleIdsByTypeAndStatusOrderByPublishat size is " +
	// ids.size());
	//
	// List<Article> articleList = articleService.getObjectsByIds(ids);
	// log.info("get article data is " + articleList);
	//
	//
	// Integer total = articleService.
	// countArticleIdsByTypeAndStatusOrderByPublishat(type,
	// Article.Status_Unpublished);
	// log.info("get article count is " + total);
	//
	// model.addAttribute("code", 0);
	// model.addAttribute("page", page);
	// int totalPage = 0;
	// if (total > 0) {
	// totalPage = total / size + 1;
	// }
	// model.addAttribute("size",total);
	// model.addAttribute("total",totalPage);
	//
	// model.addAttribute("articleList", articleList);
	// return "/admin/article/json/articleListJson";
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("get article list error,page is  " + start + " , size "
	// + size);
	// // for test
	// model.addAttribute("code", -100000);
	// }
	//
	// return "/common/success";
	// }
	/**
	 * 查询所有内容
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	// @RequestMapping(value = "/a/article", method = RequestMethod.GET)
	// public String getArticleIdsJsonList(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model, Integer page,
	// Integer size) throws Exception {
	//
	//
	// if (page == null) {
	// page = 1;
	// }
	// if (size == null) {
	// size = 10;
	// }
	// int start = (page - 1) * size;
	// if (start < 0) {
	// start = 0;
	// }
	//
	// log.info("pageList : page= " + start + " , size=" + size);
	//
	// try {
	//
	// List<Long> ids= articleService.getArticleIds(start, size);
	// log.info("get countarticleIdsList size is " + ids.size());
	//
	// List<Article> articleList = articleService.getObjectsByIds(ids);
	// log.info("get article data is " + articleList.size());
	// if(articleList != null&& articleList.size()>0 ) {
	// Integer total = articleList.size();
	// log.info("get article count is " + total);
	// int totalPage = 0;
	// if (total > 0) {
	// totalPage = total / size + 1;
	// }
	// model.addAttribute("size",total);
	// model.addAttribute("total",totalPage);
	// // model.addAttribute("total", total);
	// }else{
	//
	// model.addAttribute("total", 0);
	// }
	// model.addAttribute("code", 0);
	// model.addAttribute("page", page);
	//
	// model.addAttribute("articleList", articleList);
	//
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("get article list error,page is  " + start + " , size "
	// + size);
	// // for test
	// model.addAttribute("code", -100000);
	// }
	//
	// return "/admin/article/json/articleMultiJson";
	// }

	/**
	 * 单个查询展示内容
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param aid
	 * @return
	 * @throws Exception
	 */

	/**
	 * 修改内容
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param article
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping(value = "/a/u/article/{aid}", method = RequestMethod.PUT)
	// public String updateArticleJson(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model, Article article,
	// @PathVariable Long aid) throws Exception {
	//
	// log.info("update article : article= "/* + article*/);
	//
	// try {
	//
	// if(article == null){
	// model.addAttribute("code",-1004);
	//
	// return "/data/json";
	// }
	//
	//
	//
	// if (DataUtils.isNullOrEmpty(article.getType())) {
	// model.addAttribute("code", -4013);
	// return "common/success";
	// }
	//
	// if (DataUtils.isNullOrEmpty(article.getImg())) {
	// model.addAttribute("code", -4014);
	// return "common/success";
	// }
	// if (DataUtils.isNullOrEmpty(article.getTitle())) {
	// model.addAttribute("code", -4015);
	// return "common/success";
	// }
	// Long uid = Long.valueOf(cookieUtil.getKeyIdentity(request,
	// com.qding.common.util.http.cookie.CookieUtil.USER_ID));
	//
	// article.setUpdateBy(uid);
	// article.setId(aid);
	// articleService.update(article);
	// Manager manager=managerService.getObjectById(uid);
	// article.setAuthor(manager.getName());
	//
	//
	//
	// model.addAttribute("code", 0);
	//
	// model.addAttribute("article", article);
	//
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("update article error,id is  " + article.getId());
	// model.addAttribute("code", -100000);
	// }
	// return "/data/json";
	// }

	/**
	 * 发布或取消发布
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param
	 * @param aid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/a/u/article/{aid}/status", method = RequestMethod.PUT)
	public String updateArticleJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long aid)
			throws Exception {
		if (aid == null) {
			model.addAttribute("code", -1000);
			log.info("put article error aid is null");
			return "/data/json";
		}
		Article article = articleService.getObjectById(aid);
		Integer status = article.getStatus();
		log.info("update article status : article id ,status = " + aid + " , "
				+ status);

		try {

			Long uid = Long.valueOf(cookieUtil.getKeyIdentity(request,
					com.qding.common.util.http.cookie.CookieUtil.USER_ID));

			article.setUpdateBy(uid);
			log.info("article is  " + article);
			// 这个功能被怼过，需要改
			if (Article.Status_Unpublished.equals(status)) {
				log.info("change strtus4");
				article.setStatus(Article.Status_Published);// 取消发布
			} else {
				log.info("change strtus5");
				Long time = System.currentTimeMillis();
				article.setStatus(Article.Status_Unpublished);// 发布内容
				article.setPublishat(time);
			}
			Manager manager = managerService.getObjectById(uid);
			article.setAuthor(manager.getName());
			article.setId(null);

			articleService.update(article);

			model.addAttribute("code", 0);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("update article error,id is  " + aid);
			model.addAttribute("code", -100000);

		}

		return "/data/json";
	}

	/**
	 * 添加内容
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param article
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping(value = "/a/u/article", method = RequestMethod.POST)
	// public String addArticleJson(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model, Article article) throws
	// Exception {
	//
	// log.info("update article : article= " + article);
	//
	// try {
	// if(article == null){
	// model.addAttribute("code",-1004);
	// return "/data/json";
	// }
	// Integer status=article.getStatus();
	// if (status!=1&&status!=2) {
	// model.addAttribute("code", -4020);
	// return "common/success";
	// }
	// if (DataUtils.isNullOrEmpty(article.getImg())) {
	// log.info(article.getImg());
	// model.addAttribute("code", -4014);
	// return "common/success";
	// }
	// if (DataUtils.isNullOrEmpty(article.getTitle())) {
	// model.addAttribute("code", -4015);
	// return "common/success";
	// }
	//
	//
	// if(Article.Status_Unpublished==article.getStatus()){
	// article.setStatus(Article.Status_Unpublished);//保存草稿，不发布\
	// article.setPublishat(0L);
	// }else if(Article.Status_Published==article.getStatus()){
	// Long time = System.currentTimeMillis();
	// article.setStatus(Article.Status_Published);//发布
	// article.setPublishat(time);
	// }else{
	// article.setStatus(Article.Status_Unpublished);
	// }
	// Long uid = Long.valueOf(cookieUtil.getKeyIdentity(request,
	// com.qding.common.util.http.cookie.CookieUtil.USER_ID));
	// article.setCreateBy(uid);
	// article.setUpdateBy(uid);
	// Manager manager=managerService.getObjectById(uid);
	// article.setAuthor(manager.getName());
	// article.setId(null);
	// Long aid=articleService.insert(article);
	// model.addAttribute("code", 0);
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("add article error ");
	// model.addAttribute("code", -100000);
	// }
	//
	// return "/data/json";
	// }

	/**
	 * 删除内容
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping(value = "/a/u/article/{id}", method =
	// RequestMethod.DELETE)
	// public String deleteArticleJson(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model, @PathVariable Long id)
	// throws Exception {
	//
	// log.info("delete article : id= " + id);
	// try {
	// articleService.delete(id);
	//
	// log.info("add article success");
	// model.addAttribute("code", 0);
	//
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("delete article error,id is  " + id);
	// model.addAttribute("code", -100000);
	//
	// }
	//
	// return "/data/json";
	// }

	//
	// /**
	// * 条件查询内容
	// * @param request
	// * @param response
	// * @param model
	// * @param page
	// * @param size
	// * @param startAt
	// * @param endAt
	// * @param type
	// * @param createBy
	// * @param status
	// * @return
	// * @throws Exception
	// */
	// @RequestMapping(value = "/a/u/article", method = RequestMethod.GET)
	// public String getArticleList(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model, Integer page, Integer size,
	// Long startAt, Long endAt, String type, String source, String createBy,
	// String status)
	// throws Exception {
	// log.info("/web/a/article/list ,page is:"+page+",size is"+size+",startAt is"+startAt+",endAt is"+endAt+", type is "
	// +
	// ""+type+" , source is "+source+" createBy is " +
	// createBy+" status is "+status);
	// if (page == null) {
	// page = 1;
	// }
	// if (size == null) {
	// size = 10;
	// }
	// int start = (page - 1) * size;
	// if (start < 0) {
	// start = 0;
	// }
	// Integer total=null;
	// List<Long> count=null;
	// List<Long> ids =null;
	// try {
	//
	// if(startAt == null && endAt == null && type == null && source == null &&
	// createBy == null && status == null){
	// ids = articleService.getArticleIds(start, size);
	// total=articleService.countArticleIds();
	// log.info("===============================================1"+ids+"====="+total);
	// }else {
	// Map<String, Object> param = DynamicUtil.getListParam(startAt, endAt,
	// type, createBy, source, status, false);
	// log.info("        param is    " + param);
	// // Map<String, Object> countparam = DynamicUtil.getListParam(startAt,
	// endAt, type, createBy, source, status, true);
	// log.info("pageList : page= " + start + " , size=" + size);
	// ids = articleService.getIdsByDynamicCondition(Article.class, param,
	// start, size);
	// count = articleService.getIdsByDynamicCondition(Article.class, param, 0,
	// Integer.MAX_VALUE);
	// total = count.size();
	// log.info("===============================================2"+ids+"====="+total);
	// }
	//
	// if(ids!=null && ids.size()>0){
	//
	// log.info("get article list ids is" + ids);
	// List<Article> articleList=articleService.getObjectsByIds(ids);
	// model.addAttribute("articleList",articleList);
	// }else{
	// model.addAttribute("code", 2);
	// return "/common/success";
	// }
	// Boolean next=false;
	// Integer nex=start+size;
	//
	// if (total>nex) {
	//
	// next=true;
	// }
	// model.addAttribute("next",next);
	//
	//
	// Integer totalPage=1;
	// if (total > 0) {
	//
	// totalPage=(total-1)/size+1;
	// }
	// model.addAttribute("total", total);
	// model.addAttribute("totalPage",totalPage);
	// model.addAttribute("size",size);
	// model.addAttribute("code",0);
	// model.addAttribute("next",next);
	// } catch (Throwable t) {
	// log.error(t.getMessage());
	// log.error("get articleList error,qfrom is"+startAt+",qto is"+endAt+", type is "
	// +
	// ""+type+" , source is "+source+" createBy is " +
	// createBy+" status is "+status);
	// model.addAttribute("code", -100000);
	// }
	//
	// return "/admin/article/json/articleMultiJson";
	// }
	//
	/**
	 * 根据type查找已发布内容
	 * 
	 * @param page
	 * @param size
	 * @param type
	 * @return
	 * @throws Exception
	 */
	// public List<Article> getArticleListByType(Integer page, Integer size,
	// Integer type) throws Exception {
	//
	// if (page == null) {
	// page = 1;
	// }
	// if (size == null) {
	// size = 10;
	// }
	// int start = (page - 1) * size;
	// if (start < 0) {
	// start = 0;
	// }
	// List<Long> ids=
	// articleService.getArticleIdsByTypeAndStatusOrderByPublishat(type,
	// Article.Status_Published, start, size);
	// log.info("get countArticleIdsByTypeAndStatusOrderByPublishat size is " +
	// ids);
	//
	//
	// List<Article> articleList = articleService.getObjectsByIds(ids);
	// log.info("get article data is " + articleList);
	//
	//
	//
	//
	// return articleList;
	// }
}
