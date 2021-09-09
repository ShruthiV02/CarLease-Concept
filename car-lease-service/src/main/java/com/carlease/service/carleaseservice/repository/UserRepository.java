package com.carlease.service.carleaseservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlease.service.carleaseservice.bean.UserCredentials;

/*JPA Repository for User Entity*/
@Repository
public interface UserRepository extends JpaRepository<UserCredentials,String> {
}
