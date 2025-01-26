package at.conmunity.API.Model;

import at.conmunity.API.Interface.IModel;
import at.conmunity.API.Service.ObjectJSONService;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;

@Entity
public class News implements IModel<News> {
    @JsonProperty("newsID")
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long NewsID;
    @JsonProperty("title")
    private String Title;
    @JsonProperty("introduction")
    private String Introduction;
    @JsonProperty("description")
    private String Description;
    @JsonProperty("imgURL")
    private String ImgURL;
    @JsonProperty("created")
    private Date created;

    protected News(){
        this.created = new Date();
    }

    public News(String title, String introduction, String description, String imgURL){
        this.Title = title;
        this.Introduction = introduction;
        this.Description = description;
        this.ImgURL = imgURL;
        this.created = new Date();
    }

    public News(String title, String introduction, String description, String imgURL, Date created){
        this(title, introduction, description, imgURL);
        this.created = created;
    }

    public Long getNewsID(){
        return this.NewsID;
    }

    public String getTitle(){
        return this.Title;
    }

    public String getIntroduction(){
        return this.Introduction;
    }

    public String getDescription(){
        return this.Description;
    }

    public String getImgURL(){
        return this.ImgURL;
    }

    public Date getCreated(){
        return this.created;
    }

    public void setTitle(String newTitle){
        this.Title = newTitle;
    }

    public void setIntroduction(String newIntroduction){
        this.Introduction = newIntroduction;
    }

    public void setDescription(String newDescription){
        this.Description = newDescription;
    }

    public void setImgURL(String newImgURL){
        this.ImgURL = newImgURL;
    }

    public void setCreated(Date newCreated){
        this.created = newCreated;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this.NewsID);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(!obj.getClass().equals(this.getClass())) return false;

        News other = (News) obj;
        return other.getNewsID().equals(this.getNewsID());
    }

    @Override
    public String toString() {
        return "News{" +
                "NewsID=" + NewsID +
                ", Title='" + Title + '\'' +
                ", Introduction='" + Introduction + '\'' +
                ", Created=" + created + '\'' +
                ", ImgURL='" + ImgURL + "'}";
    }

    @Override
    public String toJSON() {
        ObjectJSONService<News> jsonService = new ObjectJSONService<>();
        return jsonService.ObjectToJson(this);
    }

    @Override
    public News doMap(News other) {
        this.setTitle(other.getTitle());
        this.setIntroduction(other.getIntroduction());
        this.setDescription(other.getDescription());
        this.setImgURL(other.getImgURL());
        this.setCreated(other.getCreated());

        return this;
    }
}
