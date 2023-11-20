/**
 * 
 */
package com.nri.busmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author arghyas
 *
 */

@Controller
public class LoginController {

	@RequestMapping("/loginPage")
	public String login() {
		return "index";
	}
}
