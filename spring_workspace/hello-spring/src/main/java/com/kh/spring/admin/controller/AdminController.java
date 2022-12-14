package com.kh.spring.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.chat.model.dto.ChatLog;
import com.kh.spring.chat.model.service.ChatService;
import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ChatService chatService;
	
	
	
	@GetMapping("/chatList.do")
	public void chatList(Model model) {
		List<ChatLog> list = chatService.findRecentChatLogList();
		log.debug("list = {}", list);
		model.addAttribute("list", list);
	}
	
	@GetMapping("/{chatroomId}/{memberId}/chat.do")
	public String chat(
			@PathVariable String chatroomId, 
			@PathVariable String memberId, 
			Model model) {
		log.debug("chatroomId = {}", chatroomId);
		log.debug("memberId = {}", memberId);
		
		List<ChatLog> list = chatService.findChatLogByChatroomId(chatroomId);
		model.addAttribute("list", list);
		model.addAttribute("memberId", memberId);
		model.addAttribute("chatroomId", chatroomId);
		
		return "admin/chat";
	}
	

	@GetMapping("/memberList.do")
	public void memberList(Model model) {
		try {
			List<Member> list = memberService.selectMemberList();
			log.debug("list = {}", list);
			
			model.addAttribute("list", list);
		} catch (Exception e) {
			log.error("???????????? ??????", e);
			throw e;
		}
	}
	
	/**
	 * @RequestBody
	 * - ??????????????? body??? ?????? json???????????? ???????????? DI??????.
	 * 
	 * @param member
	 * @return
	 */
	@PostMapping("/memberRoleUpdate.do")
	public ResponseEntity<?> memberRoleUpdate(@RequestBody Member member){
		Map<String, Object> map = new HashMap<>();
		
		log.debug("member = {}", member);
		
		// List<SimpleGrantedAuthority> -> List<String>
		List<String> authorities = new ArrayList<>();
		for(GrantedAuthority auth : member.getAuthorities()) {
			authorities.add(auth.getAuthority());
		}
		
		try {
			int result = memberService.updateMemberRole(member.getMemberId(), authorities);
			map.put("msg", "??????????????? ??????????????? ??????????????????.");
		}
		catch(Exception e) {
			log.error("????????? ???????????? ?????? ??????", e);
			map.put("msg", "????????? ???????????? ?????? ??????");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		
		return ResponseEntity.ok(map);
	}	
	
//	@PostMapping("/memberRoleUpdate.do")
//	public ResponseEntity<?> memberRoleUpdate(
//			@RequestParam String memberId, 
//			@RequestParam("authorities[]") List<String> authorities){
//		Map<String, Object> map = new HashMap<>();
//		
//		log.debug("memberId = {}", memberId);
//		log.debug("authorities = {}", authorities);
//		
//		try {
//			int result = memberService.updateMemberRole(memberId, authorities);
//			map.put("msg", "??????????????? ??????????????? ??????????????????.");
//		}
//		catch(Exception e) {
//			log.error("????????? ???????????? ?????? ??????", e);
//			map.put("msg", "????????? ???????????? ?????? ??????");
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
//		}
//		
//		return ResponseEntity.ok(map);
//	}	
}




