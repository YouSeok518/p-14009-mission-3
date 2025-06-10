package com.back.domain.wiseSaying.controller;

import com.back.Rq;
import com.back.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private Scanner scanner = new Scanner(System.in);
    WiseSayingService wiseSayingService = new WiseSayingService();

    // 사용자가 '등록'이라고 입력했을때 출력하는것
    public void actionWrite(){
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wiseSaying = wiseSayingService.write(content, author);
        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    // 사용자가 '목록'이라고 입력했을때 목록 출력 담당
    public void actionList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> forListWiseSayings = wiseSayingService.findForList();

        for (WiseSaying wiseSaying : forListWiseSayings){
            System.out.println("%d / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()));
        }
    }

    // 사용자가 '삭제'라고 입력했을때 삭제 출력 담당
    public void actionDelete(Rq rq){
        int id = rq.getParamAsInt("id", -1);

        if(id==-1){
            System.out.println("id를 숫자로 입력해주세요.");
            return;
        }
        WiseSaying deleteIndex = wiseSayingService.delete(id);

        if (deleteIndex == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    // 사용자가 '수정'이라고 입력했을때 수정 출력 담당
    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id" , -1);

        if(id==-1){
            System.out.println("id를 숫자로 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = wiseSayingService.findById(id);

        if (wiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s\n", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        wiseSayingService.modify(wiseSaying, content, author);
    }

}
