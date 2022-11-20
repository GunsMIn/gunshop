package gunproject.gunshop.dto;

import gunproject.gunshop.domain.item.UploadFile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FileDto {

    private Long id;
    private String originFileName;
    private String filename;
    private String fullPath;

    //엔티티 변환을 위한 편의 메서드
    public UploadFile toEntity() {
        return UploadFile.builder()
                .id(this.id)
                .originFileName(this.originFileName)
                .filename(this.filename)
                .fullPath(this.fullPath)
                .build();
    }

    @Builder
    public FileDto(Long id, String originFileName,String filename, String fullPath) {
        this.id = id;
        this.originFileName = originFileName;
        this.filename = filename;
        this.fullPath = fullPath;
    }
}
