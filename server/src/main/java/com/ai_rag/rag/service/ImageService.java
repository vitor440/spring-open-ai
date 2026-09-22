package com.ai_rag.rag.service;

import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class ImageService {

    private final OpenAiImageModel imageModel;

    public ImageService(OpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public String generateImage(String message) {

        ImagePrompt prompt = new ImagePrompt(message, ImageOptionsBuilder.builder()
                .height(1024)
                .width(1024)
                .n(1)
                .build());

        ImageResponse call = null;
        try {
            call = imageModel.call(prompt);
            String b64Json = call.getResult().getOutput().getB64Json();

            byte[] decode = Base64.getDecoder().decode(b64Json);
            Path directory = Path.of("./imagens/ai");
            Path path = directory.resolve(message.trim() + ".png");

            Files.createDirectories(directory);

            Files.write(path, decode);
            return b64Json;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
