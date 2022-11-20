package gunproject.gunshop.repository;

import gunproject.gunshop.domain.item.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<UploadFile, Long> {

}
