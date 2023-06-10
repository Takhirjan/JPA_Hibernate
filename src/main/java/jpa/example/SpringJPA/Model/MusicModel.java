package jpa.example.SpringJPA.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_musics")
@Getter
@Setter
public class MusicModel {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment
 @Column(name = "id")
  private Long id;

 @Column(name = "music_name")
  private String  name;

 @Column(name = "duration")
  private int duration;

 @ManyToOne
 private AuthorModel authorModel;
//
// @Column(name = "author")
//  private String author;

 @ManyToMany
 private List<Genre> genres;
 @PrePersist
 public void checkForNegativeDuration(){
  if(this.duration<=0){
   this.duration=180;
  }
 }
}
