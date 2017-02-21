package com.ptteng.carrots.home.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import util.DynamicUtil;

import com.ptteng.carrots.home.model.Company;
import com.ptteng.carrots.home.model.CompanyIndustry;
import com.ptteng.carrots.home.model.CompanyTags;
import com.ptteng.carrots.home.model.Product;
import com.ptteng.carrots.home.model.Profession;
import com.ptteng.carrots.home.model.ProfessionTags;
import com.ptteng.carrots.home.service.CompanyIndustryService;
import com.ptteng.carrots.home.service.CompanyService;
import com.ptteng.carrots.home.service.CompanyTagsService;
import com.ptteng.carrots.home.service.ProductService;
import com.ptteng.carrots.home.service.ProfessionService;
import com.ptteng.carrots.home.service.ProfessionTagsService;
import com.ptteng.carrots.home.vo.CompanyInfo;
import com.qding.common.util.DataUtils;
import com.qding.common.util.http.cookie.CookieUtil;

/**
 * Company crud
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
	private com.qding.common.util.http.cookie.CookieUtil cookieUtil;

	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	/*
	 * @RequestMapping(value = "/c/company", method = RequestMethod.GET) public
	 * String getcompanyList(HttpServletRequest request, HttpServletResponse
	 * response, ModelMap model) throws Exception {
	 * 
	 * 
	 * 
	 * log.info("/company  to /company/view/companyList");
	 * 
	 * return "/carrots-home-service/company/view/companyList"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @RequestMapping(value = "/c/company/{id}", method = RequestMethod.GET)
	 * public String getCompany(HttpServletRequest request, HttpServletResponse
	 * response, ModelMap model, @PathVariable Long id) throws Exception {
	 * 
	 * log.info("/company/" + id + "  to /company/view/companyDeail"); if(null
	 * != id){ model.addAttribute("id", id); }else{ model.addAttribute("id", 0);
	 * }
	 * 
	 * return "/carrots-home-service/company/view/companyDetail"; }
	 */

	// 查询公司明细信息
	@RequestMapping(value = "/a/company/{id}", method = RequestMethod.GET)
	public String getCompanyJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("get data : id= " + id);
		try {
			Company company = companyService.getObjectById(id);
			log.info("get company data is " + company);
			model.addAttribute("company", company);
			// 获取行业ID
			List<Long> industryIds = companyIndustryService
					.getCompanyIndustryIdsByCompanyId(id, 0, 10);
			// 获取行业
			List<CompanyIndustry> industryList = companyIndustryService
					.getObjectsByIds(industryIds);
			model.addAttribute("industryList", industryList);

			// 获取公司标签ID
			List<Long> tagIds = companyTagsService
					.getCompanyTagsIdsByCompanyId(id, 0, 10);
			// 获取公司标签名称
			List<CompanyTags> tagList = companyTagsService
					.getObjectsByIds(tagIds);
			model.addAttribute("tagList", tagList);

			// 获取公司产品ID
			List<Long> productIds = productService.getProductIdsByCompanyId(id,
					0, 10);
			// 获取公司产品信息
			List<Product> productList = productService
					.getObjectsByIds(productIds);
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

	// 修改公司信息
	@RequestMapping(value = "/a/u/company/{id}", method = RequestMethod.PUT)
	public String updateCompanyJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@PathVariable Long id, @RequestBody CompanyInfo companyInfo)
			throws Exception {

		// 检查必输项
		// 获取公司信息
		Company company = companyInfo.getCompany();
		log.info("update company = " + company);
		// 获取公司行业
		List<CompanyIndustry> industryList = companyInfo.getIndustryList();
		log.info("update industry : " + industryList);
		// 获取公司标签
		List<CompanyTags> tagList = companyInfo.getTagList();
		log.info("update tag : " + tagList);
		// 获取公司产品
		List<Product> productList = companyInfo.getProductList();
		log.info("update product : " + productList);
		// 检查公司名称
		if (company.getName() == null) {
			model.addAttribute("code", -6500);
			return "/common/fail";
		}
		// 检查公司标语
		if (company.getSlogan() == null) {
			model.addAttribute("code", -6501);
			return "/common/fail";
		}
		// 检查公司人数
		if (company.getTotalNum() == null) {
			model.addAttribute("code", -6502);
			return "/common/fail";
		}
		// 检查融资规模
		if (company.getFinancing() == null) {
			model.addAttribute("code", -6503);
			return "/common/fail";
		}
		// 检查省份
		if (company.getProvince() == null) {
			model.addAttribute("code", -6504);
			return "/common/fail";
		}
		// 检查城市
		if (company.getCity() == null) {
			model.addAttribute("code", -6505);
			return "/common/fail";
		}
		// 检查地区
		if (company.getCounty() == null) {
			model.addAttribute("code", -6506);
			return "/common/fail";
		}
		// 检查行业（支持一个公司多个行业）
		if (industryList == null) {
			model.addAttribute("code", -6507);
			return "/common/fail";
		}
		// 检查认证状态
		if (company.getApproved() == null) {
			model.addAttribute("code", -6508);
			return "/common/fail";
		}
		// 检查公司LOGO
		if (company.getLogo() == null) {
			model.addAttribute("code", -6509);
			return "/common/fail";
		}
		// 检查公司介绍
		if (company.getSummary() == null) {
			model.addAttribute("code", -6510);
			return "/common/fail";
		}

		try {
			// 获取原有公司相关创建人和创建时间
			Company companySource = companyService.getObjectById(id);
			company.setCreateAt(companySource.getCreateAt());
			company.setCreateBy(companySource.getCreateBy());
			// 给ID字段赋值
			log.info("====================id" + id);
			company.setId(id);
			// 从cookie中获取USERID
			Long uid = Long.valueOf(cookieUtil.getKeyIdentity(request,
					com.qding.common.util.http.cookie.CookieUtil.USER_ID));
			company.setUpdateBy(uid);
			
			//如果公司省市县修改，则修改公司所有职位的省市县
			try {
				if(!company.getProvince().equals(companySource.getProvince())||company.getCity().equals(companySource.getCity())||company.getCounty().equals(companySource.getCounty())){
					List<Long> professionIds=professionService.getProfessionIdsByCompanyId(id, 0, Integer.MAX_VALUE);
					List<Profession> professionList=professionService.getObjectsByIds(professionIds);
						for(Profession profession:professionList){
							profession.setProvince(company.getProvince());
							profession.setCity(company.getCity());
							profession.setCounty(company.getCounty());
						}
					professionService.updateList(professionList);
				}
			} catch(Throwable t) {
				t.printStackTrace();
				log.error(t.getMessage());
				log.error("update profession province error, companyId is  "
						+ company.getId());
				model.addAttribute("code", -6003);
				return "/common/fail";
			}
		
			
			// 获取修改时间戳
			// company.setUpdateAt(System.currentTimeMillis());//康总的service里面已经做对updateAt赋值了
			// 判断产品名称是否有值，有则修改公司表中相关字段
			if (productList != null) {
				String productNameList = null;
				for (Product product : productList) {
					if (product.getName() != null) {
						// 获取公司名称字符串
						productNameList = product.getName() + "|";
					}
				}
				company.setProduct(productNameList);
			}

			log.info("update company = " + company);
			// 修改公司记录
			companyService.update(company);
			System.out.println("update companyId=" + id);
			model.addAttribute("companyId", id);
			// 修改公司行业记录
			List<Long> companyIndustryIds = companyIndustryService
					.getCompanyIndustryIdsByCompanyId(id, 0, 10);
			companyIndustryService.deleteList(CompanyIndustry.class,
					companyIndustryIds);
			for (CompanyIndustry companyIndustry : industryList) {
				// 给公司ID赋值
				companyIndustry.setCompanyId(id);
				// 获取用户ID
				companyIndustry.setCreateBy(uid);
				companyIndustry.setUpdateBy(companyIndustry.getCreateBy());
				// 获取修改时间戳
				companyIndustry.setCreateAt(System.currentTimeMillis());
				companyIndustry.setUpdateAt(companyIndustry.getCreateAt());
				// 修改表
				log.info("insert companyIndustry = " + companyIndustry);
				companyIndustryService.insert(companyIndustry);
			}

			List<Long> companyTagsListIds = companyTagsService
					.getCompanyTagsIdsByCompanyId(id, 0, Integer.MAX_VALUE);

			// 删除公司标签
			try {
				List<CompanyTags> companyTagsOldList = companyTagsService
						.getObjectsByIds(companyTagsListIds);

				List<CompanyTags> companyTagsDeleteList = new ArrayList();
				// 判断删除了哪些公司标签
				for (CompanyTags companyTagOld : companyTagsOldList) {
					for (CompanyTags companyTag : tagList) {
						log.info("companyTagOld ======================================= "
								+ companyTagOld.getTag().equals(companyTag.getTag()));
						if (companyTagOld.getTag().equals(companyTag.getTag())) {
							// 如果原公司标签和新提交的标签一致（已存在标签），则对该标签做一个标识，这里通过updateAt字段标识
							companyTagOld.setUpdateAt(100L);
						}
					}
				}

				for (CompanyTags companyTagOld : companyTagsOldList) {
					// 如果原公司标签未被标识，则将该标签添加至需要删除公司标签的list中
					if (companyTagOld.getUpdateAt() != 100L) {
						companyTagsDeleteList.add(companyTagOld);
					}
				}

				List<Long> companyTagsDeleteIds = new ArrayList<Long>();
				for (CompanyTags companyTagDelete : companyTagsDeleteList) {
					companyTagsDeleteIds.add(companyTagDelete.getId());
				}
				// 标签修改中有新增标签和删除标签，故统一删除后插入 比较省事
				// companyTagsService.deleteList(CompanyTags.class,
				// companyTagsDeleteIds);
				if (companyTagsDeleteIds != null) {
					try {
						List<Long> professionTagsIds = new ArrayList<Long>();

						for (CompanyTags companyTagDelete : companyTagsDeleteList) {
							Map<String, Object> param = DynamicUtil
									.getProfessionTagsList(
											companyTagDelete.getCompanyId(),
											companyTagDelete.getTag());
							// 获取所有需要删除职位标签的id
							professionTagsIds.addAll(professionService
									.getIdsByDynamicCondition(
											ProfessionTags.class, param, 0,
											Integer.MAX_VALUE));
						}
						log.info("delte professionTags, profession ids ============= "
								+ professionTagsIds);

						professionTagsService.deleteList(ProfessionTags.class,
								professionTagsIds);
					} catch (Throwable t) {
						t.printStackTrace();
						log.error(t.getMessage());
						log.error("delete professionTags error,id is  "
								+ company.getId());
						model.addAttribute("code", -6003);
						return "/common/fail";
					}
				}
			} catch (Throwable t) {
				t.printStackTrace();
				log.error(t.getMessage());
				log.error("delete companyTags error,id is  " + company.getId());
				model.addAttribute("code", -6003);
				return "/common/fail";

			}

			// 标签修改中有新增标签和删除标签，故统一删除后插入 比较省事
			companyTagsService
					.deleteList(CompanyTags.class, companyTagsListIds);
			// 如果公司标签列表不为空则插入公司标签
			if (tagList != null) {
				for (CompanyTags companyTags : tagList) {
					// 给公司ID赋值
					companyTags.setCompanyId(id);
					// 获取用户ID
					companyTags.setCreateBy(uid);
					companyTags.setUpdateBy(companyTags.getCreateBy());
					// 获取修改时间戳
					// companyTags.setCreateAt(System.currentTimeMillis());
					// companyTags.setUpdateAt(companyTags.getCreateAt());
					// 写表
					log.info("insert companyTags = " + companyTags);
					companyTagsService.insert(companyTags);// 可以用插入list来取代循环插入
				}
			}
			// 删除公司所有产品
			List<Long> productIdList = productService.getProductIdsByCompanyId(
					id, 0, 10);
			productService.deleteList(Product.class, productIdList);
			// 如果productList不为空则新增产品信息
			if (productList != null) {
				for (Product product : productList) {
					// 获取用户ID
					product.setCreateBy(uid);
					product.setUpdateBy(product.getCreateBy());
					// 获取修改时间戳
					product.setCreateAt(System.currentTimeMillis());
					product.setUpdateAt(product.getCreateAt());
					// 给公司ID赋值
					product.setCompanyId(id);
					// 写表
					log.info("insert product = " + product);
					productService.insert(product);
				}
			}
			model.addAttribute("code", 0);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("update company error,id is  " + company.getId());
			model.addAttribute("code", -6003);
			return "/common/fail";

		}

		return "/common/success";
	}

	/*
	 * @RequestMapping(value = "/a/company", method = RequestMethod.POST) public
	 * String addCompanyJson(HttpServletRequest request, HttpServletResponse
	 * response, ModelMap model, Company company) throws Exception {
	 * 
	 * log.info("update company : company= " + company);
	 * 
	 * try { company.setId(null);
	 * 
	 * companyService.insert(company);
	 * 
	 * model.addAttribute("code", 0); } catch (Throwable t) {
	 * t.printStackTrace(); log.error(t.getMessage());
	 * log.error("add company error "); model.addAttribute("code", -6002); }
	 * 
	 * return "/data/json"; }
	 */

	// 删除公司信息
	@RequestMapping(value = "/a/u/company/{id}", method = RequestMethod.DELETE)
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
			return "/common/fail";

		}
		// 删除公司行业
		List<Long> IndustryIds = companyIndustryService
				.getCompanyIndustryIdsByCompanyId(id, 0, 10);
		try {
			companyIndustryService.deleteList(CompanyIndustry.class,
					IndustryIds);

			log.info("delete companyIndustryList success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete companyIndustryList error,companyIndustryIds is  "
					+ IndustryIds);
			model.addAttribute("code", -6004);
			return "/common/fail";

		}
		// 删除公司标签
		List<Long> companyTagsIds = companyTagsService
				.getCompanyTagsIdsByCompanyId(id, 0, 10);
		try {
			companyTagsService.deleteList(CompanyTags.class, companyTagsIds);

			log.info("delete companyTags success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete companyTags error,companyTagsIds is  "
					+ companyTagsIds);
			model.addAttribute("code", -6004);
			return "/common/fail";

		}
		// 删除公司产品
		List<Long> productIds = productService.getProductIdsByCompanyId(id, 0,
				10);
		try {
			productService.deleteList(Product.class, productIds);

			log.info("delete productList success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete productList error,productIds is  " + productIds);
			model.addAttribute("code", -6004);
			return "/common/fail";

		}
		// 删除职位信息
		List<Long> professionIds = professionService
				.getProfessionIdsByCompanyId(id, 0, 10);
		try {
			professionService.deleteList(Profession.class, professionIds);

			log.info("delete professionList success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete professionList error,professionIds is  "
					+ professionIds);
			model.addAttribute("code", -6004);
			return "/common/fail";
		}
		// 删除职位标签
		List<Long> professionTagsIds = professionTagsService
				.getProfessionTagsIdsByCompanyId(id, 0, 10);
		try {
			professionTagsService.deleteList(ProfessionTags.class,
					professionTagsIds);

			log.info("delete professionTagsList success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete professionTagsList error,professionTagsIds is  "
					+ professionTagsIds);
			model.addAttribute("code", -6004);
			return "/common/fail";
		}
		return "/common/success";
	}

	/*
	 * @RequestMapping(value = "/a/multi/company", method = RequestMethod.GET)
	 * public String getMultiCompanyJson(HttpServletRequest request,
	 * HttpServletResponse response, ModelMap model, Long[] ids) throws
	 * Exception {
	 * 
	 * List<Long> idList = new ArrayList(); if (ids == null) {
	 * 
	 * } else { idList = Arrays.asList(ids); } try {
	 * 
	 * 
	 * 
	 * List<Company> companyList = companyService.getObjectsByIds(idList);
	 * log.info("get  company data is " + companyList);
	 * 
	 * model.addAttribute("code", 0);
	 * model.addAttribute("total",companyList.size());
	 * 
	 * model.addAttribute("companyList", companyList);
	 * 
	 * } catch (Throwable t) { log.error(t.getMessage());
	 * log.error("get company error,id is  " + idList);
	 * model.addAttribute("code", -100000); }
	 * 
	 * return "/carrots-home-service/company/json/companyListJson"; }
	 */

	// 新增公司记录，新增行业记录，新增公司标签，新增公司产品

	@RequestMapping(value = "/a/u/company", method = RequestMethod.POST)
	public String addCompanyInfoJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestBody CompanyInfo companyInfo) throws Exception {

		log.info("update companyinfo : companyinfo= " + companyInfo);
		// 获取公司信息
		Company company = companyInfo.getCompany();
		// 获取公司行业
		List<CompanyIndustry> industryList = companyInfo.getIndustryList();
		// 获取公司标签
		List<CompanyTags> tagList = companyInfo.getTagList();
		// 获取公司产品
		List<Product> productList = companyInfo.getProductList();
		// 检查公司名称
		if (company.getName() == null) {
			model.addAttribute("code", -6500);
			return "/common/fail";
		}
		// 检查公司标语
		if (company.getSlogan() == null) {
			model.addAttribute("code", -6501);
			return "/common/fail";
		}
		// 检查公司人数
		if (company.getTotalNum() == null) {
			model.addAttribute("code", -6502);
			return "/common/fail";
		}
		// 检查融资规模
		if (company.getFinancing() == null) {
			model.addAttribute("code", -6503);
			return "/common/fail";
		}
		// 检查省份
		if (company.getProvince() == null) {
			model.addAttribute("code", -6504);
			return "/common/fail";
		}
		// 检查城市
		if (company.getCity() == null) {
			model.addAttribute("code", -6505);
			return "/common/fail";
		}
		// 检查地区
		if (company.getCounty() == null) {
			model.addAttribute("code", -6506);
			return "/common/fail";
		}
		// 检查行业
		if (industryList == null) {
			model.addAttribute("code", -6507);
			return "/common/fail";
		}
		// 检查认证状态
		if (company.getApproved() == null) {
			model.addAttribute("code", -6508);
			return "/common/fail";
		}
		// 检查公司LOGO
		if (company.getLogo() == null) {
			model.addAttribute("code", -6509);
			return "/common/fail";
		}
		// 检查公司介绍
		if (company.getSummary() == null) {
			model.addAttribute("code", -6510);
			return "/common/fail";
		}

		try {
			// 从cookie中获取userid
			Long uid = Long.valueOf(cookieUtil.getKeyIdentity(request,
					com.qding.common.util.http.cookie.CookieUtil.USER_ID));
			company.setCreateBy(uid);
			company.setUpdateBy(company.getCreateBy());
			// 获取修改时间戳
			company.setCreateAt(System.currentTimeMillis());
			company.setUpdateAt(company.getCreateAt());
			// 设置新增公司为解冻状态
			company.setFreezed(0L);
			// 判断产品名称是否有值，有则修改公司表中相关字段
			if (productList != null) {
				String productNameList = null;
				for (Product product : productList) {
					if (product.getName() != null) {
						// 获取公司名称字符串
						productNameList = product.getName() + "|";
					}
				}
				company.setProduct(productNameList);
			}
			// 插入公司记录
			Long companyId = companyService.insert(company);
			System.out.println("insert companyId=" + companyId);
			model.addAttribute("companyId", companyId);
			// 插入行业记录
			for (CompanyIndustry companyIndustry : industryList) {
				// 给公司ID字段赋值
				companyIndustry.setCompanyId(companyId);
				// 获取用户ID
				companyIndustry.setCreateBy(uid);
				companyIndustry.setUpdateBy(companyIndustry.getCreateBy());
				// 获取修改时间戳
				companyIndustry.setCreateAt(System.currentTimeMillis());
				companyIndustry.setUpdateAt(companyIndustry.getCreateAt());
				// 写表
				companyIndustryService.insert(companyIndustry);
			}
			// 插入公司标签
			if (null != tagList) {
				CompanyTags tag1 = tagList.get(0);
				if (tag1.getTag() != null) {
					for (CompanyTags companyTags : tagList) {
						companyTags.setCompanyId(companyId);
						// 获取用户ID
						companyTags.setCreateBy(uid);
						companyTags.setUpdateBy(companyTags.getCreateBy());
						// 获取修改时间戳
						companyTags.setCreateAt(System.currentTimeMillis());
						companyTags.setUpdateAt(companyTags.getCreateAt());
						// 写表
						companyTagsService.insert(companyTags);
					}
				}
			}
			// 插入公司产品
			if (productList != null) {
				for (Product product : productList) {
					product.setCompanyId(companyId);
					// 获取用户ID
					product.setCreateBy(uid);
					product.setUpdateBy(product.getCreateBy());
					// 获取修改时间戳
					product.setCreateAt(System.currentTimeMillis());
					product.setUpdateAt(product.getCreateAt());
					// 写表
					productService.insert(product);
				}
			}
			model.addAttribute("code", 0);
		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("add company error ");
			model.addAttribute("code", -1);
			return "/common/fail";
		}
		return "/common/addsuccess";
	}

	// 通过公司ID获取公司标签，供新增/修改职位功能使用
	@RequestMapping(value = "/a/tags/{id}", method = RequestMethod.GET)
	public String getCompanyTagsJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("get data : companyId= " + id);
		try {

			List<Long> companyTagsIdList = companyTagsService
					.getCompanyTagsIdsByCompanyId(id, 0, 10);
			List<CompanyTags> companyTagsList = companyTagsService
					.getObjectsByIds(companyTagsIdList);

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

	// 多条件查询，动态sql查询
	@RequestMapping(value = "/a/company/search", method = RequestMethod.GET)
	public String getMulticompanyJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String name,
			String product, String province, String city, String county,
			Long industry, Long financing, Long freezed, Long approved,
			Integer page, Integer size) throws Exception {

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

		// 职位id列表
		List<Long> ids = null;
		List<Long> companyIds = null;

		try {
			// 组装动态sql
			Map<String, Object> param = DynamicUtil.getCompanyMangerList(name,
					product, province, city, county, industry, financing,
					freezed, approved);
			log.info("get  param data is " + param);
			// 通过动态sql查询获得公司ID列表
			ids = companyService.getIdsByDynamicCondition(Company.class, param,
					start, size);
			List<Company> companyList = companyService.getObjectsByIds(ids);

			for (Company company : companyList) {
				if (industry != null) {
					log.info("get  industry " + industry);
					CompanyIndustry indusryOne = new CompanyIndustry();
					indusryOne.setIndustry(industry);
					List<CompanyIndustry> industryList = new ArrayList<CompanyIndustry>();
					industryList.add(indusryOne);
					company.setCompanyIndustryList(industryList);
				} else {
					Long companyId = company.getId();
					log.info("get industry, company is " + company.getId());
					// 获取行业ID列表
					List<Long> industryIdList = companyIndustryService
							.getCompanyIndustryIdsByCompanyId(companyId, 0, 10);
					log.info("get industry, industryIdList is "
							+ industryIdList);
					// 获取行业对象列表
					List<CompanyIndustry> industryList = companyIndustryService
							.getObjectsByIds(industryIdList);
					log.info("get industry, industryList is " + industryList);
					// 给公司的行业列表赋值
					company.setCompanyIndustryList(industryList);
				}
			}

			companyIds = companyService.getIdsByDynamicCondition(Company.class,
					param, 0, Integer.MAX_VALUE);

			log.info("get  company data is " + companyList);

			model.addAttribute("code", 0);
			model.addAttribute("page", page);
			model.addAttribute("size", size);
			model.addAttribute("total", companyIds.size());
			model.addAttribute("companyList", companyList);

		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get company error,id is  " + ids);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/company/json/companyListJson";
	}

	// 修改公司状态
	@RequestMapping(value = "/a/u/company/status", method = RequestMethod.PUT)
	public String updateCompanyStatus(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Long id, Long type,
			Long status) throws Exception {

		log.info("update companyID : " + id);
		log.info("update statusType : " + type);
		log.info("update status : " + status);

		/*
		 * //检查ID if (company.getName() == null) { model.addAttribute("code",
		 * -6500); return "/common/fail"; } //检查状态类型 if (company.getSlogan() ==
		 * null){ model.addAttribute("code", -6501); return "/common/fail"; }
		 * //检查状态 if (company.getTotalNum() == null){ model.addAttribute("code",
		 * -6502); return "/common/fail"; }
		 */

		try {
			// 获取原有公司相关信息
			Company company = companyService.getObjectById(id);
			// type为0则修改冻结/解冻状态
			// status 0-解冻 1-冻结
			// type为1则修改认证/取消状态
			// status 0-接触认证 1-认证
			if (type == 0) {
				company.setFreezed(status);
			} else if (type == 1) {
				company.setApproved(status);
			}
			companyService.update(company);
			// 当公司状态修改为冻结时，修改该公司下所有职位状态为下架
			if (type == 0 && status == 1) {
				List<Long> professionIds = professionService
						.getProfessionIdsByCompanyId(id, 0, 10);
				List<Profession> professionList = professionService
						.getObjectsByIds(professionIds);
				for (Profession profession : professionList) {
					// 给职位状态赋值为0-下架
					profession.setStatus(0L);
					professionService.update(profession);
				}
			}
			model.addAttribute("code", 0);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("update company status error!!");
			log.error("update company id is  " + id);
			log.error("update status type is  " + type);
			log.error("update status is  " + status);
			model.addAttribute("code", -6003);
			return "/common/fail";

		}

		return "/common/success";
	}

}
