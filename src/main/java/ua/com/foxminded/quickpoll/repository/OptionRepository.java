package ua.com.foxminded.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.foxminded.quickpoll.domain.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {

}
