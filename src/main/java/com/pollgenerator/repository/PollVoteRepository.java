package com.pollgenerator.repository;

import com.pollgenerator.model.PollVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PollVoteRepository extends JpaRepository<PollVote, UUID> {

    List<PollVote> findByIpAddressAndPollOption_Id(String ipAddress, UUID pollOptionId);

    List<PollVote> findByIpAddressAndPollOption_Poll_Id(String ipAddress, UUID pollId);
}
