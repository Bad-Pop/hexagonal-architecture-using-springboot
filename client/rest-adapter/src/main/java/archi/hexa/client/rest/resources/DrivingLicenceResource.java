package archi.hexa.client.rest.resources;

import archi.hexa.client.rest.model.responses.DrivingLicenceDto;
import archi.hexa.client.rest.resources.api.DrivingLicenceResourceApi;
import archi.hexa.domain.ports.client.IDrivingLicenceClientPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.vavr.API.Tuple;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/driving-licence")
public class DrivingLicenceResource implements DrivingLicenceResourceApi {

  private final IDrivingLicenceClientPort drivingLicenceClientPort;

  @Override
  @PostMapping
  public ResponseEntity<DrivingLicenceDto> create(
      @RequestParam final String firstName, @RequestParam final String lastName) {
    return ResponseEntity.status(CREATED)
        .body(DrivingLicenceDto.from(drivingLicenceClientPort.create(Tuple(firstName, lastName))));
  }

  @Override
  @GetMapping(path = "/{licenceNumber}")
  public ResponseEntity<DrivingLicenceDto> get(@PathVariable final String licenceNumber) {
    //    return ResponseEntity.ok().body(drivingLicenceClientPort.getCurrentPoints(licenceNumber));
    // TODO IMPLEMENTS
    return null;
  }

  @Override
  @GetMapping(path = "/{licenceNumber}/points")
  public ResponseEntity<Integer> getPoints(@PathVariable final String licenceNumber) {
    return ResponseEntity.ok().body(drivingLicenceClientPort.getCurrentPoints(licenceNumber));
  }

  @Override
  @PutMapping(path = "/{licenceNumber}/points/remove")
  public ResponseEntity<DrivingLicenceDto> removePoints(
      @PathVariable final String licenceNumber, @RequestParam final String offenceCause) {
    return ResponseEntity.ok()
        .body(
            DrivingLicenceDto.from(
                drivingLicenceClientPort.removePoints(Tuple(licenceNumber, offenceCause))));
  }

  @Override
  @PutMapping(path = "/{licenceNumber}/points/add")
  public ResponseEntity<DrivingLicenceDto> addPoints(
      @PathVariable final String licenceNumber, @RequestParam final int points) {
    return ResponseEntity.ok()
        .body(
            DrivingLicenceDto.from(
                drivingLicenceClientPort.addPoints(Tuple(licenceNumber, points))));
  }

  @Override
  @PutMapping(path = "/{licenceNumber}/cancel")
  public ResponseEntity<DrivingLicenceDto> cancel(@PathVariable final String licenceNumber) {
    return ResponseEntity.ok()
        .body(DrivingLicenceDto.from(drivingLicenceClientPort.cancel(licenceNumber)));
  }
}
