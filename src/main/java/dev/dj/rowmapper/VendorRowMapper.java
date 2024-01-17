package dev.dj.rowmapper;

import dev.dj.model.VendorDto;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorRowMapper implements RowMapper<VendorDto> {
    @Override
    public VendorDto map(ResultSet resultSet, StatementContext ctx) throws SQLException {
        return VendorDto.builder()
                .vendorId(resultSet.getLong(    1))
                .name(resultSet.getString(2))
                .contact(resultSet.getString(3))
                .phone(resultSet.getString(4))
                .email(resultSet.getString(5))
                .address(resultSet.getString(6))
                .build();
    }
}
