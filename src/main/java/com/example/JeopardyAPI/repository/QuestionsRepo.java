package com.example.JeopardyAPI.repository;

import com.example.JeopardyAPI.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuestionsRepo extends JpaRepository<Question, Long> {
    @Modifying
    @Query("delete from Question q where q.id = :id")
    int deleteId(@Param("id") Long id);
}
