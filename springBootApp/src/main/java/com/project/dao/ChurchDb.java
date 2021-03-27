package com.project.dao;

import com.project.churchJson.RussianChurch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ChurchDb {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public ChurchDb(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void update(RussianChurch[] churches) {
        clearTable();

        try (Connection con = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection()) {

            jdbcTemplate.batchUpdate("INSERT INTO churches (name, adm_area, district, adress, metro_station," +
                            "metro_line, website, phone_numbers, coordinates, coordinate_type) VALUES(?,?,?,?,?,?,?,?,?,?)",
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            List<String> listNumbers = new ArrayList<>();

                            churches[i].getCells().getPublicPhone().forEach(phone -> listNumbers.add(phone.getPublicPhone()));

                            Array phoneNumbers = con.createArrayOf("text", listNumbers.toArray());
                            Array coordinates = con.createArrayOf("text",
                                    churches[i].getCells().getGeoData().getCoordinates());

                            ps.setString(1, churches[i].getCells().getName());
                            ps.setString(2, churches[i].getCells().getAdmArea());
                            ps.setString(3, churches[i].getCells().getDistrict());
                            ps.setString(4, churches[i].getCells().getAddress());
                            ps.setString(5, churches[i].getCells().getMetroStation());
                            ps.setString(6, churches[i].getCells().getMetroLine());
                            ps.setString(7, churches[i].getCells().getWebSite());
                            ps.setArray(8, phoneNumbers);
                            ps.setArray(9, coordinates);
                            ps.setString(10, churches[i].getCells().getGeoData().getType());
                        }

                        @Override
                        public int getBatchSize() {
                            return churches.length;
                        }
                    });

        } catch (SQLException ignore) {
        }
    }

    public void show() {
        jdbcTemplate.execute("SELECT * FROM churches");
    }


    public void clearTable() {
        jdbcTemplate.execute("DELETE FROM churches");
    }
}
