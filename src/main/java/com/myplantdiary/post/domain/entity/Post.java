package com.myplantdiary.post.domain.entity;

import com.myplantdiary.plant.domain.entity.Plant;
import com.myplantdiary.user.domain.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id")
    private Plant plant;

    private LocalDate postDate;

    private String imgUrl;

    private String text;

    //연관 관계 메서드
    public void setUser(User user){
        this.user = user;
        user.getPosts().add(this);
    }

    public void setPlant(Plant plant){
        this.plant = plant;
    }

    //글 작성할 때마다 Plant 의 postCount 1식증가
    public void addPlantPostCount(){
        int increaseCount = this.plant.getPostCount() + 1;
        this.plant.setPostCount(increaseCount);
    }

    //생성 메서드
    public static Post createPost(User user, Plant plant, String text, String imgName){
        Post post = new Post();
        post.setUser(user);
        post.setPlant(plant);
        post.setText(text);
        post.setPostDate(LocalDate.now());
        post.setImgUrl(imgName);
        post.addPlantPostCount();
        return post;
    }
}