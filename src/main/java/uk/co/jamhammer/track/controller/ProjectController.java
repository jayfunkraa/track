package uk.co.jamhammer.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.co.jamhammer.track.model.Customer;
import uk.co.jamhammer.track.model.Project;
import uk.co.jamhammer.track.model.Task;
import uk.co.jamhammer.track.service.CustomerService;
import uk.co.jamhammer.track.service.ProjectService;
import uk.co.jamhammer.track.service.TaskService;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("/projects")
    public String projectList(Model model) {
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("project", new Project());
        model.addAttribute("customers", customerService.findAll());
        return "project/list";
    }

    @PostMapping("/project")
    public String projectPost(Project project,
                              @RequestParam long customerId,
                              @RequestHeader(HttpHeaders.REFERER) final String referer) {
        if (customerService.findById(customerId).isPresent()) {
            project.setCustomer(customerService.findById(customerId).get());
        }
        projectService.save(project);
        return "redirect:" + referer;
    }

    @RequestMapping("/project/delete/{id}")
    public String projectDelete(@PathVariable long id,
                                @RequestHeader(HttpHeaders.REFERER) final String referer) {
        if (projectService.findById(id).isPresent()) {
            projectService.delete(projectService.findById(id).get());
        }
        return "redirect:" + referer;
    }

    @RequestMapping("/project/view/{id}")
    public String projectView(@PathVariable long id, Model model) {
        if (projectService.findById(id).isPresent()) {
            model.addAttribute("project", projectService.findById(id).get());
        }
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("task", new Task());
        return "project/view";
    }

    @PostMapping("/project/{projectId}/task")
    public String projectAddTask(@PathVariable long projectId,
                                 Task task,
                                 @RequestHeader(HttpHeaders.REFERER) final String referer) {
        if (projectService.findById(projectId).isPresent() && task.getProject() == null) {
            task.setProject(projectService.findById(projectId).get());
        }
        taskService.save(task);
        return "redirect:" + referer;
    }
}
