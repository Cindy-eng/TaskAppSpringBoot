package com.tasklist.controller;

import com.tasklist.entity.Task;
import com.tasklist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
  @Autowired
  private TaskRepository taskRepository;





  @GetMapping("/tasks")
  public String getAll(Model model, @Param("keyword") String keyword) {
    try {
      List<Task> tasks = new ArrayList<Task>();

      if (keyword == null) {
        taskRepository.findAll().forEach(tasks::add);
      } else {
        taskRepository.findByTitleContainingIgnoreCase(keyword).forEach(tasks::add);
        model.addAttribute("keyword", keyword);
      }

      model.addAttribute("tasks", tasks);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }

    return "tasks";
  }



  @GetMapping("/tasks/new")
  public String addTask(Model model) {
    Task task = new Task();
    task.setCompleted(false);

    model.addAttribute("task", task);
    model.addAttribute("pageTitle", "Create new task");

    return "task_form";
  }

  @PostMapping("/tasks/save")
  public String saveTask(Task task, RedirectAttributes redirectAttributes) {
    try {
      taskRepository.save(task);

      redirectAttributes.addFlashAttribute("message", "The Task has been saved successfully!");
    } catch (Exception e) {
      redirectAttributes.addAttribute("message", e.getMessage());
    }

    return "redirect:/tasks";
  }

  @GetMapping("/tasks/{id}")
  public String editTask(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      Task task = taskRepository.findById(id).get();

      model.addAttribute("task", task);
      model.addAttribute("pageTitle", "Edit Task (ID: " + id + ")");

      return "task_form";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());

      return "redirect:/tasks";
    }
  }

  @GetMapping("/tasks/delete/{id}")
  public String deleteTask(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      taskRepository.deleteById(id);

      redirectAttributes.addFlashAttribute("message", "The Task with id=" + id + " has been deleted successfully!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());
    }

    return "redirect:/tasks";
  }


  @GetMapping("/tasks/{id}/completed/{status}")
  public String updateTaskCompletedStatus(@PathVariable("id") Integer id, @PathVariable("status") boolean completed,
                                          Model model, RedirectAttributes redirectAttributes) {
    try {
      taskRepository.updateCompletedStatus(id, completed);

      String status = completed ? "completed" : "disabled";
      String message = "The Task id=" + id + " has been " + status;

      redirectAttributes.addFlashAttribute("message", message);
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());
    }

    return "redirect:/tasks";
  }


}
