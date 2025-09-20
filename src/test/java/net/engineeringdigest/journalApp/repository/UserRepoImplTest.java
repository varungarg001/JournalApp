package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.Repository.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserRepoImplTest {

    @Autowired
    private UserRepoImpl userRepo;

    @Test
    void getUser(){
        Assertions.assertNotNull(userRepo.getUserSA());
    }

}
