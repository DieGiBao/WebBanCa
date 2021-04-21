package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ptithcm.entity.*;
import ptithcm.service.CheckLogin;
import ptithcm.service.HoaDonService;
import ptithcm.service.CaService;
@Controller
@Transactional
@RequestMapping("/home")
public class HomePageController {	
	@Autowired
	CaService caService;
	@Autowired
	HoaDonService hoaDonService;
	@RequestMapping("")
	public String index(ModelMap model,HttpSession session) {
		
		Users user=(Users) session.getAttribute("user");
		HoaDon hoaDon = hoaDonService.searchHoadon(user);
		model.addAttribute("hoaDon",hoaDon);
		
		List<Ca> listCa= caService.findAll();
		model.addAttribute("ca",listCa);
		return "user/homepage";
	}
}
