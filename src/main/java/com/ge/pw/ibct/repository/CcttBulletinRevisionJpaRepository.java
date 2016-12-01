package com.ge.pw.ibct.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.ge.pw.ibct.entity.CcttBulletinRevisionEntity;
import com.ge.pw.ibct.entity.CcttBulletinRevisionEntityKey;

/**
 * Repository : CcttBulletinRevision.
 */
public interface CcttBulletinRevisionJpaRepository extends PagingAndSortingRepository<CcttBulletinRevisionEntity, CcttBulletinRevisionEntityKey> {

}
