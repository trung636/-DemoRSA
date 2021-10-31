package com.rsa.model;

import java.math.BigInteger;

import javax.servlet.http.HttpSession;

public class TaoKhoa {

	private BigInteger snt1;
	private BigInteger snt2;
	private BigInteger e;
	private BigInteger n;
	private BigInteger d;
	
	public BigInteger getSnt1() {
		return snt1;
	}
	public void setSnt1(BigInteger snt1) {
		this.snt1 = snt1;
	}
	public BigInteger getSnt2() {
		return snt2;
	}
	public void setSnt2(BigInteger snt2) {
		this.snt2 = snt2;
	}
	public BigInteger getE() {
		return e;
	}
	public void setE(BigInteger e) {
		this.e = e;
	}
	public BigInteger getN() {
		return n;
	}
	public void setN(BigInteger n) {
		this.n = n;
	}
	public BigInteger getD() {
		return d;
	}
	public void setD(BigInteger d) {
		this.d = d;
	}
	public void setSesssion(HttpSession session) {
		session.setMaxInactiveInterval(864000);
		session.setAttribute("snt", snt1);
		session.setAttribute("snt2", snt2);
		session.setAttribute("e", e);
		session.setAttribute("n", n);
		session.setAttribute("d", d);
	}
	

}
