package uk.co.jamhammer.track.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String name;
  private String address;

  @OneToMany(mappedBy = "customer")
  private List<Project> projects;

  public Customer() {
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public double getTotalHours() {
    double total = 0;
    for (Project project : getProjects()) {
      for (Task task : project.getTasks()) {
        for (Booking booking : task.getBookings()) {
          total += booking.getHours();
        }
      }
    }
    return total;
  }
}
