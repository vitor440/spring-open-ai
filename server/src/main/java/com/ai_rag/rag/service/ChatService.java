package com.ai_rag.rag.service;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final OpenAiChatModel chatModel;

    public ChatService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String chat(String message) {
        Prompt prompt = new Prompt(message, OpenAiChatOptions.builder()
                .model("gpt-4o")
                .temperature(0.4)
                .build());
        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
