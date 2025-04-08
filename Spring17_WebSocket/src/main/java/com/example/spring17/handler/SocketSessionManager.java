package com.example.spring17.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import java.util.*;


@Component
public class SocketSessionManager {
	// Thread Safe 한 동기화된 리스트 객체 사용하기 
	List<WebSocketSession> sessionList=Collections.synchronizedList(new ArrayList<>());
	
	public void register(WebSocketSession session) {
		sessionList.add(session);
	}
	
	public void remove(WebSocketSession session) {
		sessionList.remove(session);
	}
	
	public List<WebSocketSession> getSessions(){
		return sessionList;
	}
}



