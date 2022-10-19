package com.example.solo.controller;

import com.example.solo.dto.TodoDTO;
import com.example.solo.entity.TodoEntity;
import com.example.solo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@Slf4j
public class TodoController {
    private final TodoService todoService;

    private String serviceUrl;

    @PostMapping
    public ResponseEntity<TodoDTO> create(@RequestBody TodoDTO todoDTO) {
        if (ObjectUtils.isEmpty(todoDTO.getTitle())) {
            return ResponseEntity.badRequest().build();
        }

        if (ObjectUtils.isEmpty(todoDTO.getOrder())) {
            todoDTO.setOrder(0L);
        }

        if (ObjectUtils.isEmpty(todoDTO.getCompleted())) {
            todoDTO.setCompleted(false);
        }

        TodoEntity result = this.todoService.add(todoDTO);
        TodoDTO response = new TodoDTO(result, serviceUrl);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDTO> readOne(@PathVariable Long id) {
        TodoEntity result = this.todoService.searchById(id);
        TodoDTO response = new TodoDTO(result, serviceUrl);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> readAll() {
        List<TodoEntity> resultList = this.todoService.searchAll();
        List<TodoDTO> response = resultList
                .stream()
                .map( result -> new TodoDTO(result, serviceUrl) )
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoDTO> update(@PathVariable Long id, @RequestBody TodoDTO todoDTO) {
        TodoEntity result = this.todoService.updateById(id, todoDTO);
        TodoDTO response = new TodoDTO(result, serviceUrl);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeOne(@PathVariable Long id) {
        this.todoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> removeAll() {
        this.todoService.deleteAll();
        return ResponseEntity.ok().build();
    }
}