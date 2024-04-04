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


@RestController
@RequestMapping("/sung")
public class TestController {

    @GetMapping("/chan")
    @ResponseBody
    public Test2 Chan(@RequestBody Test test){
        System.out.println(test.getMessage());
        Test2 test2 = new Test2();
        test2.setCode(1001);
        test2.setResult("success");
        return test2;
    }

}
