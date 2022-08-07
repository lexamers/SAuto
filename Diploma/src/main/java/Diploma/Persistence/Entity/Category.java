package Diploma.Persistence.Entity;

import org.springframework.context.i18n.LocaleContextHolder;
import javax.persistence.*;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String nameEng;

    public Category() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(nameEng, category.nameEng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameEng);
    }

    public String getAnchorName(){
        return "category-"+this.getId();
    }

    public String getLocalizedName(){
        Locale locale = LocaleContextHolder.getLocale();
        if(locale.getLanguage().equals("en")){
            return nameEng;
        } else {
            return name;
        }
    }
}
