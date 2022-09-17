package ch.lubu.timekeeper.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeRepository extends JpaRepository<Time, Integer> {

    // todo: useless?
    List<Time> findByAmount(String amount);

    List<Time> findByYear_Year(int year);

}