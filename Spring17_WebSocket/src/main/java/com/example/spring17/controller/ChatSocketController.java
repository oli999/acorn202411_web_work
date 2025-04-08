package com.example.spring17.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import com.example.spring17.anno.SocketController;
import com.example.spring17.anno.SocketMapping;
import com.example.spring17.dto.ChatMessage;
import com.example.spring17.handler.SocketSessionManager;

@SocketController
public class ChatSocketController {
	@Autowired
	private SocketSessionManager sessionManager;
	
	@SocketMapping("/chat/send")
	public void sendMessage(WebSocketSession session, ChatMessage message) {
		//전달된 대화내용을 TextMessage 객체에 담는다.
		TextMessage msg=new TextMessage(message.getText());
		//sessionManager 객체를 이용해서 접속된 모든 세션에 TextMessage 를 전달한다.
		sessionManager.getSessions().forEach( item->{
			// item 은 WebSocketSession 객체 이다.
			try {
				item.sendMessage(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
}








