package com.jung.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jung.domain.MemberBean;
		
public class RegisterRequestValidator implements Validator{
	@Inject
	MemberService service;
	MemberServiceImple si = new MemberServiceImple();
	private static final String emailRegExp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String passRegExp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~,!,@,#,$,*,(,),=,+,_,.,|]).{8,20}$";
	private static final String idRegExp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{5,10}$";

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberBean.class.isAssignableFrom(clazz); //파라미터로 받은 clazz타입의 객체가 MemberBean클 여기서는 supports() 메서드를 사용하지 않지만
		//클래스로 타입 변환이 가능한지 확인. 
		//여기서는 supports()메서드를 사용 안하지만 
		//스프링 MVC가 자동으로 검증을 수행하도록 설정하는 경우 supports()메서드를 올바르게 구현해주어야 한다.
	}

	@Override
	public void validate(Object target, Errors errors){
		MemberBean mb = (MemberBean)target;
		
		if(mb.getEmail()==null || mb.getEmail().trim().isEmpty()){
			errors.rejectValue("email", "required");
		}else if(!Pattern.matches(emailRegExp, mb.getEmail())){
			 errors.rejectValue("email", "bad");
		}
		
		if(mb.getId()==null || mb.getId().trim().isEmpty()){
			errors.rejectValue("id", "required");
		}else if(!Pattern.matches(idRegExp, mb.getId())){
			 errors.rejectValue("id", "bad");
		}else{
			try {
				if(service.dupIdCheck(mb.getId())) errors.rejectValue("id", "duplicate");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(mb.getPass()==null || mb.getPass().trim().isEmpty()){
			errors.rejectValue("pass", "required");
		}else{
			if(!Pattern.matches(passRegExp, mb.getPass())) errors.rejectValue("pass", "bad");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "required");
		
	}


}
