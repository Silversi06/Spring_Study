package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class SomeController {

    //1
    @GetMapping("/get_test")
    public String getTest(){
        return "GET";
    }
    @PostMapping("/post_test")
    public String postTest(){
        return "POST";
    }
    @PutMapping("/put_test")
    public String putTest(){
        return "PUT";
    }
    @PatchMapping("patch_test")
    public String patchTest(){
        return "PATCH";
    }
    @DeleteMapping("delete_test")
    public String deleteTest(){
        return "DELETE";
    }

    //2
    private String message = "";

    @PatchMapping("/update_message")
    public void updateMessage(@RequestParam String message) {
        this.message = message;
    }

    @PatchMapping("/update_message/{newMessage}")
    public void updateMessageWithPathVariable(@PathVariable("newMessage") String newMessage) {
        this.message = newMessage;
    }

    @GetMapping("/get_message")
    public String getMessage() {
        return this.message;
    }

    //3
    @PostMapping("/calc")
    public CalculationResult calculate(
            @RequestParam("num1") int num1,
            @RequestParam("num2") int num2,
            @RequestParam("op") String op,
            @RequestBody(required = false) String requestBody) {

        double result;
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / (double) num2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }

        return new CalculationResult(num1, num2, op, result);
    }

    static class CalculationResult {
        private final int num1;
        private final int num2;
        private final String op;
        private final double result;

        public CalculationResult(int num1, int num2, String op, double result) {
            this.num1 = num1;
            this.num2 = num2;
            this.op = op;
            this.result = result;
        }

        public int getNum1() {
            return num1;
        }

        public int getNum2() {
            return num2;
        }

        public String getOp() {
            return op;
        }

        public double getResult() {
            return result;
        }
    }

    //4
    private int visitCount = 0;
    @PostMapping("/update_visit_count")
    public String updateVisitCount(){
        visitCount++;

        return String.valueOf(visitCount);
    }

    //5
    @PostMapping("/update_visit_count_json")
    public HashMap<String ,Object> updateVisitCount2(){
        visitCount++;

        HashMap<String, Object> result = new HashMap<>();
        result.put("visit_count", visitCount);
        return result;
    }

    //6
    private List<String> emailAddresses = new ArrayList<>();

    @PostMapping("/register_email")
    public ResponseEntity<Object> registerEmail(@RequestBody String emails) {

        String[] emailArray = emails.split(",");
        emailAddresses.addAll(Arrays.asList(emailArray));


        return new ResponseEntity<>(new EmailResponse(emailAddresses), HttpStatus.OK);
    }


    private static class EmailResponse {
        private List<String> emailAddresses;

        public EmailResponse(List<String> emailAddresses) {
            this.emailAddresses = emailAddresses;
        }

        public List<String> getEmailAddresses() {
            return emailAddresses;
        }
    }

    //7
    private List<Vote> votes = new ArrayList<>();

    @PatchMapping("/vote/register_option")
    public ResponseEntity<String> registerOption(@RequestBody String option) {
        Vote newVote = new Vote(option);
        votes.add(newVote);
        return ResponseEntity.ok("성공");
    }
    @GetMapping("/vote/show_options")
    public ResponseEntity<List<Vote>> showOptions() {
        return ResponseEntity.ok(votes);
    }
    @PostMapping("/vote/make_vote")
    public ResponseEntity<String> makeVote(@RequestBody int index) {
        if (index < 0 || index >= votes.size()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못됨");
        }
        votes.get(index).incrementCount();
        return ResponseEntity.ok("투표성공.");
    }

    public static class Vote {
        private String option;
        private int count;

        public Vote(String option) {
            this.option = option;
            this.count = 0;
        }

        public String getOption() {
            return option;
        }

        public int getCount() {
            return count;
        }

        public void incrementCount() {
            count++;
        }
    }

}
