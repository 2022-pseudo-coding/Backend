package WEB3D.repository;

import WEB3D.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    Module findByCreatorIdAndName(Long creatorID,String name);
}
