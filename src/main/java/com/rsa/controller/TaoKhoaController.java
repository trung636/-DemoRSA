package com.rsa.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rsa.model.HopDong;
import com.rsa.model.TaoKhoa;
import com.rsa.utils.Utils;

@Controller
public class TaoKhoaController {

	@GetMapping()
	public String viewTaoKhoa(Model model) {
		return "home";
	}
	
	@GetMapping("tao-khoa")
	public String taokhoa(Model model ,HttpSession session) {
			
		TaoKhoa taoKhoa = new TaoKhoa();
		BigInteger snt1 = Utils.taoSoNguyenTo();
		BigInteger snt2 = Utils.taoSoNguyenTo();
		BigInteger n = snt1.multiply(snt2);
 		BigInteger m = (snt1.subtract(BigInteger.ONE)).multiply(snt2
                .subtract(BigInteger.ONE));
		BigInteger e = Utils.soE(m);
	    BigInteger d = e.modInverse(m);
	    taoKhoa.setSnt1(snt1);
	    taoKhoa.setSnt2(snt2);
	    taoKhoa.setN(n);
	    taoKhoa.setE(e);
	    taoKhoa.setD(d);
	    taoKhoa.setSesssion(session);
	    model.addAttribute("khoa", taoKhoa);
		return "tao-khoa";
	}
	@GetMapping("/ky-hd")
	public String kiHD(HttpSession session) {
		if(session.getAttribute("e")==null) {
			return "redirect:/";
		}
		return "ky-hd";
	}
	@PostMapping("/ky")
	public String kyHD(@ModelAttribute HopDong hopDong, HttpSession session, RedirectAttributes redirectAttributes) {
		BigInteger bamFile =  Utils.md(hopDong.getFileData()).abs();
		BigInteger priKey = Utils.enCrypt(bamFile, session);
		session.setAttribute("prikey", priKey);
		session.setAttribute("bamFile", bamFile);
		redirectAttributes.addFlashAttribute("kySuccess", true);
		return "redirect:/ky-hd1";
	}
	@GetMapping("/xac-thuc")
	private String getXacThuc() {
		return "xac-thuc";
	}
	@PostMapping("/xac-thuc")
	private String postXacThuc(@ModelAttribute HopDong hopDong, RedirectAttributes redirectAttributes) {
		BigInteger bamFile =  Utils.md(hopDong.getFileData()).abs();
		BigInteger result = hopDong.getKey().modPow(hopDong.getE(), hopDong.getN());
		if(bamFile.equals(result)) {
			redirectAttributes.addFlashAttribute("success", true);
		}else {
			redirectAttributes.addFlashAttribute("fail", true);
		}
		return "redirect:/xac-thuc";
	}
}
