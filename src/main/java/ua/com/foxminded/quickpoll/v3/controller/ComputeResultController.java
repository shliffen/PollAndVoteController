package ua.com.foxminded.quickpoll.v3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.quickpoll.domain.Vote;
import ua.com.foxminded.quickpoll.dto.OptionCount;
import ua.com.foxminded.quickpoll.dto.VoteResult;
import ua.com.foxminded.quickpoll.repository.VoteRepository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RestController("ComputeResultControllerV3")
@RequestMapping({"/v3/", "/oauth2/v3/"})
public class ComputeResultController {
    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(value = "/computeresult/{pollId}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseEntity<?> computeResult(@PathVariable Long pollId) {
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        VoteResult voteResult = countVotes(allVotes);
        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }

    private VoteResult countVotes(Iterable<Vote> allVotes) {
        int totalVotes = 0;
        VoteResult voteResult = new VoteResult();
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for (Vote v : allVotes) {
            totalVotes++;
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if (optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(),
                            optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());
        return voteResult;
    }
}
