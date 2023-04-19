package com.dezzy.repository;

import com.dezzy.dto.UserDTO;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDTO, Long> {
    UserDTO findByName(String name);
}
