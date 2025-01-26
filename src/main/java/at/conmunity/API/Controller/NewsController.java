package at.conmunity.API.Controller;

import at.conmunity.API.Interface.IController;
import at.conmunity.API.Model.News;
import at.conmunity.API.Repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Controller to handle HTTP-Requests on a {@link News} Object.
 *
 * @author tempelhoff
 */
@RestController()
@CrossOrigin(origins = "http://localhost:4200")
public class NewsController implements IController<News> {
    private final NewsRepository repository;
    private final Logger log = LoggerFactory.getLogger(NewsController.class);

    public NewsController(NewsRepository repository){
        this.repository = repository;
    }

    /**
     * Creates a new {@link News} and saves it in the DB
     * @param obj The new {@link News} we want saved
     * @return A ResponseEntity which returns a HTTPStatus-Code and the {@link News} we just saved.
     */
    @PostMapping("/News")
    @Override
    public ResponseEntity<News> create(@RequestBody News obj) {
        if(obj == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        log.info(obj.toString());
        News news = repository.save(obj);
        return new ResponseEntity<>(news, HttpStatus.CREATED);
    }

    /**
     * Gets a List of news in descending order of creation date. If given a newsID it will only get that one.
     * @param ID The newsID we want to search for. If all are wanted give null.
     * @return A ResponseEntity which returns a HTTPStatus-Code and the {@link News} we wanted to query.
     */
    @GetMapping("/News")
    @Override
    public ResponseEntity<List<News>> get(@RequestParam(name = "newsID", required = false) Long ID) {
        List<News> news;
        if(ID == null){
            news = repository.findAllByOrderByCreatedDesc();
        }
        else if(!repository.existsById(ID)) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        else {
            news = new ArrayList<>();
            News news1 = repository.findById(ID).orElse(null);
            news.add(news1);
        }
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    /**
     * Gets a List of {@link News} which can be used for a paginated list/display.
     * @param amount The amount of {@link News} you want to display at each site.
     * @param site The site you're currently on.
     * @return A ResponseEntity which includes an HTTP-Status-Code and the List of {@link News} in descending order by date of creation.
     */
    @GetMapping("/News/Paginated")
    public ResponseEntity<News[]> getPaginated(@RequestParam(name = "amount", required = false, defaultValue = "10")int amount, @RequestParam(name = "site") int site){
        News[] news = new News[amount];
        List<News> newsList = repository.findAllByOrderByCreatedDesc();
        int x = 0;
        for (int i = (site - 1) * amount; i < site * amount; i++){
            if(i >= newsList.size() - 1){
                break;
            }
            news[x] = newsList.get(i);
            x++;
        }
        log.info("News: {}", Arrays.toString(news));
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    /**
     * Updates a {@link News} and saves it in the database.
     * @param obj The {@link News} you want to edit, must contain edited data.
     * @return A ResponseEntity which returns an HTTP-Status-Code and the {@link News} which was just edited.
     */
    @PutMapping("/News")
    @Override
    public ResponseEntity<News> update(@RequestBody News obj) {
        News news = repository.findById(obj.getNewsID()).orElse(null);
        if(news == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        news.doMap(obj);
        news = repository.save(news);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    /**
     * Deletes a {@link News} from the database.
     * @param id The newsID for the {@link News} you want to delete.
     * @return A ResponseEntity which contains an HTTP-Status-Code and the {@link News} we just deleted.
     */
    @Override
    public ResponseEntity<News> delete(@RequestParam(name = "newsID") Long id) {
        News news = repository.findById(id).orElse(null);
        if(news == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        repository.delete(news);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}
