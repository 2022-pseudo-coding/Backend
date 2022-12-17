package WEB3D.repository;

import WEB3D.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    Module findByCreatorIdAndName(Long creatorId,String name);
    List<Module> findAllByCreatorId(Long creatorId);
}
