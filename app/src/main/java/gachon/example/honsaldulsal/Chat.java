package gachon.example.honsaldulsal;

public class Chat {
    private String id;
    private String content;
    private String time;
    private String item;

    public Chat(){}

    public Chat(String id, String content, String time) {

        this.id = id;
        this.content = content;
        this.time = time;
    }


    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getItem() {
        return item;
    }
}