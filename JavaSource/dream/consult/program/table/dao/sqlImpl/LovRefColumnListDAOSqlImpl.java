package dream.consult.program.table.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.table.dao.LovRefColumnListDAO;
import dream.consult.program.table.dto.LovRefColumnListDTO;

/**
 * Ref테이블 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovRefColumnListDAOTarget"
 * @spring.txbn id="lovRefColumnListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovRefColumnListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovRefColumnListDAO
{
    /**
     * 테이블 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovRefColumnListDTO
     * @return
     */
    public List findWkCtrList(LovRefColumnListDTO lovRefColumnListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" SELECT 							");
        query.append(" 		table_name		tableName	");
        query.append(" 		,description 	description ");
        query.append(" 		,table_id 		tableId 	");
        query.append(" FROM TATABLE  					");
        query.append(" WHERE 1=1 						");
        query.getLikeQuery("table_name", lovRefColumnListDTO.getTable());
        query.getLikeQuery("description", lovRefColumnListDTO.getTableDesc());
        if(!"".equals(lovRefColumnListDTO.getExtCode1())){
        	query.append(" AND table_id != '"+lovRefColumnListDTO.getExtCode1()+"' ");
        }
        return getJdbcTemplate().queryForList(query.toString());
    }

    /**
     * 작업그룹 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovRefColumnListDTO
     * @return
     */
    public List findColList(LovRefColumnListDTO lovRefColumnListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" SELECT  							");
        query.append(" 		x.column_name 	columnName	");
        query.append(" 		,x.description 	description	");
        query.append(" 		,x.tabcol_id 	tabcolId 	");
        query.append(" 		,(select y.table_name FROM TATABLE y where x.table_id=y.table_id)	tableId	");
        query.append(" 		,(select y.table_id FROM TATABLE y where x.table_id=y.table_id)		table_Id	");
        query.append(" 		,(select y.description FROM TATABLE y where x.table_id=y.table_id)	tableDesc	");
        query.append(" FROM TATABCOL x  				");
        query.append(" WHERE 1=1 ");
        query.getAndQuery("table_id", lovRefColumnListDTO.getSelectedTableId());
//      
        return getJdbcTemplate().queryForList(query.toString());
    }
}