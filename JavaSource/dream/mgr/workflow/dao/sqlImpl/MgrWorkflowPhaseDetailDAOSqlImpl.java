package dream.mgr.workflow.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.workflow.dao.MgrWorkflowPhaseDetailDAO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseDetailDTO;
/**
 * Wokrflow Phase Page - Detail DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrWorkflowPhaseDetailDAOTarget"
 * @spring.txbn id="mgrWorkflowPhaseDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrWorkflowPhaseDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrWorkflowPhaseDetailDAO
{
	
    public MgrWorkflowPhaseDetailDTO findWorkflowPhaseDetail(MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("wfphase_id                                                                                                  AS wfphaseId           ");
        query.append(",wflist_id                                                                                                     AS wflistId               ");
        query.append(",step_num                                                                                                   AS stepNum             ");
        query.append(",description                                                                                                 AS description           ");
        query.append(",approval_type                                                                                             AS approvalType       ");
        query.append(",SFACODE_TO_DESC(approval_type,'APPROVAL_TYPE','SYS','','"+user.getLang()+"')   AS approvalTypeDesc ");
        query.append(",grade_type                                                                                                AS gradeType            ");
        query.append(",SFACODE_TO_DESC(grade_type,'GRADE_TYPE','SYS','','"+user.getLang()+"')           AS gradeTypeDesc     ");
        query.append(",is_use                                                                                                       AS isUse                   ");
        query.append(",remark                                                                                                       AS remark                ");
        query.append("FROM TAWFPHASE                                                                                                                      ");
        query.append("WHERE comp_no = ?                                                                                                                   ");
        query.append("AND wfphase_id = ?                                                                                                                    ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
                ,mgrWorkflowPhaseListDTO.getWfphaseId()
        };
    
        MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO = 
                (MgrWorkflowPhaseDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrWorkflowPhaseDetailDTO()));
        
        return mgrWorkflowPhaseDetailDTO;
        
    }
    

    public int insertWorkflowPhaseDetail(MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("INSERT INTO TAWFPHASE(       ");
        query.append("comp_no                               ");
        query.append(",wfphase_id                           ");
        query.append(",wflist_id                               ");
        query.append(",step_num                             ");
        query.append(",description                           "); 
        query.append(",approval_type                       ");
        query.append(",grade_type                           ");
        query.append(",is_use                                  ");
        query.append(",REMARK                                ");
        query.append(") VALUES(                             ");
        query.append("?                                         ");
        query.append(",?                                        ");
        query.append(",?                                        ");
        query.append(",?                                        ");
        query.append(",?                                        "); 
        query.append(",?                                        ");
        query.append(",?                                        ");
        query.append(",?                                        ");
        query.append(",?                                        ");
        query.append(")                                         ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
                ,mgrWorkflowPhaseDetailDTO.getWfphaseId()
                ,mgrWorkflowPhaseDetailDTO.getWflistId()
                ,mgrWorkflowPhaseDetailDTO.getStepNum()
                ,mgrWorkflowPhaseDetailDTO.getDescription()
                ,mgrWorkflowPhaseDetailDTO.getApprovalType()
                ,mgrWorkflowPhaseDetailDTO.getGradeType()
                ,mgrWorkflowPhaseDetailDTO.getIsUse()
                ,mgrWorkflowPhaseDetailDTO.getRemark()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    
    
    public int updateWorkflowPhaseDetail(MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;

        query.append("UPDATE TAWFPHASE SET  ");
        query.append("step_num = ?                  ");
        query.append(",description = ?               ");
        query.append(",approval_type = ?           ");
        query.append(",grade_type = ?               ");
        query.append(",is_use = ?                      ");
        query.append(",remark = ?                     ");
        query.append("WHERE comp_no = ?         ");
        query.append("AND wfphase_id = ?          ");
        
        Object[] objects = new Object[] {
                mgrWorkflowPhaseDetailDTO.getStepNum()
                ,mgrWorkflowPhaseDetailDTO.getDescription()
                ,mgrWorkflowPhaseDetailDTO.getApprovalType()
                ,mgrWorkflowPhaseDetailDTO.getGradeType()
                ,mgrWorkflowPhaseDetailDTO.getIsUse()
                ,mgrWorkflowPhaseDetailDTO.getRemark()
                ,user.getCompNo()
                ,mgrWorkflowPhaseDetailDTO.getWfphaseId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
}