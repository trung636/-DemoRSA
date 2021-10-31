package com.rsa.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rsa.model.HopDong;
import com.rsa.utils.Utils;

@Controller
public class NewController {
	
	@GetMapping("/home1")
	public String welcome() {
		return "taokhoa";
	}
	
	@GetMapping("taokhoa")
	public String  taoKhoa(HttpSession session, RedirectAttributes redirectAttributes) {
		BigInteger p = Utils.taoSoNguyenTo();
		BigInteger q = Utils.taoSoNguyenTo();
		session.setAttribute("p", p);
		session.setAttribute("q", q);
		redirectAttributes.addFlashAttribute("CreateSNT",true );
		return "redirect:/home1";
	}
	@GetMapping("tinh")
	public String tinh(HttpSession session,RedirectAttributes redirectAttributes) {
		BigInteger snt1 = (BigInteger) session.getAttribute("p");
		BigInteger snt2 = (BigInteger) session.getAttribute("q");
		BigInteger n = snt1.multiply(snt2);
 		BigInteger m = (snt1.subtract(BigInteger.ONE)).multiply(snt2
                .subtract(BigInteger.ONE));
		BigInteger e = Utils.soE(m);
	    BigInteger d = e.modInverse(m);
	    session.setAttribute("n",n);
	    session.setAttribute("e",e);
	    session.setAttribute("d",d);
	    redirectAttributes.addFlashAttribute("tinhSuccess", true);
		return "redirect:/home1";
	}
	@GetMapping("ky-hd1")
	public String kyHD(HttpSession session,RedirectAttributes redirectAttributes) {
		if(session.getAttribute("e")==null) {
			redirectAttributes.addFlashAttribute("kyhdFail", true);
			return "redirect:/home1";
		}
		return "kyhopdong";
	}
	@PostMapping("/ky1")
	public String kyHD(@ModelAttribute HopDong hopDong, HttpSession session) {
		BigInteger bamFile =  Utils.md(hopDong.getFileData()).abs();
		BigInteger priKey = Utils.enCrypt(bamFile, session);
		session.setAttribute("prikey", priKey);
		session.setAttribute("bamFile", bamFile);
		return "redirect:/ky-hd1";
	}

	@GetMapping("/reset")
	public String reset(HttpServletRequest request) {
		HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/home1";
	}
	

}
