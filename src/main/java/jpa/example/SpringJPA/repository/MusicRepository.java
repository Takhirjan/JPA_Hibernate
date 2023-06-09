package jpa.example.SpringJPA.repository;


import jpa.example.SpringJPA.Model.MusicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<MusicModel,Long> {
  List<MusicModel> findAllByDurationGreaterThan(int duration );
}
