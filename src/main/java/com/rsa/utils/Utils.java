package com.rsa.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	private static int BUFFER_SIZE = 32 * 1024;
	
	public static BigInteger taoSoNguyenTo() {
		SecureRandom r = new SecureRandom();
		BigInteger p = new BigInteger(256/2, 100, r);
		return p;
	}

	public static BigInteger soE(BigInteger m) {
		SecureRandom r = new SecureRandom();
		BigInteger e;
		boolean found = false;
        do {
             e = new BigInteger(256/2, 50, r);
            if (m.gcd(e).equals(BigInteger.ONE) && e.compareTo(m) < 0) {
                found = true;
            }
        } while (!found);
		return e;
	}

	public static BigInteger md(MultipartFile fileData) {
		
			MessageDigest md = null;
			try {
				InputStream file =  new BufferedInputStream(fileData.getInputStream());
				md = MessageDigest.getInstance("SHA-256");
				DigestInputStream in = new DigestInputStream(file, md);
				int i;
			    byte[] buffer = new byte[BUFFER_SIZE];
			    do {
			      i = in.read(buffer, 0, BUFFER_SIZE);
			    } while (i == BUFFER_SIZE);
			    md = in.getMessageDigest();
			    in.close();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		    return new BigInteger(md.digest());
	}

	public static BigInteger enCrypt(BigInteger bamFile, HttpSession session) {
		
		BigInteger d = (BigInteger) session.getAttribute("d");
		BigInteger n = (BigInteger) session.getAttribute("n");
		
		return bamFile.modPow(d, n);
	}
}
