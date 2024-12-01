package inventory.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.inventory.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

