package hello;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //jpql
    @Query(nativeQuery = true, value = "select * from customer where id <= ?1")
    List<Customer> findByIdLimit(Long id);


}
