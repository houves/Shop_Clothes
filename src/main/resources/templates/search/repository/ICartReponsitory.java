package templates.search.repository;

import com.example.shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartReponsitory extends JpaRepository<Cart, Long> {
    //List<Cart> findAllByIdUser(Long id_user);
}
