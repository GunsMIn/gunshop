package gunproject.gunshop.domain.item;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity @Getter @Setter @NoArgsConstructor
@DiscriminatorValue("book")
public class Book extends Item {

    private String author;
    private String isbn;


    public Book(Long id, String name, Integer price, Integer stockQuantity, String author, String isbn, Long fileId) {
        super(id,name,price,stockQuantity,fileId);
        this.author = author;
        this.isbn = isbn;
    }


}

