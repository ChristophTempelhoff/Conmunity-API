package at.conmunity.API.Controller;

import at.conmunity.API.Exception.MemberNotFoundException;
import at.conmunity.API.Interface.IController;
import at.conmunity.API.Model.Member;
import at.conmunity.API.Repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MemberController implements IController<Member> {

    private MemberRepository repository;

    private final Logger log = LoggerFactory.getLogger(MemberController.class);

    MemberController(MemberRepository repo){
        this.repository = repo;
    }

    @PostMapping("/Users")
    public Member create(@RequestBody Member obj) {
        return repository.save(obj);
    }

    @GetMapping("/Users")
    @Override
    public List<Member> getAll() {
        return repository.findAll();
    }

    @GetMapping("/Users/{id}")
    @Override
    public Member getByID(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
    }

    @PutMapping("/Users")
    @Override
    public Member update(@RequestBody Member obj) {
        return repository.findById(obj.getMemberID()).map(member -> {
            Member memberToSave = member.doMap(obj);
            return repository.save(memberToSave);
        })
                .orElseGet(()-> repository.save(obj));
    }

    @DeleteMapping("/Users/{id}")
    @Override
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
