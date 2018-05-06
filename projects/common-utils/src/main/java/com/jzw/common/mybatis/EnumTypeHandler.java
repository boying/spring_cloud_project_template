package com.jzw.common.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class EnumTypeHandler extends BaseTypeHandler<Enum> {
    public interface IntCode {
        int getCode();
    }

    public interface StringCode {
        String getCode();
    }

    private Class<? extends Enum> type;

    public EnumTypeHandler(Class<? extends Enum> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Enum parameter, JdbcType jdbcType) throws SQLException {
        if (Arrays.asList(type.getInterfaces()).contains(IntCode.class)) {
            ps.setInt(i, ((IntCode) parameter).getCode());
        } else if (Arrays.asList(type.getInterfaces()).contains(StringCode.class)) {
            ps.setString(i, ((StringCode) parameter).getCode());
        } else {
            ps.setString(i, parameter.name());
        }
    }

    @Override
    public Enum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (Arrays.asList(type.getInterfaces()).contains(IntCode.class)) {
            int intCode = rs.getInt(columnName);
            for (Enum anEnum : type.getEnumConstants()) {
                if (((IntCode) anEnum).getCode() == intCode) {
                    return anEnum;
                }
            }
            throw new IllegalStateException();
        } else if (Arrays.asList(type.getInterfaces()).contains(StringCode.class)) {
            String stringCode = rs.getString(columnName);
            for (Enum anEnum : type.getEnumConstants()) {
                if (((StringCode) anEnum).getCode().equals(stringCode)) {
                    return anEnum;
                }
            }
            throw new IllegalStateException();
        } else {
            return Enum.valueOf(type, rs.getString(columnName));
        }
    }

    @Override
    public Enum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (Arrays.asList(type.getInterfaces()).contains(IntCode.class)) {
            int intCode = rs.getInt(columnIndex);
            for (Enum anEnum : type.getEnumConstants()) {
                if (((IntCode) anEnum).getCode() == intCode) {
                    return anEnum;
                }
            }
            throw new IllegalStateException();
        } else if (Arrays.asList(type.getInterfaces()).contains(StringCode.class)) {
            String stringCode = rs.getString(columnIndex);
            for (Enum anEnum : type.getEnumConstants()) {
                if (((StringCode) anEnum).getCode().equals(stringCode)) {
                    return anEnum;
                }
            }
            throw new IllegalStateException();
        } else {
            return Enum.valueOf(type, rs.getString(columnIndex));
        }
    }

    @Override
    public Enum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (Arrays.asList(type.getInterfaces()).contains(IntCode.class)) {
            int intCode = cs.getInt(columnIndex);
            for (Enum anEnum : type.getEnumConstants()) {
                if (((IntCode) anEnum).getCode() == intCode) {
                    return anEnum;
                }
            }
            throw new IllegalStateException();
        } else if (Arrays.asList(type.getInterfaces()).contains(StringCode.class)) {
            String stringCode = cs.getString(columnIndex);
            for (Enum anEnum : type.getEnumConstants()) {
                if (((StringCode) anEnum).getCode().equals(stringCode)) {
                    return anEnum;
                }
            }
            throw new IllegalStateException();
        } else {
            return Enum.valueOf(type, cs.getString(columnIndex));
        }
    }

}
