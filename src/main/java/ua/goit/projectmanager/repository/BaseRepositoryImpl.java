package ua.goit.projectmanager.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ua.goit.projectmanager.model.BaseEntity;
import ua.goit.projectmanager.util.DataBaseConnection;
import ua.goit.projectmanager.util.PropertiesLoader;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseRepositoryImpl<T extends BaseEntity<ID>, ID> implements Closeable, BaseRepository<T, ID> {

    private final Connection connection;
    private final ObjectMapper mapper;
    private final Class<T> modelClass;
    private final Map<String, String> columnFieldName;
    private final PreparedStatement createStatement;
    private final PreparedStatement deleteStatement;
    private final PreparedStatement updateStatement;
    private final PreparedStatement getAllStatement;
    private final PreparedStatement getByIdStatement;

    @SneakyThrows
    public BaseRepositoryImpl(Class<T> modelClass) {
        this.connection = DataBaseConnection.getInstance().getConnection();
        String databaseSchemeName = PropertiesLoader.getProperty("db.name");
        this.mapper = new ObjectMapper();
        this.modelClass = modelClass;
        this.columnFieldName = Arrays.stream(this.modelClass.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toMap(BaseRepositoryImpl::getColumnName, Field::getName));

        String[] generatedColumns = {getColumnName(Arrays.stream(this.modelClass.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .filter(field -> field.getAnnotation(Id.class) != null)
                .findAny().orElseThrow(() -> new RuntimeException("Entity must contain Id")))};
        String tableName = modelClass.getAnnotation(Table.class) != null ? modelClass.getAnnotation(Table.class).name()
                : modelClass.getSimpleName().toLowerCase();
        String countValues = IntStream.range(0, columnFieldName.size()).mapToObj(x -> "?").collect(Collectors.joining(","));
        String fieldsForCreate = String.join(",", columnFieldName.keySet());
        String fieldsForUpdate = columnFieldName.keySet().stream().map(x -> x + "=?").collect(Collectors.joining(","));

        this.createStatement = connection.prepareStatement("INSERT INTO " + databaseSchemeName + "." + tableName + "(" + fieldsForCreate + ")" +
                " VALUES (" + countValues + ")", generatedColumns);
        this.updateStatement = connection.prepareStatement("UPDATE " + databaseSchemeName + "." + tableName +
                " SET " + fieldsForUpdate + " WHERE id=?", generatedColumns);
        this.deleteStatement = connection.prepareStatement("DELETE FROM " + databaseSchemeName + "." + tableName + " WHERE id=?", generatedColumns);
        this.getByIdStatement = connection.prepareStatement("SELECT * FROM " + databaseSchemeName + "." + tableName + " WHERE id=?", generatedColumns);
        this.getAllStatement = connection.prepareStatement("SELECT * FROM " + databaseSchemeName + "." + tableName);
    }

    private static String getColumnName(Field field) {
        return field.getAnnotation(Column.class) == null ? field.getName() : field.getAnnotation(Column.class).name();
    }

    @SneakyThrows
    @Override
    public T save(T t) {
        if (t.getId() == null || getById(t.getId()).isEmpty()) {
            return executeStatement(createStatement, t);
        } else {
            updateStatement.setObject(columnFieldName.size() + 1, t.getId());
            return executeStatement(updateStatement, t);
        }
    }

    @SneakyThrows
    @Override
    public List<T> getAll() {
        return parse(getAllStatement.executeQuery());
    }

    @SneakyThrows
    @Override
    public Optional<T> getById(ID id) {
        getByIdStatement.setObject(1, id);
        List<T> result = parse(getByIdStatement.executeQuery());
        if (result.isEmpty()) return Optional.empty();
        if (result.size() > 1)throw new RuntimeException("There are two or more entities with such id " + id);
        return Optional.of(result.get(0));
    }

    @SneakyThrows
    @Override
    public void deleteById(ID id) {
        deleteStatement.setObject(1,id);
        deleteStatement.executeUpdate();
    }

    @SneakyThrows
    private T executeStatement(PreparedStatement statement, T t) {
        int count = 1;
        for (String fieldName : columnFieldName.values()) {
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            statement.setObject(count++, field.get(t));
        }
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        resultSet.next();
        return getById((ID) resultSet.getObject(1)).get();
    }

    @SneakyThrows
    private List<T> parse(ResultSet resultSet) {
        final List<T> result = new ArrayList<>();
        while (resultSet.next()) {
            final Map<String, Object> objectMap = new HashMap<>();
            for (String fieldName : columnFieldName.keySet()) {
                objectMap.put(columnFieldName.get(fieldName), resultSet.getObject(fieldName));
            }
            result.add(mapper.convertValue(objectMap, modelClass));
        }
        return result;
    }

    @Override
    @SneakyThrows
    public void close() {
        connection.close();
    }
}
