package com.jung.web;



import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jung.domain.MemberBean;
import com.jung.persistence.MemberDAO;
import com.jung.service.CustomUserDetails;
import com.jung.service.MemberService;
import com.jung.service.RegisterRequestValidator;


@Controller
@RequestMapping(value="/member/*")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService service;
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insertGet() throws Exception{
		logger.info("/member/insert get => /member/insertForm 이동");
		return "/member/insertForm";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertPost(MemberBean mb, Errors error, Model model) throws Exception{
		new RegisterRequestValidator().validate(mb, error);
		if(error.hasErrors()){
			model.addAttribute("mb", mb);
			return "/member/insertForm";
		}else{
			System.out.println("nothingWrong");
			service.insertMember(mb);
			return "redirect:/member/login";
		}
	}
	
	@RequestMapping(value="/dupIdCheck", method=RequestMethod.GET)
	public String dupIdCheckGet(boolean dupCheck, Model model) throws Exception{
		model.addAttribute("dupCheck", dupCheck);
		return "/member/dupIdCheck";
	}
	
	@RequestMapping(value="/dupIdCheck", method=RequestMethod.POST)
	public String dupIdCheckPost(MemberBean mb, Model model) throws Exception{
		model.addAttribute("dupCheck", service.dupIdCheck(mb.getId())) ;
		return "redirect:/member/dupIdCheck";
	}
	
	@RequestMapping(value="/login", method={RequestMethod.GET,RequestMethod.POST})
	public String loingGet() throws Exception{
		logger.info("/member/login get => /member/loginForm 이동");
		return "/member/loginForm";
	}
	
	@RequestMapping(value="/login_success", method={RequestMethod.GET,RequestMethod.POST})
	public String loginPost(MemberBean mb, HttpSession session, HttpServletResponse response) throws Exception{
		logger.info("login Post이동");
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
		session.setAttribute("id", userDetails.getUsername());
			
		return "redirect:/member/main";
	}
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String mainGet(HttpSession session, Model model) throws Exception{
		model.addAttribute("id", session.getAttribute("id"));
		return "/member/main";
	}
	
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public String infoGet(HttpSession session, Model model) throws Exception{
		String id = (String)session.getAttribute("id");
		MemberBean mb = service.getInfo(id);
		model.addAttribute("mb", mb);
		
		return "/member/info";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String updateGet(HttpSession session, Model model) throws Exception{
		String id = (String)session.getAttribute("id");
		MemberBean mb = service.getInfo(id);
		if(mb.getGender() == null){
			mb.setGender("남");
		}
		
		model.addAttribute("mb", mb);
		return "/member/updateForm";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updatePost(HttpSession session, MemberBean mb, Model model) throws Exception{
		String id = (String)session.getAttribute("id");
		String encodedPass = service.getPass(id);
		boolean isMatch = service.matches(mb.getPass(), encodedPass);
		String url = "";
		String mesg = "";
		if(isMatch){
			service.updateMember(mb);
			url = "ok";
			mesg = "업데이트에 성공하였습니다";
		}else{
			url = "notOk";
			mesg = "비밀번호를 다시한번 확인해주세요.";
		}
		
		model.addAttribute("url", url);
		model.addAttribute("mesg", mesg);
		return "/member/updateCheck";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteGet(HttpSession session, Model model) throws Exception{
		String id = (String)session.getAttribute("id");
		model.addAttribute("id", id);
		return "/member/deleteForm";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deletePost(HttpSession session, MemberBean mb, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String id = (String)session.getAttribute("id");
		String encodedPass = service.getPass(id);
		boolean isMatch = service.matches(mb.getPass(), encodedPass);
		String url = "";
		String mesg = "";
		if(isMatch){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(id != null){
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			service.deleteMember(id);
			url = "ok";
			mesg = "성공적으로 회원탈퇴 되었습니다.";
		}else{
			url = "notOk";
			mesg = "비밀번호를 확인해주세요.";
		}
		
		model.addAttribute("url", url);
		model.addAttribute("mesg", mesg);
		
		return "/member/deleteCheck";
	}
	
	@RequestMapping(value="/login_duplicate", method=RequestMethod.GET)
	public String loginDuplicate() throws Exception{
		System.out.println("Dubplicated");
		return "/member/loginDuplicate";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listGet(Model model) throws Exception{
		List<MemberBean> list = service.getList();
		model.addAttribute("list", list);
		return "/member/list";
	}
}
