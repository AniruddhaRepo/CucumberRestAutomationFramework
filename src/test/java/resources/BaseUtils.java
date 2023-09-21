package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class BaseUtils {
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException {
        if(req==null){
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

            req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseURI")).addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON)
                    .addQueryParam("key", "qaclick123").build();
            return req;
        }
        return req;
//        so that the log is not overwritten with the new set of data when executed through Examples and more than one set of data

    }
    public String getGlobalValues(String globalVar) throws IOException {
        String filePath = System.getProperty("user.dir")+"/src/test/java/resources/global.properties";
        FileInputStream fis = new FileInputStream(filePath);
        Properties properties = new Properties();
        properties.load(fis);

        String value = properties.getProperty(globalVar);
        return value;

    }
}
