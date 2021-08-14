package uk.co.jamhammer.track.service;

import java.util.Optional;
import uk.co.jamhammer.track.model.Project;

public interface ProjectService {
  Iterable<Project> findAll();
  Optional<Project> findById(long id);
  Project save(Project project);
  void delete(Project project);
}
