package com.tasklist.repository;

import com.tasklist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Integer> {
  List<Task> findByTitleContainingIgnoreCase(String keyword);

  @Query("UPDATE Task t SET t.completed = :completed WHERE t.id = :id")
  @Modifying
  public void updateCompletedStatus(Integer id, boolean completed);
}

