package com.ptteng.carrots.home.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.ptteng.carrots.home.vo.ProfessionAndTags;

/**
 * Profession crud
 * 
 * @author magenm
 * @Date 2014-4-16 13:43
 * 
 */
@Controller
public class ProfessionController {
	private static final Log log = LogFactory
			.getLog(ProfessionController.class);

	@Autowired
	private ProfessionService professionService;

	@Autowired
	private ProfessionTagsService professionTagsService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyTagsService companyTagsService;

	@Autowired
	private CompanyIndustryService companyIndustryService;

	@Autowired
	private ProductService productService;

	/**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	// @RequestMapping(value = "/c/profession", method = RequestMethod.GET)
	// public String getprofessionList(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model) throws Exception {
	//
	// log.info("/profession  to /profession/view/professionList");
	//
	// return "/carrots-home-service/profession/view/professionList";
	// }
	//
	// @RequestMapping(value = "/c/profession/{id}", method = RequestMethod.GET)
	// public String getProfession(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model, @PathVariable Long id)
	// throws Exception {
	//
	// log.info("/profession/" + id + "  to /profession/view/professionDeail");
	// if (null != id) {
	// model.addAttribute("id", id);
	// } else {
	// model.addAttribute("id", 0);
	// }
	//
	// return "/carrots-home-service/profession/view/professionDetail";
	// }

	@RequestMapping(value = "/a/profession/{id}", method = RequestMethod.GET)
	public String getProfessionJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("get data : id= " + id);

		// 定义职位，公司，职位标签列表对象
		Profession profession = null;
		Company company = null;
		List<ProfessionTags> tags = null;

		try {
			profession = professionService.getObjectById(id);
			log.info("get profession data is " + profession);
			model.addAttribute("profession", profession);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("get profession error,id is  " + id);
			model.addAttribute("code", -100000);
		}

		try {
			company = companyService.getObjectById(profession.getCompanyId());
			model.addAttribute("company", company);

		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get company error,id is  " + profession.getCompanyId());
			model.addAttribute("code", -100000);
		}

		try {
			List<Long> productIds = productService.getProductIdsByCompanyId(
					profession.getCompanyId(), 0, Integer.MAX_VALUE);
			Product product = productService.getObjectById(productIds.get(0));
			model.addAttribute("product", product);

		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get product error ");
			model.addAttribute("code", -100000);
		}

		try {
			List<Long> companyIndustryIds = companyIndustryService
					.getCompanyIndustryIdsByCompanyId(
							profession.getCompanyId(), 0, Integer.MAX_VALUE);
			List<CompanyIndustry> companyIndustryList = companyIndustryService
					.getObjectsByIds(companyIndustryIds);
			model.addAttribute("companyIndustryList", companyIndustryList);
		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get companyIndustry error,id is  "
					+ profession.getCompanyId());
			model.addAttribute("code", -100000);
		}

		try {
			List<Long> companyTagIds = companyTagsService
					.getCompanyTagsIdsByCompanyId(profession.getCompanyId(), 0,
							Integer.MAX_VALUE);
			List<CompanyTags> companyTags = companyTagsService
					.getObjectsByIds(companyTagIds);
			for (CompanyTags companyTag : companyTags) {
				log.info("get companyTag data is " + companyTag);
			}
			model.addAttribute("companyTags", companyTags);
			// model.addAttribute("code", 0);
		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get companyTagsList error");
			model.addAttribute("code", -100000);
		}

		try {
			List<Long> tagIds = professionTagsService
					.getProfessionTagsIdsByProfessionId(id, 0,
							Integer.MAX_VALUE);
			tags = professionTagsService.getObjectsByIds(tagIds);

			// for (ProfessionTags professionTag : tags) {
			// log.info("get professionTag data is " + professionTag);
			// }
			// log.info("get professionTag data is " + tags);
			model.addAttribute("tags", tags);
			model.addAttribute("code", 0);
		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get professionTagsList error");
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/profession/json/professionDetailJson";
	}

	@RequestMapping(value = "/a/profession/search", method = RequestMethod.GET)
	public String getMultiProfessionJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Long companyId,
			String companyName, String name, String province, String city,
			String county, String[] category, String[] subCategory,
			String industry, String experience, String education,
			String compensation, Long updateAt, Long recommend,
			Long returnTags, Long startAt, Long endAt, Integer page,
			Integer size) throws Exception {

		// page默认1，size默认10
		if (page == null) {
			page = 1;
		}
		if (size == null) {
			size = 10;
		}
		// 查询从start开始
		int start = (page - 1) * size;
		if (start < 0) {
			start = 0;
		}

		// 职位id列表
		List<Long> ids = null;
		List<Long> count = null;

		try {
			log.info(" start to get param ");
			// 组装动态sql
			Map<String, Object> param = null;
			try {
				 param = DynamicUtil.getProfessionList(
						companyId, companyName, name, province, city, county,
						industry, category, subCategory, experience, education,
						compensation, updateAt, recommend, startAt, endAt);
			} catch (Throwable t) {
				log.error(t.getMessage());
				log.error("======= Now param is " + param );
			}
			log.info("get  param data is " + param);

			// 通过动态sql查询获得职位id列表
			ids = professionService.getIdsByDynamicCondition(Profession.class,
					param, start, size);
			// 记录总数
			count = professionService.getIdsByDynamicCondition(
					Profession.class, param, 0, Integer.MAX_VALUE);

			if(count.size()==0){
				model.addAttribute("code", 0);
				model.addAttribute("page", page);
				model.addAttribute("size", size);
				model.addAttribute("total", count.size());
				return "/carrots-home-service/profession/json/professionListJson";
			}

			// 查询返回职位列表
			List<Profession> professionList = professionService
					.getObjectsByIds(ids);
			// log.info("get  professionId data is ====================="
			// + ids.toString());

			// 获取职位标签列表
			List<Long> professionTagIds = new ArrayList<Long>();
			List<ProfessionTags> professionTagsList = new ArrayList<ProfessionTags>();
			if (returnTags != null && returnTags == 1L) {
				String profession_ids = ids.toString();
				profession_ids = profession_ids.replace("[", " ");
				profession_ids = profession_ids.replace("]", " ");
				// log.info("profession_ids is =====================" +
				// profession_ids);

				Map<String, Object> professionTagsParam = DynamicUtil
						.getProfessionTags(profession_ids);
				professionTagIds = professionTagsService
						.getIdsByDynamicCondition(ProfessionTags.class,
								professionTagsParam, 0, Integer.MAX_VALUE);

				professionTagsList = professionTagsService
						.getObjectsByIds(professionTagIds);
			}

			// 通过职位列表获取公司id列表,并去重
			Set<Long> companyIds = new HashSet<Long>();

			for (Profession profession : professionList) {
				companyIds.add(profession.getCompanyId());
			}
			// 获取公司列表只能通过List，不能通过set
			List<Long> companyIdList = new ArrayList<Long>();
			for (Long company_id : companyIds) {
				companyIdList.add(company_id);
			}
			List<Company> companyList = companyService
					.getObjectsByIds(companyIdList);

			String company_ids = companyIds.toString();
			company_ids = company_ids.replace("[", "");
			company_ids = company_ids.replace("]", "");

			Map<String, Object> industryParam = DynamicUtil
					.getIndustryList(company_ids);

			List<Long> companyIndustryIds = companyIndustryService
					.getIdsByDynamicCondition(CompanyIndustry.class,
							industryParam, 0, Integer.MAX_VALUE);

			List<CompanyIndustry> companyIndustryList = companyIndustryService
					.getObjectsByIds(companyIndustryIds);

			// log.info("get  professionList data is ====================="
			// + professionList.toString());
			// log.info("get  professionTagsList data is ====================="
			// + professionTagsList.toString());
			// log.info("get  companyList data is ====================="
			// + companyList.toString());
			// log.info("get  companyIndustryList data is ====================="
			// + companyIndustryList.toString());

			List<ProfessionAndTags> professionAndTagsList = new ArrayList<ProfessionAndTags>();

			// 循环的将profession对象add到professionTagsList中
			for (Profession profession : professionList) {

				ProfessionAndTags professionAndTags = new ProfessionAndTags();
				List<ProfessionTags> tags = new ArrayList<ProfessionTags>();
				List<CompanyIndustry> industryList = new ArrayList<CompanyIndustry>();
				try {
					if (returnTags != null && returnTags == 1L) {
						for (ProfessionTags professionTag : professionTagsList) {
							if (profession.getId().equals(
									professionTag.getProfessionId())) {
								tags.add(professionTag);
							}
						}
						professionAndTags.setTags(tags);
					}

					for (Company company : companyList) {
						if (profession.getCompanyId().equals(company.getId())) {
							// profession.setCompanyName(company.getName());
							professionAndTags.setLogo(company.getLogo());
							// log.info("get  company data is ====================="
							// + company.toString());

						}
					}
					for (CompanyIndustry companyIndustry : companyIndustryList) {
						if (profession.getCompanyId().equals(
								companyIndustry.getCompanyId())) {
							industryList.add(companyIndustry);
							// log.info("get companyIndustry data is ====================="
							// + companyIndustry.toString());
						}
					}
					professionAndTags.setProfession(profession);

					professionAndTags.setIndustryList(industryList);
				} catch (Throwable t) {
					log.error(t.getMessage());
					log.error("add profession to professionAndTagsList error,professionId is  "
							+ profession.getId());
				}

				// null不能和1L做比较
				// if (returnTags != null && returnTags == 1L) {
				// try {
				// // 通过职位ID查询职位tag ID列表
				// List<Long> professionTagIds = professionTagsService
				// .getProfessionTagsIdsByProfessionId(
				// profession.getId(), 0, 10);
				// // 通过职位tag ID列表查询professionTag列表
				// List<ProfessionTags> tags = professionTagsService
				// .getObjectsByIds(professionTagIds);
				//
				// professionAndTags.setTags(tags);
				// log.info("get professionTags");
				// } catch (Throwable t) {
				// log.error(t.getMessage());
				// log.error("get professionTagsList error,professionId is  "
				// + profession.getId());
				// // model.addAttribute("code", -100000);
				// }
				// } else if (returnTags == null) {
				// log.info("returnTags==========" + returnTags);
				// log.info("not get professionTags data");
				// }

				// try {
				// // 通过公司id查询公司信息
				// Company company = companyService.getObjectById(profession
				// .getCompanyId());
				// log.info("get company data, companyId is"
				// + profession.getCompanyId());
				// // 获取公司logo
				// profession.setCompanyName(company.getName());//
				// 数据库profession表加字段后删除
				// professionAndTags.setLogo(company.getLogo());
				// } catch (Throwable t) {
				// log.error(t.getMessage());
				// log.error("get company logo error,companyId is  "
				// + profession.getCompanyId());
				// }
				//
				// try {
				// // 通过公司id查询公司行业列表
				// List<Long> industryIds = companyIndustryService
				// .getCompanyIndustryIdsByCompanyId(
				// profession.getCompanyId(), 0,
				// Integer.MAX_VALUE);
				//
				// List<CompanyIndustry> industryList = companyIndustryService
				// .getObjectsByIds(industryIds);
				//
				// // 设置profesionAndTags的indutryLIst
				// professionAndTags.setIndustryList(industryList);
				// } catch (Throwable t) {
				// log.error(t.getMessage());
				// log.error("get companyIndustryList error,companyId is  "
				// + profession.getCompanyId());
				// }

				// 将professionAndTags加入ProfessionAndTagsList
				professionAndTagsList.add(professionAndTags);

			}

			// log.info("get  profession data is " + professionList);

			model.addAttribute("code", 0);
			model.addAttribute("page", page);
			model.addAttribute("size", size);
			model.addAttribute("total", count.size());
			model.addAttribute("professionList", professionList);
			model.addAttribute("professionAndTagsList", professionAndTagsList);

			// 返回首页轮播的1个list中有5个list的蛋疼逻辑
			// 容易出错，砍掉了
			// if (returnPage == 0L) {
			// List homePageProfessionAndTagsList = new ArrayList();
			// for (int i = 0; i < 5; i++) {
			// List<ProfessionAndTags> subHomePageProfessionAndTagsList = new
			// ArrayList<>();
			// for (int j = 0; j < 4; j++) {
			// subHomePageProfessionAndTagsList
			// .add(professionAndTagsList.get(j));
			// }
			// homePageProfessionAndTagsList
			// .add(subHomePageProfessionAndTagsList);
			//
			// }
			// model.addAttribute("homePageProfessionAndTagsList",
			// homePageProfessionAndTagsList);
			// }

		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get profession error,id is  " + ids);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/profession/json/professionListJson";
	}


	// @RequestMapping(value = "/a/u/profession/{id}", method =
	// RequestMethod.PUT)
	// public String updateProfessionJson(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model,
	// @PathVariable Long id,
	// @RequestBody ProfessionAndTags professionAndTags) throws Exception {
	//
	// log.info(" professionAndTag : professionAndTag= " + professionAndTags);
	// Profession profession = professionAndTags.getProfession();
	// List<ProfessionTags> tags = professionAndTags.getTags();
	// // log.info("update profession : profession= " + profession.toString());
	//
	// // 入参校验
	// if (null == profession.getName()) {
	// model.addAttribute("code", -9002);
	// return "data/json";
	// }
	// if (profession.getName().length() > 255) {
	// model.addAttribute("code", -9003);
	// return "data/json";
	// }
	// if (null == profession.getCategory()
	// || !(profession.getCategory() instanceof Long)
	// || profession.getCategory() < 0 || profession.getCategory() > 4) {
	// model.addAttribute("code", -9004);
	// return "data/json";
	// }
	// if (null == profession.getSubCategory()
	// || !(profession.getSubCategory() instanceof Long)
	// || profession.getSubCategory() < 0
	// || profession.getSubCategory() > 7) {
	// model.addAttribute("code", -9005);
	// return "data/json";
	// }
	// if (null == profession.getEducation()
	// || !(profession.getEducation() instanceof Long)
	// || profession.getEducation() < 0
	// || profession.getEducation() > 3) {
	// model.addAttribute("code", -9006);
	// return "data/json";
	// }
	// if (null == profession.getExperience()
	// || !(profession.getExperience() instanceof Long)
	// || profession.getExperience() < 0
	// || profession.getExperience() > 4) {
	// model.addAttribute("code", -9007);
	// return "data/json";
	// }
	// if (null == profession.getRecommend()
	// || !(profession.getRecommend() instanceof Long)
	// || profession.getRecommend() < 0
	// || profession.getRecommend() > 1) {
	// model.addAttribute("code", -9008);
	// return "data/json";
	// }
	// if (null == profession.getResponsibility()) {
	// model.addAttribute("code", -9009);
	// return "data/json";
	// }
	// if (null == profession.getRequisite()) {
	// model.addAttribute("code", -9010);
	// return "data/json";
	// }
	// if (null == profession.getBoon()) {
	// model.addAttribute("code", -9011);
	// return "data/json";
	// }
	// if (!CollectionUtils.isEmpty(tags)) {
	// for (ProfessionTags professionTag : tags) {
	// if (null == professionTag) {
	// model.addAttribute("code", -9012);
	// return "data/json";
	// }
	// }
	// }
	//
	// try {
	// log.info("update profession : profession= " + profession.toString());
	// // Company company = companyService.getObjectById(profession
	// // .getCompanyId());
	// // 获取公司省市县
	// profession.setId(id);
	// profession.setProvince("1");//从company获取
	// profession.setCity("1");
	// profession.setCounty("1");
	// profession.setStatus(1L);
	// profession.setCreateBy(120L);
	// profession.setUpdateBy(231L);
	// profession.setCreateAt(11L);
	// professionService.update(profession);
	//
	// // 根据professionId查询得到tagIds的list
	// List<Long> tagIds = professionTagsService
	// .getProfessionTagsIdsByProfessionId(id, 0, 10);
	// // 如果tagIds不为空，则全部删除
	// if (!CollectionUtils.isEmpty(tagIds))
	// professionTagsService.deleteList(ProfessionTags.class, tagIds);
	//
	// // 如果传入职位标签不为空，则将标签插入profession_tags
	// if (!CollectionUtils.isEmpty(tags)) {
	//
	// for (ProfessionTags professionTag : tags) {
	// professionTag.setId(null);
	// professionTag.setProfessionId(id);
	// professionTag.setCreateBy((long) 120);
	// professionTag.setUpdateBy((long) 120);
	// }
	// professionTagsService.insertList(tags);
	// }
	// model.addAttribute("code", 0);
	//
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("update profession error,id is  " + profession.getId());
	// model.addAttribute("code", -6003);
	//
	// }
	//
	// return "/data/json";
	// }
	//
	// @RequestMapping(value = "/a/u/profession", method = RequestMethod.POST)
	// public String addProfessionJson(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model,
	// @RequestBody ProfessionAndTags professionAndTags) throws Exception {
	//
	// log.info("insert professionAndTag : professionAndTag= "
	// + professionAndTags.toString());
	// Profession profession = professionAndTags.getProfession();
	// List<ProfessionTags> tags = professionAndTags.getTags();
	// log.info("insert profession : profession= " + profession.toString());
	// log.info("insert profession : professionTags= " + tags.toString());
	// // 入参校验
	//
	// if (null == profession.getName()) {
	// model.addAttribute("code", -9002);
	// return "data/json";
	// }
	// if (profession.getName().length() > 255) {
	// model.addAttribute("code", -9003);
	// return "data/json";
	// }
	// if (null == profession.getCategory()
	// || !(profession.getCategory() instanceof Long)
	// || profession.getCategory() < 0 || profession.getCategory() > 4) {
	// model.addAttribute("code", -9004);
	// return "data/json";
	// }
	// if (null == profession.getSubCategory()
	// || !(profession.getSubCategory() instanceof Long)
	// || profession.getSubCategory() < 0
	// || profession.getSubCategory() > 7) {
	// model.addAttribute("code", -9005);
	// return "data/json";
	// }
	// if (null == profession.getEducation()
	// || !(profession.getEducation() instanceof Long)
	// || profession.getEducation() < 0
	// || profession.getEducation() > 3) {
	// model.addAttribute("code", -9006);
	// return "data/json";
	// }
	// if (null == profession.getExperience()
	// || !(profession.getExperience() instanceof Long)
	// || profession.getExperience() < 0
	// || profession.getExperience() > 4) {
	// model.addAttribute("code", -9007);
	// return "data/json";
	// }
	// if (null == profession.getRecommend()
	// || !(profession.getRecommend() instanceof Long)
	// || profession.getRecommend() < 0
	// || profession.getRecommend() > 1) {
	// model.addAttribute("code", -9008);
	// return "data/json";
	// }
	// if (null == profession.getResponsibility()) {
	// model.addAttribute("code", -9009);
	// return "data/json";
	// }
	// if (null == profession.getRequisite()) {
	// model.addAttribute("code", -9010);
	// return "data/json";
	// }
	// if (null == profession.getBoon()) {
	// model.addAttribute("code", -9011);
	// return "data/json";
	// }
	// if (!CollectionUtils.isEmpty(tags)) {
	// for (ProfessionTags professionTag : tags) {
	// if (null == professionTag) {
	// model.addAttribute("code", -9012);
	// return "data/json";
	// }
	// }
	// }
	//
	// try {
	// // professionTag id设置为null
	// profession.setId(null);
	// // Company company = companyService.getObjectById(profession
	// // .getCompanyId());
	// // profession参数和tags参数写死
	// profession.setProvince("1");
	// profession.setCity("1");
	// profession.setCounty("1");
	// profession.setStatus(1L);
	// profession.setCreateBy(120L);
	// profession.setUpdateBy(231L);
	// log.info("insert profession : profession= " + profession.toString());
	// // 需要从cookie中拿到createBy，updateBy，createAt,updateAt等参数，否则插入报错
	// Long professionId = professionService.insert(profession);
	//
	// // 如果职位标签不为空，则将标签插入profession_tags
	// if (!CollectionUtils.isEmpty(tags)) {
	// for (ProfessionTags professionTag : tags) {
	// professionTag.setId(null);
	// professionTag.setProfessionId(professionId);
	// professionTag.setCreateBy(120L);
	// professionTag.setUpdateBy(120L);
	// }
	// professionTagsService.insertList(tags);
	// }
	// //往公司表professionCount+1
	// model.addAttribute("code", 0);
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("add profession error ");
	// model.addAttribute("code", -6002);
	// }
	//
	// try {
	//
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("add professionTags error ");
	// model.addAttribute("code", -6003);
	// }
	//
	// return "/data/json";
	// }
	//
	// @RequestMapping(value = "/a/u/profession/{id}", method =
	// RequestMethod.DELETE)
	// public String deleteProfessionJson(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model, @PathVariable Long id)
	// throws Exception {
	//
	// log.info("delete profession : id= " + id);
	// try {
	// professionService.delete(id);
	//
	// log.info("add profession success");
	// model.addAttribute("code", 0);
	//
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("delete profession error,id is  " + id);
	// model.addAttribute("code", -6004);
	//
	// }
	//
	// try {
	// List<Long> professionTagIds = professionTagsService
	// .getProfessionTagsIdsByProfessionId(id, 0, 10);
	// professionTagsService.deleteList(ProfessionTags.class,
	// professionTagIds);
	// model.addAttribute("code", 0);
	//
	// } catch (Throwable t) {
	// t.printStackTrace();
	// log.error(t.getMessage());
	// log.error("delete professionTags error");
	// model.addAttribute("code", -6004);
	//
	// }
	//
	// return "/data/json";
	// }

	// @RequestMapping(value = "/a/u/profession/status/{id}", method =
	// RequestMethod.PUT)
	// public String updateProfessionStatusJson(HttpServletRequest request,
	// HttpServletResponse response, ModelMap model,
	// @PathVariable Long id, Long status) throws Exception {
	//
	// if(status==null||status<0||status>1){
	// model.addAttribute("code",-9013);
	// return "/data/json";
	// }
	//
	// try {
	// Profession profession = professionService.getObjectById(id);
	//
	// log.info("get prfession data is" + profession.toString());
	//
	// profession.setStatus(status);
	//
	// professionService.update(profession);
	//
	// log.info("update profession success");
	//
	// model.addAttribute("code", 0);
	//
	// } catch (Throwable t) {
	// log.error(t.getMessage());
	// log.error("get/update profession error, professionId is  " + id);
	// model.addAttribute("code", -100000);
	// }
	//
	//
	// return "/data/json";
	// }
}
