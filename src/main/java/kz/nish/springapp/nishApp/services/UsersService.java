package kz.nish.springapp.nishApp.services;


import kz.nish.springapp.nishApp.models.Users;
import kz.nish.springapp.nishApp.repositories.UsersRepository;
import kz.nish.springapp.nishApp.util.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional()
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> findALL(){return usersRepository.findAll();}

    public Users findOne(int id){
        Optional<Users> foundStudent = usersRepository.findById(id);
        return foundStudent.orElseThrow(UserNotFoundException::new);

    }

    public void save(Users users){
        usersRepository.save(users);
    }

}
