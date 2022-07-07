package com.rebwon.bot.interfaces;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class WebhookHandlerTest {

    @Autowired
    MockMvc mockMvc;

    @Value("${webhook.cron.url}")
    String cronUrl;

    @Test
    void using_url_path_value_from_properties_files() throws Exception {
        mockMvc.perform(get(cronUrl))
            .andExpect(status().isOk());
    }

}