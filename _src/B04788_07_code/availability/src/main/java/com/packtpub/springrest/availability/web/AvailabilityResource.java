package com.packtpub.springrest.availability.web;

import com.packtpub.springrest.DateRange;
import com.packtpub.springrest.availability.AvailabilityQuery;
import com.packtpub.springrest.availability.AvailabilityService;
import com.packtpub.springrest.web.ApiResponse;
import com.packtpub.springrest.web.ApiResponse.ApiError;
import com.packtpub.springrest.web.ApiResponse.Status;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * RESTful endpoint for availability.
 *
 * @author Ludovic Dewailly
 */
@RestController
@RequestMapping("/availability")
public class AvailabilityResource {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    @Autowired
    private AvailabilityService availabilityService;

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse getAvailability(@RequestParam("from") String from,
                                       @RequestParam("until") String until,
                                       @RequestParam(value = "roomCategoryId", required = false) String categoryId,
                                       WebRequest request) {
        try {
            DateRange dateRange = new DateRange(DATE_FORMAT.parse(from), DATE_FORMAT.parse(until));
            if (dateRange.isBeforeNow()) {
                return new ApiResponse(Status.ERROR, null, new ApiError(10, "Date range is in the past"));
            }
            AvailabilityQuery query = new AvailabilityQuery(dateRange,
                    StringUtils.isBlank(categoryId) ? 0 : Long.parseLong(categoryId));
            // we use the last updated booking date as our Last Modified value
            Date lastUpdatedBooking = getLastModified(query);
            if (request.checkNotModified(lastUpdatedBooking.getTime())) {
                return null;
            }
            return new ApiResponse(Status.OK, availabilityService.getAvailableRooms(query)
                    .stream()
                    .map(status -> new AvailabilityStatusDTO(DATE_FORMAT.format(status.getDate()), status.getRooms()))
                    .collect(Collectors.toList()));
        } catch (ParseException e) {
            return new ApiResponse(Status.ERROR, null, new ApiError(17, "Invalid dates"));
        }
    }

    private Date getLastModified(AvailabilityQuery query) {
        Date lastUpdatedBooking = availabilityService.getLastUpdatedBooking(query);
        if (lastUpdatedBooking == null) {
            lastUpdatedBooking = DateRange.toStartOfDay(new Date());
        }
        return lastUpdatedBooking;
    }
}
