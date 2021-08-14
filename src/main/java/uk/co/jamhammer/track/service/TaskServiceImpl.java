package uk.co.jamhammer.track.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.jamhammer.track.data.TaskDao;
import uk.co.jamhammer.track.model.Task;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
  private TaskDao taskDao;

  @Override
  public Iterable<Task> findAll() {
    return taskDao.findAll();
  }

  @Override
  public Optional<Task> findById(long id) {
    return taskDao.findById(id);
  }

  @Override
  public Task save(Task task) {
    return taskDao.save(task);
  }

  @Override
  public void delete(Task task) {
    taskDao.delete(task);
  }
}
