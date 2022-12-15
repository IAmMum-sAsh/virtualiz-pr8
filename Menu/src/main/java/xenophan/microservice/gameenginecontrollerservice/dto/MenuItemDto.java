package xenophan.microservice.gameenginecontrollerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xenophan.microservice.gameenginecontrollerservice.model.Item;

import java.util.List;

/**
 * The type Menu item dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDto {
    /**
     * The Menu items.
     */
    protected List<Item> menuItems;

}
