package net.wazim.chestnut;

import net.wazim.chestnut.domain.ItemRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AddingTitleTest {

    private ChestnutRunner chestnutRunner;

    @Before
    public void setupChestnut() {
        chestnutRunner = new ChestnutRunner();
        chestnutRunner.start();
    }

    @After
    public void shutdownChestnut() {
        chestnutRunner.stop();
    }

    @Ignore
    @Test
    public void userCanAddTitleToTheirCollection() throws IOException {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setImdbId("tt0816692");
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/items", itemRequest, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ResponseEntity<String> items = restTemplate.getForEntity("http://localhost:8080/items", String.class);
        assertThat(items.getBody(), containsString("Interstellar"));
    }

}
