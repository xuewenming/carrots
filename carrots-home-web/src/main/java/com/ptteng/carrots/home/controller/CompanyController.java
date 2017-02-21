package com.ptteng.carrots.home.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import util.DynamicUtil;

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.ptteng.carrots.home.model.Article;
import com.ptteng.carrots.home.model.Company;
import com.ptteng.carrots.home.model.CompanyIndustry;
import com.ptteng.carrots.home.model.CompanyTags;
import com.ptteng.carrots.home.model.Product;
import com.ptteng.carrots.home.model.Profession;
import com.ptteng.carrots.home.service.ArticleService;
import com.ptteng.carrots.home.service.CompanyIndustryService;
import com.ptteng.carrots.home.service.CompanyService;
import com.ptteng.carrots.home.service.CompanyTagsService;
import com.ptteng.carrots.home.service.ProductService;
import com.ptteng.carrots.home.service.ProfessionService;
import com.ptteng.carrots.home.service.ProfessionTagsService;

/**
 * Company  crud
 * 
 * @author magenm
 * @Date 2014-4-16 13:43
 * 
 */
@Controller
public class CompanyController {
	private static final Log log = LogFactory.getLog(CompanyController.class);

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyIndustryService companyIndustryService;

	@Autowired
	private CompanyTagsService companyTagsService;	

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProfessionService professionService;
	
	@Autowired
	private ProfessionTagsService professionTagsService;

	@Autowired
	private ArticleService articleService;
	
    /**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

/*	@RequestMapping(value = "/c/company", method = RequestMethod.GET)
	public String getcompanyList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		
		
		log.info("/company  to /company/view/companyList");

		return "/carrots-home-service/company/view/companyList";
	}
    
    

    
	
	@RequestMapping(value = "/c/company/{id}", method = RequestMethod.GET)
	public String getCompany(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("/company/" + id + "  to /company/view/companyDeail");
		if(null != id){
			model.addAttribute("id", id);
		}else{
			model.addAttribute("id", 0);
		}

		return "/carrots-home-service/company/view/companyDetail";
	}*/
	
	
	
	    
	
