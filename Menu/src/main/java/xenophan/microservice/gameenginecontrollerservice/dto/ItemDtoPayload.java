package xenophan.microservice.gameenginecontrollerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Item dto payload.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDtoPayload {
    /**
     * The Title.
     */
    protected String title;
    /**
     * The Cost.
     */
    protected int cost;
    /**
     * The Description.
     */
    protected String description;
}
