package app.truefleet.com.truefleet.Tasks;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Chris Lacy on 3/4/2015.
 */
/* Web service returns both errorMessage and message as errors
*  This type adapter maps both to RestError
*  */
public class RestErrorTypeAdapter extends TypeAdapter<RestError> {


        @Override
        public RestError read(final JsonReader in) throws IOException {
            final RestError restErrorInstance = new RestError();

            in.beginObject();
            while (in.hasNext()) {
                String jsonTag = in.nextName();
                if ("errorMessage".equals(jsonTag)
                        || "message".equals(jsonTag)) {
                    restErrorInstance.strMessage = in.nextString();

                }
            }
            in.endObject();
            return restErrorInstance;
        }

        @Override
        public void write(final JsonWriter out, final RestError restErrorInstance)
                throws IOException {
            out.beginObject();
            out.name("errorMessage").value(restErrorInstance.getStrMessage());
            out.endObject();
        }

}
