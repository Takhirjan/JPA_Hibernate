package jpa.example.SpringJPA.Controller;

import jpa.example.SpringJPA.Model.AuthorModel;
import jpa.example.SpringJPA.Model.Genre;
import jpa.example.SpringJPA.Model.MusicModel;
import jpa.example.SpringJPA.repository.AuthorModelRepository;
import jpa.example.SpringJPA.repository.GenreModelRepository;
import jpa.example.SpringJPA.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
  private final MusicRepository musicRepository;

  private final AuthorModelRepository authorModelRepository;
  private final GenreModelRepository genreModelRepository;

  @GetMapping(value = "/") // @WebServlet(value = "/") + doGet()
  public String indexPage(Model model,
                          @RequestParam(name = "key", required = false, defaultValue = "") String key) {
    List<MusicModel> musicModelList = musicRepository.searchMusics("%" + key + "%");
    model.addAttribute("musics", musicModelList);
    return "index";
  }

  @PostMapping(value = "/add-music")
  public String addMusic(MusicModel music) {
    musicRepository.save(music);
    return "redirect:/";
  }

  @GetMapping(value = "/add-music")
  public String addMudicPage(Model model) {
    List<AuthorModel> authorModels = authorModelRepository.findAll();
    model.addAttribute("authors", authorModels);
    return "addmusic";
  }

  @GetMapping(value = "/details/{musicId}")
  public String musicDetails(@PathVariable(name = "musicId") Long id, Model model) {
    MusicModel musicModel = musicRepository.findById(id).orElse(null);
    model.addAttribute("muzyka", musicModel);

    List<AuthorModel> authorModels = authorModelRepository.findAll();
    model.addAttribute("authors", authorModels);

    List<Genre> genres = genreModelRepository.findAll();
    genres.removeAll(musicModel.getGenres());
    model.addAttribute("genres", genres);
    return "details";
  }

  @PostMapping(value = "/save-music")
  public String saveMusic(MusicModel model) {
    musicRepository.save(model);
    return "redirect:/";
  }

  @PostMapping(value = "/delete-music")
  public String deleteMusic(@RequestParam(name = "id") Long id) {
    musicRepository.deleteById(id);
    return "redirect:/";
  }

  @PostMapping(value = "/assign-genre")
  public String assignGenre(@RequestParam(name = "music_id") Long musicId,
                            @RequestParam(name = "genre_id") Long genreId) {
    MusicModel musicModel = musicRepository.findById(musicId).orElseThrow();
    Genre genre = genreModelRepository.findById(genreId).orElseThrow();

    if (musicModel.getGenres() != null && musicModel.getGenres().size() > 0) {
      if (!musicModel.getGenres().contains(genre)) {
        musicModel.getGenres().add(genre); //Я в твои жанры докидыаю жанры

      }
    } else { // а если он пустой
        List<Genre> genres = new ArrayList<>(); //Я создам новый ArrayLust
        genres.add(genre); //Туда закину этот жанр
        musicModel.setGenres(genres); // переназначу твои жанры
      }
      musicRepository.save(musicModel);
      return "redirect:/details/" + musicId;
    }
    @PostMapping(value = "/unassign-genre")
    public String unassignGenre(@RequestParam(name = "music_id") Long musicId,
                                @RequestParam(name = "genre_id") Long genreId){
      MusicModel musicModel = musicRepository.findById(musicId).orElseThrow();
      Genre genre = genreModelRepository.findById(genreId).orElseThrow();

      if (musicModel.getGenres() != null && musicModel.getGenres().size() > 0) {
        musicModel.getGenres().remove(genre); //Я в твои жанры докидыаю жанры
      }
      musicRepository.save(musicModel);
      return "redirect:/details/" + musicId;
    }
  }

