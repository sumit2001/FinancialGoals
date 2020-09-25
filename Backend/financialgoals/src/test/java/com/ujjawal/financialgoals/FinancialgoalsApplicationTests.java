package com.ujjawal.financialgoals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.ujjawal.financialgoals.Entity.Goals;
import com.ujjawal.financialgoals.Repository.GoalsRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@SpringBootTest
@AutoConfigureMockMvc

@RunWith(SpringJUnit4ClassRunner.class)
class FinancialgoalsApplicationTests {

    @Autowired
    GoalsRepository goalsRepository;
    @Autowired
    private MockMvc mockMvc;





    @Test
    void TestaddGoals() {

        Goals goals=new Goals();

        goals.setGoal("TestingDemo");
        goals.setTargetAmount(5000);
        goals.setTranscations(new ArrayList<>());

        Goals response=goalsRepository.save(goals);

        Assert.assertEquals(goals.getGoal(),response.getGoal());







    }

    @Test
    void testController() throws Exception {
        this.mockMvc.perform(get("/getGoals")).andExpect(status().isOk());






    }

}
