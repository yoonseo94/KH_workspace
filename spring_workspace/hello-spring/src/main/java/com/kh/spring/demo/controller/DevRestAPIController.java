package com.kh.spring.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.spring.demo.model.dto.Dev;
import com.kh.spring.demo.model.exception.DevNotFoundException;
import com.kh.spring.demo.model.service.DemoService;

import lombok.extern.slf4j.Slf4j;

/**
 * RestAPI
 * - Representational State Transfer
 * 
 * - 요청 성격별로 전송방식을 결정해서 사용하는 서비스
 * - c POST
 * - r GET
 * - u PUT / PATCH
 * - d DELETE
 * 
 * - url작성시에 명사형으로 계층구조를 갖도록 작성 
 * 		(동사를 사용하지 않는다)
 * 
 *
 */
@RequestMapping("/dev")
@Controller
@Slf4j
public class DevRestAPIController {

	@Autowired
	DemoService demoService;
	
	/**
	 * ResponseEntity<T>
	 * - body에 작성할 자바타입
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> dev(){
		List<Dev> list = null;
		try {
			list = demoService.selectDevList();
			log.debug("list = {}", list);
		} catch (Exception e) {
			log.error("Dev 목록 조회 오류", e);
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "Dev 목록 조회 오류");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		return ResponseEntity.ok(list);
	}
	
//	@GetMapping
//	@ResponseBody
//	public Object _dev(){
//		List<Dev> list = null;
//		try {
//			list = demoService.selectDevList();
//			log.debug("list = {}", list);
//		} catch (Exception e) {
//			log.error("Dev 목록 조회 오류", e);
//			Map<String, Object> map = new HashMap<>();
//			map.put("msg", "Dev 목록 조회 오류");
//			return map;
//		}
//		return list;
//	}
	
	@GetMapping("/{no}")
	public ResponseEntity<?> dev(@PathVariable int no){
		Dev dev = null;
		try {
			log.debug("no = {}", no);
			dev = demoService.selectOneDev(no);
			log.debug("dev = {}", dev);
			
			if(dev == null)
				throw new DevNotFoundException(String.valueOf(no));
			
		} catch (DevNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Dev 한명 조회 오류", e);
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "Dev 한명 조회 오류");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		
		return ResponseEntity.ok(dev);
	}
	
	
	/**
	 * @PathVariable 에 .이 포함된 경우 정규표현식으로 작성
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping("/email/{email:.+}")
	public ResponseEntity<?> dev(@PathVariable String email){
		Dev dev = null;
		try {
			log.debug("email = {}", email);
			// a. mvc요청 새로 만들기
//			dev = demoService.selectOneDevByEmail(email);
			
			// b. 전체목록에서 필터링
			List<Dev> list = demoService.selectDevList();
			for(Dev _dev : list) {
				if(email.equals(_dev.getEmail())) {
					dev = _dev;
					break;
				}
			}
			
			log.debug("dev = {}", dev);
			
			if(dev == null)
				throw new DevNotFoundException(email);
			
		} catch (DevNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error("Dev 한명 이메일 조회 오류", e);
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "Dev 한명 이메일  조회 오류");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.body(dev);
	}
	
	/**
	 * http://localhost:9090/spring/dev/lang/Java
	 * http://localhost:9090/spring/dev/lang/C
	 * http://localhost:9090/spring/dev/lang/Javascript
	 * 
	 * @param lang
	 * @return
	 */
	@GetMapping("/lang/{lang}")
	public ResponseEntity<?> selectDevListByLang(@PathVariable String lang){
		log.debug("lang = {}", lang);
		lang = lang.toLowerCase();
		List<Dev> resultList = new ArrayList<>();
		try {
			List<Dev> list = demoService.selectDevList();
			for(Dev dev : list) {
				String[] langs = dev.getLang();
				List<String> langList = new ArrayList<>();
				for(String _lang : langs) 
					langList.add(_lang.toLowerCase());
					
				if(langList.contains(lang)) {
					resultList.add(dev);
				}
			}
			
			if(resultList.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
		}
		catch(Exception e) {
			log.error("언어별 개발자 조회 오류", e);
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "언어별 개발자 조회 오류");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.body(resultList);
	}
	
}










