package com.ssafy.mvc.model.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingService{

    public List<String> info() throws IOException {
        String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=260";
        Document document = Jsoup.connect(url).get();

        List<String>list = new ArrayList<>();

        Elements selects = document.select(".type06_headline");

        int idx = 0;
        for (Element select : selects) {
            System.out.println("몇번??");
            if(idx==5){
                break;
            }
            idx++;
            list.add(select.html());

        }
        return list;
    }

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=260";
//        Document document = Jsoup.connect(url).get();
//
//        List<String>list = new ArrayList<>();
//
//        Elements selects = document.select(".content");
//
//
//        for (Element select : selects) {
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//            System.out.println(select.html());		//⭐⭐⭐
//            //html(), text(), children(), append().... 등 다양한 메서드 사용 가능
//            //https://jsoup.org/apidocs/org/jsoup/nodes/Element.html 참고
//        }
//    }
}
