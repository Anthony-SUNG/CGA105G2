package google_api.me;

import com.core.common.Common;
import com.core.entity.ErrorTitle;
import com.store.model.Store.pojo.Store;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.BatchUpdateException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class googleapi extends Common {
    private static int a;

    public static void main(String[] args) throws IOException, BatchUpdateException {
        JSONParser parser = new JSONParser();
        int storen = 0;
        int ins = 0;
        int tonext = 0;
        try {
            Common comm = new Common();

            File placeJson = new File("src\\main\\java\\google_api\\store.json");
            FileReader fileReaderPlace = new FileReader(placeJson);
            JSONArray placeArray = (JSONArray) parser.parse(fileReaderPlace);
            JSONObject place = (JSONObject) placeArray.get(0);
            JSONArray placeResults = (JSONArray) place.get("results");

            for (Object o : placeResults) {
                JSONObject obj = (JSONObject) o;

                String name = (String) obj.get("name");
                String[] compoundCode = ((String) ((JSONObject) obj.get("plus_code")).get("compound_code")).split(" ");
                String city = compoundCode[2];
                String community = compoundCode[1];
                String vicinity = ((String) obj.get("vicinity")).substring(3);
                JSONObject location = (JSONObject) ((JSONObject) obj.get("geometry")).get("location");
                String loc = "(" + location.get("lat") + "," + location.get("lng") + ")";
                String placeId = (String) obj.get("place_id");
                String googleUrl = "https://maps.googleapis.com/maps/api/place/details/json?"
                        + "place_id=" + placeId + "&"
                        + "language=zh-TW&"
                        + "key=AIzaSyDeePRPPIrCXlI_ETMPHfF7sJS-naoIh9k";
                URL url = new URL(googleUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setUseCaches(false);
                FileWriter fw2 = new FileWriter("src\\main\\java\\google_api\\Details.json");
                BufferedWriter br2 = new BufferedWriter(fw2);
                PrintWriter ps2 = new PrintWriter(br2);
                if (con.getResponseCode() == 200) {
                    InputStream is = con.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    String str;
                    ps2.println("[");
                    while ((str = br.readLine()) != null) {
                        ps2.println(str);
                    }
                    ps2.println("]");
                    br.close();
                    isr.close();
                    is.close();
                } else {
                    logger.info("Failed...");
                }
                ps2.close();
                br2.close();
                fw2.close();
                JSONParser parserDetails = new JSONParser();
                JSONArray weekday_text = null;
                String phone = null;
                String detail_url = null;
                String website = null;
                try {
                    File detailsJson = new File("src\\main\\java\\google_api\\Details.json");
                    FileReader fileReaderDetails = new FileReader(detailsJson);
                    JSONArray detailsArray = (JSONArray) parserDetails.parse(fileReaderDetails);
                    JSONObject details = (JSONObject) detailsArray.get(0);
                    JSONObject detailsResults = (JSONObject) details.get("result");
                    try {

                        JSONObject openingHours = (JSONObject) detailsResults.get("current_opening_hours");
                        weekday_text = (JSONArray) openingHours.get("weekday_text");
                        phone = ((String) detailsResults.get("formatted_phone_number")).replace(" ", "");
                        detail_url = (String) detailsResults.get("url");
                        website = (String) detailsResults.get("website");
                    } catch (NullPointerException e) {
                        logger.info(name + ":資料不完整");
                    }
                    fileReaderDetails.close();
                } catch (FileNotFoundException e) {
                    logger.info(Arrays.toString(e.getStackTrace()) + " :detailsFile Not Found");
                } catch (ParseException e) {
                    logger.info(Arrays.toString(e.getStackTrace()) + " :Could not details parse");
                } catch (IOException e) {
                    logger.info(Arrays.toString(e.getStackTrace()) + " :IOException for details");
                }
                try {
                    logger.info("Name: " + name);
                    logger.info("city: " + city);
                    logger.info("community: " + community);
                    logger.info("vicinity: " + vicinity);
                    logger.info("Others: " + loc);
                    logger.info("time: " + weekday_text);
                    logger.info("phone: " + phone);
                    logger.info("url: " + detail_url);
                    logger.info("website: " + website);
                    logger.info("===============================");
                    storen++;
                    String sql = "select STORE_MAP from cga105g2.store" + " where STORE_MAP like ?";
                    try (PreparedStatement ps3 = comm.getConnection().prepareStatement(sql)) {
                        ps3.setString(1, "%" + loc + "%");
                        ResultSet rs3 = ps3.executeQuery();
                        if (!rs3.next()) {
                            ins++;
                            List<Store> list = new ArrayList<Store>();
                            list.add(new Store(name, phone, String.join(",", weekday_text), loc, city, community, vicinity, detail_url, website));
                            insertsoq(list);
                        } else {
                            tonext++;
                        }
                    } catch (SQLException se) {
                        logger.error(ErrorTitle.UNKNOWN_TITLE.getTitle(), se);
                        tonext++;
                        ins--;
                    }

                } catch (NullPointerException e) {
                    logger.info("資料不完整");
                    ins--;
                    tonext++;
                }
            }
            logger.info("catch到" + storen + "店家");
            logger.info("inserts" + (ins + a) + "筆資料");
            logger.info("略過" + (tonext - a) + "筆資料");
            fileReaderPlace.close();
        } catch (FileNotFoundException e) {
            logger.info(Arrays.toString(e.getStackTrace()) + " :File Not Found");
        } catch (ParseException e) {
            logger.info(Arrays.toString(e.getStackTrace()) + " :Could not parse");
        } catch (IOException e) {
            logger.info(Arrays.toString(e.getStackTrace()) + " :IOException");
        }
    }

    private static void parseObject(JSONObject obj) {
        String name = (String) obj.get("name");
        String[] compoundCode = ((String) ((JSONObject) obj.get("plus_code")).get("compound_code")).split(" ");
        String city = compoundCode[2];
        String community = compoundCode[1];
        String vicinity = ((String) obj.get("vicinity")).substring(3);
        JSONObject location = (JSONObject) ((JSONObject) obj.get("geometry")).get("location");
        String loc = "(" + location.get("lat") + "," + location.get("lng") + ")";
        logger.info("Name: " + name);
        logger.info("city: " + city);
        logger.info("community: " + community);
        logger.info("vicinity: " + vicinity);
        logger.info("Others: " + loc);
    }

    private static void Details_pri(JSONObject obj) {
        JSONObject currentOpeningHours = (JSONObject) obj.get("current_opening_hours");
        JSONArray weekdayText = (JSONArray) currentOpeningHours.get("weekday_text");
        String phone = ((String) obj.get("formatted_phone_number")).replace(" ", "");
        String detailUrl = (String) obj.get("url");
        String website = (String) obj.get("website");
        logger.info("time: " + weekdayText);
        logger.info("phone: " + phone);
        logger.info("url: " + detailUrl);
        logger.info("website: " + website);
    }

    private static void insertsoq(List<Store> store_1_google) {
        Common comm = new Common();
        String sql = "insert into cga105g2.store("
                + "STORE_NAME, STORE_PHONE1, STORE_HOURS,STORE_MAP,STORE_CITY, STORE_DISTRICT, STORE_ADDRESS,STORE_URL,STORE_WEB) "
                + "values(?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement ps = comm.getConnection().prepareStatement(sql)) {
            // 批次新增
            for (Store store : store_1_google) {
                ps.setString(1, store.getStoreName());
                ps.setString(2, store.getStorePhone1());
                ps.setString(3, store.getStoreHours());
                ps.setString(4, store.getStoreMap());
                ps.setString(5, store.getStoreCity());
                ps.setString(6, store.getStoreDistrict());
                ps.setString(7, store.getStoreAddress());
                ps.setString(8, store.getStoreUrl());
                ps.setString(9, store.getStoreWeb());
                ps.addBatch();
            }
            ps.executeBatch();
            comm.close();
        } catch (BatchUpdateException e) {
            a--;
            logger.error(ErrorTitle.UNDEF_TITLE.getTitle(), e);
        } catch (SQLException se) {
            logger.error(ErrorTitle.INSERT_TITLE.getTitle(sql), se);
            try {
                comm.getCon().rollback();
            } catch (SQLException r) {
                logger.error(ErrorTitle.ROLLBACK_TITLE.getTitle(sql), r);
            }
        }
    }


}
