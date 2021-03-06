package com.example.demo.repository;

import com.example.demo.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ReaderRepository extends JpaRepository<Reader,String> {
    Reader findByUsername(String userName);
}
