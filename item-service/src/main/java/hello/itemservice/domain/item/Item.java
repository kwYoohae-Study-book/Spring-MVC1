package hello.itemservice.domain.item;

import lombok.Data;

@Data
public class Item {

  private Long id;
  private String itemName;
  private Integer price;
  private Integer quantity;

  public Item() {

  }

  public Item(final String itemName, final Integer price, final Integer quantity) {
    this.itemName = itemName;
    this.price = price;
    this.quantity = quantity;
  }
}
