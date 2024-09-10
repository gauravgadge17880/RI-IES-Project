package com.user.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.api.enity.UserInfoEntity;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoEntity, Integer> {
	
	public UserInfoEntity findByEmail(String mail);
}
