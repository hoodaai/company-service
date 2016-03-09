package com.service.impl;

import com.persistent.entities.BeneficialOwner;
import com.persistent.entities.Company;
import com.persistent.repositories.CompanyRepository;
import com.rest.dto.BeneficialOwnerDTO;
import com.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class contains service methods for company
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{


    private final Logger log = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Page<Company> findAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public Optional<Company> getByName(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    public void updateCompany(Company company) {

        companyRepository.findByName(company.getName()).ifPresent(c -> {
            c.setEmail(company.getEmail());
            c.setAddress(company.getAddress());
            c.setCity(company.getCity());
            c.setCountry(company.getCountry());
            c.setName(company.getName());
            c.setPhoneNumber(company.getPhoneNumber());
            c.setBeneficialOwner(company.getBeneficialOwner());
            companyRepository.save(c);
            log.debug("Changed Information for User: {}", c);
        });
    }

    @Override
    public void saveBeneficialOwner(Integer companyId, BeneficialOwnerDTO beneficialOwner) {
        companyRepository.findByCompanyId(companyId).ifPresent(company -> {
            BeneficialOwner bo = new BeneficialOwner();
            bo.setName(beneficialOwner.getName());
            bo.setCompany(company);
            List beneficialList = new ArrayList<>();
            beneficialList.add(bo);
            company.setBeneficialOwner(beneficialList);

            companyRepository.save(company);
            log.debug("Changed Information for User: {}", company);
        });
    }
}
