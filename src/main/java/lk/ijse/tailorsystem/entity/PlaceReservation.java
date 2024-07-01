package lk.ijse.tailorsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode

public class PlaceReservation {
    private Reservation reservation;
    private List<ReservationDetails> rdList;
}
