package service;

import java.util.List;

import domain.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	MemberVO login(MemberVO mvo);

	int lastLogin(String id);

	int edit(MemberVO mvo);

	int delete(String id);

	List<MemberVO> showlist();

	int dupcheck(String id);

}
