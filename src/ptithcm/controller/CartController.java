package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.entity.Ca;
import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.HoaDon;
import ptithcm.entity.Users;
import ptithcm.service.CaService;
import ptithcm.service.CheckLogin;
import ptithcm.service.ChiTietHoaDonService;
import ptithcm.service.HoaDonService;


@Transactional
@Controller
@RequestMapping("/cart")
public class CartController {
	@Qualifier("hoaDonService")
	@Autowired
	HoaDonService hoaDonService;
	@Qualifier("chiTietHoaDonService")
	@Autowired
	ChiTietHoaDonService chiTietHoaDonService;
	@Qualifier("caService")
	@Autowired
	CaService caService;
	@Autowired
	SessionFactory factory;
	@Autowired
	HttpSession session;
	@RequestMapping(value ="/addCaVoHoaDon/{maCa}" ,method=RequestMethod.POST)
	public String themCaVaoGioHang(HttpSession session,@PathVariable("maCa") int maCa,@RequestParam("soLuong") int soLuong) {
		
		Users user = (Users) session.getAttribute("user");
		HoaDon hoaDon= hoaDonService.timHoaDonChuaTinhTien(user);
		Ca ca = caService.timCa(maCa);
		
		
		ChiTietHoaDon chiTietHoaDon =  chiTietHoaDonService.timChiTietHoaDonTheoCaVaMaHoaDon(hoaDon.getMaHoaDon(), maCa);
		if(chiTietHoaDon==null) {
			chiTietHoaDon= new ChiTietHoaDon();
		}
		chiTietHoaDon.setHoaDon(hoaDon);
		chiTietHoaDon.setCa(ca);
		if(chiTietHoaDon.getSoLuong()==null) {
			chiTietHoaDon.setSoLuong(soLuong);
		}
		else
		chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong() + soLuong );
		chiTietHoaDon.setDonGia(ca.getDonGiaXuat());
		ca.setSoLuongConLai(ca.getSoLuongConLai()-soLuong);
		ca.setSoLuongDaBan(ca.getSoLuongDaBan()+soLuong);
		caService.muaCa(ca);
		chiTietHoaDon.setThanhTien(ca.getDonGiaXuat()*chiTietHoaDon.getSoLuong());		
		chiTietHoaDonService.luuChiTietHoaDon(chiTietHoaDon);
		
		return "redirect:/home.htm";
	}
	@RequestMapping("/xemGioHang")
	public String xem(ModelMap model) {
		
		Users user=(Users) session.getAttribute("user");
		HoaDon hoaDon = hoaDonService.searchHoadon(user);
		if(hoaDon == null) {
			return "redirect:/home.htm";
		}
		List<ChiTietHoaDon> list =(List<ChiTietHoaDon>) hoaDon.getChiTietHoaDons();
		Float tongTien=(float) 0;
		for(ChiTietHoaDon n : list) {
			tongTien+=n.getThanhTien();
		}
		model.addAttribute("chiTietHoaDon",list);
		model.addAttribute("tongTien",tongTien);
		return "user/cartpage";
	}
	@RequestMapping(value ="/xoa/{maChiTietHoaDon}",method = RequestMethod.GET)
	public String deleteChiTietHoaDon(ModelMap model,@PathVariable("maChiTietHoaDon") Integer maChiTietHoaDon) {
		chiTietHoaDonService.xoaChiTietHoaDon(maChiTietHoaDon);
		return "redirect:/cart/xemGioHang.htm";
	}

	@RequestMapping(value ="/thanhToan/{tongTien}",method = RequestMethod.GET)
	public String updateHoaDon(ModelMap model,@PathVariable("tongTien") Float tongTien)  {
		Users user=(Users) session.getAttribute("user");
		HoaDon hoaDon = hoaDonService.searchHoadon(user);
		hoaDonService.hoanThanhHoaDon(hoaDon, tongTien);
		return "redirect:/home.htm";
	}
}
