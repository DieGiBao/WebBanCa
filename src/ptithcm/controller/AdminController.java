package ptithcm.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.entity.Ca;
import ptithcm.entity.ChiTietHoaDon;
import ptithcm.entity.HoaDon;
import ptithcm.entity.Users;
import ptithcm.service.CaService;
import ptithcm.service.CheckLogin;
import ptithcm.service.ChiTietHoaDonService;
import ptithcm.service.UserService;

@Transactional
@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	ServletContext context;
	@Autowired
	UserService userService;
	@Autowired
	ChiTietHoaDonService chiTietHoaDonService;
	@Autowired
	CaService caService;
	@Autowired
	SessionFactory factory;
	@Autowired
	HttpSession session;
	@RequestMapping("index")
	public String index(ModelMap model) {
		return "admin/admin";
	}
	
	@RequestMapping("hoaDon")
	public String indexHoaDon(ModelMap model) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		Session session = factory.getCurrentSession();
		String hql = "From HoaDon";
		Query query = session.createQuery(hql);
		List<Users> list = query.list();
		model.addAttribute("hoaDon",list);
		return "admin/hoaDon/hoaDon";
	}
	@RequestMapping(value="hoaDon/sua/{maHoaDon}",method=RequestMethod.GET)
	public String updateHoaDon(ModelMap model,@PathVariable("maHoaDon") int maHoaDon) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		Session session = factory.getCurrentSession();
		HoaDon hoaDon = (HoaDon)session.get(HoaDon.class, maHoaDon);		
		model.addAttribute("hoaDon",hoaDon);
		return "admin/hoaDon/sua";
	}
	@RequestMapping(value ="hoaDon/sua",method = RequestMethod.POST)
	public String updateHoaDon(ModelMap model,@ModelAttribute("hoaDon") HoaDon hoaDon,BindingResult errors)  {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(hoaDon.getUser().getEmail().length() == 0) {
			errors.rejectValue("user.email","hoaDon","vui lòng nhập email khách hàng !");
		}
		if(hoaDon.getNgayBan() == null) {
			errors.rejectValue("ngayBan","hoaDon","Vui lòng nhập ngày tháng!");
		}		
		if(hoaDon.getTongTien() == null ) {
			errors.rejectValue("tongTien","hoaDon","Bạn phải nhập tổng tiền!");
		} 
		if(errors.hasErrors()) {
			model.addAttribute("message","vui lòng sửa các lỗi sau đây !");
			return "admin/hoaDon/sua";
		}
		else {
			model.addAttribute("message","Chúc mừng bạn đã nhập đúng !");
		}
		try {
			session.update(hoaDon);
			t.commit();
			model.addAttribute("message","Cập nhật thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message","Cập nhập thất bại!");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/hoaDon.htm";
	}
	@RequestMapping(value="hoaDon/them",method=RequestMethod.GET)	
	public String insertHoaDon(ModelMap model) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		model.addAttribute("hoaDon",new HoaDon());	
		return "admin/hoaDon/them";
	}
	
	@RequestMapping(value ="hoaDon/them",method = RequestMethod.POST)
	public String insertHoaDon(ModelMap model,@ModelAttribute("hoaDon") HoaDon hoaDon,BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(hoaDon.getUser().getEmail().length() == 0) {
			errors.rejectValue("user.email","hoaDon","vui lòng nhập email khách hàng !");
		}
		if(hoaDon.getNgayBan() == null) {
			errors.rejectValue("ngayBan","hoaDon","Vui lòng nhập ngày tháng!");
		}		
		if(hoaDon.getTongTien() == null ) {
			errors.rejectValue("tongTien","hoaDon","Bạn phải nhập tổng tiền!");
		} 
		if(errors.hasErrors()) {
			model.addAttribute("message","vui lòng sửa các lỗi sau đây !");
			return "admin/hoaDon/sua";
		}
		else {
			model.addAttribute("message","Chúc mừng bạn đã nhập đúng !");
		}
		if(!userService.findUser(hoaDon.getUser().getEmail())) {
			model.addAttribute("message","Không tìm thấy user");
			return  "redirect:/admin/hoaDon.htm";
		}
		try {			
			session.save(hoaDon);
			t.commit();
			model.addAttribute("message","Thêm thành công");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message","Thêm thất bại !");
		}
		finally {
			session.close();
		}
		return  "redirect:/admin/hoaDon.htm";
	}
	@RequestMapping(value ="hoaDon/xoa/{maHoaDon}",method = RequestMethod.GET)
	public String deleteHoaDon(ModelMap model,@PathVariable("maHoaDon") int maHoaDon) {
		
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {			
			List<ChiTietHoaDon> list=chiTietHoaDonService.findAllChiTietHoaDon(maHoaDon);
			if(!list.isEmpty())	{
				for(ChiTietHoaDon chiTiet : list) {
					session.delete(chiTiet);
				}
			}
			HoaDon hoaDon = (HoaDon)session.get(HoaDon.class,maHoaDon);
			session.delete(hoaDon);
			t.commit();
			model.addAttribute("message","Xóa thành công!");
		} catch (Exception e) {
			t.rollback();
			System.out.print("hellothere "+e);
			model.addAttribute("message","Xóa thất bại!");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/hoaDon.htm";
	}
	@RequestMapping("user")
	public String indexUser(ModelMap model) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		Session session = factory.getCurrentSession();
		String hql = "From Users ";
		Query query = session.createQuery(hql);
		List<Users> list = query.list();
		model.addAttribute("user",list);
		return "admin/user/user";
	}
	@RequestMapping(value="user/sua/{email}",method=RequestMethod.GET)
	public String updateUser(ModelMap model,@PathVariable("email") String email) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		Session session = factory.getCurrentSession();
		Users user = (Users)session.get(Users.class, email);		
		model.addAttribute("user",user);
		return "admin/user/sua";
	}
	@RequestMapping(value ="user/sua",method = RequestMethod.POST)
	public String updateUser(ModelMap model,@ModelAttribute("user") Users user,BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(user.getTenKhach().length() == 0) {
			errors.rejectValue("tenKhach","user","vui lòng nhập email !");
		}
		if(user.getDiaChi().length() == 0) {
			errors.rejectValue("diaChi","user","Vui lòng nhập địa chỉ!");
		}		
		if(user.getDienThoai().length() == 0 ) {
			errors.rejectValue("dienThoai","user","Vui lòng nhập điên thoại!");
		} 		
		if(user.getMatKhau().length() == 0 ) {
			errors.rejectValue("matKhau","user","Thiếu mật khẩu!");
		} 
		if(errors.hasErrors()) {
			model.addAttribute("message","vui lòng sửa các lỗi sau đây !");
			return "admin/user/sua";
		}
		else {
			model.addAttribute("message","Chúc mừng bạn đã nhập đúng !");
		}
		try {
			session.update(user);
			t.commit();
			model.addAttribute("message","Cập nhật thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message","Cập nhập thất bại!");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/user.htm";
	}
	@RequestMapping(value="user/them",method=RequestMethod.GET)
	public String insertUser(ModelMap model) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		model.addAttribute("user",new Users());	
		return "admin/user/them";
	}
	
	@RequestMapping(value ="user/them",method = RequestMethod.POST)
	public String insert(ModelMap model,@ModelAttribute("user") Users user,BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(user.getEmail().length() == 0) {
			errors.rejectValue("email","user","vui lòng nhập email khách hàng !");
		}
		if(user.getTenKhach().length() == 0) {
			errors.rejectValue("tenKhach","user","vui lòng nhập tên khách khách hàng !");
		}
		if(user.getDiaChi().length() == 0) {
			errors.rejectValue("diaChi","user","Vui lòng nhập địa chỉ!");
		}		
		if(user.getDienThoai().length() == 0 ) {
			errors.rejectValue("dienThoai","user","Bạn phải nhập số điên thoại!");
		} 		
		if(user.getMatKhau().length() == 0 ) {
			errors.rejectValue("matKhau","user","Bạn phải nhập mật khẩu!");
		} 
		if(errors.hasErrors()) {
			model.addAttribute("message","vui lòng sửa các lỗi sau đây !");
			return "admin/user/them";
		}
		try {			
			session.save(user);
			t.commit();
			model.addAttribute("message","Thêm thành công");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message","Thêm thất bại !");
		}
		finally {
			session.close();
		}
		return  "redirect:/admin/user.htm";
	}
	@RequestMapping(value ="user/xoa/{email}",method = RequestMethod.GET)
	public String delete(ModelMap model,@PathVariable("email") String email) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(email.compareTo("admin")==0) {
			model.addAttribute("message","Không thể xóa admin!");
			return "redirect:/admin/user.htm";
		}
		try {			
			Users user = (Users)session.get(Users.class,email);
			user.setMatKhau(null);
			//session.delete(ca);session.save(ca);
			session.save(user);
			t.commit();
			model.addAttribute("message","Xóa thành công!");
		} catch (Exception e) {
			t.rollback();
			System.out.print("hellothere "+e);
			model.addAttribute("message","Xóa thất bại!");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/user.htm";
	}
	
	@RequestMapping("ca")
	public String indexCa(ModelMap model) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		Session session = factory.getCurrentSession();
		String hql = "From Ca ";
		Query query = session.createQuery(hql);
		List<Users> list = query.list();
		model.addAttribute("ca",list);
		return "admin/ca/ca";
	}
	@RequestMapping(value="ca/sua/{maCa}",method=RequestMethod.GET)
	public String updateCa(ModelMap model,@PathVariable("maCa") Integer maCa) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		Session session = factory.getCurrentSession();
		Ca ca = (Ca)session.get(Ca.class, maCa);		
		model.addAttribute("ca",ca);
		return "admin/ca/sua";
	}
	@RequestMapping(value ="ca/sua",method = RequestMethod.POST)
	public String update(ModelMap model,@ModelAttribute("Ca") Ca ca,@RequestParam("photo") MultipartFile photo,BindingResult errors) throws IllegalStateException, IOException {
		
		System.out.println(photo);
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(ca.getDonGiaNhap() == null) {
			errors.rejectValue("donGiaNhap","ca","vui lòng nhập đơn giá nhập !");
		}
		if(ca.getDonGiaXuat() == null ) {
			errors.rejectValue("donGiaXuat","ca","Vui lòng nhập đơn giá xuất!");
		}		
//		if(photo.isEmpty() ) {
//			errors.rejectValue("anh","ca","Vui lòng nhập ảnh !");
//		}			
		if(ca.getKichCo() == null ) {
			errors.rejectValue("kichCo","ca","Vui lòng nhập kích cỡ !");
		} 			
		if(ca.getLoaiCa().length() == 0 ) {
			errors.rejectValue("loaiCa","ca","Vui lòng nhập loài cá !");
		} 				
		if(ca.getMauSac().length() == 0 ) {
			errors.rejectValue("mauSac","ca","Vui lòng nhập màu sắc !");
		}
		if(ca.getSoLuongConLai() == null ) {
			errors.rejectValue("soLuongConLai","ca","Vui lòng nhập số lượng còn lại !");
		} 	
		if(ca.getSoLuongDaBan() == null ) {
			errors.rejectValue("soLuongDaBan","ca","Vui lòng nhập số lượng đã bán !");
		} 	
		if(errors.hasErrors()) {
			model.addAttribute("message","vui lòng sửa các lỗi sau đây !");
			return "admin/ca/sua";
		}
		else {
			model.addAttribute("message","Chúc mừng bạn đã nhập đúng !");
		}

		if(!photo.isEmpty())
		{
			ca.setAnh(photo.getOriginalFilename());
			String photoPath =context.getRealPath("/files/"+photo.getOriginalFilename());
			photo.transferTo(new File(photoPath));
		}
		else {
			ca.setAnh(caService.timCa(ca.getMaCa()).getAnh());
		}
		try {
			session.update(ca);
			t.commit();
			model.addAttribute("message","Cập nhật thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message","Cập nhập thất bại!");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/ca.htm";
	}
	@RequestMapping(value="ca/them",method=RequestMethod.GET)
	public String insert(ModelMap model) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		model.addAttribute("ca",new Ca());	
		return "admin/ca/them";
	}
	
	@RequestMapping(value ="ca/them",method = RequestMethod.POST)
	public String insert(ModelMap model,@ModelAttribute("ca") Ca ca,@RequestParam("photo") MultipartFile photo,BindingResult errors) throws IllegalStateException, IOException {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(ca.getDonGiaNhap() == null) {
			errors.rejectValue("donGiaNhap","ca","vui lòng nhập đơn giá nhập !");
		}
		if(ca.getDonGiaXuat() == null ) {
			errors.rejectValue("donGiaXuat","ca","Vui lòng nhập đơn giá xuất!");
		}		
		if(ca.getKichCo() == null ) {
			errors.rejectValue("kichCo","ca","Vui lòng nhập kích cỡ !");
		} 			
		if(ca.getLoaiCa().length() == 0 ) {
			errors.rejectValue("loaiCa","ca","Vui lòng nhập loài cá !");
		} 				
		if(ca.getMauSac().length() == 0 ) {
			errors.rejectValue("mauSac","ca","Vui lòng nhập màu sắc !");
		}
		if(ca.getSoLuongConLai() == null ) {
			errors.rejectValue("soLuongConLai","ca","Vui lòng nhập số lượng còn lại !");
		} 	
		if(ca.getSoLuongDaBan() == null ) {
			errors.rejectValue("soLuongDaBan","ca","Vui lòng nhập số lượng đã bán !");
		} 	
		if(errors.hasErrors()) {
			model.addAttribute("message","vui lòng sửa các lỗi sau đây !");
			return "admin/ca/them";
		}
		else {
			model.addAttribute("message","Chúc mừng bạn đã nhập đúng !");
		}

			ca.setAnh(photo.getOriginalFilename());
			String photoPath =context.getRealPath("/files/"+photo.getOriginalFilename());
			photo.transferTo(new File(photoPath));
		try {			
			session.save(ca);
			t.commit();
			model.addAttribute("message","Thêm thành công");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message","Thêm thất bại !");
		}
		finally {
			session.close();
		}
		return  "redirect:/admin/ca.htm";
	}
	@RequestMapping(value ="ca/xoa/{maCa}",method = RequestMethod.GET)
	public String delete(ModelMap model,@PathVariable("maCa") int maCa) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		} 
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {			
			Ca ca = (Ca)session.get(Ca.class,maCa);
			ca.setSoLuongConLai(0);
			//session.delete(ca);session.save(ca);
			session.save(ca);
			t.commit();
			model.addAttribute("message","Xóa thành công!");
		} catch (Exception e) {
			t.rollback();
			System.out.print("hellothere "+e);
			model.addAttribute("message","Xóa thất bại!");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/ca.htm";
	}
	
	@RequestMapping("chiTietHoaDon")
	public String indexChiTietHoaDon(ModelMap model) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		} 
		Session session = factory.getCurrentSession();
		String hql = "From ChiTietHoaDon";
		Query query = session.createQuery(hql);
		List<Users> list = query.list();
		model.addAttribute("chiTietHoaDon",list);
		return "admin/chiTietHoaDon/chiTietHoaDon";
	}
	@RequestMapping(value="chiTietHoaDon/sua/{maChiTietHoaDon}",method=RequestMethod.GET)
	public String update(ModelMap model,@PathVariable("maChiTietHoaDon") Integer maChiTietHoaDon) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		Session session = factory.getCurrentSession();
		ChiTietHoaDon chiTietHoaDon = (ChiTietHoaDon)session.get(ChiTietHoaDon.class, maChiTietHoaDon);		
		model.addAttribute("chiTietHoaDon",chiTietHoaDon);
		return "admin/chiTietHoaDon/sua";
	}
	@RequestMapping(value ="chiTietHoaDon/sua",method = RequestMethod.POST)
	public String updateChiTietHoaDon(ModelMap model,@ModelAttribute("chiTietHoaDon") ChiTietHoaDon chiTietHoaDon,BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(chiTietHoaDon.getSoLuong() == null) {
			errors.rejectValue("soLuong","chiTietHoaDon","vui lòng nhập số lượng !");
		}
		if(chiTietHoaDon.getDonGia() == null ) {
			errors.rejectValue("donGia","chiTietHoaDon","Vui lòng nhập đơn giá !");
		}		
		if(chiTietHoaDon.getThanhTien() == null ) {
			errors.rejectValue("thanhTien","chiTietHoaDon","Vui lòng nhập thành tiền !");
		} 	
		if(errors.hasErrors()) {
			model.addAttribute("message","vui lòng sửa các lỗi sau đây !");
			return "admin/chiTietHoaDon/sua";
		}
		else {
			model.addAttribute("message","Chúc mừng bạn đã nhập đúng !");
		}
		try {
			session.update(chiTietHoaDon);
			t.commit();
			model.addAttribute("message","Cập nhật thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message","Cập nhập thất bại!");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/chiTietHoaDon.htm";
	}
	@RequestMapping(value="chiTietHoaDon/them",method=RequestMethod.GET)
	public String inserChiTietHoaDon(ModelMap model) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		model.addAttribute("chiTietHoaDon",new ChiTietHoaDon());	
		return "admin/chiTietHoaDon/them";
	}
	
	@RequestMapping(value ="chiTietHoaDon/them",method = RequestMethod.POST)
	public String insertChiTietHoaDon(ModelMap model,@ModelAttribute("chiTietHoaDon") ChiTietHoaDon chiTietHoaDon,BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(chiTietHoaDon.getCa().getMaCa() == null) {
			errors.rejectValue("ca.maCa","chiTietHoaDon","vui lòng nhập mã cá !");
		}
		if(chiTietHoaDon.getHoaDon().getMaHoaDon() == null) {
			errors.rejectValue("hoaDon.maHoaDon","chiTietHoaDon","vui lòng nhập mã hóa đơn !");
		}
		if(chiTietHoaDon.getSoLuong() == null) {
			errors.rejectValue("soLuong","chiTietHoaDon","vui lòng nhập số lượng !");
		}
		if(chiTietHoaDon.getDonGia() == null ) {
			errors.rejectValue("donGia","chiTietHoaDon","Vui lòng nhập đơn giá !");
		}		
		if(chiTietHoaDon.getThanhTien() == null ) {
			errors.rejectValue("thanhTien","chiTietHoaDon","Vui lòng nhập thành tiền !");
		} 	
		if(errors.hasErrors()) {
			model.addAttribute("message","vui lòng sửa các lỗi sau đây !");
			return "admin/chiTietHoaDon/them";
		}
		else {
			model.addAttribute("message","Chúc mừng bạn đã nhập đúng !");
		}
		try {			
			session.save(chiTietHoaDon);
			t.commit();
			model.addAttribute("message","Thêm thành công");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message","Thêm thất bại !");
		}
		finally {
			session.close();
		}
		return  "redirect:/admin/chiTietHoaDon.htm";
	}
	@RequestMapping(value ="chiTietHoaDon/xoa/{maChiTietHoaDon}",method = RequestMethod.GET)
	public String deleteChiTietHoaDon(ModelMap model,@PathVariable("maChiTietHoaDon") Integer maChiTietHoaDon) {
		String isLogin = CheckLogin.check(session);
		if(isLogin!=null) {
			return isLogin;
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		try {			
			ChiTietHoaDon chiTietHoaDon = (ChiTietHoaDon)session.get(ChiTietHoaDon.class, maChiTietHoaDon);	
			session.delete(chiTietHoaDon);
			t.commit();
			model.addAttribute("message","Xóa thành công!");
		} catch (Exception e) {
			t.rollback();
			System.out.print("hellothere " + e);
			model.addAttribute("message","Xóa thất bại!");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/chiTietHoaDon.htm";
	}
}
