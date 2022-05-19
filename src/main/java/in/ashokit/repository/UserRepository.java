package in.ashokit.repository;

import org.springframework.stereotype.Repository;
import in.ashokit.entities.UserDtlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserDtlsEntity,Integer> {
	
public UserDtlsEntity findByEmailAndPassword(String email,String password);

public UserDtlsEntity findByEmail(String email);

}
