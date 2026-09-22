package com.ai_rag.rag.service;

import com.openai.models.audio.AudioResponseFormat;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.tts.TextToSpeechOptions;
import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Service
public class AudioService {

    private final OpenAiAudioSpeechModel audioSpeechModel;
    private final OpenAiAudioTranscriptionModel transcriptionModel;

    public AudioService(OpenAiAudioSpeechModel audioSpeechModel, OpenAiAudioTranscriptionModel transcriptionModel) {
        this.audioSpeechModel = audioSpeechModel;
        this.transcriptionModel = transcriptionModel;
    }

    public void generateAudio(String instruction) {

        TextToSpeechPrompt prompt = new TextToSpeechPrompt(instruction, OpenAiAudioSpeechOptions.builder()
                .instructions(instruction)
                .responseFormat(OpenAiAudioSpeechOptions.AudioResponseFormat.MP3)
                .voice(OpenAiAudioSpeechOptions.Voice.ALLOY)
                .speed(1.0)
                .build());

        TextToSpeechResponse call = audioSpeechModel.call(prompt);
        byte[] output = call.getResult().getOutput();

        try {

            Path directory = Path.of("./audio/ai");
            Path path = directory.resolve("transcrição-audio".trim() + ".mp3");

            Files.createDirectories(directory);

            Files.write(path, output);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String transcribeAudio(MultipartFile file) {

        AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(file.getResource(), OpenAiAudioTranscriptionOptions.builder()
                .responseFormat(AudioResponseFormat.TEXT)
                .language("pt")
                .build());

        return transcriptionModel.call(prompt).getResult().getOutput();
    }
}
