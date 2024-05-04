package kz.nish.springapp.nishApp.services;

import kz.nish.springapp.nishApp.models.Event;
import kz.nish.springapp.nishApp.repositories.EventsRepository;
import kz.nish.springapp.nishApp.util.EventNotCreatedException;
import kz.nish.springapp.nishApp.util.EventNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EventsService {

    private final EventsRepository eventsRepository;

    public EventsService(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public List<Event> findAll(){
        return eventsRepository.findAll();
    }

    public Event findOne(int id){
        Optional<Event> foundEvent = eventsRepository.findById(id);
        return foundEvent.orElseThrow(EventNotFoundException::new);
    }

    @Transactional
    public void save(Event event){
        eventsRepository.save(event);
    }

}
