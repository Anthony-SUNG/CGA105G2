package text;

import com.foodorder.model.Reserva.dao.impl.ReservaJDBCDAO;
import com.foodorder.model.Reserva.pojo.Reserva;
import com.pushmesg.model.service.pgService;
import com.store.model.Store.dao.impl.StoreDAO;
import com.subs.model.Subscribe.dao.impl.SubscribeJDBCDAO;
import com.subs.model.Subscribe.pojo.Subscribe;
import org.json.simple.JSONArray;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class hellow {
    public static void main(String[] args) {
        pgService svc=new pgService();
        JSONArray json= svc.all(2);
        System.out.println(json.toJSONString());
    }
}
