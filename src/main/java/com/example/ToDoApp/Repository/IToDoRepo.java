package com.example.ToDoApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ToDoApp.model.ToDo;



@Repository
public interface IToDoRepo extends JpaRepository<ToDo,Long> {
	
}
