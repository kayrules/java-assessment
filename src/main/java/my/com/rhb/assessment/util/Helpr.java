package my.com.rhb.assessment.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;

public class Helpr {

    public static Date stringToDate(String dateInString) {
        ZoneId zone = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateInString, formatter);
        Date date = Date.from(localDate.atStartOfDay().atZone(zone).toInstant());
        return date;
    }

    public static MappingJacksonValue jsonFilter(String filterName, Object obj, String... props) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(props);
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, filter);
        MappingJacksonValue mapping = new MappingJacksonValue(obj);
        mapping.setFilters(filters);
        return mapping;
    }

}