	//查询公司明细信息
	@RequestMapping(value = "/a/company/{id}", method = RequestMethod.GET)
	public String getCompanyJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("get data : id= " + id);
		try {
			Company company = companyService.getObjectById(id);
			log.info("get company data is " + company);
			model.addAttribute("company", company);
			//获取行业ID
			List<Long> industryIds = companyIndustryService.getCompanyIndustryIdsByCompanyId(id, 0, 10);
			//获取行业
			List<CompanyIndustry> industryList = companyIndustryService.getObjectsByIds(industryIds);
			model.addAttribute("industryList", industryList);
			
			//获取公司标签ID
			List<Long> tagIds = companyTagsService.getCompanyTagsIdsByCompanyId(id, 0, 10);
			//获取公司标签名称
			List<CompanyTags> tagList = companyTagsService.getObjectsByIds(tagIds);
			model.addAttribute("tagList", tagList);

			//获取公司产品ID
			List<Long> productIds = productService.getProductIdsByCompanyId(id, 0, 10);
			//获取公司产品信息
			List<Product> productList = productService.getObjectsByIds(productIds);
			model.addAttribute("productList", productList);
			
			model.addAttribute("code", 0);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("get company error,id is  " + id);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/company/json/companyDetailJson";
	}
	
	
	//多条件查询，动态sql查询
	@RequestMapping(value = "/a/company/search", method = RequestMethod.GET)
	public String getMulticompanyJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String name,
			String product,String province, String city, String county, String industry,
			String financing, Long freezed, Long approved, Long returnPage, Long updateTimeType,
			Integer page, Integer size)
			throws Exception {
		
		log.info("get returnPage is " + returnPage);
		//检查returnPage有效性
		if (returnPage != null && returnPage != 0 && returnPage != 1){
			model.addAttribute("code", -6599);
			return "/common/fail";
		}
		
		// 公司ID列表
		List<Long> ids = null;
		//公司列表
		List<Long> companyIds = null;
		
		try {
			//搜索公司页和公司列表页的返回为公司列表（认证在前，普通在后；再按更新时间逆序）
			if (returnPage == null){
				log.info("get returnPage11 is " + returnPage);
				if (page == null) {
					page = 1;
				}
				if (size == null) {
					size = 10;
				}
				int start = (page - 1) * size;
				if (start < 0) {
					start = 0;
				}
				
				//过滤冻结公司
				freezed = 0L;
				// 组装动态sql
				Map<String, Object> param = DynamicUtil.getCompanyList(name, product, province, city, county, 
						industry, financing, freezed, approved, returnPage, updateTimeType);
				log.info("get  param data is " + param);
				// 通过动态sql查询获得公司ID列表
				ids = companyService.getIdsByDynamicCondition(Company.class, param, start, size);
				
				log.info("get ids is " + ids);
				List<Company> companyList = companyService.getObjectsByIds(ids);
				
				for (Company company : companyList) {
					Long companyId = company.getId();
					log.info("get industry, company is " + company.getId());
					//获取行业ID列表
					List<Long> industryIdList = companyIndustryService.getCompanyIndustryIdsByCompanyId(companyId, 0, 10);
					//将company的professionNum空值设为0
					if(company.getProfessionNum()==null)
						company.setProfessionNum(0L);
					
					log.info("get industry, industryIdList is " + industryIdList);
					//获取行业对象列表
					List<CompanyIndustry> industryList = companyIndustryService.getObjectsByIds(industryIdList);
					log.info("get industry, industryList is " + industryList);
					//给公司的行业列表赋值
					company.setCompanyIndustryList(industryList);
				}

				companyIds = companyService.getIdsByDynamicCondition(Company.class,param, 0, Integer.MAX_VALUE);

				log.info("get  company data is " + companyList);

				model.addAttribute("code", 0);
				model.addAttribute("page", page);
				model.addAttribute("size", size);
				model.addAttribute("total", companyIds.size());
				model.addAttribute("companyList", companyList);
			}
			
			//找精英的成功案例（认证在前，普通在后；再按职位更新时间排序）
			if (returnPage != null && returnPage == 0){
				
				if (page == null) {
					page = 1;
				}
				if (size == null) {
					size = 8;
				}
				int start = (page - 1) * size;
				if (start < 0) {
					start = 0;
				}
				
				//过滤冻结公司
				freezed = 0L;
				
				// 组装动态sql
				Map<String, Object> param = DynamicUtil.getCompanyList(name, product, province, city, county, 
						industry, financing, freezed, approved, returnPage, updateTimeType);
				log.info("get  param data is " + param);
				// 通过动态sql查询获得公司ID列表
				ids = companyService.getIdsByDynamicCondition(Company.class,param, start, size);
				List<Company> companyList = companyService.getObjectsByIds(ids);
				
				for (Company company : companyList) {
					Long companyId = company.getId();
					log.info("get industry, company is " + company.getId());
					//获取行业ID列表
					List<Long> industryIdList = companyIndustryService.getCompanyIndustryIdsByCompanyId(companyId, 0, 10);
					log.info("get industry, industryIdList is " + industryIdList);
					//获取行业对象列表
					List<CompanyIndustry> industryList = companyIndustryService.getObjectsByIds(industryIdList);
					log.info("get industry, industryList is " + industryList);
					//给公司的行业列表赋值
					company.setCompanyIndustryList(industryList);
				}

				log.info("get  company data is " + companyList);

				model.addAttribute("code", 0);
				model.addAttribute("page", page);
				model.addAttribute("size", size);
				model.addAttribute("companyList", companyList);
			}
			
			log.info("=========get  returnPage is " + returnPage);
			//找职位的推荐职位部分(行业大图、十个普通公司、四个轮播认证公司）
			if (returnPage != null && returnPage == 1){
				//==========获取行业大图==========
				page = 1;
				size = 1;
				int start = 0;
				//查找认证公司
				approved = 1L;
				//过滤冻结公司
				freezed = 0L;
				//按公司更新时间排序
				updateTimeType = 0L;
				// 组装动态sql
				Map<String, Object> param = DynamicUtil.getCompanyList(name, product, province, city, county, 
						industry, financing, freezed, approved, returnPage, updateTimeType);
				log.info("get  param data is " + param);
				// 通过动态sql查询获得公司ID列表
				ids = companyService.getIdsByDynamicCondition(Company.class, param, start, size);
				log.info("get company, ids is " + ids);
				List<Company> companyList = companyService.getObjectsByIds(ids);
				log.info("get companyList, ids is " + companyList);
				model.addAttribute("company", companyList.get(0));
				//获取行业ID
				for (Company company : companyList) {
					Long companyId = company.getId();
					log.info("get industry, company is " + company.getId());
					//获取行业ID列表
					List<Long> industryIdList = companyIndustryService.getCompanyIndustryIdsByCompanyId(companyId, 0, 1);
					log.info("get industry, industryIdList is " + industryIdList);
					//获取行业对象列表
					List<CompanyIndustry> industryList = companyIndustryService.getObjectsByIds(industryIdList);
					log.info("get industry, industryList is " + industryList);
					//给公司的行业列表赋值
					model.addAttribute("industry", industryList.get(0));
					CompanyIndustry industryOne = industryList.get(0);
					Long industryId = industryOne.getIndustry();
					//根据行业ID取行业大图
					log.info("==============================1111119999");
					//行业大图
					int type = 3;
					//上线状态
					int status = 2;
//					List<Long> articleIdList = articleService.getArticleIdsByTypeAndIndustry(type, industryId, 0, 10);
					List<Long> articleIdList = articleService.getArticleIdsByTypeAndIndustryAndStatus(type, industryId, status, 0, 10);
					log.info("==============================111111"+articleIdList.size());
					List<Article> articleList = articleService.getObjectsByIds(articleIdList);
					log.info("==============================111111"+articleList.size());
					model.addAttribute("article", articleList.get(0));
					log.info("==============================11111122222");
				}
				
				//==========获取普通公司==========
				page = 1;
				size = 8;
				start = 0;
				/*ids = null;*/
				log.info("=====================start " );
				//搜索普通公司
				approved = 0L;
				//非冻结公司
				freezed = 0L;
				//按公司更新时间排序
				updateTimeType = 0L;
				// 组装动态sql，获取普通公司列表
				Map<String, Object> param1 = DynamicUtil.getCompanyList(name, product, province, city, county, 
						industry, financing, freezed, approved, returnPage, updateTimeType);
				log.info("get  param data is " + param1);
				// 通过动态sql查询获得公司ID列表
				ids = companyService.getIdsByDynamicCondition(Company.class, param1, start, size);
				log.info("=====================ids.size() "+ ids.size() );
				List<Company> normalCompanyList = companyService.getObjectsByIds(ids);
				log.info("=====================normalCompanyList.size() "+ normalCompanyList.size() );
				//给JSP页面的普通公司复制
				model.addAttribute("normalCompanyList", normalCompanyList);

				
				//==========获取四个轮播认证公司（按职位更新时间排序）==========
				page = 1;
				size = 4;
				start = 0;
				log.info("==============================2222");
				approved = 1L;
				updateTimeType = 1L;
				//非冻结公司
				freezed = 0L;
				// 组装动态sql，获取职位更新认证公司列表
				Map<String, Object> param2 = DynamicUtil.getCompanyList(name, product, province, city, county, 
						industry, financing, freezed, approved, returnPage, updateTimeType);
				log.info("get  param data is " + param2);
				// 通过动态sql查询获得公司ID列表
				ids = companyService.getIdsByDynamicCondition(Company.class, param2, start, size);
				List<Company> approvedCompanyList = companyService.getObjectsByIds(ids);
				log.info("==============================approvedCompanyList" + approvedCompanyList.size());
				//获取职位名称列表
				for (Company company : approvedCompanyList) {
					Long approvedCompanyId = company.getId();
					log.info("get industry, company is " + company.getId());
					//获取行业ID列表
					List<Long> industryIdList1 = companyIndustryService.getCompanyIndustryIdsByCompanyId(approvedCompanyId, 0, 10);
					log.info("get industry, industryIdList is " + industryIdList1);
					//获取行业对象列表
					List<CompanyIndustry> industryList1 = companyIndustryService.getObjectsByIds(industryIdList1);
					log.info("get industry, industryList is " + industryList1);
					//给公司的行业列表赋值
					company.setCompanyIndustryList(industryList1);

					//获取职位ID列表
					List<Long> professionIdList = professionService.getProfessionIdsByCompanyId(approvedCompanyId, 0, 3);
					log.info("get professionIdList, professionIdList is " + professionIdList);
					//获取职位名称列表
					List<Profession> professionList = professionService.getObjectsByIds(professionIdList);
					log.info("get professionList, professionList is " + professionList);
					//给公司的行业列表赋值
					company.setProfessionList(professionList);
				}
				
				//给JSP页面的认证轮播公司复制
				model.addAttribute("approvedCompanyList", approvedCompanyList);
				log.info("get  approvedCompanyList data is " + approvedCompanyList);
				model.addAttribute("code", 0);
			}
			

		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("returnPage is  " + returnPage);
			model.addAttribute("code", -100000);
		}

		log.info("get  returnPage is " + returnPage);
		//
		if (returnPage == null ){
		return "/carrots-home-service/company/json/companyListJson";
		}
		//
		if (returnPage != null && returnPage == 0){
		return "/carrots-home-service/company/json/approvedCompanyListJson";
		}
		//
		if (returnPage != null && returnPage == 1){
		return "/carrots-home-service/company/json/approvedAndNormalListJson";
		}
		
		return "/carrots-home-service/company/json/companyListJson";
	}

	//修改公司信息
