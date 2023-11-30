public class InfoLog extends LogProcessor{
    InfoLog(LogProcessor nextLogProcessor) {
        super(nextLogProcessor);
    }
    public void log(int loglevel, String message){
        if(loglevel == INFO){
            System.out.println("[INFO] log level set to info: message-> " + message);
        }
        else{
            super.log(loglevel, message);
        }
    }
}
