package com.project.fin.controllers;

import com.project.fin.services.AiTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ai")
public class AiTagController {
    @Autowired
    AiTagService aiTagService;

    @PostMapping("/tags")
    @CrossOrigin
    @PreAuthorize("hasRole('ARTIST')")
    public List<String> suggestAiTags(@RequestPart("image") MultipartFile image) throws IOException {
        return aiTagService.generateTags(image);
    }
}
