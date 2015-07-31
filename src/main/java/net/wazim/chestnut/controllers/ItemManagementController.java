package net.wazim.chestnut.controllers;

import net.wazim.chestnut.domain.Item;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.lang.String.format;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "items")
public class ItemManagementController {

    private final RestTemplate restTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemManagementController(JdbcTemplate jdbcTemplate) {
        this.restTemplate = new RestTemplate();
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Item> addNewItem(@RequestBody String id) {
        String response = restTemplate.getForObject(format("http://www.omdbapi.com/?i=%s&plot=short&r=json", id), String.class);
        JSONObject jsonObject = new JSONObject(response);
        String omdbResponse = jsonObject.getString("Response");

        if ("True".equals(omdbResponse)) {
            String tmdbResponse = restTemplate.getForObject(format(
                    "https://api.themoviedb.org/3/find/%s?external_source=imdb_id&api_key=%s",
                    id,
                    "6df9b0060f92da2824cf753db1798d65"
            ), String.class);

            JSONObject tmdbJsonResponse = new JSONObject(tmdbResponse);
            String posterPath = tmdbJsonResponse.getString("poster_path");

            jdbcTemplate.update(format("INSERT INTO titles " +
                            "(id, title, type, url)" +
                            " VALUES " +
                            "('%s', '%s', '%s', '%s')",

                    jsonObject.getString("imdbID"),
                    jsonObject.getString("Title").replaceAll("'", "&#39;"),
                    jsonObject.getString("Type"),
                    "http://image.tmdb.org/t/p/w154" + posterPath
            ));

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Item> getItems() {
        return jdbcTemplate.query("SELECT * FROM titles", (resultSet, i) -> {
            return new Item(
                    resultSet.getString("id"),
                    resultSet.getString("title"),
                    resultSet.getString("type"),
                    resultSet.getString("url")
            );
        });
    }

}
