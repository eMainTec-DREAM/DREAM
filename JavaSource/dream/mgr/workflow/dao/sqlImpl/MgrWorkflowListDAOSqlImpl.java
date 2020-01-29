package dream.mgr.workflow.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.workflow.dao.MgrWorkflowListDAO;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;

/**
 * Wokrflow Page - List DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrWorkflowListDAOTarget"
 * @spring.txbn id="mgrWorkflowListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrWorkflowListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrWorkflowListDAO
{
    public List findWorkflowList(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                                                                                                                            ");
        query.append("''                                                                                                                              AS seqNo          ");
        query.append(",''                                                                                                                             AS isDelCheck    ");
        query.append(",dbo.SFACODE_TO_DESC(workflow_type, 'WORKFLOW_TYPE', 'SYS', a.comp_no,'"+user.getLangId()+"') workflowType"); 
        query.append(",description                                                                                                                AS description    ");
        query.append(",is_use                                                                                                                      AS isUse            ");
        query.append(",reg_date                                                                                                                  AS regDate         ");
        query.append(",remark                                                                                                                     AS remark          ");
        query.append(",wflist_id                                                                                                                   AS wflistId          ");
        query.append("FROM TAWFLIST a                                                                                                                              ");
        query.append("WHERE a.comp_no = '"+user.getCompNo()+"'");   
        query.append(this.getWhere(mgrWorkflowCommonDTO, user));
        query.getOrderByQuery("a.wflist_id","a.wflist_id", mgrWorkflowCommonDTO.getOrderBy(), mgrWorkflowCommonDTO.getDirection());
        
        
        return getJdbcTemplate().queryForList(query.toString(mgrWorkflowCommonDTO.getIsLoadMaxCount(), mgrWorkflowCommonDTO.getFirstRow()));
        } 

    private String getWhere(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user)
    {        
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        if(!"".equals(mgrWorkflowCommonDTO.getWflistId())||mgrWorkflowCommonDTO.getWflistId()!=null) {
            query.getAndQuery("a.wflist_id", mgrWorkflowCommonDTO.getWflistId());
        }
        query.getSysCdQuery("a.workflow_type", mgrWorkflowCommonDTO.getFilterWorkflowTypeId(), mgrWorkflowCommonDTO.getFilterWorkflowTypeDesc(), "WORKFLOW_TYPE", user.getCompNo(), user.getLangId());
        query.getLikeQuery("a.description", mgrWorkflowCommonDTO.getFilterDescription());
        
        return query.toString();
    }

    public int deleteWorkflowList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAWFLIST          ");
        query.append("WHERE  comp_no        = ?     ");
        query.append("  AND  wflist_id      = ?             ");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                     ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAWFLIST a                                      ");
        query.append("WHERE a.comp_no = "+user.getCompNo());    
        query.append(this.getWhere(mgrWorkflowCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}