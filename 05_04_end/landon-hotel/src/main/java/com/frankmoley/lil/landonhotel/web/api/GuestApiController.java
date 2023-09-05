package com.frankmoley.lil.landonhotel.web.api;

import com.frankmoley.lil.landonhotel.data.entity.Guest;
import com.frankmoley.lil.landonhotel.data.repository.GuestRepository;
import com.frankmoley.lil.landonhotel.web.exception.BadRequestException;
import com.frankmoley.lil.landonhotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestApiController {

  private final GuestRepository guestRepository;

  public GuestApiController(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }

  @GetMapping
  public List<Guest> getAllGuests(){
    return this.guestRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Guest addGuest(@RequestBody Guest guest){
    return this.guestRepository.save(guest);
  }

  @GetMapping("/{id}")
  public Guest getGuest(@PathVariable("id")long id){
    Optional<Guest> guest = this.guestRepository.findById(id);
    if (guest.isEmpty()){
      throw new NotFoundException("guest not found with id: " + id);
    }
    return guest.get();
  }

  @PutMapping("/{id}")
  public Guest updateGuest(@PathVariable("id")long id, @RequestBody Guest guest){
   if (id != guest.getId()){
     throw new BadRequestException("id on path must match body");
   }
   return guestRepository.save(guest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteGuest(@PathVariable("id")long id){
    this.guestRepository.deleteById(id);
  }
}