/*	@RequestMapping(value = "/a/u/company/{id}", method = RequestMethod.PUT)
	public String updateCompanyJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id, @RequestBody CompanyInfo companyInfo) throws Exception {
		
		log.info("update company : companyInfo= " + companyInfo);
		//检查必输项
		//获取公司信息
		Company company=companyInfo.getCompany();
		//获取公司行业
		List<CompanyIndustry> industryList=companyInfo.getIndustryList();
		//获取公司标签
		List<CompanyTags> tagList=companyInfo.getTagList();
		//获取公司产品
		List<Product> productList=companyInfo.getProductList();
		//检查公司名称
		if (company.getName() == null) {
			model.addAttribute("code", -6500);
			return "/common/fail";
		}
		//检查公司标语
		if (company.getSlogan() == null){
			model.addAttribute("code", -6501);
			return "/common/fail";
		}
		//检查公司人数
		if (company.getTotalNum() == null){
			model.addAttribute("code", -6502);
			return "/common/fail";
		}
		//检查融资规模
		if (company.getFinancing() == null){
			model.addAttribute("code", -6503);
			return "/common/fail";
		}
		//检查省份
		if (company.getProvince() == null){
			model.addAttribute("code", -6504);
			return "/common/fail";
		}
		//检查城市
		if (company.getCity() == null){
			model.addAttribute("code", -6505);
			return "/common/fail";
		}
		//检查地区
		if (company.getCounty() == null){
			model.addAttribute("code", -6506);
			return "/common/fail";
		}
		//检查行业（支持一个公司多个行业）
		if (industryList == null) {
			model.addAttribute("code", -6507);
			return "/common/fail";
		}
		//检查认证状态
		if (company.getApproved() == null){
			model.addAttribute("code", -6508);
			return "/common/fail";
		}
		//检查公司LOGO
		if (company.getLogo() == null){
			model.addAttribute("code", -6509);
			return "/common/fail";
		}
		//检查公司介绍
		if (company.getSummary() == null){
			model.addAttribute("code", -6510);
			return "/common/fail";
		}	
		
		try {
			//获取原有公司相关创建人和创建时间
			Company companySource = companyService.getObjectById(id);
			company.setCreateAt(companySource.getCreateAt());
			company.setCreateBy(companySource.getCreateBy());
			//给ID字段赋值
			company.setId(id);
			//从cookie中获取userid,暂时写死
			company.setUpdateBy(11L);
			//获取修改时间戳
			company.setUpdateAt(System.currentTimeMillis());
			//修改公司记录
			companyService.update(company);
			System.out.println("update companyId=" + id);
			model.addAttribute("companyId", id);
			//修改公司行业记录
			for (CompanyIndustry companyIndustry : industryList) {
				if (companyIndustry.getId() == null){
					//给公司ID赋值
					companyIndustry.setCompanyId(id);
					//获取用户ID
					companyIndustry.setCreateBy(11L);
					companyIndustry.setUpdateBy(companyIndustry.getCreateBy());
					//获取修改时间戳
					companyIndustry.setCreateAt(System.currentTimeMillis());
					companyIndustry.setUpdateAt(companyIndustry.getCreateAt());
					//写表
					companyIndustryService.insert(companyIndustry);
				}
				else{
					//获取原有行业相关的创建人和创建时间
					CompanyIndustry companyIndustrySource = companyIndustryService.getObjectById(companyIndustry.getId());
					companyIndustry.setCreateAt(companyIndustrySource.getCreateAt());
					companyIndustry.setCreateBy(companyIndustrySource.getCreateBy());
					//给公司ID赋值
					companyIndustry.setCompanyId(id);
					//获取用户ID
					companyIndustry.setUpdateBy(11L);
					//获取修改时间戳
					companyIndustry.setUpdateAt(System.currentTimeMillis());
					//修改表
					companyIndustryService.update(companyIndustry);
				}
			}
			//删除公司标签
			List<Long> companyTagsIds = companyTagsService.getCompanyTagsIdsByCompanyId(id, 0, 10);
			companyTagsService.deleteList(CompanyTags.class, companyTagsIds);
			//如果公司标签列表不为空则插入公司标签
			if (tagList != null) {
				for (CompanyTags companyTags : tagList) {
					//给公司ID赋值
					companyTags.setCompanyId(id);
					//获取用户ID
					companyTags.setCreateBy(11L);
					companyTags.setUpdateBy(companyTags.getCreateBy());
					//获取修改时间戳
					companyTags.setCreateAt(System.currentTimeMillis());
					companyTags.setUpdateAt(companyTags.getCreateAt());
					//写表
					companyTagsService.insert(companyTags);
				}
			}
			//如果公司产品ID为空则新增公司产品信息
			for (Product product : productList) {
				if (product.getId() == null){
					//获取用户ID
					product.setCreateBy(11L);
					product.setUpdateBy(product.getCreateBy());
					//获取修改时间戳
					product.setCreateAt(System.currentTimeMillis());
					product.setUpdateAt(product.getCreateAt());
					//给公司ID赋值
					product.setCompanyId(id);
					//写表
					productService.insert(product);
				}
				else{
					//获取原有产品相关创建人和创建时间
					Product productSource = productService.getObjectById(product.getId());
					product.setCreateBy(productSource.getCreateBy());
					product.setCreateAt(productSource.getCreateAt());
					//给公司ID赋值
					product.setCompanyId(id);
					//获取用户ID
					product.setUpdateBy(11L);
					//获取修改时间戳
					product.setUpdateAt(System.currentTimeMillis());
					//写表
					productService.update(product);
				}
			}
			model.addAttribute("code", 0);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("update company error,id is  " + company.getId());
			model.addAttribute("code", -6003);

		}

		return "/carrots-home-service/company/json/companyDetailJson";
	}*/

	
