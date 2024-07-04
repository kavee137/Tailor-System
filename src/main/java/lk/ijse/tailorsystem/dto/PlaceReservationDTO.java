package lk.ijse.tailorsystem.dto;

import lk.ijse.tailorsystem.entity.Reservation;
import lk.ijse.tailorsystem.entity.ReservationDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode

public class PlaceReservationDTO {
    private Reservation reservation;
    private List<ReservationDetails> rdList;

}
