package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entities.CityMasterEntity;
@Repository
public interface CityRepository  extends JpaRepository<CityMasterEntity,Integer> {

	List<CityMasterEntity> findByStateid(Integer stateid);

}
