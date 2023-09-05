package com.frankmoley.lil.landonhotel;

import com.frankmoley.lil.landonhotel.data.repository.GuestRepository;
import com.frankmoley.lil.landonhotel.data.repository.ReservationRepository;
import com.frankmoley.lil.landonhotel.data.repository.RoomRepository;
import com.frankmoley.lil.landonhotel.service.RoomReservationService;
import com.frankmoley.lil.landonhotel.service.model.RoomReservation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CLRunner implements CommandLineRunner {

  private final RoomRepository roomRepository;
  private final GuestRepository guestRepository;
  private final ReservationRepository reservationRepository;
  private final RoomReservationService roomReservationService;

  public CLRunner(RoomRepository roomRepository, GuestRepository guestRepository,
      ReservationRepository reservationRepository, RoomReservationService roomReservationService) {
    this.roomRepository = roomRepository;
    this.guestRepository = guestRepository;
    this.reservationRepository = reservationRepository;
    this.roomReservationService = roomReservationService;
  }

  @Override
  public void run(String... args) throws Exception {
   List<RoomReservation> reservations = this.roomReservationService.getRoomReservationsForDate("2023-08-28");
   reservations.forEach(System.out::println);
  }
}
