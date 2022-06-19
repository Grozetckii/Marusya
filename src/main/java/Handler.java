import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;


import java.util.ArrayList;
import java.util.function.Function;

//class Request {
//    static class _Meta{
//        static String client_id;
//        static String locale;
//        static String timezone;
//    }
//    static class _Request{
//        static String command;
//        static String original_utterance;
//        static String type;
//    }
//    static class _Session{
//        static String session_id;
//        static String user_id;
//        static String skill_id;
//        static boolean _new;
//        static int message_id;
//        static class User{
//            static String user_id;
//        }
//        static class Application {
//            static String application_id;
//            static String application_type;
//        }
//    }
//    String version;
//    Request(String client_id, String locale, String timezone, String command, String original_utterance, String type,
//            String session_id, String user_id, String skill_id, boolean _new, int message_id, String _user_id,
//            String application_id, String application_type, String version){
//        _Meta.client_id = client_id;
//        _Meta.locale = locale;
//        _Meta.timezone = timezone;
//        _Request.command = command;
//        _Request.original_utterance = original_utterance;
//        _Request.type = type;
//        _Session.session_id = session_id;
//        _Session.user_id = user_id;
//        _Session.skill_id = skill_id;
//        _Session._new = _new;
//        _Session.message_id = message_id;
//        _Session.User.user_id = _user_id;
//        _Session.Application.application_id = application_id;
//        _Session.Application.application_type = application_type;
//        this.version = version;
//    }
//}

//class Response {
//    static class _Response{
//        static String text;
//        static String tts;
//        static class Button{
//            static String title;
//            static String url;
//        }
//        static ArrayList<Button> buttons;
//        static boolean end_session;
//    }
//    static class _Session{
//        static String session_id;
//        static String user_id;
//        static String skill_id;
//        static boolean _new;
//        static int message_id;
//        static class User{
//            static String user_id;
//        }
//        static class Application{
//            static String application_id;
//            static String application_type;
//        }
//    }
//    String version;
//    Response(String text/*, String tts*/, boolean end_session, String session_id, String user_id,
//             String skill_id, boolean _new, int message_id, String _user_id, String application_id,
//             String application_type, String version){
//        _Response.text = text;
//        //_Response.tts = tts;
//        _Response.end_session = end_session;
//        _Session.session_id = session_id;
//        _Session.user_id = user_id;
//        _Session.skill_id = skill_id;
//        _Session._new = _new;
//        _Session.message_id = message_id;
//        _Session.User.user_id = _user_id;
//        _Session.Application.application_id = application_id;
//        _Session.Application.application_type = application_type;
//        this.version = version;
//    }
//    Response(){
//        _Response.text = "text";
//        //_Response.tts = "tts";
//        _Response.end_session = true;
//        _Session.session_id = "";
//        _Session.user_id = "";
//        _Session.skill_id = "";
//        _Session._new = false;
//        _Session.message_id = 1;
//        _Session.User.user_id = "";
//        _Session.Application.application_id = "";
//        _Session.Application.application_type = "";
//        this.version = "";
//    }
//}

public class Handler implements Function<String, String> {
    @Override
    public String apply(String request) {
        JSONObject req;
        try {
            req = (JSONObject) JSONValue.parseWithException(request);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//        Response response = new Response(req.get("command") + ", hi!", true,
//                (String) req.get("session_id"), (String) req.get("user_id"), (String) req.get("skill_id"),
//                false, (Integer) req.get("message_id"), (String) req.get("user_id"), (String) req.get("application_id"),
//                (String) req.get("application_type"), (String) req.get("version"));

        JSONObject response = new JSONObject();
        response.put("text", req.get("command"));
        response.put("tts", "");
        response.put("end_session", true);

        JSONObject session = new JSONObject();
        session.put("session_id", req.get("session_id"));
        session.put("user_id", req.get("user_id"));
        session.put("skill_id", req.get("skill_id"));
        session.put("new", false);
        session.put("message_id", req.get("message_id"));

        JSONObject user = new JSONObject();
        user.put("user_id", req.get("user_id"));

        session.put("user", user);

        JSONObject application = new JSONObject();
        application.put("application_id", req.get("application_id"));
        application.put("application_type", req.get("application_type"));

        session.put("application", application);

        JSONObject res = new JSONObject();
        res.put("response", response);
        res.put("session", session);
        res.put("version", req.get("version"));

//        res.put("text", req.get("command"));
//        res.put("tts", req.get("original_utterance"));
//        res.put("end_session", true);
//        res.put("session_id", req.get("session_id"));
//        res.put("user_id", req.get("user_id"));
//        res.put("skill_id", req.get("skill_id"));
//        res.put("new", false);
//        res.put("message_id", req.get("message_id"));
//        //res.put("user_id", req.get("user_id"));
//        res.put("version", req.get("version"));



        return res.toJSONString();
    }
}