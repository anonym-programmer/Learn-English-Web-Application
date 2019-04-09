package pl.robert.api.app.vote.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface VoteRepository extends JpaRepository<Vote, Long> {
}
