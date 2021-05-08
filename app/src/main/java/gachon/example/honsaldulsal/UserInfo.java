package gachon.example.honsaldulsal;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class UserInfo{
    public String email;
    public String password;
    public String name;
    public String birth;
    public String location;
    public float point;
    public String transaction;

    public UserInfo() {

    }

    public UserInfo(String email, String password, String name, String birth,
                    String location ,float point, String transaction) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.location = location;
        this.point = point;
        this.transaction = transaction;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("password", password);
        result.put("name", name);
        result.put("birth", birth);
        result.put("location", location);
        result.put("point", point);
        result.put("transaction", transaction);

        return result;
    }
}