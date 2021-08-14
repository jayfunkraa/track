package uk.co.jamhammer.track.service;

import java.util.Optional;
import uk.co.jamhammer.track.model.User;

public interface UserService {
  Iterable<User> findAll();
  Optional<User> findById(long id);
  User save(User user);
  void delete(User user);

}
