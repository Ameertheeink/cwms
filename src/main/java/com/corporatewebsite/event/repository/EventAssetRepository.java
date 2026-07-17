package com.corporatewebsite.event.repository;

import com.corporatewebsite.event.entity.EventAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventAssetRepository extends JpaRepository<EventAsset,Long> {

    List<EventAsset> findByEventIdOrderByDisplayOrderAsc(Long eventId);
    Optional<EventAsset> findByIdAndEventId(Long assetId,
                                            Long eventId);
    List<EventAsset> findByEventId(Long eventId);
}
