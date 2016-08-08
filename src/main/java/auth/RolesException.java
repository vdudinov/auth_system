package auth;

/**
 * Created by Vadim Dudinov on 7/29/2016.
 */
public class RolesException extends Exception
{
    public void SendErrMsg() {
        System.out.println("No roles assigned- Anonym");
    }

}
