package jpa.example.SpringJPA.Controller;

import jpa.example.SpringJPA.Model.AuthorModel;
import jpa.example.SpringJPA.Model.MusicModel;
import jpa.example.SpringJPA.repository.AuthorModelRepository;
import jpa.example.SpringJPA.repository.MusicRepository;
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
public class HomeController {

  @Autowired
  private MusicRepository musicRepository;
  @Autowired
  private AuthorModelRepository authorModelRepository;
  @GetMapping(value = "/") // @WebServlet(value = "/") + doGet()
  public String indexPage(Model model,
                          @RequestParam(name = "key",required = false,defaultValue = "")String key) {
//    List <MusicModel> musicModelList=musicRepository.findAll();
//    List <MusicModel> musicModelList=musicRepository.findAllByDurationGreaterThan(0);
    List <MusicModel> musicModelList=musicRepository.searchMusics("%"+key+"%");
    model.addAttribute("musics", musicModelList);;
    return "index";
  }
  @PostMapping(value = "/add-music")
  public String addMusic(MusicModel music) {
    musicRepository.save(music);
    return "redirect:/";
  }
  @GetMapping(value = "/add-music")
  public String addMudicPage(Model model) {
  List<AuthorModel> authorModels=authorModelRepository.findAll();
  model.addAttribute("authors",authorModels);
  return "addmusic";
  }

  @GetMapping(value = "/details/{musicId}")
  public String musicDetails(@PathVariable(name = "musicId") Long id, Model model) {
    MusicModel musicModel=musicRepository.findById(id).orElse(null);
    model.addAttribute("muzyka", musicModel);

    List<AuthorModel> authorModels=authorModelRepository.findAll();
    model.addAttribute("authors",authorModels);
    return "details";
  }
  @PostMapping(value = "/save-music")
  public String saveMusic(MusicModel model){
  musicRepository.save(model);
  return "redirect:/";
  }
  @PostMapping(value = "/delete-music")
  public String deleteMusic(@RequestParam(name = "id") Long id){
    musicRepository.deleteById(id);
    return "redirect:/";
  }
}
