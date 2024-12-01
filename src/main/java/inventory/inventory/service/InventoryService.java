package inventory.inventory.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.inventory.model.Inventory;
import inventory.inventory.model.Item;
import inventory.inventory.repository.InventoryRepository;
import inventory.inventory.repository.ItemRepository;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Inventory addInventory(Inventory inventory) {
        Item item = itemRepository.findById(inventory.getItemId()).orElse(null);
        if (item != null) {
            if ("T".equals(inventory.getType())) {
                item.setQty(item.getQty() + inventory.getQty());
            } else if ("W".equals(inventory.getType())) {
                item.setQty(item.getQty() - inventory.getQty());
            }
            itemRepository.save(item);
            return inventoryRepository.save(inventory);
        }
        return null; // Return null if item is not found
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory updateInventory(Long id, Inventory inventory) {
        inventory.setId(id);
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}