package com.project.fin.controllers;

import com.project.fin.dto.ArtworkDto;
import com.project.fin.dto.PublicArtworkDto;
import com.project.fin.exceptions.AccessDeniedException;
import com.project.fin.services.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/artworks")
public class ArtworkController {
    @Autowired
    ArtworkService artworkService;
    @PutMapping("/{id}")
    @CrossOrigin
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<?> updateArtwork(@PathVariable Long id, @RequestBody ArtworkDto dto, Principal principal) throws AccessDeniedException {
        return ResponseEntity.ok(ArtworkDto.toDto(artworkService.update(id, dto, principal)));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<?> deleteArtwork(@PathVariable Long id) {
        artworkService.delete(id);
        return ResponseEntity.ok("Artwork deleted");
    }

    @GetMapping("/random")
    @CrossOrigin
    public List<PublicArtworkDto> getRandomArtworks(@RequestParam(defaultValue = "12") int limit) {
        return artworkService.getRandomArtworks(limit);
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<?> getArtwork(@PathVariable Long id) {
        return ResponseEntity.ok(artworkService.getArtwork(id));
    }

    @GetMapping("/search")
    @CrossOrigin
    public List<PublicArtworkDto> searchByTag(@RequestParam String tag, @RequestParam(defaultValue = "12") int limit) {
        return artworkService.findArtworksByTag(tag, limit);
    }
}
