package uk.co.jamhammer.track.model;

import javax.persistence.*;

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
}
