package kz.nish.springapp.nishApp.repositories;

import kz.nish.springapp.nishApp.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationsRepository extends JpaRepository<Organization, Integer> {
}
