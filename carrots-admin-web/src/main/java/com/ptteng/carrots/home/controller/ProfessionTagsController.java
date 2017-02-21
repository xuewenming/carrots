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

import com.ptteng.carrots.home.model.ProfessionTags;
import com.ptteng.carrots.home.service.ProfessionTagsService;

/**
 * ProfessionTags  crud
 * 
 * @author magenm
 * @Date 2014-4-16 13:43
 * 
 */
@Controller
public class ProfessionTagsController {
	private static final Log log = LogFactory.getLog(ProfessionTagsController.class);

	@Autowired
	private ProfessionTagsService professionTagsService;






    /**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	@RequestMapping(value = "/c/professionTags", method = RequestMethod.GET)
	public String getprofessionTagsList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		
		
		log.info("/professionTags  to /professionTags/view/professionTagsList");

		return "/carrots-home-service/professionTags/view/professionTagsList";
	}
    
    

    
	
	@RequestMapping(value = "/c/professionTags/{id}", method = RequestMethod.GET)
	public String getProfessionTags(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("/professionTags/" + id + "  to /professionTags/view/professionTagsDeail");
		if(null != id){
			model.addAttribute("id", id);
		}else{
			model.addAttribute("id", 0);
		}

		return "/carrots-home-service/professionTags/view/professionTagsDetail";
	}
	
	
	
	    
	

	@RequestMapping(value = "/a/professionTags/{id}", method = RequestMethod.GET)
	public String getProfessionTagsJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("get data : id= " + id);
		try {
			ProfessionTags professionTags = professionTagsService.getObjectById(id);
			log.info("get professionTags data is " + professionTags);

			model.addAttribute("code", 0);

			model.addAttribute("professionTags", professionTags);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("get professionTags error,id is  " + id);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/professionTags/json/professionTagsDetailJson";
	}

	@RequestMapping(value = "/a/professionTags/{id}", method = RequestMethod.PUT)
	public String updateProfessionTagsJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, ProfessionTags professionTags) throws Exception {
		
		log.info("update professionTags : professionTags= " + professionTags);
		
		try {
			
			professionTagsService.update(professionTags);

			model.addAttribute("code", 0);

			model.addAttribute("professionTags", professionTags);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("update professionTags error,id is  " + professionTags.getId());
			model.addAttribute("code", -6003);

		}

		return "/data/json";
	}

	@RequestMapping(value = "/a/professionTags", method = RequestMethod.POST)
	public String addProfessionTagsJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, ProfessionTags professionTags) throws Exception {
		
		log.info("update professionTags : professionTags= " + professionTags);
		
		try { 
			professionTags.setId(null);

			professionTagsService.insert(professionTags);

			model.addAttribute("code", 0);
		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("add professionTags error ");
			model.addAttribute("code", -6002);
		}

		return "/data/json";
	}

	@RequestMapping(value = "/a/professionTags/{id}", method = RequestMethod.DELETE)
	public String deleteProfessionTagsJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("delete professionTags : id= " + id);
		try {
			professionTagsService.delete(id);

			log.info("add professionTags success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete professionTags error,id is  " + id);
			model.addAttribute("code", -6004);

		}

		return "/data/json";
	}
	
	
	@RequestMapping(value = "/a/multi/professionTags", method = RequestMethod.GET)
	public String getMultiProfessionTagsJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Long[] ids)
			throws Exception {
			
		List<Long> idList = new ArrayList();	
	   if (ids == null) {

		} else {
			idList = Arrays.asList(ids);
		}
		try {

			

			List<ProfessionTags> professionTagsList = professionTagsService.getObjectsByIds(idList);
			log.info("get  professionTags data is " + professionTagsList);

			model.addAttribute("code", 0);			
			model.addAttribute("total",professionTagsList.size());

			model.addAttribute("professionTagsList", professionTagsList);

		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get professionTags error,id is  " + idList);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/professionTags/json/professionTagsListJson";
	}
	
	
	
	
	
}

