package uk.co.jamhammer.track.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.jamhammer.track.model.Project;

@Repository
public interface ProjectDao extends CrudRepository<Project, Long> {

}
