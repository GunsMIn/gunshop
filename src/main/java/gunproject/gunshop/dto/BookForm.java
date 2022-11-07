package gunproject.gunshop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BookForm {

    //수정(update)을 위해 필요함
    private Long id;

    @NotEmpty(message = "상품의 이름을 입력해주세요")
    private String name;

    @Max(value = 1000000)
    private Integer price;

    private Integer stockQuantity;

    private String author;

    private String isbn;

}
