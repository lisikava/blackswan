package com.project.fin.controllers;

import com.project.fin.dto.ArtworkDto;
import com.project.fin.dto.CreateShopDto;
import com.project.fin.exceptions.AccessDeniedException;
import com.project.fin.models.Artwork;
import com.project.fin.models.Shop;
import com.project.fin.services.ArtworkService;
import com.project.fin.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    ShopService shopService;
    @Autowired
    ArtworkService artworkService;
    @PostMapping
    @CrossOrigin
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<?> createShop(@RequestBody CreateShopDto createShopDto, Principal principal) throws AccessDeniedException {
        return ResponseEntity.ok(shopService.create(principal.getName(), createShopDto));
    }
    @GetMapping("/me")
    @CrossOrigin
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<?> myShop(Principal principal) {
        return ResponseEntity.ok(CreateShopDto.toDto(shopService.getShopByOwner(principal.getName())));
    }
    @PutMapping("/me")
    @CrossOrigin
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<?> updateShop(@RequestBody CreateShopDto dto, Principal principal) throws AccessDeniedException {
        shopService.update(dto, principal);
        return ResponseEntity.ok(CreateShopDto.toDto(shopService.getShopByOwner(principal.getName())));
    }

    @PostMapping(path="/me/artworks", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @CrossOrigin
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<?> uploadArtwork(@RequestPart("data") ArtworkDto dto, Principal principal, @RequestPart("image") MultipartFile file) throws IOException, AccessDeniedException {
        Artwork artwork = artworkService.upload(dto, principal, file);
        return ResponseEntity.ok(ArtworkDto.toDto(artwork));
    }
    @GetMapping("/me/artworks")
    @CrossOrigin
    @PreAuthorize("hasRole('ARTIST')")
    public List<ArtworkDto> getArtworks(Principal principal) {
        return artworkService.getArtworksByArtist(principal);
    }

    @GetMapping("/{slug}")
    @CrossOrigin
    public ResponseEntity<?> getPublicShop(@PathVariable String slug) {
        return ResponseEntity.ok(shopService.getPublicShop(slug));
    }



}
