package com.ai_rag.rag.controller;

import com.ai_rag.rag.service.AudioService;
import com.ai_rag.rag.service.ChatService;
import com.ai_rag.rag.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final ChatService chatService;
    private final ImageService imageService;
    private final AudioService audioService;

    public AiController(ChatService chatService, ImageService imageService, AudioService audioService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.audioService = audioService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "prompt") String message) {
        return ResponseEntity.ok(chatService.chat(message));
    }

    @GetMapping("/image")
    public ResponseEntity<String> generateImage(@RequestParam(value = "prompt") String message) {
        return ResponseEntity.ok(imageService.generateImage(message));

    }

    @GetMapping("/audio")
    public ResponseEntity<Void> generateAudio(@RequestParam(value = "prompt") String message) {
        audioService.generateAudio(message);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/audio/transcription")
    public ResponseEntity<String> transcribeAudio(@RequestParam(value = "file") MultipartFile file) {

        return ResponseEntity.ok(audioService.transcribeAudio(file));
    }
}
