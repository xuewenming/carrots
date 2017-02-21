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

import com.ptteng.carrots.home.model.Product;
import com.ptteng.carrots.home.service.ProductService;

/**
 * Product  crud
 * 
 * @author magenm
 * @Date 2014-4-16 13:43
 * 
 */
@Controller
public class ProductController {
	private static final Log log = LogFactory.getLog(ProductController.class);

	@Autowired
	private ProductService productService;






    /**
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 * @throws ServiceDaoException
	 */

	@RequestMapping(value = "/c/product", method = RequestMethod.GET)
	public String getproductList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		
		
		log.info("/product  to /product/view/productList");

		return "/carrots-home-service/product/view/productList";
	}
    
    

    
	
	@RequestMapping(value = "/c/product/{id}", method = RequestMethod.GET)
	public String getProduct(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("/product/" + id + "  to /product/view/productDeail");
		if(null != id){
			model.addAttribute("id", id);
		}else{
			model.addAttribute("id", 0);
		}

		return "/carrots-home-service/product/view/productDetail";
	}
	
	
	
	    
	

	@RequestMapping(value = "/a/product/{id}", method = RequestMethod.GET)
	public String getProductJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("get data : id= " + id);
		try {
			Product product = productService.getObjectById(id);
			log.info("get product data is " + product);

			model.addAttribute("code", 0);

			model.addAttribute("product", product);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("get product error,id is  " + id);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/product/json/productDetailJson";
	}

	@RequestMapping(value = "/a/product/{id}", method = RequestMethod.PUT)
	public String updateProductJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Product product) throws Exception {
		
		log.info("update product : product= " + product);
		
		try {
			
			productService.update(product);

			model.addAttribute("code", 0);

			model.addAttribute("product", product);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("update product error,id is  " + product.getId());
			model.addAttribute("code", -6003);

		}

		return "/data/json";
	}

	@RequestMapping(value = "/a/product", method = RequestMethod.POST)
	public String addProductJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Product product) throws Exception {
		
		log.info("update product : product= " + product);
		
		try { 
			product.setId(null);

			productService.insert(product);

			model.addAttribute("code", 0);
		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("add product error ");
			model.addAttribute("code", -6002);
		}

		return "/data/json";
	}

	@RequestMapping(value = "/a/product/{id}", method = RequestMethod.DELETE)
	public String deleteProductJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @PathVariable Long id)
			throws Exception {

		log.info("delete product : id= " + id);
		try {
			productService.delete(id);

			log.info("add product success");
			model.addAttribute("code", 0);

		} catch (Throwable t) {
		    t.printStackTrace();
			log.error(t.getMessage());
			log.error("delete product error,id is  " + id);
			model.addAttribute("code", -6004);

		}

		return "/data/json";
	}
	
	
	@RequestMapping(value = "/a/multi/product", method = RequestMethod.GET)
	public String getMultiProductJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Long[] ids)
			throws Exception {
			
		List<Long> idList = new ArrayList();	
	   if (ids == null) {

		} else {
			idList = Arrays.asList(ids);
		}
		try {

			

			List<Product> productList = productService.getObjectsByIds(idList);
			log.info("get  product data is " + productList);

			model.addAttribute("code", 0);			
			model.addAttribute("total",productList.size());

			model.addAttribute("productList", productList);

		} catch (Throwable t) {
			log.error(t.getMessage());
			log.error("get product error,id is  " + idList);
			model.addAttribute("code", -100000);
		}

		return "/carrots-home-service/product/json/productListJson";
	}
	
	
	
	
	
}

