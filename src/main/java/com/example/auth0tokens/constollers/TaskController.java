package com.example.auth0tokens.constollers;

import com.example.auth0tokens.models.Task;
import com.example.auth0tokens.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TasksRepository tasksRepository;

    @Autowired
    public TaskController(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @GetMapping
    public List<Task> getTasks(){
        return tasksRepository.findAll();
    }

    @PostMapping
    public void addTask(@RequestBody Task task){
        tasksRepository.save(task);
    }

    @PutMapping("/{id}")
    public void editTask(@PathVariable long id, @RequestBody Task task){
        Task existingTask = tasksRepository.findOne(id);
        Assert.notNull(existingTask, "Task Not found");
        existingTask.setDescription(task.getDescription());
        tasksRepository.save(existingTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id){
        tasksRepository.delete(id);
    }

}
