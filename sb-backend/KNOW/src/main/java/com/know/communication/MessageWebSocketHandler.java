package com.know.communication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.know.repository.CoachingRepository;


public class MessageWebSocketHandler extends TextWebSocketHandler {

	private static final Map<String, Map<String, WebSocketSession>> coachingSessions = new HashMap<>();
	
	@Autowired
	private CoachingRepository coachingRepository;
	
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		String userId = extractUserId(session);
		String coachingId = extractCoachingId(session);

		coachingSessions.computeIfAbsent(coachingId, val -> new HashMap<>());
		coachingSessions.get(coachingId).put(userId, session);
//		System.out.println(coachingSessions);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

		String coachingId = extractCoachingId(session);
		
		String message_payload = message.getPayload();
		ObjectMapper objectMapper = new ObjectMapper();
//		System.out.println(message_payload);
		JsonNode jsonNode = objectMapper.readTree(message_payload);

		ObjectNode objectNode = (ObjectNode) jsonNode;

		String writeValueAsString = objectMapper.writeValueAsString(objectNode);
		System.out.println(writeValueAsString);
		broadcastMessageToReciever(coachingId, writeValueAsString, session);

	}

	private void broadcastMessageToReciever(String coachingId, String messagePayload, WebSocketSession sender) {

		Map<String, WebSocketSession> sessions = coachingSessions.get(coachingId);
		if (sessions != null) {

			for (Map.Entry<String, WebSocketSession> session : sessions.entrySet()) {
//				System.out.println(session);
				if (session.getValue().isOpen() && !sender.getId().equals(session.getValue().getId())
						) {
					try {
						session.getValue().sendMessage(new TextMessage(messagePayload));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		String coachingId = extractCoachingId(session);
		String userId = extractUserId(session);

		coachingSessions.get(coachingId).remove(userId);
	}

	private String extractCoachingId(WebSocketSession session) {

		String uri = session.getUri().toString();
		Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
		Matcher matcher = pattern.matcher(uri);
		String userId = "";
		if (matcher.find() && matcher.groupCount() == 2) {
			userId = matcher.group(1);
		}
		return userId;
	}

	private String extractUserId(WebSocketSession session) {

		String uri = session.getUri().toString();
		Pattern pattern = Pattern.compile("/chat/(.*?)/(.*?)$");
		Matcher matcher = pattern.matcher(uri);
		String problemId = "";
		if (matcher.find() && matcher.groupCount() == 2) {
			problemId = matcher.group(2);
		}
		return problemId;
	}
	
}
