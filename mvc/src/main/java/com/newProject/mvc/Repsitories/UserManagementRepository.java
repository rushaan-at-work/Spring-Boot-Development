package com.newProject.mvc.Repsitories;

import com.newProject.mvc.Entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends
        JpaRepository<UserAccount,Long>{

}
