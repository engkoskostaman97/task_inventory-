package inventory.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.inventory.model.Item;
import inventory.inventory.model.Order;
import inventory.inventory.repository.ItemRepository;
import inventory.inventory.repository.OrderRepository;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Order createOrder(Order order) {
        Item item = itemRepository.findById(order.getItemId()).orElse(null);
        if (item != null && item.getQty() >= order.getQty()) {
            item.setQty(item.getQty() - order.getQty());
            itemRepository.save(item);
            order.setPrice(item.getPrice() * order.getQty());
            return orderRepository.save(order);
        }
        return null;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderNo) {
        return orderRepository.findById(orderNo).orElse(null);
    }

    @Transactional
public Order updateOrder(Long orderNo, Order order) {
    // Cari order yang ada
    Order existingOrder = orderRepository.findById(orderNo).orElse(null);
    if (existingOrder == null) {
        throw new IllegalArgumentException("Order not found");
    }

    // Cari item terkait
    Item item = itemRepository.findById(order.getItemId()).orElse(null);
    if (item == null) {
        throw new IllegalArgumentException("Item not found");
    }

    // Cek jika qty baru melebihi qty yang ada
    if (item.getQty() < order.getQty()) {
        throw new IllegalArgumentException("Not enough item quantity available");
    }

    if (existingOrder.getQty() != order.getQty()) {

        item.setQty(item.getQty() + existingOrder.getQty());
        item.setQty(item.getQty() - order.getQty());
    }
    
    itemRepository.save(item);

    order.setPrice(item.getPrice() * order.getQty());

    // Update order dengan informasi baru
    order.setOrderNo(orderNo);
    order.setItemId(order.getItemId()); 
    order.setQty(order.getQty());
    return orderRepository.save(order);
}

    public void deleteOrder(Long orderNo) {
        orderRepository.deleteById(orderNo);
    }
}