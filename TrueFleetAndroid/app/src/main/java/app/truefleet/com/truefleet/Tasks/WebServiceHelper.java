package app.truefleet.com.truefleet.Tasks;


public class WebServiceHelper {

    private boolean connectionSuccess;
    private String body;
    private boolean responseSuccess;

    WebServiceHelper(boolean connectionSuccess, String body, boolean responseSuccess) {

        this.connectionSuccess = connectionSuccess;
        this.body = body;
        this.responseSuccess = responseSuccess;
    }

    public boolean getConnectionSuccess() {
        return connectionSuccess;
    }
    public String getBody() {
        return body;
    }

    public boolean getResponseSuccess() {
        return responseSuccess;
    }
}
