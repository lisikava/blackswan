package com.project.fin.services;

import com.project.fin.dto.ArtistOrderDto;
import com.project.fin.dto.ArtworkDto;
import com.project.fin.dto.CustomerOrderDto;
import com.project.fin.dto.OrderDto;
import com.project.fin.exceptions.AccessDeniedException;
import com.project.fin.exceptions.ArtworkNotFoundException;
import com.project.fin.exceptions.OrderNotFoundException;
import com.project.fin.models.*;
import com.project.fin.repositories.ArtworkRepository;
import com.project.fin.repositories.OrderRepository;
import com.project.fin.repositories.ShopRepository;
import com.project.fin.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ArtworkRepository artworkRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Transactional
    public Order placeOrder(OrderDto dto, Long artworkId, String userEmail) throws ArtworkNotFoundException, AccessDeniedException {
        Artwork artwork = artworkRepository.findByIdWithLock(artworkId).orElseThrow(() -> new ArtworkNotFoundException(artworkId.toString()));
        User customer = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException(userEmail));
        if (artwork.getShop().getOwner().equals(customer)) {
            throw new AccessDeniedException("Cannot buy own artwork");
        }
        if (artwork.getStatus().equals(ArtworkStatus.SOLD)) {
            throw new AccessDeniedException("Artwork unavailable");
        }
        Order order = new Order();
        artwork.setStatus(ArtworkStatus.SOLD);
        artworkRepository.save(artwork);

        order.setArtwork(artwork);
        order.setBuyer(customer);
        order.setShop(artwork.getShop());
        order.setPrice(artwork.getPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        if (artwork.getType().equals(ArtworkType.PHYSICAL)) {
            order.setFullName(dto.getFullName());
            order.setAddress(dto.getAddress());
            order.setCity(dto.getCity());
            order.setCountry(dto.getCountry());
            order.setZipCode(dto.getZipCode());
            order.setPhone(dto.getPhone());
        }
        return orderRepository.save(order);
    }

    public List<ArtistOrderDto> getArtistOrders(Principal principal) throws OrderNotFoundException {
        String name = principal.getName();
        User user = userRepository.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException(name));
        Shop shop = shopRepository.findByOwnerId(user.getId()).orElseThrow(() -> new UsernameNotFoundException(name));
        List<Order> orders = orderRepository.findByShopId(shop.getId()).orElseThrow(() -> new OrderNotFoundException(shop.getName()));
        List<ArtistOrderDto> dtos = new ArrayList<>();
        for (Order order: orders) {
            dtos.add(ArtistOrderDto.toDto(order));
        }
        return dtos;
    }

    public List<CustomerOrderDto> getCustomerOrders(Principal principal) throws OrderNotFoundException {
        String name = principal.getName();
        User user = userRepository.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException(name));
        List<Order> orders = orderRepository.findByBuyerId(user.getId()).orElseThrow(() -> new OrderNotFoundException(name));
        List<CustomerOrderDto> dtos = new ArrayList<>();
        for (Order order: orders) {
            dtos.add(CustomerOrderDto.toDto(order));
        }
        return dtos;
    }
}
