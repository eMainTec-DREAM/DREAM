package dream.mgr.workflow.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.workflow.dao.MgrWorkflowDetailDAO;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
import dream.mgr.workflow.dto.MgrWorkflowDetailDTO;

/**
 * Wokrflow Page - Detail DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrWorkflowDetailDAOTarget"
 * @spring.txbn id="mgrWorkflowDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrWorkflowDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrWorkflowDetailDAO
{
	
    public MgrWorkflowDetailDTO findWorkflowDetail(MgrWorkflowCommonDTO mgrWorkflowCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT                                                                                                                                                ");
        query.append("comp_no             AS compNo                                                                                                                  ");
        query.append(",wflist_id             AS wflistId                                                                                                                   ");
        query.append(",workflow_type    AS workflowTypeId                                                                                                        ");
        query.append(",SFACODE_TO_DESC(workflow_type, 'WORKFLOW_TYPE', 'SYS', comp_no,'"+user.getLangId()+"') workflowTypeDesc"); 
        query.append(",description        AS description                                                                                                                ");
        query.append(",is_use              AS isUse                                                                                                                        ");
        query.append(",reg_date           AS regDate                                                                                                                    ");
        query.append(",remark              AS remark                                                                                                                      ");
        query.append("FROM TAWFLIST                                                                                                                                    ");
        query.append("WHERE comp_no = ?                                                                                                                               ");
        query.append("and wflist_id = ?                                                                                                                                     ");

        Object[] objects = new Object[] {
                compNo
                ,mgrWorkflowCommonDTO.getWflistId()
    	};
    
        MgrWorkflowDetailDTO mgrWorkflowDetailDTO = 
        		(MgrWorkflowDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MgrWorkflowDetailDTO()));
        
        return mgrWorkflowDetailDTO;
        
    }
    

    public int insertWorkflowDetail(MgrWorkflowDetailDTO mgrWorkflowDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAWFLIST     ");
    	query.append("(comp_no                        ");
    	query.append(",wflist_id                         ");
    	query.append(",workflow_type                 ");
    	query.append(",description                      ");
    	query.append(",is_use                            ");
    	query.append(",reg_date                        ");
    	query.append(",REMARK                          ");
    	query.append(")                                    ");
    	query.append("VALUES                           ");
    	query.append("(?                                   ");
    	query.append(",?                                   ");
    	query.append(",?                                   ");
    	query.append(",?                                   ");
    	query.append(",?                                   ");
    	query.append(",?                                   ");
    	query.append(",?                                   ");
    	query.append(")                                    ");
    	
    	Object[] objects = new Object[] {
    	        mgrWorkflowDetailDTO.getCompNo()
    	        ,mgrWorkflowDetailDTO.getWflistId()
    	        ,mgrWorkflowDetailDTO.getWorkflowTypeId()
    	        ,mgrWorkflowDetailDTO.getDescription()
    	        ,mgrWorkflowDetailDTO.getIsUse()
    	        ,mgrWorkflowDetailDTO.getRegDate()
    	        ,mgrWorkflowDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    public int updateWorkflowDetail(MgrWorkflowDetailDTO mgrWorkflowDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAWFLIST SET     ");
    	query.append("workflow_type = ?            ");
    	query.append(",description = ?                ");
    	query.append(",is_use = ?                      ");
    	query.append(",reg_date = ?                  ");
    	query.append(",remark = ?                     ");
    	query.append("WHERE comp_no = ?         ");
    	query.append("AND wflist_id = ?              ");
    	
    	Object[] objects = new Object[] {
    	        mgrWorkflowDetailDTO.getWorkflowTypeId()
    	        ,mgrWorkflowDetailDTO.getDescription()
    	        ,mgrWorkflowDetailDTO.getIsUse()
    	        ,mgrWorkflowDetailDTO.getRegDate()
    	        ,mgrWorkflowDetailDTO.getRemark()
    	        ,mgrWorkflowDetailDTO.getCompNo()
    	        ,mgrWorkflowDetailDTO.getWflistId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}