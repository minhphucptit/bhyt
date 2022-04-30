package com.nminh.bhyt.repository;

import com.nminh.bhyt.model.HouseHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldHouseRepository extends JpaRepository<HouseHold,Integer>, JpaSpecificationExecutor<HouseHold> {
}
