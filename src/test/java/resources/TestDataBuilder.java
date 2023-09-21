package resources;

import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {
    public AddPlace addPlacePayload(String name, String address, String language) {
        AddPlace addPlaceObj = new AddPlace();
        Location locationObj = new Location();
        List<String> types = new ArrayList<String>();

        addPlaceObj.setTypes(types);
        addPlaceObj.setLanguage(language);
        addPlaceObj.setAddress(address);
        addPlaceObj.setWebsite("https://www.therahulshetty.com");
        addPlaceObj.setName(name);
        addPlaceObj.setPhone_number("1242363374");
        addPlaceObj.setAccuracy(5);
        locationObj.setLat(-38.383494);
        locationObj.setLng(33.427362);
        addPlaceObj.setLocation(locationObj);
        types.add("shoe park");
        types.add("shop");

        return addPlaceObj;
    }

    public DeletePlace deletePlacePayload(String placeId) {
        DeletePlace deletePlace = new DeletePlace();
        deletePlace.setPlace_id(placeId);
        return deletePlace;
    }
}
