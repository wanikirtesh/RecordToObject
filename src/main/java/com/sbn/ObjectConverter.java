package com.sbn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectConverter {

    public static <T> List<T> getObjectListFromDbQuery(Class<T> type, String query) {
        List<T> list = new ArrayList<T>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String connectionURL = "jdbc:mysql://localhost/ems";
        try(Connection cn = DriverManager.getConnection(connectionURL,"root","admin")) {
            try(Statement statement = cn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    T t = type.getDeclaredConstructor().newInstance();
                    loadResultSetIntoObject(resultSet, t);
                    list.add(t);
                }
            }catch (Exception e){
                throw e;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static <T> List<T> getObjectListFromJsonArray(Class<T> convertType,String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, mapper.getTypeFactory().constructCollectionType(List.class, convertType));
    }

    private static void loadResultSetIntoObject(ResultSet resultSet, Object object)
            throws IllegalArgumentException, IllegalAccessException, SQLException {
        Class<?> zClass = object.getClass();
        for (Field field : zClass.getDeclaredFields()) {
            field.setAccessible(true);
            DBTable column = field.getAnnotation(DBTable.class);
            Object value = resultSet.getObject(column.columnName());
            Class<?> type = field.getType();
            if (isPrimitive(type)) {
                Class<?> boxed = boxPrimitiveClass(type);
                value = boxed.cast(value);
            }
            field.set(object, value);
        }
    }

    private static boolean isPrimitive(Class<?> type) {
        return (type == int.class || type == long.class || type == double.class || type == float.class
                || type == boolean.class || type == byte.class || type == char.class || type == short.class);
    }

    private static Class<?> boxPrimitiveClass(Class<?> type) {
        if (type == int.class) {
            return Integer.class;
        } else if (type == long.class) {
            return Long.class;
        } else if (type == double.class) {
            return Double.class;
        } else if (type == float.class) {
            return Float.class;
        } else if (type == boolean.class) {
            return Boolean.class;
        } else if (type == byte.class) {
            return Byte.class;
        } else if (type == char.class) {
            return Character.class;
        } else if (type == short.class) {
            return Short.class;
        } else {
            String string = "class '" + type.getName() + "' is not a primitive";
            throw new IllegalArgumentException(string);
        }
    }
}


