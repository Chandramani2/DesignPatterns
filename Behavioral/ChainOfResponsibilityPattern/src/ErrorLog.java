public class ErrorLog extends LogProcessor{
    ErrorLog(LogProcessor nextLogProcessor) {
        super(nextLogProcessor);
    }
    public void log(int loglevel, String message){
        if(loglevel == ERROR){
            System.out.println("[ERROR] log level set to error: message-> " + message);
        }
        else{
            super.log(loglevel, message);
        }
    }
}
