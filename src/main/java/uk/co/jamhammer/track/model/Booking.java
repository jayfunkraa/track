package uk.co.jamhammer.track.model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private double hours;
  private String remarks;

  @ManyToOne
  private Task task;

  public Booking() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public double getHours() {
    return hours;
  }

  public void setHours(double hours) {
    this.hours = hours;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public String getTimeSpanHours() {
    double hours = this.getHours();
    String hh = String.valueOf((int) hours);
    String mm = String.format("%02d", Math.round((hours % 1) * 60));
    return hh + ":" + mm;
  }
}
