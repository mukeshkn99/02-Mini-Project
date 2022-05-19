package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entities.CountryMasterEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryMasterEntity,Integer> {

}
