package pri.xjb.ticket.config.shiro.exceptions;

/**
 * Created by xjb on 2018/11/29
 **/
public class PrincipalIdNullException  extends RuntimeException{
    private static final String MESSAGE = "Principal Id shouldn't be null!";

    public PrincipalIdNullException(Class clazz, String idMethodName) {
        super(clazz + " id field: " +  idMethodName + ", value is null\n" + MESSAGE);
    }

}
