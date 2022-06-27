package com.backendkasir.kasir.repository;

import com.backendkasir.kasir.model.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<DAOUser, Long> {
        public DAOUser getUserByUsername(String userName);
	DAOUser findByUsername(String username);

}