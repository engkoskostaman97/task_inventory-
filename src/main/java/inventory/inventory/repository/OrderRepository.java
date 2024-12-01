package inventory.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.inventory.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
