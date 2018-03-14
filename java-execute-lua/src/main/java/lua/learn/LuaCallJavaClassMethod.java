package lua.learn;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * LuaCallJavaClassMethod
 */
public class LuaCallJavaClassMethod {

    static public class JavaClassOne{

    }

    static public class JavaClass{
        static public class Test extends ZeroArgFunction {

            String output;

            void setOutput(String str) {
                output = str;
            }

            @Override
            public LuaValue call() {
                final StringBuffer append = new StringBuffer().append(output);
                System.out.println(append.toString());
                return NIL;
            }
        }
    }

    public static void main(String[] args) {
        Globals globals = JsePlatform.standardGlobals();
        LuaValue test = CoerceJavaToLua.coerce(new JavaClassOne());
//        String test = new String("hello");
        globals.set("obj", test);

        LuaTable t = new LuaTable();
        final JavaClass.Test test1 = new JavaClass.Test();
        test1.setOutput("helloworld");
        t.set("test", test1);
        t.set("__index", t);
        test.setmetatable(t);
        LuaValue chunk = globals.load("obj.test()");
        chunk.call();

        for (int i = 0; ; i++){
            String text = "hello " + i;
            test1.setOutput(text);
            chunk.call();
        }
    }
}
