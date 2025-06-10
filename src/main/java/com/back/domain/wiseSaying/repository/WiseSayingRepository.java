package com.back.domain.wiseSaying.repository;

import com.back.WiseSaying;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private int lastId = 0;
    private List<WiseSaying> wiseSayings = new ArrayList<>();

    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            LocalDateTime now = LocalDateTime.now();
            wiseSaying.setCreateDate(now);
            wiseSaying.setModifyDate(now);
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
        } else{
            wiseSaying.setModifyDate(LocalDateTime.now());
        }
    }

    public List<WiseSaying> findForList() {
        return wiseSayings.reversed();
    }


    public WiseSaying deleteById(int id) {
        WiseSaying deleteIndex = findById(id);

        if (deleteIndex == null) return deleteIndex;

        wiseSayings.remove(deleteIndex);

        return deleteIndex;

    }

    public WiseSaying findById(int id) {
        return wiseSayings
                .stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
