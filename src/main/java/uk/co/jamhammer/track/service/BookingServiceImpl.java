package uk.co.jamhammer.track.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.jamhammer.track.data.BookingDao;
import uk.co.jamhammer.track.model.Booking;

@Service
public class BookingServiceImpl implements BookingService {

  @Autowired
  private BookingDao bookingDao;

  @Override
  public Iterable<Booking> findAll() {
    return bookingDao.findAll();
  }

  @Override
  public Optional<Booking> findById(long id) {
    return bookingDao.findById(id);
  }

  @Override
  public Booking save(Booking booking) {
    return bookingDao.save(booking);
  }

  @Override
  public void delete(Booking booking) {
    bookingDao.delete(booking);
  }
}
