package com.example.team8recipe.service;

import com.example.team8recipe.entity.Good;
import com.example.team8recipe.entity.Post;
import com.example.team8recipe.entity.User;
import com.example.team8recipe.repository.GoodRepository;
import com.example.team8recipe.repository.PostRepository;
import com.example.team8recipe.security.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {

    private final PostRepository postRepository;
    private final GoodRepository goodRepository;

    public GoodService(PostRepository postRepository, GoodRepository goodRepository) {
        this.postRepository = postRepository;
        this.goodRepository = goodRepository;
    }

    public void goodClick(Long id, boolean requestDto, UserDetailsImpl userDetails) {
        //게시글이 있는지 확인
        Post post = findPost(id);
        User user = userDetails.getUser();

        Good good = new Good(post,user);


//        goodRepository.;
        //버튼을 눌른 상태이거나 누르지않은 상태를 true/false 로 구분
        // true 로 오면 데이터 넣기 false로 오면 데이터 삭제
        if(!requestDto){
            System.out.println("삭제");
            List<Good> goodtest = goodRepository.findByUser(user);
            for(Good goodtests :goodtest){
                System.out.println("goodtests.getId() = " + goodtests.getId());
                System.out.println("goodtests.getPost().getId() = " + goodtests.getPost().getId());
                if(goodtests.getPost().getId().equals(id)){
                    goodRepository.deleteById(goodtests.getId());
                }

            }
        }else {
            System.out.println("저장");
            goodRepository.save(good);
        }

    }

    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

    public boolean getGood(Long id, UserDetailsImpl userDetails) {
        Post post = findPost(id);
        User user = userDetails.getUser();

        Good good = new Good(post,user);

        System.out.println("good = " + good.getId());

        List<Good> goodtest = goodRepository.findByUser(user);
        for(Good goodtests :goodtest){
            System.out.println("goodtests.getId() = " + goodtests.getId());
            System.out.println("goodtests.getPost().getId() = " + goodtests.getPost().getId());
            if(goodtests.getPost().getId().equals(id)){
                System.out.println("있다.");
                return true;
            }
        }
        return false;
    }


    public int number(Long id) {
        Post post = findPost(id);

        System.out.println("post.getGoodList() = " + post.getGoodList().size());

        return post.getGoodList().size();
    }
}
