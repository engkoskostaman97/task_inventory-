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

    public Order updateOrder(Long orderNo, Order order) {
        order.setOrderNo(orderNo);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderNo) {
        orderRepository.deleteById(orderNo);
    }
}