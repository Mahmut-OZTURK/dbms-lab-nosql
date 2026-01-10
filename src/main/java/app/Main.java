package app;
import static spark.Spark.*;
import com.google.gson.Gson;
import app.store.*;

public class Main {
    public static void main(String[] args) {
        port(8080);
        Gson gson = new Gson();
        
        RedisStore.init();
        HazelcastStore.init();
        MongoStore.init();
        
        
        get("/nosql-lab-rd", (req, res) -> {
            String studentNo = req.queryParams("student_no");
            return gson.toJson(RedisStore.get(studentNo));
        });
        
        get("/nosql-lab-hz", (req, res) -> {
            String studentNo = req.queryParams("student_no");
            return gson.toJson(HazelcastStore.get(studentNo));
        });
        
        get("/nosql-lab-mon", (req, res) -> {
            String studentNo = req.queryParams("student_no");
            return gson.toJson(MongoStore.get(studentNo));
        });
    }
}