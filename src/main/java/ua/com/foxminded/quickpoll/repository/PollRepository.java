package ua.com.foxminded.quickpoll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.foxminded.quickpoll.domain.Poll;

public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {

}