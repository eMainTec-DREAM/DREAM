package dream.consult.program.uploaddata.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.uploaddata.dao.LovExcelTabAcListDAO;
import dream.consult.program.uploaddata.dto.LovExcelTabAcListDTO;

/**
 * Excel Tab ÆË¾÷
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovExcelTabAcListDAOTarget"
 * @spring.txbn id="lovExcelTabAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovExcelTabAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovExcelTabAcListDAO
{
    /**
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovExcelTabAcListDTO
     * @param loginUser
     * @return
     */
    public List findExcelTabList(LovExcelTabAcListDTO lovExcelTabAcListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT      ");
        query.append("    exceltab_id               exceltab_id        ");
        query.append("    ,exceltab_no              exceltab_no     ");
        query.append("    ,description                description       ");
        query.append("    ,table_name               table_name      ");
        query.append("    ,upload_pgm_name    upload_pgm_name       ");
        query.append("FROM TAEXCELTAB       ");
        query.append("WHERE 1=1     ");
        query.getLikeQuery("description", lovExcelTabAcListDTO.getDescription());
        query.append("ORDER BY description                                         ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    @Override
    public List findExcelTabAcList(LovExcelTabAcListDTO lovExcelTabAcListDTO, User user,
            Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("    exceltab_id               exceltab_id         ");
        query.append("    ,exceltab_no              exceltab_no         ");
        query.append("    ,description              description         ");
        query.append("    ,table_name               table_name          ");
        query.append("    ,upload_pgm_name          upload_pgm_name     ");
        query.append("    ,sheet_name               sheet_name          ");
        query.append("    ,excel_upload_type        excel_upload_type   ");
        query.append("FROM TAEXCELTAB                                   ");
        query.append("WHERE 1=1                                         ");
        query.getAndQuery("excellist_status", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        query.getAndQuery("is_use_config", conditionMap);
        query.getLikeQuery("description", lovExcelTabAcListDTO.getDescription());
        query.append("ORDER BY description                              ");

        return getJdbcTemplate().queryForList(query.toString());
    }
}