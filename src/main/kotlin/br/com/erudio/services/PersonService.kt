package br.com.erudio.services

import br.com.erudio.repository.exceptions.ResourceNotFoundException
import br.com.erudio.model.Person
import br.com.erudio.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<Person> {
        logger.info("Finding all people!")

        return repository.findAll()
    }

    fun findById(id: Long): Person {
        logger.info("Finding one person!")
        return repository.findById(id).orElseThrow({ ResourceNotFoundException("Não econtrado pessoa") })
    }

    fun create(person: Person): Person {
        logger.info("Create person")

        return repository.save(person)

    }

    fun update(entity: Person) : Person {
        logger.info("update person")

        val person = repository.findById(entity.id).orElseThrow({ ResourceNotFoundException("Não econtrado pessoa") })

        person.firstName = entity.firstName
        person.lastName = entity.lastName
        person.address = entity.address
        person.gender = entity.gender

        return repository.save(person)
    }

    fun delete(id: Long) {
        logger.info("delete person")
        val person = repository.findById(id).orElseThrow({ ResourceNotFoundException("Não econtrado pessoa") })

        repository.delete(person)

    }


}