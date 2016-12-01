package com.ge.pw.ibct.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.ge.pw.ibct.entity.CcttProductLineEntity;

/**
 * Repository : CcttProductLine.
 */
public interface CcttProductLineJpaRepository extends PagingAndSortingRepository<CcttProductLineEntity, String> {

}
