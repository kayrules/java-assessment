package my.com.rhb.assessment.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import my.com.rhb.assessment.model.AdHoc;

@Repository
public interface AdhocRepository extends JpaRepository<AdHoc, Long> {

    public boolean existsByDate(Date date);

    @Transactional
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleteFlag=false AND e.date=?1")
    public List<AdHoc> findByDate(Date date);

    @Override
    @Transactional
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleteFlag=false")
    public List<AdHoc> findAll();

    @Override
    @Transactional
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleteFlag=false AND e.id=?1")
    public Optional<AdHoc> findById(Long id);

    // Look up deleted entities
    @Transactional
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleteFlag=true")
    public List<AdHoc> recycleBin();

    // Soft delete.
    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.deleteFlag=true WHERE e.id=?1")
    public void softDeleteById(Long id);
}