public class DebugLog extends LogProcessor{
    DebugLog(LogProcessor nextLogProcessor) {
        super(nextLogProcessor);
    }
    public void log(int loglevel, String message){
        if(loglevel == DEBUG){
            System.out.println("[DEBUG] log level set to debug: message-> " + message);
        }
        else{
            super.log(loglevel, message);
        }
    }
}
