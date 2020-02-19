package jp.co.sample.emp_management.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check-password")
public class PasswordCheckController {
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, String> passwordcheck(String password, String confirmPassword){
		
	Map<String, String> map = new HashMap<>();
	String errorMessage = null;
	if(password != null && confirmPassword.equals(password)) {
		errorMessage = "「確認用パスワード入力OK!」";
	}else {
		errorMessage = "「パスワードが一致していません」";
	}
	map.put("errorMessage", errorMessage);
	return map;
	}

}
