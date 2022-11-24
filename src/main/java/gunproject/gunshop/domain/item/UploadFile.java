package gunproject.gunshop.domain.item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;

import static javax.persistence.FetchType.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "file")
public class UploadFile {
    @Id
    @GeneratedValue @Column(name = "file_id")
    private Long id;
    @Column(nullable = false)
    private String originFileName;
    @Column(nullable = false)
    private String filename;
    @Column(nullable = false)
    private String fullPath;


    public UploadFile(String originFileName, String filename) {
        this.originFileName = originFileName;
        this.filename = filename;
    }

    @Builder
    public UploadFile(Long id, String originFileName, String filename, String fullPath) {
        this.id = id;
        this.originFileName = originFileName;
        this.filename = filename;
        this.fullPath = fullPath;
    }


}
