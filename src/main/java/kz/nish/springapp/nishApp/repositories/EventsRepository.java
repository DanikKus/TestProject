package kz.nish.springapp.nishApp.repositories;

import kz.nish.springapp.nishApp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Event, Integer> {
}
