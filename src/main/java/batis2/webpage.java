package batis2;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class webpage {
	ProductVO pv = new ProductVO();
	@Autowired
	BasicDataSource datasource;
	@Inject
	private SqlSessionFactory sqlsessionfactory;
	@Resource
	private SqlSessionTemplate sqlsession;

	@RequestMapping("test.do")
	public String test() {
		System.out.println("test");
		return "/WEB-INF/admin/adminLogin.jsp";
	}

	@PostMapping("/adminlogin.do")
	public String adminLogin(@RequestParam String adminId, @RequestParam String adminPw) {
		String asd = "";
		if (adminId.equals("admin") && adminPw.equals("123")) {
			asd = "/WEB-INF/admin/adminPage.jsp";
		} else {
			asd = "/WEB-INF/admin/adminLogin.jsp";
		}
		System.out.println(adminId);
		return asd;
	}

	@RequestMapping("/productInfo.do")
	public String productPage(@ModelAttribute("test_product") ProductVO pv, Model model) {
		SqlSession se = sqlsessionfactory.openSession();
		List<ProductVO> list = null;
		list = se.selectList("adminDB.selectAll",pv);
		System.out.println(pv);
		model.addAttribute("data",list);
		se.close();
		return "WEB-INF/admin/productInfo.jsp";
	}

	@GetMapping("/addproduct.do")
	public String addProduct() {
		return "WEB-INF/admin/addProduct.jsp";
	}

	@PostMapping("/addproduct2.do")
	public String addProduct2(@ModelAttribute("test_product") ProductVO pv, @RequestParam MultipartFile ppimage,
			@RequestParam MultipartFile ppthumbnail) {
		String imagedir = "D:\\batis2\\src\\main\\webapp\\WEB-INF\\images";
		String thumbdir = "D:\\batis2\\src\\main\\webapp\\WEB-INF\\thumbnails";
		String pimage = ppimage.getOriginalFilename();
		String pthumbnail = ppthumbnail.getOriginalFilename();
		File saveImage = new File(imagedir + "\\" + pimage);
		File saveThumb = new File(thumbdir + "\\" + pthumbnail);
		pv.setPimage(imagedir + "\\" + pimage);
		pv.setPthumbnail(thumbdir + "\\" + pthumbnail);
		SqlSession se = sqlsessionfactory.openSession();
		try {
			ppimage.transferTo(saveImage);
			ppthumbnail.transferTo(saveThumb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			int a = se.insert("adminDB.productInsert", pv);
			if (a > 0) {
				System.out.println("성공");
			} else {
				System.out.println("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			se.close();
		}
		return "/productinfo.do";
	}
}
