package co.com.poli.cine.demo.repositories;


import co.com.poli.cine.demo.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findAll();
    Booking findBookingById(Long id);
    List<Booking> findBookingByUserId(Long userId);
}
