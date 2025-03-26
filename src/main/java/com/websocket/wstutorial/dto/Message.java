package com.websocket.wstutorial.dto;

public class Message {
    private String messageContent;

    private String deviceId;
    
    
    public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

	@Override
	public String toString() {
		return "Message [messageContent=" + messageContent + ", deviceId=" + deviceId + "]";
	}
    
    
}
