package dream.comm.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.comm.dao.CommLogScreenTraceDAO;
import dream.comm.dto.CommLogScrnTraceDTO;

/**
 * »ó¼¼ dao
 * 
 * @author jung7126
 * @version $Id: CommRevDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 * @spring.bean id="commLogScreenTraceDAOTarget"
 * @spring.txbn id="commLogScreenTraceDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CommLogScreenTraceDAOOraImpl extends BaseJdbcDaoSupportOra implements CommLogScreenTraceDAO
{
	
    public int insertLogScreenTrace(CommLogScrnTraceDTO commLogScrnTraceDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append(" insert into TASCRNTRACELOG(   "); 
    	query.append("   scrntracelog_id             ");
    	query.append(" 	,upd_date                    ");
    	query.append(" 	,comp_no                     ");
    	query.append(" 	,user_id                     ");
    	query.append(" 	,user_no                     ");
    	query.append(" 	,user_name                   ");
    	query.append(" 	,file_name                   ");
    	query.append(" 	,object_id                   ");
    	query.append(" 	,object_no                   ");
    	query.append(" 	,description                 ");
    	query.append(" 	,contents                    ");
    	query.append(" )                             ");
    	query.append(" values(                       ");
    	query.append("      ?                        ");
    	query.append(" 	,?                           ");
    	query.append(" 	,?                           ");
    	query.append(" 	,?                           ");
    	query.append(" 	,?                           ");
    	query.append(" 	,?                           ");
    	query.append(" 	,?                           ");
    	query.append(" 	,?                           ");
    	query.append(" 	,?                           ");
    	query.append(" 	,?                           ");
    	query.append(" 	,?                           ");
    	query.append(" )                             ");
    	
    	Object[] objects = new Object[] {
    			commLogScrnTraceDTO.getScrnTraceLogId()
    			,commLogScrnTraceDTO.getUpdDate()
    			,user.getCompNo()
    			,user.getUserId()
    			,user.getUserNo()
    			,user.getUserName()
    			,commLogScrnTraceDTO.getPageName()
    			,commLogScrnTraceDTO.getObjectId()
    			,commLogScrnTraceDTO.getObjectNo()
    			,commLogScrnTraceDTO.getDescription()
    			,commLogScrnTraceDTO.getContents()    			
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
   
}