package com.dxc.barry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.barry.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByNameAndBirthday(String name, String birthday);

}
