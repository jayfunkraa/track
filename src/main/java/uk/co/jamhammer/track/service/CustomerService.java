package uk.co.jamhammer.track.service;

import java.util.Optional;
import uk.co.jamhammer.track.model.Booking;
import uk.co.jamhammer.track.model.Customer;

public interface CustomerService {
  Iterable<Customer> findAll();
  Optional<Customer> findById(long id);
  Customer save(Customer customer);
  void delete(Customer customer);
}
