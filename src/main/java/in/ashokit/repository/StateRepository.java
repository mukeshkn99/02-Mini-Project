package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entities.StateMasterEntity;

@Repository
public interface StateRepository extends JpaRepository<StateMasterEntity,Integer> {

	List<StateMasterEntity> findByCountryid(Integer countryid);

}
