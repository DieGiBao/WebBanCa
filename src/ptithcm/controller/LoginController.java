package ptithcm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.entity.Users;
import ptithcm.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Qualifier("userService")
	@Autowired
	UserService userService;
	
	@RequestMapping("/loginPage")
	public String showLogin(ModelMap model,@ModelAttribute("user") Users user) {
		model.addAttribute("user", user);
		return "login/login";
	}
	
	@RequestMapping(value ="/doLogin" ,method =RequestMethod.POST)
	public String doLogin(ModelMap model,@ModelAttribute("user") Users user,HttpSession session,BindingResult errors) {
		
		Users foundUser= userService.findUser(user);
		if(user.getEmail().length() == 0) {
			errors.rejectValue("email","user","Vui lòng nhập email !");
		}
		if(user.getMatKhau().length() == 0) {
			errors.rejectValue("matKhau","user","Bạn chưa nhập mật khẩu !");
		}
		if(errors.hasErrors()) {
			model.addAttribute("message","Vui lòng sửa các lỗi sau đây !");
			return "login/login";
		}
		if(foundUser== null) {
			model.addAttribute("message","Sai email hoặc mật khẩu");
			return "login/login";
		}
		session.setAttribute("user", foundUser);		
		if(foundUser.getEmail().compareTo("admin")==0) {
			return "redirect:/admin/index.htm";
		}
		return "redirect:/home.htm";
	}
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String insertUser(ModelMap model) {
		model.addAttribute("user",new Users());	
		return "login/signup";
	}
	
	@RequestMapping(value ="/dosignup" ,method =RequestMethod.POST)
	public String doSignUp(ModelMap model,@ModelAttribute("user") Users user,@RequestParam("xacnhan") String xacnhan,HttpSession session, BindingResult errors) {
		
		if(user.getEmail().length() == 0) {
			errors.rejectValue("email","user","Vui lòng nhập email !");
		}
		if(user.getMatKhau().length() == 0) {
			errors.rejectValue("matKhau","user","Bạn chưa nhập mật khẩu !");
		}
		System.out.print(user.getMatKhau() + " : " + xacnhan);
		if(user.getMatKhau().compareTo(xacnhan) != 0) {
			errors.rejectValue("matKhau","user","Xác nhận mật khẩu không đúng!");
		}
		if(user.getTenKhach().length() == 0 ) {
			errors.rejectValue("tenKhach","user","Vui lòng điền tên khách!");
		}
		if(user.getDiaChi().length() == 0 ) {
			errors.rejectValue("diaChi","user","Vui lòng điền địa chỉ!");
		}
		if(user.getDienThoai().length() == 0 ) {
			errors.rejectValue("dienThoai","user","Vùi lòng nhập số điện thoại!");
		}
		
		if(errors.hasErrors()) {
			model.addAttribute("message","Vui lòng sửa các lỗi sau đây !");
			return "login/signup";
		}
		else {
			model.addAttribute("message","Chúc mừng bạn đã nhập đúng !");
		}

		Users foundUser= userService.findUser(user);
		if(foundUser != null) {
			errors.rejectValue("email","user","Tài khoản đã tồn tại");
			return "login/signup";
		}
	    userService.createUser(user);
		return "redirect:/login/loginPage.htm";
	}
	@RequestMapping(value ="/doSignOut" )
	public String doSignOut(ModelMap model,	HttpSession session) {
		session.removeAttribute("user");
		//session.removeAttribute("cart");
		return "redirect:/login/loginPage.htm";
	}
}