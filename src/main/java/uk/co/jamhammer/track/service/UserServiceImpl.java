package uk.co.jamhammer.track.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.jamhammer.track.data.UserDao;
import uk.co.jamhammer.track.model.User;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public Iterable<User> findAll() {
    return userDao.findAll();
  }

  @Override
  public Optional<User> findById(long id) {
    return userDao.findById(id);
  }

  @Override
  public User save(User user) {
    return userDao.save(user);
  }

  @Override
  public void delete(User user) {
    userDao.delete(user);
  }
}
