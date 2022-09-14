package com.kh.spring.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.chat.model.dto.ChatMember;
import com.kh.spring.chat.model.service.ChatService;
import com.kh.spring.email.model.service.EmailService;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberSecurityController {

	@Autowired
	ChatService chatService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/memberEnroll.do")
	public void memberEnroll() {}
	
	/**
	 * 
	 * @param member
	 * @param redirectAttr
	 * @return
	 */
	@PostMapping("/memberEnroll.do")
	public String memberEnroll(Member member, RedirectAttributes redirectAttr) {
		log.info("member = {}", member);
		try {
			// 암호화처리
			String rawPassword = member.getPassword();
			String encryptedPassword = bcryptPasswordEncoder.encode(rawPassword);
			member.setPassword(encryptedPassword);
			log.info("encryptedPassword = {}", encryptedPassword);
			
			// service에 insert요청
			int result = memberService.insertMember(member);
			
			// 사용자 처리 피드백
			redirectAttr.addFlashAttribute("msg", "성공적으로 회원가입했습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "redirect:/";
	}
	
	@GetMapping("/memberLogin.do")
	public void memberLogin() {}
	
	/**
	 * 로그인 성공시 후처리
	 * @param session
	 * @param model
	 * @return
	 */
	@PostMapping("/loginSuccess.do")
	public String loginSuccess(@AuthenticationPrincipal Member member, HttpSession session, Model model) {
		log.debug("loginSuccess");

		List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) member.getAuthorities();
		// 관리자가 아닌 경우에만 안읽은 메세지수 체크
		if(!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {			
			// 관리자와의 1:1채팅 안읽은 메세지 카운팅
			ChatMember chatMember = chatService.findChatMemberByMemberId(member.getMemberId());
			if(chatMember != null) {
				int unreadCount = chatService.getUnreadCount(chatMember);
				log.debug("unreadCount = {}", unreadCount);
				// 세션스코프에 저장
				session.setAttribute("unreadCount", unreadCount);
			}
		}
		
		// security redirect사용하기
		SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		String location = "/";
		if(savedRequest != null)
			location = savedRequest.getRedirectUrl();
		
		log.debug("location = {}", location);
		
		return "redirect:" + location;
	}
	
	
	@GetMapping("/checkIdDuplicate.do")
	public ResponseEntity<?> checkIdDuplicate3(@RequestParam String memberId) {
		Map<String, Object> map = new HashMap<>();
		try {
			Member member = memberService.selectOneMember(memberId);
			boolean available = member == null;
			
			map.put("memberId", memberId);
			map.put("available", available);
			
		} catch (Exception e) {
			log.error("중복아이디 체크 오류", e);
			// throw e;
			
			map.put("error", e.getMessage());
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
					.body(map);
			
		}
//		return ResponseEntity.ok(map); // 200 + body에 작성할 맵
		return ResponseEntity
				.status(HttpStatus.OK)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.body(map);
				
	}
	
	/**
	 * SecurityContextHolder - SecurityContext - Authentication에 보관중인 로그인한 사용자 정보 가져오기
	 * 
	 * - Principal
	 * - Credentials
	 * - Authorities
	 * 
	 * 1. SecurityContextHolder로 부터 Authentication 가져오기
	 * 2. 핸들러의 인자로 Authentication 받기
	 * 3. @AuthenticationPrincipal 통해서 Principal객체 받기
	 * 
	 */
	@GetMapping("/memberDetail.do")
//	public void memberDetail(Authentication authentication) {
////		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Member principal = (Member) authentication.getPrincipal();
//		log.debug("principal = {}", principal); // Member(super=MemberEntity(memberId=honggd, password=$2a$10$oDUuqgA16JOHn39iSwq.GeIYzPvj5MyjlA01buPbC6K.ikxFobXGi, name=홍길동동, gender=M, birthday=2000-09-09, email=honggd@naver.com, phone=01012341234, address=null, hobby=[운동, 등산, 독서, 게임, 여행], createdAt=2022-07-11T14:12:38, updatedAt=2022-07-12T11:27:56, enabled=true), authorities=[ROLE_USER])
//		
//		Object credentials = authentication.getCredentials();
//		log.debug("credentials = {}", credentials); // null
//		
//		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();
//		log.debug("authorities = {}", authorities); // [ROLE_USER]
//	}
	public void memberDetail(@AuthenticationPrincipal Member member) {
		log.debug("member = {}", member);
	}
	
	
	@PostMapping("/memberUpdate.do")
	public ResponseEntity<?> memberUpdate(Member updateMember, @AuthenticationPrincipal Member loginMember){
		log.debug("updateMember = {}", updateMember);
		log.debug("loginMember = {}", loginMember);
		
		Map<String, Object> map = new HashMap<>();
		
		try {
			// 1. db갱신
			int result = memberService.updateMember(updateMember);
			
			// 2. security가 관리하는 session 사용자정보 업데이트
			loginMember.setName(updateMember.getName());
			loginMember.setBirthday(updateMember.getBirthday());
			loginMember.setEmail(updateMember.getEmail());
			loginMember.setPhone(updateMember.getPhone());
			loginMember.setAddress(updateMember.getAddress());
			loginMember.setGender(updateMember.getGender());
			loginMember.setHobby(updateMember.getHobby());
			
			// 비밀번호/권한정보가 바뀌었을때는 전체 Authentication을 대체
			Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
														loginMember, loginMember.getPassword(), loginMember.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(newAuthentication);
			
			
			map.put("msg", "회원 정보를 성공적으로 수정했습니다.");
		} catch (Exception e) {
			log.error("회원정보 수정 오류!", e);
			map.put("msg", "회원정보 수정 오류!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/findMemberId.do")
	public void findMemberId() {

	}
	
	@PostMapping("/findMemberId.do")
	public String findMemberId(@RequestParam String email, Model model) {
		log.debug("email = {}", email);
		
		// 1. 이메일로 member 조회 select * from member where email = #{email}
		Member member = memberService.findByEmail(email);
		log.debug("member = {}", member);
		
		// 2.a member가 있는 경우
		if(member != null) {
			// memberId를 노출 & jsp사용
			model.addAttribute("memberId", member.getMemberId());
						
		}
		// 2.b member가 없는 경우
		else {
			// 해당회원이 존재하지않음 경고처리
		}
		
		return "member/findMemberIdResult";
		
	}
	
	/**
	 * 아이디/이메일 입력폼 요청
	 */
	@GetMapping("/findPassword.do")
	public void findPassword() {

	}
	
	@PostMapping("/findPassword.do")
	public String findPassword(Member member, Model model) {
		log.debug("member = {}", member);
		// 1. 회원조회 memberId/email
		Member retrievedMember = memberService.findByMemberIdAndEmail(member);
		log.debug("retrievedMember = {}", retrievedMember);
		
		// 2. 조회 성공시
		if(retrievedMember != null) {
			// 임시비밀번호 생성 및 BCryptPasswordEncoder 해싱
			String tempPassword = UUID.randomUUID().toString();
			log.debug("tempPassword = {}", tempPassword);
			String encodedPassword = bcryptPasswordEncoder.encode(tempPassword);
			member.setPassword(encodedPassword);
			int result = memberService.updatePassword(member);
			
			// 메일발송
			emailService.sendMail(member.getEmail(), "[hello-spring] 임시비밀번호 발송", tempPassword);
			
			model.addAttribute("result", "success");
			model.addAttribute("resultMsg", "비밀번호를 재설정했습니다. 임시 비밀번호를 " + member.getEmail() + "에서 확인하세요😊");
		}
		// 3. 조회 실패시
		else {
			model.addAttribute("result", "failure");
			model.addAttribute("resultMsg", "해당 회원을 찾을 수 없습니다.");
		}
		
		return "member/findPasswordResult";
	}
	
	
}
