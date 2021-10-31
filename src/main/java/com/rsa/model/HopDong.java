package com.rsa.model;

import java.math.BigInteger;

import org.springframework.web.multipart.MultipartFile;

public class HopDong {
	
	private MultipartFile fileData;
	private BigInteger key;
	private BigInteger e;
	private BigInteger n;
	
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
	public MultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}
	public BigInteger getKey() {
		return key;
	}
	public void setKey(BigInteger key) {
		this.key = key;
	}
}
