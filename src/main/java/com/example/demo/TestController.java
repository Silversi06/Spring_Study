package com.example.demo;

import org.springframework.web.bind.annotation.*;

class Test{
    private String message;
    private String from;
    private String to;
    private Integer importance;

    public String getMessage() {return message;}public void setMessage(String message) {this.message = message;}public String getFrom() {return from;}public void setFrom(String from) {this.from = from;}public String getTo() {return to;}public void setTo(String to) {this.to = to;}public Integer getImportance() {return importance;}public void setImportance(Integer importance) {this.importance = importance;}

    @Override public String toString() {return "Test{message='" + message + '\'' + ", from='" + from + '\'' + ", to='" + to + '\'' + ", importance=" + importance + '}'; }
}

class Test2 {
    private String result;

    private Integer code;

    public String getResult() {return result;}public void setResult(String result) {this.result = result;}public Integer getCode() {return code;}public void setCode(Integer code) {this.code = code;}

    @Override public String toString() {return "Test2{result='" + result + '\'' + ", code=" + code + '}'; }
}

class Test3{
    private String title;
    private String author;
    private Integer first_realease;
    private Integer rating;

    public String getTitle() {return title;}public void setTitle(String title) {this.title = title;}public String getAuthor() {return author;}public void setAuthor(String author) {this.author = author;}public Integer getFirst_realease() {return first_realease;}public void setFirst_realease(Integer first_realease) {this.first_realease = first_realease;}public Integer getRating() {return rating;}public void setRating(Integer rating) {this.rating = rating;}

    @Override public String toString() {return "Test3{title='" + title + '\'' + ", author='" + author + '\'' + ", first_realease=" + first_realease + ", rating=" + rating + '}'; }
}
class Test4 {
    private String result;
    private String id;
    private Boolean success;

    public String getResult() {return result;}public void setResult(String result) {this.result = result;}public String getId() {return id;}public void setId(String id) {this.id = id;}public Boolean getSuccess() {return success;}public void setSuccess(Boolean success) {this.success = success;}

    @Override public String toString() {return "Test4{result='" + result + '\'' + ", id='" + id + '\'' + ", success=" + success + '}'; }
}

class Test5{
    private String text;
    private Integer count;

    public String getText() {return text;}public void setText(String text) {this.text = text;}public Integer getCount() {return count;}public void setCount(Integer count) {this.count = count;}

    @Override public String toString() {return "Test5{text='" + text + '\'' + ", count=" + count + '}'; }
}

@RestController
@RequestMapping("/sung")
public class TestController {

    @GetMapping("/chan")
    @ResponseBody
    public Test2 Chan(@RequestBody Test test) {
        System.out.println(test.getMessage());
        Test2 test2 = new Test2();
        test2.setCode(1001);
        test2.setResult("success");
        return test2;
    }
    @GetMapping("/eun")
    @ResponseBody
    public Test4 Eun(@RequestBody Test3 test3){
        System.out.println(test3.getTitle());
        Test4 test4 = new Test4();
        test4.setResult("created");
        test4.setId("a1234");
        test4.setSuccess(true);
        return test4;
    }
    @GetMapping("/count")
    public String Count(@RequestBody Test5 test5){
        String result ="";
        for(int i = 0; i< test5.getCount(); i++){
            result+= test5.getText();
        }
        return result;
    }
}


