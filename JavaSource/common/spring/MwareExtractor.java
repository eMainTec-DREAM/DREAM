package common.spring;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import common.util.CommonUtil;

/**
 * M.Ware 공통 Extractor<br>
 * SQL 의 fieldName(alias)이 DTO의 같은 field Name으로 set 된다.<br>
 * DTO의 setFieldName 으로 rs.getString(field Name) 값이 셋팅된다.<br>
 * DTO 의 parameter는 String이어야만 호출된다.<br>
 * @author  javaworker
 * @version $Id: MwareExtractor.java,v 1.1 2013/08/30 09:13:56 javaworker Exp $
 * @since   1.0
 */
public class MwareExtractor implements ResultSetExtractor
{
    private Object objDto = null;
    public MwareExtractor(Object objDto)
    {
        this.objDto = objDto;
    }
    
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException
    {
        List<Field> fieldList = new ArrayList<Field>();
        
        fieldList = CommonUtil.getAllFields(fieldList, objDto.getClass());
        
//        Field[] FieldArray = objDto.getClass(). getDeclaredFields();
        
        if (rs.next())
        {
            for (Field field:fieldList)
            {
                String fieldName = field.getName();
                try
                {
                    String value = rs.getString(fieldName);
                    String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = objDto.getClass().getMethod(methodName, new Class[]{java.lang.String.class});
                    method.invoke(objDto, new Object[]{new String(value)});
                }
                catch(Exception ex)
                {
                    ex.getMessage();
                }
            }
        }
        return objDto;
    }
}
