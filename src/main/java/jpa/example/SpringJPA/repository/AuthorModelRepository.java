package jpa.example.SpringJPA.repository;

import jpa.example.SpringJPA.Model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorModelRepository extends JpaRepository<AuthorModel,Long> {

}
