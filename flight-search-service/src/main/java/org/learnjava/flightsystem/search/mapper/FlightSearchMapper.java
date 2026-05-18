package org.learnjava.flightsystem.search.mapper;

import org.learnjava.flightsystem.search.dto.FlightDto;
import org.learnjava.flightsystem.search.entity.Flight;
import org.mapstruct.Mapper;

@Mapper(config = MapperSpringConfig.class)
public interface FlightSearchMapper {

    FlightDto convertToFlightDto(Flight flight);

    Flight convertToFlightEntity(FlightDto flightDto);
}