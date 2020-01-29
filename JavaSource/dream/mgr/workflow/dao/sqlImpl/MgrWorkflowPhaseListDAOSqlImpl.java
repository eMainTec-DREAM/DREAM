package dream.mgr.workflow.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.workflow.dao.MgrWorkflowPhaseListDAO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;

/**
 * Wokrflow Phase Page - List DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrWorkflowPhaseListDAOTarget"
 * @spring.txbn id="mgrWorkflowPhaseListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrWorkflowPhaseListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrWorkflowPhaseListDAO
{
    public List findWorkflowPhaseList(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO, User user) throws Exception
    { 
    	QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT        ");
        query.append("        ''                                                                                                                AS seqNo          ");
        query.append("       ,''                                                                                                                AS isDelCheck    ");
        query.append("       ,step_num                                                                                                    AS stepNum       ");
        query.append("       ,description                                                                                                   AS description    ");
        query.append("       ,dbo.SFACODE_TO_DESC(approval_type,'APPROVAL_TYPE','SYS','','"+user.getLang()+"')    AS approvalType ");
        query.append("       ,dbo.SFACODE_TO_DESC(grade_type,'GRADE_TYPE','SYS','','"+user.getLang()+"')             AS gradeType     ");
        query.append("       ,is_use                                                                                                         AS isUse            ");
        query.append("       ,remark                                                                                                        AS remark          ");
        query.append("       ,wfphase_id                                                                                                  AS wfphaseId      ");
        query.append("FROM TAWFPHASE                                                                                                                        ");
        query.append("WHERE 1=1                                                                                                                                  ");
        query.getAndQuery("comp_no", user.getCompNo());
        query.getAndNumKeyQuery("wflist_id",mgrWorkflowPhaseListDTO.getWflistId() );
        if(!"".equals(mgrWorkflowPhaseListDTO.getWfphaseId())||mgrWorkflowPhaseListDTO.getWfphaseId()!=null) {
            query.getAndQuery("wfphase_id", mgrWorkflowPhaseListDTO.getWfphaseId());
        }
        query.getOrderByQuery("wfphase_id","step_num", mgrWorkflowPhaseListDTO.getOrderBy(), mgrWorkflowPhaseListDTO.getDirection());
        
        
        return getJdbcTemplate().queryForList(query.toString(mgrWorkflowPhaseListDTO.getIsLoadMaxCount(), mgrWorkflowPhaseListDTO.getFirstRow()));
        } 

    public int deleteWorkflowPhaseList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAWFPHASE         ");
        query.append("WHERE  comp_no        = ?     ");
        query.append("  AND  wfphase_id     = ?         ");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                          ");
        query.append("FROM TAWFPHASE                                      ");
        query.append("WHERE comp_no = "+user.getCompNo()         );
        query.append("AND wflist_id = "+mgrWorkflowPhaseListDTO.getWflistId());
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}