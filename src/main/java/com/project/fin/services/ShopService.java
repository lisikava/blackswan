package com.project.fin.services;

import com.project.fin.dto.PublicShopDto;
import com.project.fin.exceptions.AccessDeniedException;
import com.project.fin.models.Role;
import com.project.fin.models.Shop;
import com.project.fin.models.User;
import com.project.fin.repositories.ShopRepository;
import com.project.fin.repositories.UserRepository;
import com.project.fin.dto.CreateShopDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.security.Principal;

@Service
public class ShopService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private JwtService jwtService;
    @Transactional
    public Shop create(String email, CreateShopDto dto) throws AccessDeniedException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        if (user.getRole() != Role.ARTIST) {
            throw new AccessDeniedException("Only artists can create shops");
//            user.setRole(Role.ARTIST);
        }
        if (user.getShop() != null) {
            throw new IllegalStateException("User already has a shop");
        }
        Shop shop = new Shop(dto.getName(), dto.getDescription(), user);
        shop.setAiTags(dto.isAiTags());
        userRepository.save(user);
        // new token for if the role changes from customer to artist
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
//        String newToken = jwtService.generateToken(userDetails, user.getRole().name());
        return shopRepository.save(shop);
    }
    public Shop update(CreateShopDto dto, Principal principal) throws AccessDeniedException {
        String name = principal.getName();
        User user = userRepository.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException(name));
        Shop shop = shopRepository.findByOwnerId(user.getId()).orElseThrow(() -> new UsernameNotFoundException(name));
        if (!principal.getName().equals(shop.getOwner().getEmail())) {
            throw new AccessDeniedException("Only the owner can modify their shop");
        }
        if (!dto.getName().isEmpty()) {
            shop.setName(dto.getName());
            shop.setSlug(Shop.generateSlug(dto.getName()));
        }
        if (!dto.getDescription().isEmpty()) {
            shop.setDescription(dto.getDescription());
        }
        return shopRepository.save(shop);
    }
    public Shop getShopByOwner(String name) {
        return shopRepository.findByOwnerId(userRepository.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException(name)).getId()).orElseThrow(() -> new UsernameNotFoundException(name));
    }

    public PublicShopDto getPublicShop(String slug) {
        Shop shop = shopRepository.findBySlug(slug)
                .orElseThrow(() -> new UsernameNotFoundException(slug));
        return PublicShopDto.toDto(shop);
    }
}
