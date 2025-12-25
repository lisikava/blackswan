package com.project.fin.services;

import com.project.fin.dto.ArtworkDto;
import com.project.fin.dto.PublicArtworkDto;
import com.project.fin.exceptions.AccessDeniedException;
import com.project.fin.models.Artwork;
import com.project.fin.models.Shop;
import com.project.fin.models.User;
import com.project.fin.repositories.ArtworkRepository;
import com.project.fin.repositories.ShopRepository;
import com.project.fin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ArtworkService {
    @Autowired
    private ArtworkRepository artworkRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;
    private final String UPLOAD_DIR = "uploads/artworks";
    public Artwork upload(ArtworkDto dto, Principal principal, MultipartFile file) throws IOException, AccessDeniedException {
        String name = principal.getName();
        User user = userRepository.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException(name));
        Shop shop = shopRepository.findByOwnerId(user.getId()).orElseThrow(() -> new UsernameNotFoundException(name));
        if (!shop.getOwner().getEmail().equals(principal.getName())) {
            throw new AccessDeniedException("Only the owner can upload to their shop");
        }
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR, fileName);
        Files.write(path, file.getBytes());

        Artwork artwork = new Artwork();
        artwork.setTitle(dto.getTitle());
        artwork.setDescription(dto.getDescription());
        artwork.setPrice(dto.getPrice());
        artwork.setImageUrl("/uploads/" + fileName);
        artwork.setStatus(dto.getStatus());
        artwork.setTags(dto.getTags());
        artwork.setShop(shop);

        return artworkRepository.save(artwork);
    }

    public List<ArtworkDto> getArtworksByArtist(Principal principal) {
        String name = principal.getName();
        Shop shop = shopRepository.findByOwnerId(userRepository.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException(name)).getId()).orElseThrow(() -> new UsernameNotFoundException(name));
        List<Artwork> artworks = artworkRepository.findByShopId(shop.getId()).orElseThrow(() -> new UsernameNotFoundException(name));
        List<ArtworkDto> artworkDtos = new ArrayList<>();
        for (Artwork artwork: artworks) {
            artworkDtos.add(ArtworkDto.toDto(artwork));
        }
        return artworkDtos;
    }

    public Artwork update(Long id, ArtworkDto dto, Principal principal) throws AccessDeniedException {
        String name = principal.getName();
        Shop shop = shopRepository.findByOwnerId(userRepository.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException(name)).getId()).orElseThrow(() -> new UsernameNotFoundException(name));
        Artwork artwork = artworkRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(name));
        if (!principal.getName().equals(artwork.getShop().getOwner().getEmail())) {
            throw new AccessDeniedException("Only the owner can modify their artworks");
        }
        if (!dto.getTitle().isEmpty())
            artwork.setTitle(dto.getTitle());
        if (!dto.getDescription().isEmpty())
            artwork.setDescription(dto.getDescription());
        if (!dto.getPrice().equals(artwork.getPrice()))
            artwork.setPrice(dto.getPrice());
        if (!dto.getTags().equals(new ArrayList<>()))
            artwork.setTags(dto.getTags());
        if (!dto.getStatus().equals(artwork.getStatus()))
            artwork.setStatus(dto.getStatus());
        return artworkRepository.save(artwork);
    }

    public void delete(Long id) {
        if (!artworkRepository.existsById(id))
            throw new UsernameNotFoundException(id.toString());
        artworkRepository.deleteById(id);
    }

    public List<PublicArtworkDto> getRandomArtworks(int limit) {
        Page<Artwork> page = artworkRepository.findRandom(PageRequest.of(0, limit));
        List<PublicArtworkDto> artworkDtos = new ArrayList<>();
        return page.stream().map(a -> PublicArtworkDto.toDto(a)).toList();
    }
}
