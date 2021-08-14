package uk.co.jamhammer.track.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.jamhammer.track.data.CustomerDao;
import uk.co.jamhammer.track.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

  @Autowired
  private CustomerDao customerDao;

  @Override
  public Iterable<Customer> findAll() {
    return customerDao.findAll();
  }

  @Override
  public Optional<Customer> findById(long id) {
    return customerDao.findById(id);
  }

  @Override
  public Customer save(Customer customer) {
    return customerDao.save(customer);
  }

  @Override
  public void delete(Customer customer) {
    customerDao.delete(customer);
  }
}
