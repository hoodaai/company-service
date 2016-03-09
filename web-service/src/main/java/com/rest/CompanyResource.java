package com.rest;

import com.persistent.entities.Company;
import com.persistent.repositories.CompanyRepository;
import com.rest.dto.BeneficialOwnerDTO;
import com.rest.util.HeaderUtil;
import com.rest.util.PaginationUtil;
import com.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CompanyResource {

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CompanyResource.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    /**
     * Create new company
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/company", method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCompany(@Valid @RequestBody Company company) {
        return companyRepository.findByName(company.getName())
                .map(comp -> new ResponseEntity<>("Company name already in use", HttpStatus.BAD_REQUEST))
                .orElseGet(() -> companyRepository.findByEmail(company.getEmail())
                                .map(comp -> new ResponseEntity<>("e-mail address already in use", HttpStatus.BAD_REQUEST))
                                .orElseGet(() -> {
                                    companyService.save(company);
                                    return new ResponseEntity<>(HttpStatus.CREATED);
                                })
                );
    }

    /**
     * Get a list of all companies
     * @return
     */
    @RequestMapping(value = "/companies", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> getAllCompany(Pageable pageable)
        throws URISyntaxException {
            Page<Company> page = companyService.findAllCompanies(pageable);
            List<Company> managedUserDTOs = page.getContent().stream()
                    .map(user -> new Company(user))
                    .collect(Collectors.toList());
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/companies");
            return new ResponseEntity<>(managedUserDTOs, headers, HttpStatus.OK);

    }


    /**
     * Get details about a company
     * @return
     */
    @RequestMapping(value = "/company/{name}", method = RequestMethod.GET)
    public ResponseEntity<Company> getCompany(@PathVariable String name) {
        return Optional.ofNullable(companyService.getByName(name))
                .map(company -> new ResponseEntity<>(company.get(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     *  update a company
     * @return
     */
    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCompany(@RequestBody Company company){

        Optional<Company> existingCompany = companyService.getByName(company.getName());

        if(!existingCompany.isPresent()) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("company-management",
                    "companynotexists", "Company does not exist")).body(null);
        }

        return companyRepository
                .findByName(company.getName())
                .map(u -> {
                    companyService.updateCompany(company);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * add beneficial owner(s) of the company
     * @return
     */
    @RequestMapping(value = "/company/{companyName}", method = RequestMethod.POST)
    public ResponseEntity<?> saveBeneficialOwner(@RequestBody BeneficialOwnerDTO beneficialOwner,
                                                 @PathVariable String companyName){

        try {
            companyService.saveBeneficialOwner(companyName, beneficialOwner);
        }catch (Exception e){
            logger.error(e.getMessage());
            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
