package gunproject.gunshop.domain.item;

import gunproject.gunshop.domain.Category;
import gunproject.gunshop.exception.NotEnoughStockException;
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


    /**
     * item의 비지니스 로직
     * 1.재고 증가(+) 필요
     * 2.재고 감소(-) 필요
     * */

    public void addStock(int stockQuantity) {
        //예를 들어서 환불하면 재고 (+) 된다
        this.stockQuantity += stockQuantity;
    }

    //예를 들어서 아이템을 구매하면 재고 (-) 된다
    public void removeStock(int stockQuantity) {
        int realQuantity = this.stockQuantity -= stockQuantity;
        if (realQuantity<0) { //예외? 재고가 0보다 아래면 터뜨려야함
                              //재고 부족! -> item의 개수가 0보다 작으면 더이상 살 수 없다.
            throw new NotEnoughStockException("수량이 더 이상 줄을 수 없습니다");
        }
        //주의! 다시 넣어줘야한다.
        this.stockQuantity = realQuantity;
    }

}
