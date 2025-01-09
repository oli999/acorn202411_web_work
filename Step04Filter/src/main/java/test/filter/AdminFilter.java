package test.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AdminFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//HttpServletRequest 객체의 참조값 
		HttpServletRequest request=(HttpServletRequest)req;
		//HttpSession 객체의 참조값
		HttpSession session=request.getSession();
		//session scope 저장된 아이디 값 읽어오기
		String id=(String)session.getAttribute("id");
		if(id != null) {
			//DB 에서 해당 아이디의 권한이 관리자 인지 읽어와 본다.
			if(id.equals("kimgura") || id.equals("superman")) {
				//관리자가 맞다면 요청의 흐름을 이어간다.
				chain.doFilter(req, resp);
				//여기서 메소드 끝내기 
				return;
			}
		}
		//session 에 저장된 아이디가 없거나 관리자가 아니라면 에러를 응답한다.
		HttpServletResponse response=(HttpServletResponse)resp;
		// 403 에러 응답
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

}









