package OneToMany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import OneToMany.entity.UserFeedback;

public interface FeedbackRepository extends JpaRepository<UserFeedback, Integer> {

}
