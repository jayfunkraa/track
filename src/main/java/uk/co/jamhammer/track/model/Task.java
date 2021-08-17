package uk.co.jamhammer.track.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;
  private String description;
  private boolean chargeable;

  @ManyToOne
  private Project project;

  @OneToMany(mappedBy = "task")
  private List<Booking> bookings;

  public Task() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isChargeable() {
    return chargeable;
  }

  public void setChargeable(boolean chargeable) {
    this.chargeable = chargeable;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public List<Booking> getBookings() {
    return bookings;
  }

  public void setBookings(List<Booking> bookings) {
    this.bookings = bookings;
  }
}
