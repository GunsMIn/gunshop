package gunproject.gunshop.domain.item;

import gunproject.gunshop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.InheritanceType.*;

@Entity @Getter @Setter
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name="itemType")
public abstract class Item {

    @Id @GeneratedValue @Column(name = "item_id")
    private Long id;

    private String name;

    private Integer price;

    private Integer stockQuantity;

    @ManyToMany(mappedBy = "items" )
    private List<Category> catagories = new ArrayList<>();



}
