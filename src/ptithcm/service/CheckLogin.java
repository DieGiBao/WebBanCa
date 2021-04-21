package ptithcm.service;

import javax.servlet.http.HttpSession;

import ptithcm.entity.Users;

public class CheckLogin {
public static String check(HttpSession session) {
	Users user=(Users) session.getAttribute("user");
	if(user == null) {
		return "redirect:/login/loginPage.htm";
	}
	if(user.getEmail().compareTo("admin")!=0) {
		return "redirect:/home.htm";
	}
	return null;
}
}
