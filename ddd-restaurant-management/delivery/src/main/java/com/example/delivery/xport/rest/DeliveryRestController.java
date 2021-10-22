package com.example.delivery.xport.rest;

import com.example.delivery.domain.model.Delivery;
import com.example.delivery.domain.model.DeliveryId;
import com.example.delivery.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Rest kontroler koj se koristi od frontend aplikacijata
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/delivery")
@AllArgsConstructor
public class DeliveryRestController {

    private final DeliveryService deliveryService;

    @GetMapping("/all")
    public List<Delivery> findAllDeliveries(){
        return this.deliveryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> findDeliveryById(@PathVariable String id){
        return this.deliveryService.findDeliveryById(DeliveryId.of(id))
                .map(delivery -> ResponseEntity.ok().body(delivery))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/finishDelivery/{id}")
    public ResponseEntity<Delivery> finishDeliveryWithId(@PathVariable String id){
        return this.deliveryService.finishDelivery(DeliveryId.of(id))
                .map(delivery -> ResponseEntity.ok().body(delivery))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