/*	@RequestMapping(value = "/a/company", method = RequestMethod.POST)
	public String addCompanyJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Company company) throws Exception {
		
		log.info("update company : company= " + company);
		
		try { 
			company.setId(null);

			companyService.insert(company);

			model.addAttribute("code", 0);
		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("add company error ");
			model.addAttribute("code", -6002);
		}

		return "/data/json";
	}
	*/
	
	//删除公司信息
/*	@RequestMapping(value = "/a/u/company/{id}", method = RequestMethod.DELETE)
	public String deleteCompanyJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("delete company : id= " + id);
		try {
			companyService.delete(id);

			log.info("delete company success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete company error,id is  " + id);
			model.addAttribute("code", -6004);

		}
		//删除公司行业
		List<Long> IndustryIds = companyIndustryService.getCompanyIndustryIdsByCompanyId(id, 0, 10);
		try {
			companyIndustryService.deleteList(CompanyIndustry.class, IndustryIds);

			log.info("delete companyIndustryList success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete companyIndustryList error,companyIndustryIds is  " + IndustryIds);
			model.addAttribute("code", -6004);

		}
		//删除公司标签
		List<Long> companyTagsIds = companyTagsService.getCompanyTagsIdsByCompanyId(id, 0, 10);
		try {
			companyTagsService.deleteList(CompanyTags.class, companyTagsIds);

			log.info("delete companyTags success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete companyTags error,companyTagsIds is  " + companyTagsIds);
			model.addAttribute("code", -6004);

		}
		//删除公司产品
		List<Long> productIds = productService.getProductIdsByCompanyId(id, 0, 10);
		try {
			productService.deleteList(Product.class, productIds);

			log.info("delete productList success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete productList error,productIds is  " + productIds);
			model.addAttribute("code", -6004);

		}
		//删除职位信息
		List<Long> professionIds = professionService.getProfessionIdsByCompanyId(id, 0, 10);
		try {
			professionService.deleteList(Profession.class, professionIds);

			log.info("delete professionList success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete professionList error,professionIds is  " + professionIds);
			model.addAttribute("code", -6004);
		}
		//删除职位标签
		List<Long> professionTagsIds = professionTagsService.getProfessionTagsIdsByCompanyId(id, 0, 10);
		try {
			professionTagsService.deleteList(ProfessionTags.class, professionTagsIds);

			log.info("delete professionTagsList success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete professionTagsList error,professionTagsIds is  " + professionTagsIds);
			model.addAttribute("code", -6004);
		}
		return "/data/json";
	}*/
	
	
