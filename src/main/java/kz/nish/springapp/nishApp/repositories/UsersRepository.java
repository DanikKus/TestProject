package kz.nish.springapp.nishApp.repositories;

import kz.nish.springapp.nishApp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
