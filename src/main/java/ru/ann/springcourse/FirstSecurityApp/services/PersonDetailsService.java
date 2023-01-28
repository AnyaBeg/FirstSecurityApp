package ru.ann.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ann.springcourse.FirstSecurityApp.models.Person;
import ru.ann.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.ann.springcourse.FirstSecurityApp.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       Optional<Person> person =  peopleRepository.findByUsername(s);
       if (person.isEmpty())
           throw new UsernameNotFoundException("Пользователь не найден");
       return  new PersonDetails(person.get());
    }
}
