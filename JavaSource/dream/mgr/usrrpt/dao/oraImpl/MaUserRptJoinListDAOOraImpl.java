package dream.mgr.usrrpt.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrrpt.dao.MaUserRptJoinListDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * ¸ñ·Ï dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maUserRptJoinListDAOTarget"
 * @spring.txbn id="maUserRptJoinListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptJoinListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUserRptJoinListDAO
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
    public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        																");
        query.append("       ''                                     seqNo,            																");
        query.append("       ''                                     isDelCheck,        																");
        query.append("       step_num                               stepNum,        																");
        query.append("       (SELECT y.table_name FROM TATABLE y WHERE x.ltable_id = y.table_id) ltableName,     									");
        query.append("       (SELECT z.column_name FROM TATABCOL z WHERE z.table_id = x.ltable_id AND z.tabcol_id = x.ltabcol_id) lcolumnName,		");
        query.append("       SFACODE_TO_DESC(x.tab_con_operator,'TAB_CON_OPERATOR','SYS','','"+user.getLangId()+"') tabConOperatorDesc,				");
        query.append("       SFACODE_TO_DESC(x.col_value_type,'COL_VALUE_TYPE','SYS','','"+user.getLangId()+"') colValueTypeDesc,					");
        query.append("       (SELECT y.table_name FROM TATABLE y WHERE x.rtable_id = y.table_id) rtableName,     									");
        query.append("       (SELECT z.column_name FROM TATABCOL z WHERE z.table_id = x.rtable_id AND z.tabcol_id = x.rtabcol_id) rcolumnName,		");
        query.append("       con_value conValue, 																									");
        query.append("       usrrpttab_id                           usrrpttabId,    																");
        query.append("       usrrptlist_id                          usrrptlistId,																	");
        query.append("       usrrptjoin_id usrrptjoinId     																						");
        query.append("FROM   TAUSRRPTJOIN x           																								");
        query.append("WHERE  1 = 1                                                    																");
        query.append(this.getWhere(maUserRptCommonDTO,user));
        query.append("ORDER BY step_num	                                    																		");
        
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

    	query.append("DELETE FROM TAUSRRPTJOIN                                  ");
    	query.append("WHERE  comp_no   = '"+loginUser.getCompNo()+"'         	");
    	query.append("  AND  usrrptjoin_id = '"+eqPartId+"'			            ");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	if (!"".equals(maUserRptCommonDTO.getUsrrptjoinId()))
        {
            query.getAndQuery("x.usrrptjoin_id", maUserRptCommonDTO.getUsrrptjoinId());
            return query.toString();
        }
    	
    	query.getAndNumKeyQuery("usrrpttab_id", maUserRptCommonDTO.getUsrrpttabId());
    	
    	return query.toString();
    }
}