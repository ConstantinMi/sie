package ro.uaic.feaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.uaic.feaa.entities.Cont;

import java.util.List;
import java.util.Optional;

/**
 * Created by Claudiu on 1/13/2018.
 */
@Transactional(propagation = Propagation.REQUIRED)
public interface IContRepository extends JpaRepository<Cont, String> {

//    void adaugareCont(Cont cont);

//    void modificareCont(Cont cont);

    @Query("select c from Cont c where c.cod = :cod")
    Optional<Cont> findByCod(@Param("cod") Long cod);

    @Query("select c from Cont c where c.tipCont = :tipCont")
    List<Cont> findByTipCont(@Param("tipCont") String tipCont);

    @Query("from Cont c order by c.cod")
    List<Cont> obtinereConturi();
}
