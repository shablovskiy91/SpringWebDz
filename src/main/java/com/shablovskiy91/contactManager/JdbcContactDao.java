package com.shablovskiy91.contactManager;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcContactDao implements ContactDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public JdbcContactDao(NamedParameterJdbcTemplate namedJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_ALL_CONTACT_SQL = "" +
            "SELECT" +
            "   ID," +
            "   FULLNAME, " +
            "   TELNUMBER, " +
            "   EMAIL " +
            "FROM CONTACT";

    private static final String GET_CONTACT_SQL = "" +
            "SELECT " +
            "ID, " +
            "FULLNAME, " +
            "TELNUMBER, " +
            "EMAIL " +
            "FROM CONTACT " +
            "WHERE ID = ?";

    private static final String SET_FULLNAME_SQL = "" +
            "UPDATE CONTACT " +
            "SET FULLNAME = ? " +
            "WHERE ID = ?";

    private static final String SET_TELNUMBER_SQL = "" +
            "UPDATE CONTACT " +
            "SET TELNUMBER = ? " +
            "WHERE ID = ?";

    private static final String SET_EMAIL_SQL = "" +
            "UPDATE CONTACT " +
            "SET EMAIL = ? " +
            "WHERE ID = ?";

    private static final String CREATE_CONTACT_SQL = "" +
            "INSERT INTO CONTACT (FULLNAME) " +
            "VALUES(?)";

    private static final String DELETE_CONTACT_SQL = "" +
            "DELETE FROM " +
            "CONTACT " +
            "WHERE ID = ?";

    private static final RowMapper<Contact> CONTACT_ROW_MAPPER =
            (rs, rowNum) -> new Contact(
                    rs.getLong("ID"),
                    rs.getString("fullname"),
                    rs.getString("telnumber"),
                    rs.getString("email"));

    @Override
    public List<Contact> getAllContacts() {
        return jdbcTemplate.query(
                GET_ALL_CONTACT_SQL,
                CONTACT_ROW_MAPPER
        );
    }

    @Override
    public Contact getContact(long contactId) {
        return jdbcTemplate.queryForObject(
                GET_CONTACT_SQL,
                CONTACT_ROW_MAPPER,
                contactId
        );
    }

    @Override
    public long addContact(Contact contact) {
        long contactId = contact.getContactId();
        String fullName = contact.getFullName();
        String telNumber = contact.getTelNumber();
        String email = contact.getEmail();

        if (contactId == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    connection -> {
                        var ps = connection.prepareStatement("INSERT INTO CONTACT(FULLNAME, TELNUMBER, EMAIL) VALUES(?, ?, ?)", new String[] {"id"});
                        ps.setString(1, fullName);
                        ps.setString(2, telNumber);
                        ps.setString(3, email);
                        return ps;
                    },
                    keyHolder
            );
            contactId = keyHolder.getKey().longValue();
        }
        else {
            jdbcTemplate.update(
                    "INSERT INTO CONTACT(ID, FULLNAME, TELNUMBER, EMAIL) VALUES(?, ?, ?, ?)",
                    contactId, fullName, telNumber, email
            );
        }
        return contactId;
    }

    @Override
    public Contact addContact(String fullName) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                con -> {
                    var ps = con.prepareStatement("INSERT INTO CONTACT(FULLNAME) VALUES(?)", new String[] {"id"});
                    ps.setString(1, fullName);
                    return ps;
                },
                keyHolder
        );

        var contactId = keyHolder.getKey().longValue();
        return new Contact(contactId, fullName);
    }

    @Override
    public void setFullName(long contactId, String fullName) {
        jdbcTemplate.update(
                SET_FULLNAME_SQL,
                fullName, contactId
        );
    }

    @Override
    public void setTelNumber(long contactId, String telNumber) {
        jdbcTemplate.update(
                SET_TELNUMBER_SQL,
                telNumber, contactId
        );
    }

    @Override
    public void setEmail(long contactId, String email) {
        jdbcTemplate.update(
                SET_EMAIL_SQL,
                email, contactId
        );
    }

    @Override
    public void deleteContact(long contactId) {
        jdbcTemplate.update(
                DELETE_CONTACT_SQL,
                contactId
        );
    }
}

