package dream.consult.program.setting.upload.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.exception.SqlIgnoreException;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.consult.program.setting.upload.dao.ConsultPgmSettingUpdFileDAO;
import dream.consult.program.setting.upload.dto.ConsultPgmSettingUpdFileDTO;

/**
 * ¿¢¼¿¾÷·Îµå - »ó¼¼ dao
 * 
 * @author ghlee
 * @version $Id$
 * @since 1.0
 * @spring.bean id="consultPgmSettingUpdFileDAOTarget"
 * @spring.txbn id="consultPgmSettingUpdFileDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmSettingUpdFileDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultPgmSettingUpdFileDAO
{
    public List getExcelTableCol(String id)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT    ");
        query.append("      excel_col_name excelColName     ");
        query.append("      ,table_col_name tableColName     ");
        query.append("FROM TAEXCELCOL                ");
        query.append("  WHERE exceltab_id = ?        ");
        query.append("  AND is_system = ?        ");
        
        Object[] objects = new Object[] {
                id
                ,"N"
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects);
    }
    
    public int insertExcelData(String tableName, Map headMap, Map valueMap, User user) throws SqlIgnoreException
    {
        try {
            if("".equals(tableName)) {
                throw new SqlIgnoreException("table name empty");
            }
            if(headMap.size()==0 && valueMap.size()==0) {
                throw new SqlIgnoreException("excel data is wrong");
            }
            if(headMap.size() != valueMap.size()) {
                throw new SqlIgnoreException("system error:missmatch columns and values");
            }
            QuerySqlBuffer query = new QuerySqlBuffer();
            Object[] objects = new Object[valueMap.size()+3];
            
            query.append("INSERT INTO "+tableName);
            query.append("  (skey_id,       comp_no,        user_no     ");
            for(Object key:headMap.keySet()) {
                query.append(","+(String) headMap.get(key));
            }
            query.append("  )                                   ");
            query.append("  VALUES                              ");
            query.append("  (?,     ?,      ?   ");
            objects[0] = getNextSequence("sqaskey_id");
            objects[1] = user.getCompNo();
            objects[2] = user.getUserNo();
            int ind = 3;
            for(Object key:valueMap.keySet()) {
                query.append(",?");
                objects[ind] = valueMap.get(key);
                ind++;
            }
            query.append("  )                                   ");
            
            return getJdbcTemplate().update(query.toString(), getObject(objects));
        }
        catch(Exception e) {
            throw new SqlIgnoreException(e.getMessage());
        }
    }
    
    public int deleteData(String tableName, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM "+tableName);
        query.append("  WHERE comp_no = ?        ");
        query.append("  AND user_no = ?        ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,user.getUserNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public List getIds(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT exceltablist_id AS excelTabListId       ");
        query.append("FROM TAEXCELTABLIST                ");
        query.append("  WHERE exceltab_id = ?        ");
        query.append("  AND comp_no = ?        ");
        
        Object[] objects = new Object[] {
                consultPgmSettingUpdFileDTO.getExcelTabId()
                ,user.getCompNo()
        };
        
        return getJdbcTemplate().queryForList(query.toString(), objects);
    }
    /**
     * detail insert
     * @author ghlee
     * @version $Id:$
     * @param consultPgmSettingUpdFileCommonDTO 
     * @since   1.0
     * 
     * @param consultPgmSettingUpdFileDTO
     * @return
     */
    public int insertDetail(ConsultPgmSettingUpdFileDTO consultPgmSettingUpdFileDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("INSERT INTO TAEXCELTABLIST                ");
        query.append("  (comp_no,       exceltablist_id,        ");
        query.append("   exceltab_id,       upload_date,            ");
        query.append("   upload_by                         ");
        query.append("  )                                   ");
        query.append("  VALUES                              ");
        query.append("  (?,             ?,                 ");
        query.append("   ?,             ?,                   ");
        query.append("   ?                             ");
        query.append("  )                                   ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,consultPgmSettingUpdFileDTO.getExcelTabListId()
                ,consultPgmSettingUpdFileDTO.getExcelTabId()
                ,DateUtil.getDate()
                ,user.getEmpId()
        };
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param consultPgmSettingUpdFileDTO
     * @return
     */
    public int updateDetail(Object id, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAEXCELTABLIST SET        ");
        query.append("  upload_date         = ?,      ");
        query.append("  upload_by            = ?      ");
        query.append("WHERE exceltablist_id       = ?      ");
        query.append("AND comp_no       = ?      ");
        
        Object[] objects = new Object[] {
                DateUtil.getDate()
                ,user.getEmpId()
                ,id
                ,user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}