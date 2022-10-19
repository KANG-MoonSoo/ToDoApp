package com.example.solo.service;

import com.example.solo.dto.TodoDTO;
import com.example.solo.entity.TodoEntity;
import com.example.solo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository toDoRepository;

    public TodoEntity add(TodoDTO todoDTO) {
        TodoEntity toDoEntity = TodoEntity.builder()
                .title(todoDTO.getTitle())
                .order(todoDTO.getOrder())
                .completed(todoDTO.getCompleted())
                .build();
        return this.toDoRepository.save(toDoEntity);
    }

    public TodoEntity searchById(Long id) {
        return this.toDoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public List<TodoEntity> searchAll(){
        return (List<TodoEntity>) this.toDoRepository.findAll();
    }
    public TodoEntity updateById(Long id, TodoDTO todoDTO) {
        TodoEntity todoEntity = this.searchById(id);

        if (todoDTO.getTitle() != null) {
            todoEntity.setTitle(todoDTO.getTitle());
        }

        if (todoDTO.getOrder() != null) {
            todoEntity.setOrder(todoDTO.getOrder());
        }

        if (todoDTO.getCompleted() != null) {
            todoEntity.setCompleted(todoDTO.getCompleted());
        }

        return this.toDoRepository.save(todoEntity);
    }

    public void deleteById(Long id) {
        this.toDoRepository.deleteById(id);
    }

    public void deleteAll() {
        this.toDoRepository.deleteAll();
    }
}