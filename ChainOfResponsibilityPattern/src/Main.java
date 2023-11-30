public class Main {
    public static void main(String[] args) {
       LogProcessor logProcessor = new InfoLog(new DebugLog(new ErrorLog(null)));
       logProcessor.log(logProcessor.INFO,"Trying Info Log");
        logProcessor.log(logProcessor.DEBUG,"Trying Debug Log");
        logProcessor.log(logProcessor.ERROR,"Trying Error Log");
    }
}