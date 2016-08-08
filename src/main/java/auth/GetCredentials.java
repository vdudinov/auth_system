package auth;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Vadim Dudinov on 7/29/2016.
 */
public class GetCredentials {

    public static String ADMIN_NAME = "SYSTEM1";
    public static String ADMIN_PASS = "SYSTEM_TEST";

    public static String USER_NAME = "USER1";
    public static String USER_PASS = "USER_TEST";

    public static boolean isAdminRole = false;
    public static boolean isUserRole = false;

    public static void main(String[] args) throws IOException, RolesException {
        RolesException rolesException = new RolesException();
        System.out.println("Admin credentials");
        InputStream resourceAsStream1 = GetCredentials.class.
                getResourceAsStream("admin.properties");

        Properties properties1 = new Properties();
        properties1.load(resourceAsStream1);

        for (String key : properties1.stringPropertyNames()) {
            System.out.println("key=" + key + "; value=" + properties1.get(key));
        }
        System.out.println("User credentials");
        InputStream resourceAsStream2 = GetCredentials.class.
                getResourceAsStream("user.properties");

        Properties properties2 = new Properties();
        properties2.load(resourceAsStream2);

        for (String key : properties2.stringPropertyNames()) {
            System.out.println("key=" + key + "; value=" + properties2.get(key));
        }

        isAdminRole = GetRole(properties1, ADMIN_NAME, ADMIN_PASS);
        if (isAdminRole) System.out.println("Admin Role");
        isUserRole = GetRole(properties2, USER_NAME, USER_PASS);
        if (isUserRole) System.out.println("User Role");
        if (isAdminRole == false && isUserRole == false) {
            try {
                throw rolesException;
            } catch (RolesException e) {
                rolesException.SendErrMsg();
            }
        }
    }

    public static boolean GetRole(Properties properties, String userName, String passw) {
        boolean userCheck = false;
        boolean passCheck = false;

        for (String key : properties.stringPropertyNames()) {
            if (key.equals("username") && properties.get(key).equals(userName))
                userCheck = true;
            if (key.equals("password") && properties.get(key).equals(passw)) {
                passCheck = true;
            }
        }
        return userCheck && passCheck;
    }
}
