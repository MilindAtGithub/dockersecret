
package com.milind.dockersecret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CaptureSecretKeyController {

	@Autowired
	Password password;

	@RequestMapping(value="/captureSecretPwd",method=RequestMethod.POST)
	public String captureSecret(@RequestBody String req){
		return  EncryptUtil.encrypt(req);
	}

	@RequestMapping(value="/getpwd",method=RequestMethod.GET)
	public String getPwd() throws IOException {
		//String content = new String(Files.readAllBytes(Paths.get(password.getPassword())));
		return "Password: "+password.getPassword();
	}
}

