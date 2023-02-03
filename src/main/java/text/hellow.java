package text;

import com.foodorder.model.Reserva.dao.impl.ReservaJDBCDAO;
import com.foodorder.model.Reserva.pojo.Reserva;

import java.util.ArrayList;
import java.util.List;

public class hellow {
    public static void main(String[] args) {
        System.out.println("32_宋源翊_Anthony");
        ReservaJDBCDAO rd=new ReservaJDBCDAO();
        System.out.println();
        List<Reserva> rs=new ArrayList<Reserva>();
        rs=rd.getByStoreIdRendate(6,"2023-02-03","13:00",0);
        for (Reserva e : rs){
            System.out.println(e.getRenId());
            System.out.println(e.getRenPhone());
            System.out.println(e.getRenName());
        }
    }
}
