package com.nminh.bhyt.repository;

import com.nminh.bhyt.model.Inform;
import com.nminh.bhyt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformRepository extends JpaRepository<Inform,Integer>, JpaSpecificationExecutor<Inform> {

    List<Inform> findAllByFamilyCode(String familyCode);
}
