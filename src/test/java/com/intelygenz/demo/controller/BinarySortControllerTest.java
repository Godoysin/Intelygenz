package com.intelygenz.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelygenz.demo.service.IBinaryService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BinarySortControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper objectMapper;

    @SpyBean
    IBinaryService binaryService;

    @Test
    void shouldCallPostApi() throws IOException {

        // given
        String url = "http://localhost:port/intelygenz/binary".replace("port", String.valueOf(port));
        List<Integer> sampleList = List.of(1, 2, 3, 4, 5);
        List<Integer> expectedOrderedList = List.of(3, 5, 1, 2, 4);

        final StringEntity entity = new StringEntity(sampleList.toString());
        Request request = Request.Post(url)
                .addHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType())
                .body(entity);

        // when
        HttpResponse response = request
                .execute()
                .returnResponse();

        // then
        assertNotNull(response);
        assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        assertEquals(expectedOrderedList, objectMapper.readValue(response.getEntity().getContent(), LinkedList.class));

        verify(binaryService, times(1)).binarySorterLogic(sampleList);
    }
}
