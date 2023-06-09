package jpa.example.SpringJPA.repository;


import jpa.example.SpringJPA.Model.MusicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<MusicModel,Long> {
  List<MusicModel> findAllByDurationGreaterThan(int duration );

  @Query(
            value = "" +
          "SELECT  mm  FROM MusicModel  mm " +
          "WHERE mm.duration>0 and lower( mm.name) LIKE lower( :searchName)"+
          "order by mm.duration desc")
  List<MusicModel> searchMusics(@Param("searchName") String musicName);
}
