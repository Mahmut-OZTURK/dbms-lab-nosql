package app;
import static spark.Spark.*;
import com.google.gson.Gson;
import app.store.*;

public class Main {
    public static void main(String[] args) {
        port(8080);
        Gson gson = new Gson();
        
        System.out.println("Redis başlatılıyor...");
        RedisStore.init();
        System.out.println("Redis tamam!");
        
        System.out.println("Hazelcast başlatılıyor...");
        HazelcastStore.init();
        System.out.println("Hazelcast tamam!");
        
        System.out.println("MongoDB başlatılıyor...");
        MongoStore.init();
        System.out.println("MongoDB tamam!");
        
        System.out.println("Endpoint'ler tanımlanıyor...");
        
        
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
        
        System.out.println("Server hazır! Port: 8080");
        System.out.println("Test: curl 'http://localhost:8080/nosql-lab-rd?student_no=2025000001'");
    }
}