package inventory.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import inventory.inventory.model.Order;
import inventory.inventory.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderNo}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderNo) {
        Order order = orderService.getOrderById(orderNo);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{orderNo}")
    public Order updateOrder(@PathVariable Long orderNo, @RequestBody Order order) {
        return orderService.updateOrder(orderNo, order);
    }

    @DeleteMapping("/{orderNo}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderNo) {
        orderService.deleteOrder(orderNo);
        return ResponseEntity.noContent().build();
    }
}