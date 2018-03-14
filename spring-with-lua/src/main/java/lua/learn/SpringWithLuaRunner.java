package lua.learn;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class SpringWithLuaRunner {
    static public class JavaClassOne{

    }

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");

        Message message = (Message) applicationContext.getBean("message");

        Globals globals = JsePlatform.standardGlobals();
        LuaValue test = CoerceJavaToLua.coerce(new JavaClassOne());
        globals.set("obj", test);

        LuaTable t = new LuaTable();
        t.set("message", message);
        t.set("__index", t);
        test.setmetatable(t);

        LuaValue chunk = globals.load("obj.message()");
        chunk.call();
    }
}
