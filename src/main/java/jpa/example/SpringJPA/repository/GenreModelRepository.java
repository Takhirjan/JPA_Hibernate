package jpa.example.SpringJPA.repository;

import jpa.example.SpringJPA.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreModelRepository extends JpaRepository<Genre,Long> {
}
