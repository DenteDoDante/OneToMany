package OneToMany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import OneToMany.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    Users findByName(String name);

}
