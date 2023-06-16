package templates.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartReponsitory extends JpaRepository<Cart, Long> {
    //List<Cart> findAllByIdUser(Long id_user);
}
