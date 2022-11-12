package gunproject.gunshop.controller;

import gunproject.gunshop.domain.item.Book;
import gunproject.gunshop.domain.item.Item;
import gunproject.gunshop.dto.BookForm;
import gunproject.gunshop.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.ModCheck;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    //아이템 등록 form으로 가는 핸들러
    @GetMapping("items/new")
    public String goItemForm(@ModelAttribute("form") BookForm form) {
        log.info("itemController 등록 getMapping");
        return "items/createItemForm";
    }

    //아이템 등록 컨트롤러
    @PostMapping("items/new")
    public String registerItem(@ModelAttribute("form") @Validated BookForm form, BindingResult result) {
        //itemDto에서 validation 검증에 걸렸을 때 원복시켜줌
        if (result.hasErrors()) {
            return "items/createItemForm";
        }

        Book item = new Book();
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setStockQuantity(form.getStockQuantity());
        item.setAuthor(form.getAuthor());
        item.setIsbn(form.getIsbn());

        Long registeredItem = itemService.register(item);
        log.info("등록된 item의 id는 {} 입니다.", registeredItem);

        return "redirect:/";
    }

    //아이템 리스트 이동 페이지 ->List<Item>
    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "/items/itemList";
    }

    //아이템 수정 페이지로 이동
    @GetMapping("/items/{id}/edit")
    public String goUpdateForm(@PathVariable Long id, Model model) {
        //여기서 중요한 점 영속성 컨텍스에서 꺼내온 엔티티를 다시 DTO로 넣어준다
        Book item = (Book) itemService.findOne(id);

        //수정페이지로 이동하기전에 수정도 다시 Dto로 넘겨줄 준비를 해준다.
        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "/items/updateItemForm";
    }

    //
    @PostMapping("/items/{id}/edit")
    public String update(@PathVariable Long id,@Validated BookForm form,BindingResult result) {
        itemService.updateItem(id, form);
        return  "redirect:/items";
    }
}
