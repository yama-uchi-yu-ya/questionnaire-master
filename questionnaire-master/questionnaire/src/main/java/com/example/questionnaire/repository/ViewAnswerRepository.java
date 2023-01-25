package com.example.questionnaire.repository;

import com.example.questionnaire.entity.ViewAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewAnswerRepository extends JpaRepository<ViewAnswer, Integer> {
    Page<ViewAnswer> findByIsDeleted(Pageable pageable, int is_deleted);
}
