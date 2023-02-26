package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

  private final ItemRepository itemRepository;

  @GetMapping
  public String items(Model model) {
    final List<Item> items = itemRepository.findAll();
    model.addAttribute("items", items);
    return "basic/items";
  }

  @GetMapping("/{itemId}")
  public String item(@PathVariable long itemId, Model model) {
    final Item item = itemRepository.findById(itemId);
    model.addAttribute("item", item);
    return "basic/item";
  }

  @GetMapping("/add")
  public String addForm() {
    return "/basic/addForm";
  }

//  @PostMapping("/add")
//  public String addItemV1(@RequestParam String itemName,
//      @RequestParam int price,
//      @RequestParam Integer quantity,
//      Model model
//  ) {
//    Item item = new Item();
//    item.setItemName(itemName);
//    item.setPrice(price);
//    item.setQuantity(quantity);
//
//    itemRepository.save(item);
//
//    model.addAttribute("item", item);
//
//    return "/basic/item";
//  }

//  @PostMapping("/add")
//  public String addItemV2(@ModelAttribute("item") Item item, Model model) {
//    itemRepository.save(item);
//
////    model.addAttribute("item", item); 안해도 자동 등록
//
//    return "/basic/item";
//  }

//  @PostMapping("/add")
//  public String addItemV3(@ModelAttribute Item item) {
//    itemRepository.save(item);
//
//    // Item 이므로 item 으로 자동등록, 첫번 째만 소문자로 바뀜
//
//    return "/basic/item";
//  }

  @PostMapping("/add")
  public String addItemV4(Item item) {
    itemRepository.save(item);

    // Item 이므로 item 으로 자동등록, 첫번 째만 소문자로 바뀜

    return "/basic/item";
  }

  @GetMapping("/{itemId}/edit")
  public String editForm(@PathVariable Long itemId, Model model) {
    final Item item = itemRepository.findById(itemId);
    model.addAttribute("item", item);
    return "basic/editForm";
  }

  @PostMapping("/{itemId}/edit")
  public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
    itemRepository.update(itemId, item);
    return "redirect:/basic/items/{itemId}";
  }

  /*
   * 테스트용 데이터 추가
   * */
  @PostConstruct
  public void init() {
    itemRepository.save(new Item("itemA", 100000, 10));
    itemRepository.save(new Item("itemB", 200000, 20));
  }

}
