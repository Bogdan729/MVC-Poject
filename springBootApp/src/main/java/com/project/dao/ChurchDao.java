package com.project.dao;

import com.project.churchJson.RussianChurch;
import com.project.entity.Church;
import com.project.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ChurchDao {
    private JdbcTemplate jdbcTemplate;
    private static int count = -10;
    private static int tableSize;

    @Autowired
    public ChurchDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ChurchDao() {}

    public int getCount() {
        return count;
    }

    public int getTableSize() {
        return tableSize;
    }

    static {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("SELECT COUNT(*) FROM churches;");
            List<BigInteger> rows = query.list();
            session.getTransaction().commit();

            tableSize = rows.get(0).intValue();
        }
    }

    public void update(RussianChurch[] churches) {
        tableSize = churches.length;
        count = -10;
        clearTable();
        resetSequence();

        try (Connection con = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection()) {
            jdbcTemplate.batchUpdate("INSERT INTO churches (name, adm_area, district, address, metro_station," +
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
        } catch (SQLException ignore) {}
    }

    public void clearTable() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE Church").executeUpdate();
            session.getTransaction().commit();
        }
    }

    public List<Church> showTable() {
        List<Church> churchList;

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            churchList = session.createQuery("from Church order by number").getResultList();
            session.getTransaction().commit();
        }

        return churchList;
    }

    public void plusItems() {
        count += 10;
    }

    public void minusItems() {
            count -= 10;
    }

    public List<Church> portion() {
        List<Church> churchList;

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            String hql = "FROM Church";
            Query query = session.createQuery(hql);
            query.setFirstResult(count);
            query.setMaxResults(10);

            churchList = query.list();

            session.getTransaction().commit();
        }

        return churchList;
    }

    public void resetSequence() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("ALTER SEQUENCE churches_tb_number_seq RESTART WITH 1;").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
