package my.com.rhb.assessment.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import my.com.rhb.assessment.exception.ResourceAlreadyExistsException;
import my.com.rhb.assessment.exception.ResourceNotFoundException;
import my.com.rhb.assessment.model.AdHoc;
import my.com.rhb.assessment.repository.AdhocRepository;
import my.com.rhb.assessment.util.Helpr;

@RestController
public class AdhocController {

    @Autowired
    AdhocRepository adhocRepository;

    /**
     * [C]rud - Create Adhoc record
     *
     * @return the adhoc post result and data source ID in json format
     */
    @PostMapping("/adhoc")
    public MappingJacksonValue createAdhoc(@RequestParam(value = "date", required = true) String date) {

        Date now = new Date();
        Date newDate = Helpr.stringToDate(date);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<AdHoc> ls = adhocRepository.findByDate(newDate);
        if (ls.size() > 0)
            throw new ResourceAlreadyExistsException("record `" + date + "` already exists");

        // Boolean exists = adhocRepository.existsByDate(newDate);
        // System.out.println(exists);
        // if (exists)
        // throw new ResourceAlreadyExistsException("record `" + date + "` already
        // exists");

        AdHoc adhoc = new AdHoc();
        adhoc.setDate(newDate);
        adhoc.setStatus("ok");
        adhoc.setCreatedAt(now);
        adhoc.setUpdatedAt(now);
        adhoc.setCreatedBy(username);
        adhoc.setUpdatedBy(username);

        adhocRepository.save(adhoc);

        return Helpr.jsonFilter("AdhocFilter", adhoc, "id", "date", "status", "createdAt", "createdBy");
    }

    /**
     * c[R]ud - GET all adhoc records
     *
     * @return the adhoc post result and data source ID in json format
     */
    @GetMapping("/adhoc")
    public MappingJacksonValue getAllAdhoc() {

        List<AdHoc> adhocList = adhocRepository.findAll();
        return Helpr.jsonFilter("AdhocFilter", adhocList, "id", "date", "updatedAt", "updatedBy");
    }

    /**
     * c[R]ud - GET single adhoc record by id
     *
     * @return the adhoc post result and data source ID in json format
     */
    @GetMapping("/adhoc/{id}")
    public MappingJacksonValue getAdhocById(@PathVariable("id") Long id) {

        AdHoc adhoc = adhocRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no adhoc date found with id `" + id + "`"));

        return Helpr.jsonFilter("AdhocFilter", adhoc, "id", "date", "updatedAt", "updatedBy");
    }

    /**
     * c[R]ud - GET all soft-deleted adhoc records
     *
     * @return the adhoc post result for deleted records in json format
     */
    @GetMapping("/adhoc/deleted")
    public MappingJacksonValue getDeletedAdhoc() {

        List<AdHoc> adhocList = adhocRepository.recycleBin();
        return Helpr.jsonFilter("AdhocFilter", adhocList, "id", "date", "updatedAt", "updatedBy");
    }

    /**
     * cr[U]d - Update single adhoc record by id
     *
     * @return the adhoc post result and data source ID in json format
     */
    @PutMapping("/adhoc/{id}")
    public MappingJacksonValue updateAdhocById(@PathVariable("id") Long id,
            @RequestParam(value = "date", required = true) String date) {

        Date now = new Date();
        Date newDate = Helpr.stringToDate(date);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<AdHoc> ls = adhocRepository.findByDate(newDate);
        if (ls.size() > 0)
            throw new ResourceAlreadyExistsException("record `" + date + "` already exists");

        AdHoc adhoc = adhocRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no adhoc date found with id `" + id + "`"));
        adhoc.setDate(newDate);
        adhoc.setStatus("ok");
        adhoc.setUpdatedAt(now);
        adhoc.setUpdatedBy(username);

        adhocRepository.save(adhoc);

        return Helpr.jsonFilter("AdhocFilter", adhoc, "id", "date", "status", "updatedAt", "updatedBy");
    }

    /**
     * cru[D] - Delete single adhoc record by id
     *
     * @return the adhoc post result and data source ID in json format
     */
    @DeleteMapping("/adhoc/{id}")
    public MappingJacksonValue deleteAdhocById(@PathVariable("id") Long id) {

        AdHoc adhoc = adhocRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("no adhoc date found with id `" + id + "`"));

        adhocRepository.softDeleteById(id);
        adhoc.setStatus("ok");

        return Helpr.jsonFilter("AdhocFilter", adhoc, "id", "date", "status", "updatedAt", "updatedBy");
    }

}