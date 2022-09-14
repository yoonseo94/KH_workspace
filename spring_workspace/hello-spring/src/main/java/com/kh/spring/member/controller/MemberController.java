package com.kh.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

// @Controller
@RequestMapping("/member")
@Slf4j
@SessionAttributes({"loginMember", "next"})
public class MemberController {

	// private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	// @RequestMapping(path="/memberEnroll.do", method=RequestMethod.GET)
	@GetMapping("/memberEnroll.do")
	public String memberEnroll() {
		
		return "member/memberEnroll";
	}
	
	/**
	 * $2a$10$oDUuqgA16JOHn39iSwq.GeIYzPvj5MyjlA01buPbC6K.ikxFobXGi
	 * 
	 * - 알고리즘 $2a$
	 * - 옵션 10$ 속도/메모리 요구량 
	 * - 랜덤솔트 22자리 oDUuqgA16JOHn39iSwq.Ge
	 * - 해시 31자리 IYzPvj5MyjlA01buPbC6K.ikxFobXGi
	 * 
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
	
	
	/**
	 * VimeNameTranslator빈
	 * - 핸들러의 리턴타입이 void라면 요청주소를 바탕으로 viewName을 유추
	 * - /member/memberLogin.do -> member/memberLogin -> /WEB-INF/views/member/memberLogin.jsp
	 *  
	 */
	@GetMapping("/memberLogin.do")
	public void memberLogin(
			@RequestHeader(name = "Referer", required = false) String referer,
			@SessionAttribute(required = false) String next, 
			Model model) {
		log.info("referer = {}", referer);
		log.info("next = {}", next);
		
		if(next == null && referer != null)
			model.addAttribute("next", referer);
		
	}
	
	@PostMapping("/memberLogin.do")
	public String memberLogin(
			@RequestParam String memberId,
			@RequestParam String password,
			RedirectAttributes redirectAttr,
			@SessionAttribute(required = false) String next, 
			HttpSession session,
			Model model) {
			log.info("memberId = {}, password = {}", memberId, password);
			
			try {
				Member member = memberService.selectOneMember(memberId);
				log.info("member = {}", member);
				
				if(member != null && bcryptPasswordEncoder.matches(password, member.getPassword())) {
//					redirectAttr.addFlashAttribute("msg", "로그인 성공!");
					
					model.addAttribute("loginMember", member);
					log.info("next = {}", next);
					
					String location = next != null ? next : "/";
					return "redirect:" + location;
				}
				else {
					redirectAttr.addFlashAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
					return "redirect:/member/memberLogin.do";
				}
			} 
			catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
	}
	
	@GetMapping("/memberLogout.do")
	public String memberLogout(SessionStatus sessionStatus, ModelMap modelMap) {
		// modelMap 속성 완전 제거
		modelMap.clear();
		
		// 사용완료 마킹처리 (세션객체 자체를 폐기하지 않는다.)
		if(!sessionStatus.isComplete())
			sessionStatus.setComplete();
		
		return "redirect:/";
	}
	
	@GetMapping("/memberDetail.do")
	public void memberDetail() {
		
	}
	
	@PostMapping("/memberUpdate.do")
	public String memberUpdate(
					@ModelAttribute("loginMember") Member loginMember, 
					RedirectAttributes redirectAttr, 
					Model model) {
		try {
			
			log.info("loginMember = {}", loginMember);
			int result = memberService.updateMember(loginMember);
			
			// 세션 정보 갱신
//			model.addAttribute("loginMember", memberService.selectOneMember(member.getMemberId()));
			
			redirectAttr.addFlashAttribute("msg", "회원정보를 성공적으로 변경했습니다");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "redirect:/member/memberDetail.do";
	}
	
	/**
	 * ajax1
	 * - BeanNameViewResolver + jsonView
	 * - jsonView : model에 저장된 속성을 json으로 변환, 응답메세지에 작성
	 * 
	 * @return
	 */
	// @GetMapping("/checkIdDuplicate.do")
	public String checkIdDuplicate(@RequestParam String memberId, Model model) {
		try {
			Member member = memberService.selectOneMember(memberId);
			boolean available = member == null;
			
			model.addAttribute("memberId", memberId);
			model.addAttribute("available", available);
			
		} catch (Exception e) {
			log.error("중복아이디 체크 오류", e);
			throw e;
		}
		return "jsonView";
	}
	
	/**
	 * ajax2
	 * @ResponseBody + MessageConverter
	 * - 리턴객체를 특정타입(json)으로 변환해주는 빈 - jaskson
	 * - @ResponseBody 핸들러의 리턴객체를 직접 응답메세지에 출력
	 * 
	 * @param memberId
	 * @param model
	 * @return
	 */
//	@GetMapping("/checkIdDuplicate.do")
	@ResponseBody
	public Map<String, Object> checkIdDuplicate2(@RequestParam String memberId) {
		Map<String, Object> map = new HashMap<>();
		try {
			Member member = memberService.selectOneMember(memberId);
			boolean available = member == null;
			
			map.put("memberId", memberId);
			map.put("available", available);
			
		} catch (Exception e) {
			log.error("중복아이디 체크 오류", e);
			throw e;
		}
		return map;
	}
	
	
	/**
	 * ajax3
	 * ResponseEntity
	 * - 응답메세지 작성을 도와주는 객체. status, header, body 자유롭게 작성가능
	 * - @ResponseBody 포함
	 * - 기본적으로 json에 반환
	 * 
	 * @param memberId
	 * @return
	 */
	@GetMapping("/checkIdDuplicate.do")
	public ResponseEntity<?> checkIdDuplicate3(@RequestParam String memberId) {
		Map<String, Object> map = new HashMap<>();
		try {
			
			if(true)
				throw new RuntimeException("중복아이디 체크 오류!");
			
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
	
	
	
}



