package app.truefleet.com.truefleet.Tasks;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chris Lacy on 2/9/2015.
 */

public class RestError
{

    @SerializedName("errorMessage")
     String strMessage;

    public RestError() {
        //needed for parcel
    }

    public RestError(String strMessage)
    {
        this.strMessage = strMessage;

    }

    public String getStrMessage() {
        return strMessage;
    }

    public void setStrMessage(String strMessage) {
        this.strMessage = strMessage;
    }

}
