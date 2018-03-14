package lua.learn;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author wushibin on 2018/3/14.
 */
@Component
public class Message extends ZeroArgFunction {
    private String message = null;

    /**
     * Gets message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public LuaValue call() {
        System.out.println(message);
        return NIL;
    }
}
