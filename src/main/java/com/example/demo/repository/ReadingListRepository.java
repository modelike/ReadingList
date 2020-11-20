package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ReadingListRepository extends JpaRepository<Book, Long> {
    @Query(value = "select t from Book t where t.reader=?1")
    List<Book> findByReader(String reader);
}
