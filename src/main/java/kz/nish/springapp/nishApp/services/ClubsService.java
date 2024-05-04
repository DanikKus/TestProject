package kz.nish.springapp.nishApp.services;

import kz.nish.springapp.nishApp.models.Clubs;
import kz.nish.springapp.nishApp.repositories.ClubsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClubsService {

    private final ClubsRepository clubsRepository;

    public ClubsService(ClubsRepository clubsRepository) {
        this.clubsRepository = clubsRepository;
    }


    public List<Clubs> findALL(){
        return clubsRepository.findAll();
    }

    public Clubs findOne(int id){
        Optional<Clubs> foundOrganization = clubsRepository.findById(id);
        return foundOrganization.orElseThrow();
    }

    @Transactional
    public void save(Clubs clubs){
        clubsRepository.save(clubs);
    }
}
