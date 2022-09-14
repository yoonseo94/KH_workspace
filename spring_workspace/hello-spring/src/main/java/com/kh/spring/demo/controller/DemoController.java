package com.kh.spring.demo.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.demo.model.dto.Dev;
import com.kh.spring.demo.model.dto.Gender;
import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.member.model.dto.Member;

/**
 * @Controller 클래스의 handler메소드가 가질수 있는 매개변수 타입
 * 
 * HttpServletRequest
 * HttpServletResponse
 * HttpSession
 * 
 * java.util.Locale : 요청에 대한 Locale
 * InputStream/Reader : 요청에 대한 입력스트림
 * OutputStream/Writer : 응답에 대한 출력스트림. ServletOutputStream, PrintWriter
 * 
 * 사용자입력값처리
 * Command객체 : http요청 파라미터를 커맨드객체에 저장한 VO객체
 * CommandMap :  HandlerMethodArgumentResolver에 의해 처리된 사용자입력값을 가진 Map객체
 * @Valid : 커맨드객체 유효성 검사객체
 * Error, BindingResult : Command객체에 저장결과(Command객체 바로 다음위치시킬것.)
 * @PathVariable : 요청url중 일부를 매개변수로 취할 수 있다.
 * @RequestParam : 사용자입력값을 자바변수에 대입처리(필수여부 설정)
 * @RequestHeader : 헤더값
 * @CookieValue : 쿠키값
 * @RequestBody : http message body에 작성된 json을 vo객체로 변환처리
 * 
 * 뷰에 전달할 모델 데이터 설정
 * ModelAndView
 * ModelMap 
 * Model
 
 * @ModelAttribute : model속성에 대한 getter
 * @SessionAttribute : session속성에 대한 getter(required여부 선택가능)
 * 
 * SessionStatus: @SessionAttributes로 등록된 속성에 대하여 사용완료(complete)처리. 세션을 폐기하지 않고 재사용한다.
 * (@SessionAttributes : session에서 관리될 속성명을 class-level에 작성)
 * 
 * 기타
 * MultipartFile : 업로드파일 처리 인터페이스. CommonsMultipartFile
 * RedirectAttributes : DML처리후 요청주소 변경을 위한 redirect시 속성처리 지원
 *
 *
 *
 * 모델 
 * - view단에서 처리할 데이터저장소
 * - Map객체
 * 
 * - ModelAndView 클래스: model + view
 * 		- 속성 addObject(String, Object)
 * 		- 뷰 setViewName(String)
 * 
 * - ModelMap 클래스: model
 * 		- 속성 addAttribute(String, Object)
 * 		- 뷰 별도의 String반환
 * 
 * - Model 인터페이스
 * 		- 속성 addAttribute(String, Object)
 * 		- 뷰 별도의 String반환
 *
 *
 * @ModelAttribute method 레벨 
 * 	- 특정 @Controller 하위에서 모든 요청에 대한 공통속성을 정의
 * @ModelAttribute parameter 레벨 
 * 	- 특정 model 속성에 대한 getter
 *  - name과 일치하는 속성이 없다면, 새로운 속성으로 등록. (Command객체는 암묵적 Model속성으로 등록)
 */

@Controller
@RequestMapping("/demo")
public class DemoController {

	static Logger log = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private DemoService demoService;

	
	@ModelAttribute("common")
	public Map<String, Object> common(){
		log.info("common 호출!");
		Map<String, Object> map = new HashMap<>();
		map.put("adminEmail", "admin@kh.or.kr");
		map.put("adminPhone", "070-123-4567");
		return map;
	}
	
	/**
	 * 전송방식 GET(기본값)
	 * 
	 * @return
	 */
	@RequestMapping("/devForm.do")
	public String devForm() {
		log.info("GET /demo/devForm.do 요청");
		log.info("{}", 1234567890);
		return "demo/devForm";
	}

	@RequestMapping("/dev1.do")
	public String dev1(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		int career = Integer.parseInt(request.getParameter("career"));
		String email = request.getParameter("email");
		String _gender = request.getParameter("gender");
		Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
		String[] langs = request.getParameterValues("lang");

		Dev dev = new Dev(0, name, career, email, gender, langs, LocalDateTime.now());
		log.info("dev = {}", dev);

		request.setAttribute("dev", dev);

		return "demo/devResult";
	}

	@RequestMapping(value = "/dev2.do", method = RequestMethod.GET)
	public String dev2(
			@RequestParam String name, 
			@RequestParam int career, 
			@RequestParam String email,
			@RequestParam(required = false, defaultValue = "M") Gender gender,
			@RequestParam(name = "lang") String[] langs, 
			Model model) {

		Dev dev = new Dev(0, name, career, email, gender, langs, LocalDateTime.now());
		log.info("dev = {}", dev);

		model.addAttribute("dev", dev); // view단에 전달한 데이터를 모델에 속성으로 등록

		return "demo/devResult";
	}

	/**
	 * 커맨드 객체 - 전송된 사용자입력값과 대응하는 필드에 자동으로 값대입 - 커맨드객체는 자동으로 model에 속성으로 등록
	 * 
	 * @param dev
	 * @return
	 */
	@RequestMapping("/dev3.do")
	public String dev3(@ModelAttribute("dev") Dev dev) {
		log.info("dev = {}", dev);
		return "demo/devResult";
	}

	@RequestMapping(path = "/insertDev.do", method = RequestMethod.POST)
	public String insertDev(Dev dev, RedirectAttributes redirectAttr) {
		log.info("dev = {}", dev);

		int result = demoService.insertDev(dev);
		log.info("result = {}", result);

		// redirect후에 사용자가 확인할수 있는 정보 저장
		redirectAttr.addFlashAttribute("msg", "Dev 등록이 정상적으로 처리되었습니다.");

		return "redirect:/demo/devForm.do";
	}

	@RequestMapping("/devList.do")
	public String devList(Model model) {
		List<Dev> list = demoService.selectDevList();
		log.info("list = {}", list);
		model.addAttribute("list", list);
		return "demo/devList";
	}

	@RequestMapping(path = "/updateDev.do", method = RequestMethod.GET)
	public String updateDev(@RequestParam int no, Model model) {
		// 사용자입력한 no값으로 Dev 한건 조회후 view단 전달
		Dev dev = demoService.selectOneDev(no);
		log.info("dev = {}", dev);
		model.addAttribute("dev", dev);
		return "demo/devUpdateForm";
	}
	
	@RequestMapping(path = "/updateDev.do", method = RequestMethod.POST)
	public String updateDev(Dev dev, RedirectAttributes redirectAttr) {
		int result = demoService.updateDev(dev);
		redirectAttr.addFlashAttribute("msg", "성공적으로 Dev정보를 수정했습니다.");
		return "redirect:/demo/updateDev.do?no=" + dev.getNo();
	}
	
	@RequestMapping(path="/deleteDev.do", method=RequestMethod.POST)
	public String deleteDev(@RequestParam int no, RedirectAttributes redirectAttr) {
		int result = demoService.deleteDev(no);
		redirectAttr.addFlashAttribute("msg", "성공적으로 Dev를 삭제했습니다.");
		return "redirect:/demo/devList.do";
	}
	
	@GetMapping("/loginMember")
	public String loginMember(@SessionAttribute Member loginMember, HttpSession session) {
		log.info("loginMember = {}", loginMember);
		session.removeAttribute("loginMember");
		log.info("session.loginMember = {}", session.getAttribute("loginMember"));
		return "redirect:/";
	}

}
