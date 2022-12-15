package xenophan.microservice.gameenginecontrollerservice.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddDto extends BaseEntity {
    protected String title;
    protected int cost;
    protected String description;
}
