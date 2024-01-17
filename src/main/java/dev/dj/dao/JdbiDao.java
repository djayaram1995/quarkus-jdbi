package dev.dj.dao;

import com.google.common.io.Files;
import jakarta.enterprise.context.ApplicationScoped;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ApplicationScoped
public class JdbiDao {
    private final Jdbi jdbi;
    public JdbiDao() {
        jdbi = Jdbi.create("jdbc:postgresql://localhost:5432/wisdom_pet", "postgres", "postgres");
    }
    public <T> List<T> runGetQuery(String sql, Map<String, Object> paramMap, RowMapper<T> rowMapper) {
        return jdbi.withHandle(handle -> {
            try {
                return handle.createQuery(Files.asCharSource(new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource(sql)).toURI()), Charset.defaultCharset()).read()).bindMap(paramMap)
                        .map(rowMapper).stream().toList();
            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public <T> List<T> runGetWithBeanQuery(String sql, Map<String, Object> paramMap, Class<T> tClass) {
        return jdbi.withHandle(handle -> {
            try {
                return handle.createQuery(Files.asCharSource(new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource(sql)).toURI()), Charset.defaultCharset()).read()).bindMap(paramMap)
                        .mapToBean(tClass).stream().toList();
            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void runInsertQuery(String sql, Map<String, Object> paramMap) {
        jdbi.useHandle(handle -> {
            try {
                handle.createUpdate(Files.asCharSource(new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource(sql)).toURI()), Charset.defaultCharset()).read())
                        .bindMap(paramMap).execute();
            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
