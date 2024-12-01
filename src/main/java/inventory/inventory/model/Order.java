package inventory.inventory.model;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNo;

    @NotNull(message = "Item ID is mandatory")
    private Long itemId;

    @NotNull(message = "Quantity is mandatory")
    private Integer qty;

    private Double price;
}