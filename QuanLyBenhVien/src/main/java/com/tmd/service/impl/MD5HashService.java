package com.tmd.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class MD5HashService {
	
	public boolean givenPassword_whenHashingUsingCommons_thenVerifying()  {
	    String hash = "f4c0c39390ec709e71e08e8cde2b0644";
	    String password = "duyanh123";

	    // handle input password
	    password = password.trim().replace("-", "");
	    
	    String md5Hex = DigestUtils
	      .md5Hex(password).toLowerCase();
	        
	    boolean result = md5Hex.equals(hash);
	    // System.out.println(result);
	    return result;
	}
}
