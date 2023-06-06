package jpa.example.SpringJPA.Controller;

import jpa.example.SpringJPA.Model.MusicModel;
import jpa.example.SpringJPA.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
  @GetMapping(value = "/") // @WebServlet(value = "/") + doGet()
  public String indexPage(Model model) {
    List <MusicModel> musicModelList=musicRepository.findAll();
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
    return "addmusic";
  }

  @GetMapping(value = "/details/{musicId}")
  public String musicDetails(@PathVariable(name = "musicId") Long id, Model model) {
    MusicModel musicModel=musicRepository.findById(id).orElse(null);

    model.addAttribute("muzyka", musicModel);
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
