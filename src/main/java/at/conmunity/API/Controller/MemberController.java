package at.conmunity.API.Controller;

import at.conmunity.API.Interface.IController;
import at.conmunity.API.Model.LoginData;
import at.conmunity.API.Model.Member;
import at.conmunity.API.Repository.MemberRepository;
import at.conmunity.API.Service.CryptographyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MemberController implements IController<Member> {

    private final MemberRepository repository;

    private final Logger log = LoggerFactory.getLogger(MemberController.class);

    MemberController(MemberRepository repo){
        this.repository = repo;
    }

    @PostMapping("/Users")
    @Override
    public ResponseEntity<Member> create(@RequestBody Member obj) {
        Member member = repository.save(obj);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @GetMapping("/Users")
    @Override
    public ResponseEntity<List<Member>> get(@RequestParam(name = "userID", required = false, defaultValue = "0") Long ID) {
        List<Member> members;

        log.info("ID:{}", ID);

        if(ID == 0) {
            members = repository.findAll();
            return new ResponseEntity<>(members, HttpStatus.OK);
        } else if(repository.existsById(ID)){
            members = new ArrayList<>();
            members.add(repository.findById(ID).orElse(null));
            return new ResponseEntity<>(members, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/Users")
    @Override
    public ResponseEntity<Member> update(@RequestBody Member obj) {
        boolean alreadyExists = repository.existsById(obj.getMemberID());
        if (alreadyExists){
            Member member = repository.findById(obj.getMemberID()).map(newMember -> {
                Member memberToSave = newMember.doMap(obj);
                return repository.save(memberToSave);
            }).orElse(null);
            return new ResponseEntity<>(member, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Users")
    @Override
    public ResponseEntity<Member> delete(@RequestParam(name = "userID", required = false, defaultValue = "0L") Long ID) {

        if(ID < 1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if(repository.existsById(ID)){

            Member member = repository.findById(ID).orElse(null);
            assert member != null;
            repository.deleteById(member.getMemberID());

            return new ResponseEntity<>(member, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/Users/login")
    public ResponseEntity<Boolean> authenticate(@RequestBody LoginData loginData){
        if(loginData == null){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        if(loginData.email().isEmpty() || loginData.password().isEmpty()){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        Member member = repository.findByEmail(loginData.email());
        if(member == null){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }

        if (member.getEmail().equals(loginData.email()) && member.getPassword().equals(CryptographyService.CreateHash(loginData.password()))){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }
}
