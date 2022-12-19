package com.info.groove.repository;

import com.info.groove.entity.UniqueTurn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUniqueTurnRepository extends JpaRepository<UniqueTurn,Long> {
}
