package com.example.demo.controller;

import com.example.demo.bean.AmazonProperties;
import com.example.demo.entity.Book;
//import com.example.demo.entity.Reader;
//import com.example.demo.repository.ReaderRepository;
import com.example.demo.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {


    @Autowired
    private AmazonProperties amazonProperties;
    @Autowired
    private ReadingListRepository readingListRepository;
//    @Autowired
//    public ReadingListController(ReadingListRepository readingListRepository,ReaderRepository readerRepository) {
//        this.readingListRepository = readingListRepository;
//        this.readerRepository=readerRepository;
//    }
//    @RequestMapping(value="/abc")
//    public void login(Reader reader){
//        reader.setUsername("abc");
//        reader.setPassword("1234");
//        reader.setFullname("abc");
//        readerRepository.save(reader);
//
//    }
    @GetMapping(value="/{reader}")
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList =readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("amazonID",amazonProperties.getAssociateId());
        }
        return "readingList";
    }
    @PostMapping(value="/{reader}")
    public String addToReadingList(@PathVariable("reader")String reader,Book book) {
  //      book.setId((long)1);
        book.setReader(reader);
        System.out.println(book.toString());
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }
}