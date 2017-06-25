package com.jung.service;

import com.jung.domain.MemberBean;

public interface MemberService {
	public void insertMember(MemberBean mb) throws Exception;
	public MemberBean getInfo(String id) throws Exception;
	public boolean matches(CharSequence inputPass, String encodedPass) throws Exception;
	public String getPass(String id) throws Exception;
	public void updateMember(MemberBean mb) throws Exception;
	public void deleteMember(String id) throws Exception;
}
