package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

  /*
  * 테스트용 데이터 추가
  * */
  @PostConstruct
  public void init() {
    itemRepository.save(new Item("itemA", 100000, 10));
    itemRepository.save(new Item("itemB", 200000, 20));
  }

}
