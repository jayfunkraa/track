package uk.co.jamhammer.track.service;

import java.util.Optional;
import uk.co.jamhammer.track.model.Booking;

public interface BookingService {
  Iterable<Booking> findAll();
  Optional<Booking> findById(long id);
  Booking save(Booking booking);
  void delete(Booking booking);
}
