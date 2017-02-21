package com.ptteng.carrots.home.controller;

import java.util.HashMap;
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

import com.gemantic.common.exception.ServiceDaoException;
import com.gemantic.common.exception.ServiceException;
import com.ptteng.carrots.home.model.Company;
import com.ptteng.carrots.home.model.CompanyTags;
import com.ptteng.carrots.home.model.Profession;
import com.ptteng.carrots.home.model.ProfessionTags;
import com.ptteng.carrots.home.service.CompanyService;
import com.ptteng.carrots.home.service.CompanyTagsService;
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
	private com.qding.common.util.http.cookie.CookieUtil cookieUtil;

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

		// 王智猛说不需要公司logo
		// try {
		// company = companyService.getObjectById(profession.getCompanyId());
		// model.addAttribute("company", company);
		// } catch (Throwable t) {
		// log.error(t.getMessage());
		// log.error("get company error,id is  " + profession.getCompanyId());
		// model.addAttribute("code", -100000);
		// }

		try {
			List<Long> tagIds = professionTagsService
					.getProfessionTagsIdsByProfessionId(id, 0, 10);
			tags = professionTagsService.getObjectsByIds(tagIds);
			for (ProfessionTags professionTag : tags) {
				log.info("get professionTag data is " + professionTag);
			}
			model.addAttribute("tags", tags);
			model.addAttribute("code", 0);
		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get professionTagsList error");
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/profession/json/professionDetailJson";
	}

	@RequestMapping(value = "/a/u/profession/{id}", method = RequestMethod.PUT)
	public String updateProfessionJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@PathVariable Long id,
			@RequestBody ProfessionAndTags professionAndTags) throws Exception {

		log.info(" professionAndTag : professionAndTag= " + professionAndTags);
		Profession profession = professionAndTags.getProfession();
		Profession professionOld = null;
		// profession.setId(id);
		List<ProfessionTags> tags = professionAndTags.getTags();
		Company company = new Company();

		// log.info("update profession : profession= " + profession.toString());

		// 入参校验
		if (null == profession.getName()) {
			model.addAttribute("code", -9002);
			return "data/json";
		}
		if (profession.getName().length() > 255) {
			model.addAttribute("code", -9003);
			return "data/json";
		}
		if (null == profession.getCategory()
				|| !(profession.getCategory() instanceof Long)) {
			model.addAttribute("code", -9004);
			return "data/json";
		}
		if (null == profession.getSubCategory()
				|| !(profession.getSubCategory() instanceof Long)) {
			model.addAttribute("code", -9005);
			return "data/json";
		}
		if (null == profession.getEducation()
				|| !(profession.getEducation() instanceof Long)
				|| profession.getEducation() < 0
				|| profession.getEducation() > 3) {
			model.addAttribute("code", -9006);
			return "data/json";
		}
		if (null == profession.getExperience()
				|| !(profession.getExperience() instanceof Long)
				|| profession.getExperience() < 0
				|| profession.getExperience() > 4) {
			model.addAttribute("code", -9007);
			return "data/json";
		}
		if (null == profession.getRecommend()
				|| !(profession.getRecommend() instanceof Long)
				|| profession.getRecommend() < 0
				|| profession.getRecommend() > 1) {
			model.addAttribute("code", -9008);
			return "data/json";
		}
		if (null == profession.getResponsibility()) {
			model.addAttribute("code", -9009);
			return "data/json";
		}
		if (null == profession.getRequisite()) {
			model.addAttribute("code", -9010);
			return "data/json";
		}
		if (null == profession.getBoon()) {
			model.addAttribute("code", -9011);
			return "data/json";
		}
		if (!CollectionUtils.isEmpty(tags)) {
			for (ProfessionTags professionTag : tags) {
				if (null == professionTag) {
					model.addAttribute("code", -9012);
					return "data/json";
				}
			}
		}

		// 获取职位的creatBy，createAt
		try {
			professionOld = professionService.getObjectById(id);
			profession.setCreateBy(professionOld.getCreateBy());
			profession.setCreateAt(professionOld.getCreateAt());
		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("get professionOld error,id is  " + id);
			model.addAttribute("code", -6003);
		}

		try {
			log.info("update profession : profession= " + profession);
			company = companyService.getObjectById(profession.getCompanyId());
			log.info("update profession : profession= " + profession);
			// 获取公司省市县
			profession.setId(id);
			profession.setProvince(company.getProvince());// 从company获取
			profession.setCity(company.getCity());
			profession.setCounty(company.getCounty());

			// 验证公司冻结状态，如果公司冻结，新增职位为下架状态
			// 公司freezed 0-正常 1-冻结
			// 职位status  0-下架 1-上架
			if (company.getFreezed() == 1L) {
				profession.setStatus(0L);
			} else if (company.getFreezed() == 0L) {
				//设置原职位状态
				profession.setStatus(professionOld.getStatus());
			}
			log.info(" profession date is: profession =========" + profession);
			Long uid = Long.valueOf(cookieUtil.getKeyIdentity(request,
					com.qding.common.util.http.cookie.CookieUtil.USER_ID));

			profession.setUpdateBy(uid);
			log.info(" profession date is: profession =========" + profession);
			professionService.update(profession);

			// 根据professionId查询得到tagIds的list
			List<Long> tagIds = professionTagsService
					.getProfessionTagsIdsByProfessionId(id, 0, 10);
			// 如果tagIds不为空，则全部删除
			if (!CollectionUtils.isEmpty(tagIds))
				professionTagsService.deleteList(ProfessionTags.class, tagIds);

			// 如果传入职位标签不为空，则将标签插入profession_tags
			if (!CollectionUtils.isEmpty(tags)) {

				for (ProfessionTags professionTag : tags) {
					professionTag.setId(null);
					professionTag.setCompanyId(company.getId());
					professionTag.setProfessionId(id);
					professionTag.setCreateBy(uid);
					professionTag.setUpdateBy(uid);
				}
				professionTagsService.insertList(tags);
			}
			model.addAttribute("code", 0);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("update profession error,id is  " + profession.getId());
			model.addAttribute("code", -6003);

		}

		try {
			log.info(" company date is: company ============" + company);
			// 更新公司职位更新时间 professionUpdateAt
			company.setProfessionUpdateAt(System.currentTimeMillis());
			// 在不更新公司的updateAt的前提下,更新公司的professionUpdateAt和professionNum
			companyService.updateWithoutChangeUpdateAt(company);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("update company professionNum error");
			model.addAttribute("code", -6004);

		}

		return "/data/json";
	}

	@RequestMapping(value = "/a/u/profession", method = RequestMethod.POST)
	public String addProfessionJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestBody ProfessionAndTags professionAndTags) throws Exception {

			log.info("insert professionAndTag : professionAndTag= "
					+ professionAndTags.toString());
			Profession profession = professionAndTags.getProfession();
			List<ProfessionTags> tags = professionAndTags.getTags();
			log.info("insert profession : profession= " + profession.toString());
			log.info("insert profession : professionTags= " + tags.toString());

			// 入参校验
			if (null == profession.getCompanyId()) {
				model.addAttribute("code", -9014);
				return "data/json";
			}

			if (null == profession.getCompanyName()) {
				model.addAttribute("code", -9015);
				return "data/json";
			}

			if (null == profession.getName()) {
			model.addAttribute("code", -9002);
			return "data/json";
		}
		if (profession.getName().length() > 255) {
			model.addAttribute("code", -9003);
			return "data/json";
		}
		if (null == profession.getCategory()
				|| !(profession.getCategory() instanceof Long)) {
			model.addAttribute("code", -9004);
			return "data/json";
		}
		if (null == profession.getSubCategory()
				|| !(profession.getSubCategory() instanceof Long)) {
			model.addAttribute("code", -9005);
			return "data/json";
		}
		if (null == profession.getEducation()
				|| !(profession.getEducation() instanceof Long)
				|| profession.getEducation() < 0
				|| profession.getEducation() > 3) {
			model.addAttribute("code", -9006);
			return "data/json";
		}
		if (null == profession.getExperience()
				|| !(profession.getExperience() instanceof Long)
				|| profession.getExperience() < 0
				|| profession.getExperience() > 4) {
			model.addAttribute("code", -9007);
			return "data/json";
		}
		if (null == profession.getRecommend()
				|| !(profession.getRecommend() instanceof Long)
				|| profession.getRecommend() < 0
				|| profession.getRecommend() > 1) {
			model.addAttribute("code", -9008);
			return "data/json";
		}
		if (null == profession.getResponsibility()) {
			model.addAttribute("code", -9009);
			return "data/json";
		}
		if (null == profession.getRequisite()) {
			model.addAttribute("code", -9010);
			return "data/json";
		}
		if (null == profession.getBoon()) {
			model.addAttribute("code", -9011);
			return "data/json";
		}
		if (!CollectionUtils.isEmpty(tags)) {
			for (ProfessionTags professionTag : tags) {
				if (null == professionTag) {
					model.addAttribute("code", -9012);
					return "data/json";
				}
			}
		}

		try {
			// professionTag id设置为null
			// profession.setId(null);
			log.info("start to get company data, companyId============== "
					+ profession.getCompanyId());
			Company company = companyService.getObjectById(profession
					.getCompanyId());
			log.info(" get company data, company date ============== "
					+ company);
			// 从company获取省市县信息
			profession.setProvince(company.getProvince());
			profession.setCity(company.getCity());
			profession.setCounty(company.getCounty());
			log.info("get company data is " + company.getProvince());

			// 验证公司冻结字段是否在0和1之间
			if (company.getFreezed() != 0L && company.getFreezed() != 1L) {
				model.addAttribute("code", -6508);
				return "/data/json";
			}
			// 验证公司冻结状态，如果公司冻结，新增职位为下架状态
			// 公司freezed 0-正常 1-冻结
			// 职位status  0-下架 1-上架
			if (company.getFreezed() == 0L) {
				profession.setStatus(1L);
			} else if (company.getFreezed() == 1L) {
				profession.setStatus(0L);
			}
			log.info("update profession status : profession status ============== "
					+ profession.getStatus());
			// 从cookie获取createBy和updateBy
			Long uid = Long.valueOf(cookieUtil.getKeyIdentity(request,
					com.qding.common.util.http.cookie.CookieUtil.USER_ID));
			profession.setCreateBy(uid);
			profession.setUpdateBy(uid);
			log.info("insert profession : profession= " + profession);
			// 需要从cookie中拿到createBy，updateBy等参数，否则插入报错
			Long professionId = professionService.insert(profession);
			model.addAttribute("professionId", professionId);
			try {
				// 如果职位标签不为空，则将标签插入profession_tags
				if (!CollectionUtils.isEmpty(tags)) {
					for (ProfessionTags professionTag : tags) {
						professionTag.setId(null);
						professionTag.setCompanyId(profession.getCompanyId());
						professionTag.setProfessionId(professionId);
						professionTag.setCreateBy(uid);
						professionTag.setUpdateBy(uid);
					}
					professionTagsService.insertList(tags);
				}
			} catch (Throwable t) {
				t.printStackTrace();
				log.error(t.getMessage());
				log.error("insert professionTags data error ");
			}

			// 获取最新的职位数
			try {
				Long companyId = company.getId();
				Integer professionCount = professionService.countProfessionIdsByCompanyId(companyId);

				company.setProfessionNum(professionCount.longValue());
				log.info(companyId + " has ProfessionNum:" + professionCount);

				// 公司职位更新时间 更新
				company.setProfessionUpdateAt(System.currentTimeMillis());

				// 在不更新公司的updateAt的前提下,更新公司的professionUpdateAt和professionNum
				companyService.updateWithoutChangeUpdateAt(company);
				log.info("update company data : company= " + company);
			} catch (Throwable t) {
				t.printStackTrace();
				log.error(t.getMessage());
				log.error("update company data error ");
			}
			model.addAttribute("code", 0);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("add profession error ");
			model.addAttribute("code", -6002);
		}

		return "/data/professionIdJson";
	}

	@RequestMapping(value = "/a/u/profession/{id}", method = RequestMethod.DELETE)
	public String deleteProfessionJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		Profession profession = null;

		try {
			profession = professionService.getObjectById(id);
			log.info("get profession date, profession is " + profession);

			log.info("delete profession : id= " + id);
			professionService.delete(id);

			log.info("add profession success");

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete profession error,id is  " + id);
			model.addAttribute("code", -6004);

		}

		try {
			List<Long> professionTagIds = professionTagsService
					.getProfessionTagsIdsByProfessionId(id, 0, 10);
			professionTagsService.deleteList(ProfessionTags.class,
					professionTagIds);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete professionTags error");
			model.addAttribute("code", -6004);

		}

		try {
			// 获取公司对象
			Long companyId = profession.getCompanyId();
			Company company = companyService.getObjectById(companyId);

			// 获取最新的职位数
			Integer professionCount = professionService.countProfessionIdsByCompanyId(companyId);
			company.setProfessionNum(professionCount.longValue());
			log.info(companyId + " has ProfessionNum:" + professionCount);

			// 公司职位更新时间 更新
			company.setProfessionUpdateAt(System.currentTimeMillis());
			// 在不更新公司的updateAt的前提下,更新公司的professionUpdateAt和professionNum
			companyService.updateWithoutChangeUpdateAt(company);

		} catch (Throwable t) {
			t.printStackTrace();
			log.error(t.getMessage());
			log.error("update company professionNum error");
			model.addAttribute("code", -6004);

		}

		model.addAttribute("code", 0);

		return "/data/json";
	}

	@RequestMapping(value = "/a/profession/search", method = RequestMethod.GET)
	public String getMultiProfessionJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Long companyId,
			String companyName, String name, String province, String city,
			String county, String category, String subCategory,
			String industry, String experience, String education,
			String compensation, Long updateAt, Long recommend,
			Long returnTags, Long startAt, Long endAt, Long status, Integer page,
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
		Company company = null;
		try {
			// 组装动态sql
			Map<String, Object> param = DynamicUtil.getProfessionList(
					companyId, companyName, name, province, city, county,
					industry, category, subCategory, experience, education,
					compensation, updateAt, recommend, startAt, endAt, status);
			log.info("get  param data is " + param);
			// 通过动态sql查询获得职位id列表
			ids = professionService.getIdsByDynamicCondition(Profession.class,
					param, start, size);

			count = professionService.getIdsByDynamicCondition(
					Profession.class, param, 0, Integer.MAX_VALUE);

			List<Profession> professionList = professionService
					.getObjectsByIds(ids);

			// 循环获取公司名字，给profession表加companyName后可以删除
			for (Profession profession : professionList) {
				try {

					company = companyService.getObjectById(profession
							.getCompanyId());

					profession.setCompanyName(company.getName());

				} catch (Throwable t) {
					log.error(t.getMessage());
					log.error("get companyName error,companyId is  "
							+ profession.getCompanyId());
					// model.addAttribute("code", -100000);
				}

			}

			log.info("get  profession data is " + professionList);

			model.addAttribute("code", 0);
			model.addAttribute("page", page);
			model.addAttribute("size", size);
			model.addAttribute("total", count.size());
			model.addAttribute("professionList", professionList);

		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get profession error,id is  " + ids);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/profession/json/professionListJson";
	}

	@RequestMapping(value = "/a/u/profession/status", method = RequestMethod.PUT)
	public String updateProfessionStatusJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Long id, Long status)
			throws Exception {

		if (status == null || status < 0 || status > 1) {
			model.addAttribute("code", -9013);
			return "/data/json";
		}

		try {
			Profession profession = professionService.getObjectById(id);
			log.info("get prfession data is" + profession.toString());

			// 上架前判断公司是否冻结，若冻结返回freezed异常
			if (status == 1L) {
				try {
					Company company = companyService.getObjectById(profession
							.getCompanyId());
					if (company.getFreezed() == 1L) {
						model.addAttribute("code", -6511);
						return "/data/json";
					}
				} catch (Throwable t) {
					log.error(t.getMessage());
					log.error("get company error, company is  "
							+ profession.getCompanyId());
				}
			}

			profession.setStatus(status);

			professionService.update(profession);

			log.info("update profession success");

			model.addAttribute("code", 0);

		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get/update profession error, professionId is  " + id);
			model.addAttribute("code", -100000);
		}

		return "/data/json";
	}

}
