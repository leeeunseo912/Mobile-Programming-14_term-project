package gachon.example.honsaldulsal;

import java.util.HashMap;
import java.util.Map;

public class UserInfo{
    private String email;
    private String name;
    private String birth;
    private String location;
    private float point;
    private String transaction;
    private String chat;

    public UserInfo() {

    }


    public UserInfo(String email, String name, String birth, String location ,float point, String transaction, String chat) {
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.location = location;
        this.point = point;
        this.transaction = transaction;
        this.chat = chat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(String birth){ this.birth = birth;}

    public String getBirth() {
        return birth;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getPoint() {
        return point;
    }

    public void setPoint(Float point) {
        this.point = point;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getChat() {return chat;}

    public void setChat(String chat) {this.chat = chat;}

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("name", name);
        result.put("birth", birth);
        result.put("location", location);
        result.put("point", point);
        result.put("transaction", transaction);
        result.put("chat", chat);

        return result;
    }

//    public UserInfo(String name, String email, String birth){
//        this.name = name;
//        this.email = email;
//        this.birth = birth;
//    }
}