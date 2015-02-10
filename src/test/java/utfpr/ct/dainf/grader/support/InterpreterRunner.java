package utfpr.ct.dainf.grader.support;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.ParseException;
import bsh.TargetError;

/**
 *
 * @author Wilson
 */
public class InterpreterRunner extends Thread {

    private final Interpreter interpreter;
    private final String script;
    private Throwable scriptException;
    private Object result;

    public InterpreterRunner(Interpreter bsh, String script) {
        this.interpreter = bsh;
        this.script = script;
        this.setName("interpreter-runner");
    }
    
    @Override
    public void run() {
        try {
            result = interpreter.eval(script);
        } catch (TargetError te) {
            scriptException = te;
            throw new RuntimeException(te);
        } catch (ParseException pe) {
            scriptException = pe;
            throw new RuntimeException(pe);
        } catch (EvalError ex) {
            scriptException = ex;
            throw new RuntimeException(ex);
        }
    }

    public Object getResult() {
        return result;
    }

    public Throwable getScriptException() {
        return scriptException;
    }
    
    public boolean isError() {
        return scriptException != null;
    }
    
}
