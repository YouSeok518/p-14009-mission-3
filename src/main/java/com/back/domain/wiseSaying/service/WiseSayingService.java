package com.back.domain.wiseSaying.service;

import com.back.AppContext;
import com.back.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {
    WiseSayingRepository wiseSayingRepository = AppContext.wiseSayingRepository;
    // 내부 로직
    // 등록
    public WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying(author, content);

        wiseSayingRepository.save(wiseSaying);

        return wiseSaying;
    }

    // 목록
    public List<WiseSaying> findForList(){
        return wiseSayingRepository.findForList();
    }

    //삭제
    public WiseSaying delete(int id) {
        return wiseSayingRepository.deleteById(id);
    }

    //수정
    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        wiseSayingRepository.save(wiseSaying);
    }

}
