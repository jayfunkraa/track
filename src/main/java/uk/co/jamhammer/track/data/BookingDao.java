package uk.co.jamhammer.track.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.jamhammer.track.model.Booking;

@Repository
public interface BookingDao extends CrudRepository<Booking, Long> {

}
