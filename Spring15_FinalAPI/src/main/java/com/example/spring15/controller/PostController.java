package com.example.spring15.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring15.dto.CommentDto;
import com.example.spring15.dto.CommentListRequest;
import com.example.spring15.dto.PostDto;
import com.example.spring15.dto.PostListDto;
import com.example.spring15.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
public class PostController {
	
	@Autowired private PostService service;
	
	@PostMapping("/post/update-comment")
	@ResponseBody
	public Map<String, Boolean> updateComment(CommentDto dto){
		service.updateComment(dto);
		return Map.of("isSuccess", true);
	}
	
	@GetMapping("/post/delete-comment") 
	@ResponseBody 
	public Map<String, Boolean> deleteComment(long num){
		
		service.deleteComment(num);
		// @ResponseBody 어노테이션을 붙여 놓고 아래의 데이터를 리턴하면  {"isSuccess":true} 형식의 json 
		// 문자열이 응답된다.
		return Map.of("isSuccess", true);
	}
	
	@GetMapping("/post/comment-list")
	@ResponseBody 
	public Map<String, Object> commentList(CommentListRequest clr){
		//CommentListRequest 객체에는 댓글의 pageNum 과 원글의 글번호 postNum 이 들어 있다.
		
		return service.getComments(clr);
	}
	
	//댓글 저장 요청처리
	@PostMapping("/post/save-comment")
	@ResponseBody // dto 에 저장된 내용을 json 으로 응답하기 위한 어노테이션 
	public CommentDto saveComment(CommentDto dto) {
		service.createComment(dto);
		return dto;
	}
	
	//글 삭제 요청 처리
	@DeleteMapping("/posts/{num}")
	public PostDto delete(@PathVariable(value = "num") long num) {
		PostDto dto=service.getByNum(num);
		//서비스 객체를 이용해서 삭제하기 
		service.deletePost(num);
		//삭제된 글 정보를 리턴하기 
		return dto;
	}
	
	//글 수정 반영 요청처리
	@PostMapping("/post/update")
	public String update(PostDto dto, RedirectAttributes ra) {
		service.updatePost(dto);
		/*
		 *  RedirectAttributes 객체에 FlashAttribute 로 담은 내용은
		 *  redirect 이동된 요청을 처리하는 곳의 Model 객체에 자동으로 담긴다.
		 */
		ra.addFlashAttribute("updateMessage", dto.getNum()+" 번 글을 수정했습니다.");
		//수정 반영후 글 자세히 보기로 이동 
		return "redirect:/post/view?num="+dto.getNum();
	}
	
	//글 수정 폼 요청처리
	@GetMapping("/post/edit")
	public String edit(long num, Model model) {
		//수정할 글정보를 얻어와서 Model 객체에 담는다.
		PostDto dto=service.getByNum(num);
		model.addAttribute("dto", dto);
		return "post/edit";
	}
	
	@GetMapping("/post/new")
	public String newForm() {
		return "post/new";
	}
	
	@PostMapping("/posts")
	public Map<String, Object> save(@RequestBody PostDto dto){
		//새글을 저장하고 글번호를 리턴 받는다.
		long num=service.createPost(dto);
		
		return Map.of("num", num);
	}
	/*
	 *  dto 에 글번호만 있는 경우도 있고 
	 *  검색과 관련된 정보도 같이 있을수 있다. 
	 */
	@GetMapping("/posts/{num}")
	public PostDto view(@PathVariable(name = "num") long num, PostDto dto) {
		//글번호는 경로 변수에, 검색키워드가 있다면 해당 정보는 PostDto 객체에 담겨 있다.
		
		//글번호를 dto 에 담는다.
		dto.setNum(num);
		//글 자세한 정보를 얻어와서 
		PostDto resultDto=service.getDetail(dto);
		//응답한다.
		return resultDto;
	}
	
	/*
	 *  pageNum 이 파라미터로 넘어오지 않으면 pageNum 의 default 값은 1 로 설정 
	 *  검색 키워드도 파라미터로 넘어오면 PostDto 의 condition 과 keyword 가 null 이 아니다 
	 *  검색 키워드가 넘어오지않으면 PostDto 의 condition 과 keyword 는 null 이다 
	 */
	@GetMapping("/posts")
	public PostListDto list(@RequestParam(defaultValue = "1") int pageNum, PostDto search) {
		//글 목록과 검색 키워드 관련 정보가 들어 있는 PostListDto
		PostListDto dto=service.getPosts(pageNum, search);
		//JSON 문자열이 응답 되도록 dto 를 리턴한다. 
		return dto;
	}
}






