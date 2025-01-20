package at.conmunity.API.Repository;

import at.conmunity.API.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
