package com.ptteng.carrots.home.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import util.CookieConstant;

import com.ptteng.carrots.home.model.Manager;
import com.ptteng.carrots.home.model.Role;
import com.ptteng.carrots.home.service.ManagerService;
import com.ptteng.carrots.home.service.RoleService;
import com.qding.common.util.http.cookie.CookieUtil;
import com.qding.common.util.json.GsonUtil;
import com.qding.common.util.pwd.PasswordUtils;


@Controller
public class SystemController {
	private static final Log log = LogFactory.getLog(SystemController.class);

	@Autowired
	private ManagerService managerService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CookieUtil cookieUtil;

	/**
	 * 游戏准备
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login/index")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer page, Integer size) throws Exception {
		log.info(" welcome to manager index ");

		return "/index";
	}
	
	/**
	 * 游戏准备
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/test")
	public String test(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer page, Integer size) throws Exception {
		log.info(" welcome to manager index ");
		return "/test";
	}

	
	@RequestMapping(value = "/nologin")
	public String noLogin(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Integer page,
			Integer size) throws Exception {
		log.info(" login wrong ");
		model.addAttribute("code", -5018);
		return "/data/json";
	}

	@RequestMapping(value = "/noPermissin")
	public String noPermission(HttpServletRequest request,
						  HttpServletResponse response, ModelMap model, Integer page,
						  Integer size) throws Exception {
		log.info(" login noPermission ");
		model.addAttribute("code",-5020);
		return "/data/json";
	}


//	@RequestMapping(value = "/web/a/publics/del/{id}", method = RequestMethod.DELETE)
//	public String delete(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model, @PathVariable long id) {
//		log.info(" welcome to publics delete ");
//		if (id == 0) {
//			model.addAttribute("success", false);
//			model.addAttribute("message", "未选择公众号");
//			model.addAttribute("result", null);
//			return "/data/json";
//		}
//		try {
//			boolean success = this.publicsService.delete(id);
//			if (success) {
//				model.addAttribute("success", true);
//				model.addAttribute("message", "删除成功");
//				model.addAttribute("result", null);
//			} else {
//				model.addAttribute("success", false);
//				model.addAttribute("message", "删除失败");
//				model.addAttribute("result", null);
//			}
//		} catch (Throwable t) {
//			log.error(t.getMessage());
//			log.error("delete publics list error,id is  " + id);
//			model.addAttribute("success", false);
//			model.addAttribute("message", "删除失败");
//			model.addAttribute("result", null);
//		}
//
//		return "/data/json";
//	}
//

	/**
	 * 管理员登录
	 * @param request
	 * @param response
	 * @param model
	 * @param name
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/a/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model, String name, String pwd) {
		log.info(" welcome to manager login ");
		
		try {
			List<Long> managerIds = this.managerService.getManagerIdsByName(name, 0, Integer.MAX_VALUE);
			log.info(name+" get managerIDs  "+managerIds);
			if (managerIds==null || managerIds.isEmpty() || managerIds.size()>1) {
				log.info(name +" not get any user ");
				model.addAttribute("code", -5003);

			} else {
				log.info(name +"  get user "+ managerIds.get(0));
				Manager manager = this.managerService.getObjectById(managerIds.get(0));
				if (PasswordUtils.authenticatePassword(manager.getPwd(), pwd) && manager.getStatus().equals(Manager.STATUS_USING)) {
					log.info(managerIds.get(0)+" login ");
					Map<String,String> maps=new HashMap();
					maps.put(CookieConstant.Cookie_WEB_ManagerName, manager.getName());
					
					
					//maps.put(CookieConstant.Cookie_WEB_PuserPublicsHash, manager.getPublicsHash());
					//maps.put(CookieConstant.Cookie_WEB_PuserPublicsHash, "-2042484612");
					
					maps.put(CookieUtil.USER_ID, manager.getId() + "");
					cookieUtil.setIdentity(request, response, maps, manager.getId());

				    Role role = 	roleService.getObjectById(manager.getRoleID());
				    if(role==null){
				    	log.error("not get any role  " + manager.getRoleID());
						model.addAttribute("code", -7001);
						return "/data/json";
				    	
				    }
					model.addAttribute("code", 0);
                    model.addAttribute("manager", manager);
					model.addAttribute("role",role);
					log.info(managerIds.get(0)+" login over ");
				} else {
					log.info(managerIds.get(0)+" has wrong pwd ");
					model.addAttribute("code", -5004);
					model.addAttribute("result", null);
				}
			}
			
		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("manager login error,name is  " + name);
			model.addAttribute("code", -5002);
			model.addAttribute("result", null);
		}

		return "/data/login";
	}

	/**
	 * 退出系统
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/a/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.info(" welcome to manager logout ");
		
		try {
			cookieUtil.clearCookie(response);
			
			model.addAttribute("code", 0);
			model.addAttribute("result", "/login/index");
			
		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("manager logout error  ");
			model.addAttribute("code", -5005);
			model.addAttribute("result", null);
		}

		return "/data/json";
	}

	/**
	 *返回登陆用户信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/a/u/manager", method = RequestMethod.GET)
	public String getUser(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		log.info(" welcome to manager getmanager ");
		
		try {
			if (null != cookieUtil.getKeyIdentity(request, CookieUtil.USER_ID)) {
				String userId = cookieUtil.getKeyIdentity(request, CookieUtil.USER_ID);
				String userName = cookieUtil.getKeyIdentity(request, CookieConstant.Cookie_WEB_ManagerName);
				String publicsHash = cookieUtil.getKeyIdentity(request, CookieConstant.Cookie_WEB_ManagerPublicsHash);
				
				Map<String,String> maps=new HashMap();
				maps.put(CookieConstant.Cookie_WEB_ManagerName, userName);
				maps.put(CookieConstant.Cookie_WEB_ManagerPublicsHash, publicsHash);
				maps.put(CookieUtil.USER_ID, userId);
				Manager manager = managerService.getObjectById(Long.valueOf(userId));
				
				model.addAttribute("code", 0);
				model.addAttribute("result", GsonUtil.toJson(manager));
			} else {
				model.addAttribute("code", -5001);
				model.addAttribute("result", null);
			}
		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("manager logout error  ");
			model.addAttribute("code", -5001);
			model.addAttribute("result", null);
		}

		return "/data/json";
	}
//
//	@RequestMapping(value = "/web/a/publics/add", method = RequestMethod.POST)
//	public String update(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model, String name,
//			String appID, String appSecret, String account, String pwd) {
//		log.info(" welcome to publics add ");
//		log.info("name=" + name + "   account=" + account + "    appID="
//				+ appID + "    appSecret=" + appSecret + "    pwd=" + pwd);
//		try {
//			Publics publics = new Publics();
//			publics.setName(name);
//			publics.setAccount(account);
//			publics.setAppID(appID);
//			publics.setAppSecret(appSecret);
//			publics.setPwd(pwd);
//			publics.setToken(DataUtils.getSimpleUUID());
//			publics.setHash(DataUtils.hash(appID + appSecret));
//			log.info("to add publics = " + publics.toString());
//			long id = this.publicsService.insert(publics);
//			log.info("id==="+id);
//			if (id > 0) {
//				model.addAttribute("success", true);
//				model.addAttribute("message", "新增成功");
//				model.addAttribute("result", null);
//			} else {
//				model.addAttribute("success", false);
//				model.addAttribute("message", "新增失败");
//				model.addAttribute("result", null);
//			}
//		} catch (Throwable t) {
//			log.error(t.getMessage());
//			log.error("add publics list error");
//			model.addAttribute("success", false);
//			model.addAttribute("message", "新增失败");
//			model.addAttribute("result", null);
//		}
//
//		return "/data/json";
//	}
}
