package uk.co.jamhammer.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.co.jamhammer.track.model.Customer;
import uk.co.jamhammer.track.model.Task;
import uk.co.jamhammer.track.service.ProjectService;
import uk.co.jamhammer.track.service.TaskService;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/tasks")
    public String taskList(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "task/list";
    }

    @RequestMapping("task/add")
    public String taskAdd(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("projects", projectService.findAll());
        return "task/add";
    }

    @PostMapping("/task")
    public String taskPost(Task task,
                           @RequestParam long projectId,
                           @RequestHeader(HttpHeaders.REFERER) final String referer) {
        if (projectService.findById(projectId).isPresent()) {
            task.setProject(projectService.findById(projectId).get());
        }
        taskService.save(task);
        return "redirect:" + referer;
    }

    @RequestMapping("/task/edit/{id}")
    public String taskEdit(Model model, @PathVariable long id) {
        if (taskService.findById(id).isPresent()) {
            model.addAttribute("task", taskService.findById(id).get());
            model.addAttribute("projects", projectService.findAll());
        }
        return "task/edit";
    }

    @RequestMapping("/task/delete/{id}")
    public String taskDelete(@PathVariable long id,
                             @RequestHeader(HttpHeaders.REFERER) final String referer) {
        if (taskService.findById(id).isPresent()) {
            taskService.delete(taskService.findById(id).get());
        }
        return "redirect:" + referer;
    }
}
