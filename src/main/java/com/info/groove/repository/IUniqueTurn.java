package com.info.groove.repository;

import com.info.groove.entity.UniqueTurn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUniqueTurn extends JpaRepository<UniqueTurn,Long> {
}
