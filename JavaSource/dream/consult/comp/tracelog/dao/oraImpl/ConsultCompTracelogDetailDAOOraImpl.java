package dream.consult.comp.tracelog.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.comp.tracelog.dao.ConsultCompTracelogDetailDAO;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogDetailDTO;

/**
 * Screen Trace - Detail DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="consultCompTracelogDetailDAOTarget"
 * @spring.txbn id="consultCompTracelogDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompTracelogDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompTracelogDetailDAO
{
	
    public ConsultCompTracelogDetailDTO findCompTracelogDetail(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("    a.scrntracelog_id       scrnTraceLogId        ");
        query.append("    ,a.comp_no              compNo        ");
        query.append("    ,(SELECT description FROM TACOMP WHERE comp_no=a.comp_no) compDesc        ");
        query.append("    ,a.file_name            fileName      ");
        query.append("    ,(SELECT page_id FROM TAPAGE WHERE file_name=a.file_name) pageId      ");
        query.append("    ,(SELECT description FROM TAPAGE WHERE file_name=a.file_name) pageDesc        ");
        query.append("    ,a.object_id            objectId      ");
        query.append("    ,a.upd_date             updDate       ");
        query.append("    ,a.user_no||' / '||a.user_name    updBy       ");
        query.append("    ,a.CONTENTS         CONTENTS      ");
        query.append("FROM TASCRNTRACELOG a     ");
    	query.append("WHERE  1=1																		");
    	query.append("AND    a.scrntracelog_id = ?																");
        
        Object[] objects = new Object[] {
        		consultCompTracelogCommonDTO.getScrnTraceLogId()
    	};
    
        ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO = 
        		(ConsultCompTracelogDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new ConsultCompTracelogDetailDTO()));
        
        return consultCompTracelogDetailDTO;
        
    }
    

    public int insertCompTracelogDetail(ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TASCRNTRACELOG(	");
    	query.append("	scrntracelog_id					");
    	query.append("	,comp_no				");
    	query.append("	,file_name				");
    	query.append("	,object_id			");
    	query.append("	,upd_date				");
    	query.append("	,user_id				");
    	query.append("	,user_no				");
    	query.append("	,user_name				");
    	query.append("	,CONTENTS					");
    	query.append(")VALUES(					");
    	query.append("	?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append(")							");
    	
    	Object[] objects = new Object[] {
    			consultCompTracelogDetailDTO.getScrnTraceLogId()
    			,consultCompTracelogDetailDTO.getCompNo()
    			,consultCompTracelogDetailDTO.getFileName()
    			,consultCompTracelogDetailDTO.getObjectId()
    			,"TO_CHAR(SYSDATE, 'yyyy-mm-dd hh24:mi:ss')"
    			,user.getUserId()
    			,user.getUserNo()
    			,user.getUserName()
    			,consultCompTracelogDetailDTO.getContents()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    public int updateCompTracelogDetail(ConsultCompTracelogDetailDTO consultCompTracelogDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TASCRNTRACELOG SET					");
    	query.append("	comp_no			= ?				");
    	query.append("	,file_name			= ?				");
    	query.append("	,object_id			= ?				");
    	query.append("	,upd_date			= ?				");
    	query.append("	,user_id			= ?				");
    	query.append("	,user_no				= ?				");
    	query.append("	,user_name			= ?				");
    	query.append("	,CONTENTS				= ?				");
    	query.append("WHERE  scrntracelog_id		= ?				");
    	
    	Object[] objects = new Object[] {
    			consultCompTracelogDetailDTO.getCompNo()
    			,consultCompTracelogDetailDTO.getFileName()
    			,consultCompTracelogDetailDTO.getObjectId()
    			,"TO_CHAR(SYSDATE, 'yyyy-mm-dd hh24:mi:ss')"
    			,user.getUserId()
                ,user.getUserNo()
                ,user.getUserName()
    			,consultCompTracelogDetailDTO.getContents()
    			,consultCompTracelogDetailDTO.getScrnTraceLogId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}