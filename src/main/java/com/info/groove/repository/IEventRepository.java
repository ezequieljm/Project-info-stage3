package com.info.groove.repository;

import com.info.groove.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventRepository extends JpaRepository<Event, Long> {

}
