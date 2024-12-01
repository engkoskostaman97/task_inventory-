package inventory.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inventory.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}

