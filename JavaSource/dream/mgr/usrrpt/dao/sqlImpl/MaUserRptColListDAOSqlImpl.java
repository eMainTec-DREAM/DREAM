package dream.mgr.usrrpt.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.usrrpt.dao.MaUserRptColListDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * ¸ñ·Ï dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maUserRptColListDAOTarget"
 * @spring.txbn id="maUserRptColListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptColListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaUserRptColListDAO
{
	/**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 									");
        query.append("       '' seqNo,							");
        query.append("       x.table_name tableName,			");
        query.append("       x.column_name columnName,			");
        query.append("       x.description colDesc,				");
        query.append("       ISNULL(use_yn,'N') isDisplay,  	");
        query.append("       step_num stepNum,					");
        query.append("       col_alias colAlias,				");
        query.append("       col_width colWidth,				");
        query.append("       col_size colSize,					");
        query.append("		 dbo.SFACODE_TO_DESC(col_align,'ALIGN_TYPE','SYS','','"+loginUser.getLangId()+"')	colAlign,");
//      
//        query.append("       SFACODE_TO_DESC(col_align,'ALIGN_TYPE','SYS','','"+loginUser.getLangId()+"') colAlign,");
        query.append("       usrrptcol_id usrrptcolId,			");
        query.append("       (SELECT a.usrrpttab_id FROM TAUSRRPTTAB a WHERE x.table_id = a.table_id AND a.usrrptlist_id = "+maUserRptCommonDTO.getUsrrptlistId()+")  usrrpttabId,                    		");
        query.append("       (SELECT a.usrrptlist_id FROM TAUSRRPTTAB a WHERE x.table_id = a.table_id AND a.usrrptlist_id = "+maUserRptCommonDTO.getUsrrptlistId()+")  usrrptlistId,      	");
        query.append("       x.tabcol_id tabcolId   			");
        query.append("FROM   TATABCOL x LEFT OUTER JOIN TAUSRRPTCOL y ON x.tabcol_id = y.tabcol_id AND y.usrrptlist_id = "+maUserRptCommonDTO.getUsrrptlistId()+"");
        query.append("WHERE  x.table_id IN (SELECT a.table_id FROM TAUSRRPTTAB a WHERE a.usrrptlist_id = "+maUserRptCommonDTO.getUsrrptlistId()+") 	");
        query.append(this.getWhere(maUserRptCommonDTO,loginUser));
        query.append("ORDER BY 5 DESC, 6, 2, 3	");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param eqPartId
     * @param loginUser
     * @return
     */
    public int deleteList(String eqPartId, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAUSRRPTTAB                                   ");
    	query.append("WHERE  comp_no   = '"+loginUser.getCompNo()+"'         	");
    	query.append("  AND  usrrpttab_id = '"+eqPartId+"'			            ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	if (!"".equals(maUserRptCommonDTO.getUsrrptcolId()))
        {
            query.getAndQuery("y.usrrptcol_id", maUserRptCommonDTO.getUsrrptcolId());
            return query.toString();
        }
    	
    	return query.toString();
    }
}