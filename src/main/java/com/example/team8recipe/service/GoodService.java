package com.example.team8recipe.service;

import com.example.team8recipe.dto.GoodRequestDto;
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


    public void goodClick(Long id, GoodRequestDto requestDto, UserDetailsImpl userDetails) {
        //게시글이 있는지 확인
        Post post = findPost(id);
        User user = userDetails.getUser();

        Good good = new Good(post,user);

//        goodRepository.;
        //버튼을 눌른 상태이거나 누르지않은 상태를 true/false 로 구분
        // true 로 오면 데이터 넣기 false로 오면 데이터 삭제
        if(!requestDto.isSuccess()){
            System.out.println("삭제");
            List<Good> goodtest = goodRepository.findByUser(user);
            for(Good goodtests :goodtest){
                System.out.println("goodtests.getId() = " + goodtests.getId());
//                goodRepository.deleteById(id);
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
}
