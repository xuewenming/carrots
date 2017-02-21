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

import com.ptteng.carrots.home.model.CompanyTags;
import com.ptteng.carrots.home.service.CompanyTagsService;

/**
 * CompanyTags  crud
 * 
 * @author magenm
 * @Date 2014-4-16 13:43
 * 
 */
@Controller
public class CompanyTagsController {
	private static final Log log = LogFactory.getLog(CompanyTagsController.class);

	@Autowired
	private CompanyTagsService companyTagsService;






    /**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
//	 */
//
//	@RequestMapping(value = "/c/companyTags", method = RequestMethod.GET)
//	public String getcompanyTagsList(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model) throws Exception {
//
//		
//		
//		log.info("/companyTags  to /companyTags/view/companyTagsList");
//
//		return "/carrots-home-service/companyTags/view/companyTagsList";
//	}
//    
    

//    
//	
//	@RequestMapping(value = "/c/companyTags/{id}", method = RequestMethod.GET)
//	public String getCompanyTags(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model, @PathVariable Long id)
//			throws Exception {
//
//		log.info("/companyTags/" + id + "  to /companyTags/view/companyTagsDeail");
//		if(null != id){
//			model.addAttribute("id", id);
//		}else{
//			model.addAttribute("id", 0);
//		}
//
//		return "/carrots-home-service/companyTags/view/companyTagsDetail";
//	}
//	
	
	
	    
	

//	@RequestMapping(value = "/a/tags/{id}", method = RequestMethod.GET)
//	public String getCompanyTagsJson(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model, @PathVariable Long id)
//			throws Exception {
//
//		log.info("get data : companyId= " + id);
//		try {
//			
//			List<Long> companyTagsIdList = companyTagsService.getCompanyTagsIdsByCompanyId(id, 0, 10);
//			List<CompanyTags> companyTagsList=companyTagsService.getObjectsByIds(companyTagsIdList);
//			
//			log.info("get companyTagsList data is " + companyTagsList);
//
//			model.addAttribute("code", 0);
//			model.addAttribute("page", 1);
//			model.addAttribute("size", companyTagsList.size());
//			model.addAttribute("total", companyTagsList.size());
//			model.addAttribute("companyTagsList", companyTagsList);
//
//		} catch (Throwable t) {
//		    t.printStackTrace();
//			log.error(t.getMessage());
//			log.error("get companyTagsList error,id is  " + id);
//			model.addAttribute("code", -100000);
//		}
//
//		return "/carrots-home-service/companyTags/json/companyTagsListJson";
//	}

//	@RequestMapping(value = "/a/companyTags/{id}", method = RequestMethod.PUT)
//	public String updateCompanyTagsJson(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model, CompanyTags companyTags) throws Exception {
//		
//		log.info("update companyTags : companyTags= " + companyTags);
//		
//		try {
//			
//			companyTagsService.update(companyTags);
//
//			model.addAttribute("code", 0);
//
//			model.addAttribute("companyTags", companyTags);
//
//		} catch (Throwable t) {
//		    t.printStackTrace();
//			log.error(t.getMessage());
//			log.error("update companyTags error,id is  " + companyTags.getId());
//			model.addAttribute("code", -6003);
//
//		}
//
//		return "/data/json";
//	}

//	@RequestMapping(value = "/a/companyTags", method = RequestMethod.POST)
//	public String addCompanyTagsJson(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model, CompanyTags companyTags) throws Exception {
//		
//		log.info("update companyTags : companyTags= " + companyTags);
//		
//		try { 
//			companyTags.setId(null);
//
//			companyTagsService.insert(companyTags);
//
//			model.addAttribute("code", 0);
//		} catch (Throwable t) {
//		    t.printStackTrace();
//			log.error(t.getMessage());
//			log.error("add companyTags error ");
//			model.addAttribute("code", -6002);
//		}
//
//		return "/data/json";
//	}

//	@RequestMapping(value = "/a/companyTags/{id}", method = RequestMethod.DELETE)
//	public String deleteCompanyTagsJson(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model, @PathVariable Long id)
//			throws Exception {
//
//		log.info("delete companyTags : id= " + id);
//		try {
//			companyTagsService.delete(id);
//
//			log.info("add companyTags success");
//			model.addAttribute("code", 0);
//
//		} catch (Throwable t) {
//		    t.printStackTrace();
//			log.error(t.getMessage());
//			log.error("delete companyTags error,id is  " + id);
//			model.addAttribute("code", -6004);
//
//		}
//
//		return "/data/json";
//	}
	
	
//	@RequestMapping(value = "/a/multi/companyTags", method = RequestMethod.GET)
//	public String getMultiCompanyTagsJson(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model, Long[] ids)
//			throws Exception {
//			
//		List<Long> idList = new ArrayList();	
//	   if (ids == null) {
//
//		} else {
//			idList = Arrays.asList(ids);
//		}
//		try {
//
//			
//
//			List<CompanyTags> companyTagsList = companyTagsService.getObjectsByIds(idList);
//			log.info("get  companyTags data is " + companyTagsList);
//
//			model.addAttribute("code", 0);			
//			model.addAttribute("total",companyTagsList.size());
//
//			model.addAttribute("companyTagsList", companyTagsList);
//
//		} catch (Throwable t) {
//			log.error(t.getMessage());
//			log.error("get companyTags error,id is  " + idList);
//			model.addAttribute("code", -100000);
//		}
//
//		return "/carrots-home-service/companyTags/json/companyTagsListJson";
//	}
	
	
	
	
	
}

