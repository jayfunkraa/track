package uk.co.jamhammer.track.service;

import java.util.Optional;
import uk.co.jamhammer.track.model.Task;

public interface TaskService {
  Iterable<Task> findAll();
  Optional<Task> findById(long id);
  Task save(Task task);
  void delete(Task task);

}
