package gunproject.gunshop.service;

import gunproject.gunshop.domain.item.UploadFile;
import gunproject.gunshop.dto.FileDto;
import gunproject.gunshop.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    @Transactional
    public Long save(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
        //파일 id 리턴
    }

    @Transactional
    public FileDto getFile(Long id) {
        UploadFile file = fileRepository.findById(id).get();

        FileDto fileDto = FileDto.builder()
                .id(id)
                .originFileName(file.getOriginFileName())
                .filename(file.getFilename())
                .fullPath(file.getFullPath())
                .build();
        return fileDto;
    }
}
