package com.QuestAPP.demo.repositories;

import com.QuestAPP.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long > {
}
