package uk.co.jamhammer.track.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.jamhammer.track.data.ProjectDao;
import uk.co.jamhammer.track.model.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  private ProjectDao projectDao;

  @Override
  public Iterable<Project> findAll() {
    return projectDao.findAll();
  }

  @Override
  public Optional<Project> findById(long id) {
    return projectDao.findById(id);
  }

  @Override
  public Project save(Project project) {
    return projectDao.save(project);
  }

  @Override
  public void delete(Project project) {
    projectDao.delete(project);
  }
}
