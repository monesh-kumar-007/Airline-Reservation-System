import java.sql.Connection;
import util.DBUtil;

public class TestOracle {
    public static void main(String[] args) {
        try (Connection con = DBUtil.getConnection()) {
            System.out.println("✅ Oracle DB Connected Successfully!"+ con);
        } catch (Exception e) {
            System.out.println("Connection Not Established.. Invalid Credentials");
        }
    }
}


