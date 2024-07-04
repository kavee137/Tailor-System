package lk.ijse.tailorsystem.entity;

import lk.ijse.tailorsystem.dto.ReservationDTO;
import lk.ijse.tailorsystem.dto.ReservationDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode

public class PlaceReservation {
    private Reservation reservation;
    private List<ReservationDetails> rdList;
}
