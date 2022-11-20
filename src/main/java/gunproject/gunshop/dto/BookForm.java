package gunproject.gunshop.dto;

import gunproject.gunshop.domain.item.Book;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class BookForm {

    //수정(update)을 위해 필요함
    private Long id;

    @NotEmpty(message = "상품의 이름을 입력해주세요")
    private String name;

    @Max(value = 1000000)
    private Integer price;

    @NotNull(message = "상품의 재고를 입력해주세요")
    private Integer stockQuantity;

    @NotEmpty(message = "상품의 저자 입력해주세요")
    private String author;

    @NotEmpty(message = "상품의 일련번호를 입력해주세요")
    private String isbn;

    private Long fileId;

    private MultipartFile file; // 첨부 파일 1건

    private List<MultipartFile> files; // list 첨부파일

    //엔티티로 변환 메소드
    public Book toEntity() {
        return new Book(id,name,price,stockQuantity,author,isbn,fileId);
    }


}
