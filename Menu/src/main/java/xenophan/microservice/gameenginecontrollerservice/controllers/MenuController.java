package xenophan.microservice.gameenginecontrollerservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xenophan.microservice.gameenginecontrollerservice.dto.ItemDtoPayload;
import xenophan.microservice.gameenginecontrollerservice.dto.MenuItemDto;
import xenophan.microservice.gameenginecontrollerservice.model.Item;
import xenophan.microservice.gameenginecontrollerservice.repos.ItemRepository;

/**
 * The type Menu controller.
 */
@RestController
@RequestMapping("/api/menu")
public class MenuController {
    /**
     * The Item repository.
     */
    @Autowired
    ItemRepository itemRepository;

    /**
     * Get menu response entity.
     *
     * @return the response entity
     */
    @RequestMapping(
            value = "",
            method = RequestMethod.GET
    )
    public ResponseEntity<MenuItemDto> getMenu(){
        MenuItemDto menuItemDto = new MenuItemDto();

        menuItemDto.setMenuItems(itemRepository.findAll());

        return ResponseEntity.ok(menuItemDto);
    }

    /**
     * Get item response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(
            value = "/item/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Item> getItem(@PathVariable long id){
        Item item = itemRepository.findById(id).orElseThrow(
                () -> {throw new RuntimeException("Товар не найден");}
        );
        return ResponseEntity.ok(item);
    }

    /**
     * Add item response entity.
     *
     * @param itemDtoPayload the item dto payload
     * @return the response entity
     */
    @RequestMapping(
            value = "/add_item",
            method = RequestMethod.POST
    )
    public ResponseEntity<Item> addItem(@RequestBody ItemDtoPayload itemDtoPayload){
        Item item = new Item(itemDtoPayload.getTitle(), itemDtoPayload.getCost(), itemDtoPayload.getDescription());
        itemRepository.save(item);

        return ResponseEntity.ok(item);
    }

    /**
     * Delete item response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(
            value = "delete_item/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Item> deleteItem(@PathVariable long id){
        Item item = itemRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Item not found")
        );
        itemRepository.delete(item);
        return ResponseEntity.ok(item);
    }
}
