package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIP(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("94.25.8.210");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

    @Test
    public void testInvalidIP(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("94.25.8.xxx");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

}