/*	@RequestMapping(value = "/a/multi/company", method = RequestMethod.GET)
	public String getMultiCompanyJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Long[] ids)
			throws Exception {
			
		List<Long> idList = new ArrayList();	
	   if (ids == null) {

		} else {
			idList = Arrays.asList(ids);
		}
		try {

			

			List<Company> companyList = companyService.getObjectsByIds(idList);
			log.info("get  company data is " + companyList);

			model.addAttribute("code", 0);			
			model.addAttribute("total",companyList.size());

			model.addAttribute("companyList", companyList);

		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get company error,id is  " + idList);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/company/json/companyListJson";
	}*/
	

	//新增公司记录，新增行业记录，新增公司标签，新增公司产品

/*	@RequestMapping(value = "/a/u/company", method = RequestMethod.POST)
	public String addCompanyInfoJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @RequestBody CompanyInfo companyInfo) throws Exception {
			
		log.info("update companyinfo : companyinfo= " + companyInfo);
		//获取公司信息
		Company company=companyInfo.getCompany();
		//获取公司行业
		List<CompanyIndustry> industryList=companyInfo.getIndustryList();
		//获取公司标签
		List<CompanyTags> tagList=companyInfo.getTagList();
		//获取公司产品
		List<Product> productList=companyInfo.getProductList();
		//检查公司名称
		if (company.getName() == null) {
			model.addAttribute("code", -6500);
			return "/common/fail";
		}
		//检查公司标语
		if (company.getSlogan() == null){
			model.addAttribute("code", -6501);
			return "/common/fail";
		}
		//检查公司人数
		if (company.getTotalNum() == null){
			model.addAttribute("code", -6502);
			return "/common/fail";
		}
		//检查融资规模
		if (company.getFinancing() == null){
			model.addAttribute("code", -6503);
			return "/common/fail";
		}
		//检查省份
		if (company.getProvince() == null){
			model.addAttribute("code", -6504);
			return "/common/fail";
		}
		//检查城市
		if (company.getCity() == null){
			model.addAttribute("code", -6505);
			return "/common/fail";
		}
		//检查地区
		if (company.getCounty() == null){
			model.addAttribute("code", -6506);
			return "/common/fail";
		}
		//检查行业
		if (industryList == null) {
			model.addAttribute("code", -6507);
			return "/common/fail";
		}
		//检查认证状态
		if (company.getApproved() == null){
			model.addAttribute("code", -6508);
			return "/common/fail";
		}
		//检查公司LOGO
		if (company.getLogo() == null){
			model.addAttribute("code", -6509);
			return "/common/fail";
		}
		//检查公司介绍
		if (company.getSummary() == null){
			model.addAttribute("code", -6510);
			return "/common/fail";
		}
		
		try {
			//从cookie中获取userid,暂时写死
			company.setCreateBy(11L);
			company.setUpdateBy(company.getCreateBy());
			//获取修改时间戳
			company.setCreateAt(System.currentTimeMillis());
			company.setUpdateAt(company.getCreateAt());
			//设置新增公司为解冻状态
			company.setFreezed(0L);
			//插入公司记录
			Long companyId =companyService.insert(company);
			System.out.println("insert companyId=" + companyId);
			model.addAttribute("companyId", companyId);
			//插入行业记录
			for (CompanyIndustry companyIndustry : industryList) {
				companyIndustry.setCompanyId(companyId);
				//获取用户ID
				companyIndustry.setCreateBy(11L);
				companyIndustry.setUpdateBy(companyIndustry.getCreateBy());
				//获取修改时间戳
				companyIndustry.setCreateAt(System.currentTimeMillis());
				companyIndustry.setUpdateAt(companyIndustry.getCreateAt());
				//写表
				companyIndustryService.insert(companyIndustry);
			}
			//插入公司标签
			CompanyTags tag1 = tagList.get(0);
			if (tag1.getTag() != null) {
				for (CompanyTags companyTags : tagList) {
					companyTags.setCompanyId(companyId);
					//获取用户ID
					companyTags.setCreateBy(11L);
					companyTags.setUpdateBy(companyTags.getCreateBy());
					//获取修改时间戳
					companyTags.setCreateAt(System.currentTimeMillis());
					companyTags.setUpdateAt(companyTags.getCreateAt());
					//写表
					companyTagsService.insert(companyTags);
				}
			}
			//插入公司产品
				if (productList != null) {
					for (Product product : productList) {
						product.setCompanyId(companyId);
						//获取用户ID
						product.setCreateBy(11L);
						product.setUpdateBy(product.getCreateBy());
						//获取修改时间戳
						product.setCreateAt(System.currentTimeMillis());
						product.setUpdateAt(product.getCreateAt());
						//写表
						productService.insert(product);
					}
				}			
			model.addAttribute("code", 0);
		} 
		catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("add company error ");
			model.addAttribute("code", -1);
		}
		return "/data/json";
	}
	
	@RequestMapping(value = "/a/tags/{id}", method = RequestMethod.GET)
	public String getCompanyTagsJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("get data : companyId= " + id);
		try {
			
			List<Long> companyTagsIdList = companyTagsService.getCompanyTagsIdsByCompanyId(id, 0, 10);
			List<CompanyTags> companyTagsList=companyTagsService.getObjectsByIds(companyTagsIdList);
			
			log.info("get companyTagsList data is " + companyTagsList);

			model.addAttribute("code", 0);
			model.addAttribute("page", 1);
			model.addAttribute("size", companyTagsList.size());
			model.addAttribute("total", companyTagsList.size());
			model.addAttribute("companyTagsList", companyTagsList);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("get companyTagsList error,id is  " + id);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/companyTags/json/companyTagsListJson";
	}
	
	*/
	

	
	
}

