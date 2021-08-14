package uk.co.jamhammer.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.co.jamhammer.track.model.Customer;
import uk.co.jamhammer.track.model.Task;
import uk.co.jamhammer.track.service.TaskService;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/tasks")
    public String taskList(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "task/list";
    }

    @RequestMapping("task/add")
    public String taskAdd(Model model) {
        model.addAttribute("task", new Task());
        return "task/add";
    }

    @PostMapping("/task")
    public String taskPost(Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @RequestMapping("/task/edit/{id}")
    public String taskEdit(Model model, @PathVariable long id) {
        if (taskService.findById(id).isPresent()) {
            model.addAttribute("task", taskService.findById(id));
        }
        return "task/edit";
    }

    @RequestMapping("/task/delete/{id}")
    public String taskDelete(@PathVariable long id) {
        if (taskService.findById(id).isPresent()) {
            taskService.delete(taskService.findById(id).get());
        }
        return "redirect:/tasks";
    }
}
