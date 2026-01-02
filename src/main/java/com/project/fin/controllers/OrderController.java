package com.project.fin.controllers;

import com.project.fin.dto.ArtistOrderDto;
import com.project.fin.dto.CustomerOrderDto;
import com.project.fin.dto.OrderDto;
import com.project.fin.exceptions.AccessDeniedException;
import com.project.fin.exceptions.ArtworkNotFoundException;
import com.project.fin.exceptions.OrderNotFoundException;
import com.project.fin.models.Order;
import com.project.fin.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/artworks/{artworkId}")
    @CrossOrigin
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> placeOrder(@PathVariable Long artworkId, @RequestBody(required = false) OrderDto dto, Principal principal) throws ArtworkNotFoundException, AccessDeniedException {
        Order order = orderService.placeOrder(dto, artworkId, principal.getName());
        return ResponseEntity.ok(OrderDto.toDto(order));
    }

    @GetMapping("/me")
    @CrossOrigin
    @PreAuthorize("hasRole('ARTIST')")
    public List<ArtistOrderDto> getArtistOrders(Principal principal) throws OrderNotFoundException {
        return orderService.getArtistOrders(principal);
    }

    @GetMapping("/placed")
    @CrossOrigin
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<CustomerOrderDto> getCustomerOrders(Principal principal) throws OrderNotFoundException {
        return orderService.getCustomerOrders(principal);
    }



}
