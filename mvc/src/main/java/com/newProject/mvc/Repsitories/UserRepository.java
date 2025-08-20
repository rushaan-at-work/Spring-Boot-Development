package com.newProject.mvc.Repsitories;

import com.newProject.mvc.Entities.UserAccount;
import org.springframework.data.repository.Repository;

public interface UserRepository extends
        Repository<UserAccount, Long> {
    public UserAccount findByUsername(String username);
}
