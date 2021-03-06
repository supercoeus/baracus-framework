package org.baracus.dao;

import android.content.ContentValues;
import android.database.Cursor;
import org.baracus.annotations.Bean;
import org.baracus.model.ConfigurationParameter;
import org.baracus.orm.Field;
import org.baracus.orm.FieldList;

import static org.baracus.model.ConfigurationParameter.*;
import static org.baracus.orm.LegacyModelBase.idCol;

/**
 * <pre>
 * Configuration DAO component <br>
 * <hr>
 *
 * This component is a fully functional configuration dao ready to use in order
 * to store in-application key-value-pairs. It is recommended to wrap
 * access to the key-value-pairs into a service bean which is used
 * to return type-safe variables (e.g. "myCount" in configuration is held as string "99")
 * but in your app your service should take care of the type and return myCount as an integer
 * value
 *
 * </pre>
 */
@Bean
public class ConfigurationDao extends BaseDao<ConfigurationParameter> {

    public ConfigurationDao() {
        super(ConfigurationParameter.class);
    }

    /**
     * <pre>Row Mapper implementation for configuration parameter</pre>
     */
    private RowMapper<ConfigurationParameter> rowMapper = new RowMapper<ConfigurationParameter>() {

        @Override
        public ConfigurationParameter from(Cursor c) {
            ConfigurationParameter result = new ConfigurationParameter();
            result.setId(c.getLong(idCol.fieldIndex));
            result.setConfigParameter(c.getString(configParamCol.fieldIndex));
            result.setConfigParameterValue(c.getString(configParamValueCol.fieldIndex));
            result.setTransient(false);
            return result;
        }

        @Override
        public String getAffectedTable() {
            return TABLE_CONFIGURATION;
        }

        @Override
        public FieldList getFieldList() {
            return ConfigurationParameter.fieldList;
        }

        @Override
        public Field getNameField() {
            return ConfigurationParameter.configParamCol;
        }

        public ContentValues getContentValues(ConfigurationParameter parm) {
            ContentValues result = new ContentValues();
            if (parm.getId() != null) {
                result.put(idCol.fieldName, parm.getId());
            }
            if (parm.getConfigParameter() != null) {
                result.put(configParamCol.fieldName, parm.getConfigParameter());
            }
            if (parm.getConfigParameterValue() != null) {
                result.put(configParamValueCol.fieldName, parm.getConfigParameterValue());
            }
            return result;
        }
    };


    @Override
    public RowMapper<ConfigurationParameter> getRowMapper() {
        return rowMapper;
    }

}
