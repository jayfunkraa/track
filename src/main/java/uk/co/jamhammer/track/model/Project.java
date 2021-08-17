package uk.co.jamhammer.track.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  private Customer customer;

  @OneToMany(mappedBy = "project")
  private List<Task> tasks;

  public Project() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public double getTotalHours() {
    double total = 0;
    for (Task task : getTasks()) {
      for (Booking booking : task.getBookings()) {
        total += booking.getHours();
      }
    }
    return total;
  }
}
