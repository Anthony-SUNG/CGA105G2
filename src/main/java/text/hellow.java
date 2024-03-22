package text;

import com.pushmesg.model.service.pgService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;

public class hellow {
    public static final Logger logger = LogManager.getLogger(hellow.class);

    public static void main(String[] args) {
        pgService svc = new pgService();
        JSONArray json = svc.all(2);
        logger.info(json.toJSONString());
        logger.error(json.toJSONString());
    }
}
