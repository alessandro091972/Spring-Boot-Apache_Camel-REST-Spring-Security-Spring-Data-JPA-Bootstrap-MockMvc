/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.company.auth.model.Address;
import com.company.auth.model.Location;
import com.company.auth.service.AtmService;
import com.company.auth.web.CityController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/*
    MockMvc simulates the calls to the spring controller
    Mockito simulates the calls to the camel service
*/

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestAppConfig.class)
public class JUnitTest {

    private final Location location;
    private final Address address;
    private final List<Location> locations;
    private final static String CITY = "Haarlem";
    private final static String POSTALCODE = "2024 DM";
    
    @Mock
    AtmService atmServiceMock;

    public JUnitTest() {
        location = new Location();
        address = new Address();
        address.setPostalcode(POSTALCODE);
        address.setCity(CITY);
        location.setAddress(address);
        locations = new ArrayList<>();
        locations.add(location);
    }
    
    @InjectMocks
    private CityController cityControllerMock;
    
    private MockMvc mockMvc;
    
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(cityControllerMock)
                .build();
    }


 
    @BeforeAll
    static void getAll() {
        System.out.println("Start...");
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println("Init..." + testInfo.getDisplayName());
    }
   
    @Test
    public void test1() throws Exception {
        when(atmServiceMock.findAllAtms()).thenReturn(locations);
        this.mockMvc.perform(get("/search")).andDo(print()).andExpect(status().isOk())
                .andExpect(model().attribute("locations", locations));

    }

    @Test
    public void test2() throws Exception {
        when(atmServiceMock.findAllAtms()).thenReturn(locations);
        this.mockMvc.perform(get("/search")).andDo(print()).andExpect(status().isOk())
                .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.view().name("searchPage"));
    }
    

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("Finished..." + testInfo.getDisplayName());
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Finished All...");
    }
}
