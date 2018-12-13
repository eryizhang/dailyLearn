package test.CommonTest;

import java.util.*;

/**
 * Describe this class...
 *
 * @author: Du.Hx
 * @date: 2018/11/12 14:26
 * @version: 1.0
 */
public class TestCommon {
  public static void main(String[] args) {
    // TestCommon.arrayListPreAdd();
    TestCommon.arrayListPOSTAdd();

    String s =
        "@#$%00B0{ \"msg_version\": \"0.1\", \"from\": \"000c431818bf\", \"to\": \"\", \"msg_id\": \"cNJyb-284\", \"msg_class\": \"gw_common\", \"msg_name\": \"gw_heartbeat\", \"msg_type\": \"ping\", \"token\": \"\" }";
    System.out.println(s.getBytes().length);

    String hex = Integer.toHexString(s.getBytes().length);
    while (hex.length() < 4) {
      hex = "0" + hex;
    }
    System.out.println(hex);
    Integer[] ay = {1, 2, 3, 5, 6};
    Set<Integer> set = new HashSet<>(Arrays.asList(ay));

    String js =
        "@#$%00ab{\"api_version\":\"0.1\",\"from\":\"\",\"to\":\"000c431818bf\",\"msg_id\":\"xfBai-127\",\"msg_class\":\"gateway_mgmt\",\"msg_name\":\"zb_net_open\",\"msg_type\":\"set\",\"token\":\"\",\"time\":255}";
    js =
        "@#$%00b7{\"api_version\":\"0.1\",\"from\":\"\",\"to\":\"000c431818bf\",\"msg_id\":\"xfBai-127\",\"msg_class\":\"device_mgmt\",\"msg_name\":\"device_query\",\"msg_type\":\"get\",\"token\":\"12345678\",\"type_list\":[]}";

    js =
        "@#$%00de{\"api_version\":\"0.1\",\"from\":\"\",\"to\":\"000c431818bf\",\"msg_id\": \"xfBai-127\",\"msg_class\":\"device_mgmt\",\"msg_name\":\"DL_id_add\",\"msg_type\":\"set\",\"device\":{\"device_id\":101,\"type\":1,\"permi\":4,\"name\":\"test\",\"pwd\":\"123456\"}}"; // js ="@#$%0148{ \"msg_version\": \"0.1\", \"from\": \"000c431818bf\", \"to\": \"\",

    js =
        "@#$%00c5{\"api_version\":\"0.1\",\"from\":\"\",\"to\":\"000c431818bf\",\"msg_id\": \"xfBai-107\",\"msg_class\":\"control_mgmt\",\"msg_name\":\"doorlock_request_open\",\"msg_type\":\"set\",\"token\":\"\",\"device\":{\"device_id\":1,}}";

    // \"msg_id\": \"cNJyb-280\", \"msg_class\": \"gw_common\", \"msg_name\": \"gw_login\",
    // \"msg_type\": \"get\", \"gateway\": { \"code\": \"000c431818bf\", \"version_info\": [ {
    // \"module\": \"device\", \"version\": 1 }, { \"module\": \"scene\", \"version\": 1 }, {
    // \"module\": \"room\", \"version\": 1 } ] } }";

    js =
        "@#$%00BF{ \"msg_version\": \"0.1\", \"from\": \"000c431818bf\", \"to\": \"\", \"msg_id\": \"ZcMoP-2\", \"msg_class\": \"user_mgmt\", \"msg_name\": \"user_query_all\", \"msg_type\": \"get\", \"token\": \"2993005558776832\" }";
    js =
        "@#$%00c2{\"from\":\"\",\"msg_class\":\"gw_common\",\"msg_id\":\"ZcMoP-1\",\"msg_name\":\"gw_login\",\"msg_type\":\"response\",\"result\":{\"module_list\":[],\"token\":\"2993005558776832\"},\"status\":\"0\",\"to\":\"000c431818bf\"}";

    js =
        "@#$%00f8{\"api_version\":\"0.1\",\"from\":\"\",\"to\":\"000c431818bf\",\"msg_id\":\"xfBai-127\",\"msg_class\":\"device_mgmt\",\"msg_name\":\"DL_id_add\",\"msg_type\":\"set\",\"device\":{\"device_id\":1,\"type\":1,\"permi\":4,\"name\":\"test123\",\"pwd\":\"32486b25f436810a08072565771f54c5\"}}";
    js =
        "@#$%00c4{\"api_version\":\"0.1\",\"from\":\"\",\"to\":\"000c431818bf\",\"msg_id\": \"xfBai-107\",\"msg_class\":\"control_mgmt\",\"msg_name\":\"doorlock_request_open\",\"msg_type\":\"set\",\"token\":\"\",\"device\":{\"device_id\":1}}";
    js =
        "@#$%00c6{\"api_version\":\"0.1\",\"from\":\"\",\"to\":\"000c431818bf\",\"msg_id\": \"xfBai-107\",\"msg_class\":\"control_mgmt\",\"msg_name\":\"device_state_get\",\"msg_type\":\"get\",\"token\":\"\",\"device_list\":[{\"device_id\":1}]}";

    hex = Integer.toHexString(js.getBytes().length);
    System.out.println(js.getBytes().length);
    while (hex.length() < 4) {
      hex = "0" + hex;
    }
    System.out.println(hex);
  }

  private static void arrayListPreAdd() {
    Integer[] ay = {1, 2, 3, 5, 6};
    List<Integer> asList1 = Arrays.asList(ay);
    List<Integer> asList = new LinkedList<>(asList1);
    for (int i = 0; i < asList.size(); i++) {
      System.out.println("if之前" + i + "   " + asList.get(i));
      if (asList.get(i) == 5) {
        asList.add(i, 4);
        System.out.println("if中" + i + "   " + asList.get(i));
        i++;
      }

      System.out.println("if之后" + i + "   " + asList.get(i));
    }
  }

  private static void arrayListPOSTAdd() {
    Integer[] ay = {1, 2, 3, 5, 6};
    List<Integer> asList1 = Arrays.asList(ay);
    List<Integer> asList = new LinkedList<>(asList1);
    for (int i = 0; i < asList.size(); i++) {
      System.out.println("if之前" + i + "   " + asList.get(i));
      if (asList.get(i) == 3 || asList.get(i) == 4) {
        asList.add(i + 1, 4);
        System.out.println("if中" + i + "   " + asList.get(i));
        i++;
      }

      System.out.println("if之后" + i + "   " + asList.get(i));
    }
  }
}
