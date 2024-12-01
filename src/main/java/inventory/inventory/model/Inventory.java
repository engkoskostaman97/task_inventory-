package inventory.inventory.model;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Item ID is mandatory")
    private Long itemId;

    @NotNull(message = "Quantity is mandatory")
    private Integer qty;

    @NotNull(message = "Type is mandatory")
    private String type; // T for Top Up, W for Withdrawal
}