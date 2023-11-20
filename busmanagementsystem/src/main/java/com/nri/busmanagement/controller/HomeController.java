/**
 * 
 */
package com.nri.busmanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author arghyas
 *
 */

@Controller
@RequestMapping("/user")
public class HomeController {
	
	@RequestMapping("/home")
	public String home() {
		return "user_dash";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping("/bookingDash")
	public String dash() {
		return "book";
	}
	
	@RequestMapping("/sendMessage")
	public String sendMessage() {

	    return "blocked.html";
	    
	}
}
