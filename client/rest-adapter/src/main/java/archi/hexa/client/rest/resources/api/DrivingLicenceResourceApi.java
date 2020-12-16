package archi.hexa.client.rest.resources.api;

import archi.hexa.client.rest.model.responses.DrivingLicenceDto;
import archi.hexa.client.rest.model.responses.ErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Driving Licence Resource")
public interface DrivingLicenceResourceApi {

  @Operation(summary = "Create a new driving licence")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Successfully created a new driving licence !",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DrivingLicenceDto.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Bad request. You must check your call params",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDto.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Unknown internal error",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDto.class)))
      })
  ResponseEntity<DrivingLicenceDto> create(
      @RequestParam final String firstName, @RequestParam final String lastName);

  @Operation(summary = "Get a driving licence by it's number")
  ResponseEntity<DrivingLicenceDto> get(@PathVariable final String licenceNumber);

  @Operation(summary = "Get a driving licence's current points")
  ResponseEntity<Integer> getPoints(@PathVariable final String licenceNumber);

  @Operation(summary = "Remove some points from a driving licence")
  ResponseEntity<DrivingLicenceDto> removePoints(
      @PathVariable final String licenceNumber, @RequestParam final String offenceCause);

  @Operation(summary = "Add some points to a driving licence")
  ResponseEntity<DrivingLicenceDto> addPoints(
      @PathVariable final String licenceNumber, @RequestParam final int points);

  @Operation(summary = "Cancel a driving licence")
  ResponseEntity<DrivingLicenceDto> cancel(@PathVariable final String licenceNumber);
}
