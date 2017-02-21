package util;

import com.ptteng.carrots.home.model.Manager;
import com.ptteng.carrots.home.model.Module;
import com.ptteng.carrots.home.model.RoleModule;
import com.ptteng.carrots.home.service.ManagerService;
import com.ptteng.carrots.home.service.ModuleService;
import com.ptteng.carrots.home.service.RoleModuleService;
import com.ptteng.carrots.home.service.RoleService;
import com.qding.common.util.DataUtils;
import com.qding.common.util.http.cookie.CookieUtil;
import com.qding.common.util.http.cookie.UserCryptUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class UserInterceptor extends HandlerInterceptorAdapter {

    private static final Log log = LogFactory.getLog(UserInterceptor.class);
    private static final String excelName = "customer_statics_"+System.currentTimeMillis()+".csv";



    @Autowired
    private ManagerService managerService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private RoleModuleService roleModuleService;
    public static String SPLITTER_REGEX = "\\|";
    public static final String USER_ID = "userId";

    @Autowired
    private CookieUtil cookieUtil;

//    /**
//     * In this case intercept the request BEFORE it reaches the controller
//     */
//	@Override
//	public boolean preHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler) throws Exception {
//
//
//		Cookie[] cookies = request.getCookies();
//		log.info("get cookies  " + Arrays.asList(cookies));
//		Map<String, String> maps = cookieUtil.getIdentity(request);
//		log.info("get maps  from cookie is " + maps);
//		Long uid = cookieUtil.getID(request, response);
//		if (uid == null) {
//
//			log.error("not legal user request");
//			return wrongUser(response);
//
//		}
//		log.info("get uid  from cookie is " + uid);
//		User user = this.userService.getObjectById(uid);
//		if (user == null) {
//			log.error(uid + " not get any data ");
//			return wrongUser(response);
//		}else{
//			log.info("get usr "+user);
//			request.setAttribute("user", user);
//		}
//		log.info("get uid is " + uid);
//
//		return true;
//	}

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        log.info("intercepter preHandle start ...");

        String token = request.getParameter("token");
        if(null == token){
            token = request.getHeader("token");
        }
        log.info(" intercept token is    "+token);
//
//
//
//        String uid_cookie = cookieUtil.getKeyIdentity(request,
//                CookieUtil.USER_ID);
//
//        if (org.apache.commons.lang3.StringUtils.isBlank(uid_cookie)) {
//            log.info(" intercept uid is    "+uid_cookie);
//            response.sendRedirect("/index");
//            return false;
//        }
//
//
        //       Map map = decodeCookie(token);
        //      Long uid = null;
        //       Manager user = null;
        //      if(null!=map){
        //         uid=  (Long) map.get(USER_ID);
//       }
//
//        if(null != uid){
//            user = this.userService.getObjectById(uid);
//            request.setAttribute("user", user);
//            log.info(" intercept user is    " + user);
//        }
        Cookie[] cookies = request.getCookies();
        log.info("get cookies  " + Arrays.asList(cookies));
        Map<String, String> maps = cookieUtil.getIdentity(request);
        log.info("get maps  from cookie is " + maps);
        Long   uid =  cookieUtil.getID(request);


        log.info(uid);
        if (uid == null) {

            log.error("not legal user request");
            return noLogin(response,request);

        }
        log.info("get uid  from cookie is " + uid);
        Manager manager = this.managerService.getObjectById(uid);
        if (manager == null) {
            log.error(uid + " not get any data ");
            return noLogin(response,request);
        }else{
            log.info("get manager "+ manager);
            request.setAttribute("manager", manager);
        }
        log.info("get uid is " + uid);


        String requestUrl =        this.getInterceptorUrl(request);
        log.info(" requstUrl  " + requestUrl);

        List<Long> roleModuleIds =  roleModuleService.getRoleModuleIdsByRid(manager.getRoleID(), 0, Integer.MAX_VALUE);
        List<RoleModule> roleModules =    roleModuleService.getObjectsByIds(roleModuleIds);
        List<Long>  mids = new ArrayList<Long>();
        for(RoleModule roleModule : roleModules){
           mids.add(roleModule.getMid());
        }
        List<Module> modules = moduleService.getObjectsByIds(mids);
        boolean flag = false;
        log.info("  method  is  "+request.getMethod());
        String[] requestUrls =  requestUrl.split("\\?");
        log.info(" reqeust is "+requestUrls[0]);
        for(Module module : modules){
            if(DataUtils.isNotNullOrEmpty(module.getUrl())){
                //log.info(" module url is  "+module.getUrl());
                String[] moduleUrls =  module.getUrl().split("\\.");
               // log.info(" managerUrl  is  "+moduleUrls.length);
                if(request.getMethod().equals("GET")){
                    flag=true;
                }else if(requestUrls[0].contains("img")){
                    flag=true;
                } else {
                    try {
                        if (requestUrls[0].contains(moduleUrls.length == 1 ? "00" : moduleUrls[1])) {
                            log.info(uid + " is have module " + moduleUrls[1]);
                            flag = true;
                            break;
                        }
                    }
                    catch (Exception e){
                        log.info(e.getMessage());
                        flag = true;
                    }
                }
            }

        }
       if(flag==false){
           log.info(uid +" is don't have module "+requestUrl);
           return  noPermission(response,request);
       }
        log.info("flag is : "+flag);

        return  true;
    }

    public static Map getUserIdentity(String userName, Long userId) {
        Map map = new HashMap();
        map.put(CookieUtil.USER_NAME, userName);
        map.put(CookieUtil.USER_ID, userId);
        return map;
    }

    private  Map decodeCookie(String value) {
        if (StringUtils.isBlank(value))
            return null;
        try {
            String[] p = value.split(SPLITTER_REGEX);
            Long userId = new Long(p[0]);
            String crypt = p[1];
            String plainUserInfo = UserCryptUtil.userDecrypt(crypt);
            String userName = UserCryptUtil.getUserNameFromPlain(plainUserInfo);
            Long uid = UserCryptUtil.getUserIdFromPlain(plainUserInfo);
            if (userId.equals(uid)) {
                return getUserIdentity(userName, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Wrong cookie format: [" + value + "] message is:" + e.getMessage());
            return null;
        }

        return null;
    }

    private boolean noPermission(HttpServletResponse response,HttpServletRequest request)  {
        log.info("sssssssssssss");
        log.info("ddddddddd   " + request.getRequestURI());


       RequestDispatcher rd =  request.getRequestDispatcher("/r/json/noPermission.json");
       try {
		rd.forward(request,response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       // response.sendRedirect("/noPermissin");


        return false;

    }

    private boolean noLogin(HttpServletResponse response,HttpServletRequest request) throws IOException {

      log.info(" manager don't login");

        RequestDispatcher rd =  request.getRequestDispatcher("/r/json/noLogin.json");
        try {
            rd.forward(request,response);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // response.sendRedirect("/noPermissin");


        return false;

    }
//
//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//
//        log.info("   postHandle  start  ======================= ");
//
//
//
//        String fileType=request.getParameter("excel");
//        if("excel".equals(fileType)){
//            log.info("you want import excel ");
//
//            HandlerMethod handler2=(HandlerMethod) handler;
//
//            Map model = 	modelAndView.getModel();
//
//
//            Method m=handler2.getMethod();
//            log.info("method name is "+m.getName());
//
//            if (m.isAnnotationPresent(FileTemplate.class)) {
//                FileTemplate p = m.getAnnotation(FileTemplate.class);
//
//                String content = generate.generateFile(model, p.templatePath(), p.filePath(),
//                        excelName, false);
//                log.info("test result is " + content);
//
//            }else{
//
//            }
//            String url="/exceldownload/"+excelName;
//            model.put("url", url);
//            model.put("code",0);
//            modelAndView.setViewName("/data/img");
//
//        }else{
//
//        }
//
//
//    }
//
    /**
     * 获取跳转链接的地址
     *
     */
    public String getInterceptorUrl(HttpServletRequest request) {

        String interceptorUrl = request.getRequestURI()
                + (null == request.getQueryString() ? "" : "?"
                + request.getQueryString()).toString();
        interceptorUrl = interceptorUrl.replace("/app/", "/");

        log.info("get getInterceptorUrl is " + interceptorUrl);

        return interceptorUrl;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        String x = URLEncoder.encode("p/hello", "utf-8");
        log.info(x);

    }
}
