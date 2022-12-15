package xenophan.microservice.gameenginecontrollerservice.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xenophan.microservice.gameenginecontrollerservice.model.Item;

/**
 * The interface Item repository.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
