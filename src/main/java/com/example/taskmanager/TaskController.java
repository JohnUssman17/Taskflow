package com.example.taskmanager;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskRepository repo;
    public TaskController(TaskRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Task> getAll() { return repo.findAll(); }

    @PostMapping
    public Task create(@RequestBody Task task) { return repo.save(task); }

    @PutMapping("/{id}")
    public Task toggle(@PathVariable Long id) {
        Task task = repo.findById(id).orElseThrow();
        task.setCompleted(!task.isCompleted());
        return repo.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}