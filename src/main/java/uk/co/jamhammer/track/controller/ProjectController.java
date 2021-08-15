package uk.co.jamhammer.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.co.jamhammer.track.model.Customer;
import uk.co.jamhammer.track.model.Project;
import uk.co.jamhammer.track.service.CustomerService;
import uk.co.jamhammer.track.service.ProjectService;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/projects")
    public String projectList(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "project/list";
    }

    @RequestMapping("project/add")
    public String projectAdd(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("customers", customerService.findAll());
        return "project/add";
    }

    @PostMapping("/project")
    public String projectPost(Project project, @RequestParam long customerId) {
        if (customerService.findById(customerId).isPresent()) {
            project.setCustomer(customerService.findById(customerId).get());
        }
        projectService.save(project);
        return "redirect:/projects";
    }

    @RequestMapping("/project/edit/{id}")
    public String projectEdit(Model model, @PathVariable long id) {
        if (projectService.findById(id).isPresent()) {
            model.addAttribute("project", projectService.findById(id).get());
            model.addAttribute("customers", customerService.findAll());
        }
        return "project/edit";
    }

    @RequestMapping("/project/delete/{id}")
    public String projectDelete(@PathVariable long id) {
        if (projectService.findById(id).isPresent()) {
            projectService.delete(projectService.findById(id).get());
        }
        return "redirect:/projects";
    }
}
