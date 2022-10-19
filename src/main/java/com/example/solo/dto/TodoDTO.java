package com.example.solo.dto;

import com.example.solo.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private String url;

    public TodoDTO(TodoEntity todoEntity, String serviceUrl) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.order = todoEntity.getOrder();
        this.completed = todoEntity.getCompleted();
        this.url = serviceUrl + this.id;
    }
}