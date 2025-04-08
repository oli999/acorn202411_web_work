package com.example.spring17.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class SocketSessionManager {
	// Thread Safe 한 동기화된 리스트 객체 사용하기 
	List<WebSocketSession> sessionList=Collections.synchronizedList(new ArrayList<>());
	/*
	 *  userName <=> SocketSession  를 저장하기 위한 Map
	 *  ConcurrentHashMap 객체도 Thread Safe 한 동기화된 Map 객체 
	 */
	Map<String, WebSocketSession> userSessions=new ConcurrentHashMap<>();
	Map<WebSocketSession, String> sessionUsers=new ConcurrentHashMap<>();
	
	//대화방에 참여한 user 의 session 을 저장하는 메소드 
	public void enterUser(String userName, WebSocketSession session) {
		userSessions.put(userName, session);
		sessionUsers.put(session, userName);
	}
	//userName 를 전달하면 해당 Session 을 리턴해주는 메소드 
	public WebSocketSession getUserSession(String userName) {
		return userSessions.get(userName);
	}
	public String getSessionUser(WebSocketSession session) {
		return sessionUsers.get(session);
	}
	//모든 user session 정보를 리턴하는 메소드
	public Map<String, WebSocketSession> getAllUserSession(){
		return userSessions;
	}
	
	public void removeUser(String userName) {
		userSessions.remove(userName);
	}
	
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



