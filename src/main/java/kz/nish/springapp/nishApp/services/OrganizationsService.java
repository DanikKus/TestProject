package kz.nish.springapp.nishApp.services;

import kz.nish.springapp.nishApp.models.Organization;
import kz.nish.springapp.nishApp.repositories.OrganizationsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrganizationsService {

    private final OrganizationsRepository organizationsRepository;

    public OrganizationsService(OrganizationsRepository organizationsRepository) {
        this.organizationsRepository = organizationsRepository;
    }


    public List<Organization> findALL(){
        return organizationsRepository.findAll();
    }

    public Organization findOne(int id){
        Optional<Organization> foundOrganization = organizationsRepository.findById(id);
        return foundOrganization.orElseThrow();
    }

    @Transactional
    public void save(Organization organization){
        organizationsRepository.save(organization);
    }
}
