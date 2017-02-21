package com.ptteng.carrots.home.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.ptteng.carrots.home.model.CompanyIndustry;
import com.ptteng.carrots.home.service.CompanyIndustryService;

/**
 * CompanyIndustry  crud
 * 
 * @author magenm
 * @Date 2014-4-16 13:43
 * 
 */
@Controller
public class CompanyIndustryController {
	private static final Log log = LogFactory.getLog(CompanyIndustryController.class);

	@Autowired
	private CompanyIndustryService companyIndustryService;






    /**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	@RequestMapping(value = "/c/companyIndustry", method = RequestMethod.GET)
	public String getcompanyIndustryList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		
		
		log.info("/companyIndustry  to /companyIndustry/view/companyIndustryList");

		return "/carrots-home-service/companyIndustry/view/companyIndustryList";
	}
    
    

    
	
	@RequestMapping(value = "/c/companyIndustry/{id}", method = RequestMethod.GET)
	public String getCompanyIndustry(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("/companyIndustry/" + id + "  to /companyIndustry/view/companyIndustryDeail");
		if(null != id){
			model.addAttribute("id", id);
		}else{
			model.addAttribute("id", 0);
		}

		return "/carrots-home-service/companyIndustry/view/companyIndustryDetail";
	}
	
	
	
	    
	

	@RequestMapping(value = "/a/companyIndustry/{id}", method = RequestMethod.GET)
	public String getCompanyIndustryJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("get data : id= " + id);
		try {
			CompanyIndustry companyIndustry = companyIndustryService.getObjectById(id);
			log.info("get companyIndustry data is " + companyIndustry);

			model.addAttribute("code", 0);

			model.addAttribute("companyIndustry", companyIndustry);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("get companyIndustry error,id is  " + id);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/companyIndustry/json/companyIndustryDetailJson";
	}

	@RequestMapping(value = "/a/companyIndustry/{id}", method = RequestMethod.PUT)
	public String updateCompanyIndustryJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, CompanyIndustry companyIndustry) throws Exception {
		
		log.info("update companyIndustry : companyIndustry= " + companyIndustry);
		
		try {
			
			companyIndustryService.update(companyIndustry);

			model.addAttribute("code", 0);

			model.addAttribute("companyIndustry", companyIndustry);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("update companyIndustry error,id is  " + companyIndustry.getId());
			model.addAttribute("code", -6003);

		}

		return "/data/json";
	}

	@RequestMapping(value = "/a/companyIndustry", method = RequestMethod.POST)
	public String addCompanyIndustryJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, CompanyIndustry companyIndustry) throws Exception {
		
		log.info("update companyIndustry : companyIndustry= " + companyIndustry);
		
		try { 
			companyIndustry.setId(null);

			companyIndustryService.insert(companyIndustry);

			model.addAttribute("code", 0);
		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("add companyIndustry error ");
			model.addAttribute("code", -6002);
		}

		return "/data/json";
	}

	@RequestMapping(value = "/a/companyIndustry/{id}", method = RequestMethod.DELETE)
	public String deleteCompanyIndustryJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("delete companyIndustry : id= " + id);
		try {
			companyIndustryService.delete(id);

			log.info("add companyIndustry success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete companyIndustry error,id is  " + id);
			model.addAttribute("code", -6004);

		}

		return "/data/json";
	}
	
	
	@RequestMapping(value = "/a/multi/companyIndustry", method = RequestMethod.GET)
	public String getMultiCompanyIndustryJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Long[] ids)
			throws Exception {
			
		List<Long> idList = new ArrayList();	
	   if (ids == null) {

		} else {
			idList = Arrays.asList(ids);
		}
		try {

			

			List<CompanyIndustry> companyIndustryList = companyIndustryService.getObjectsByIds(idList);
			log.info("get  companyIndustry data is " + companyIndustryList);

			model.addAttribute("code", 0);			
			model.addAttribute("total",companyIndustryList.size());

			model.addAttribute("companyIndustryList", companyIndustryList);

		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get companyIndustry error,id is  " + idList);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/companyIndustry/json/companyIndustryListJson";
	}
	
	
	
	
	
}

