package dream.consult.program.error.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.program.error.dao.ConsultPgmErrorDetailDAO;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;
import dream.consult.program.error.dto.ConsultPgmErrorDetailDTO;

/**
 * Error Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="consultPgmErrorDetailDAOTarget"
 * @spring.txbn id="consultPgmErrorDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmErrorDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultPgmErrorDetailDAO
{
	
    public ConsultPgmErrorDetailDTO findPgmErrorDetail(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 									");
        query.append("      x.errorlog_id 		AS errorLogId	");
        query.append("    , x.errorlog_id 		AS errorLogNo	");
        query.append("    , TO_CHAR(x.err_date,'yyyymmddhh24miss') 		AS errorDate	");
        query.append("    , x.comp_no ||' / '||x.user_no 				AS compUser		");
        query.append("    , x.req_url 			AS url			");
        query.append("    , x.check_yn			AS checkYnId	");
        query.append("    , SFACODE_TO_DESC( x.check_yn, 'IS_USE','SYS','', ? )   AS checkYn	");
        query.append("    , TO_CHAR(x.check_date,'yyyymmddhh24miss')	AS checkDate	");
        query.append("    , x.remark 			AS remark		");
        query.append("    , x.stacktrace_clob  	AS errorlog		");
        query.append("FROM TAERRORLOG x							");
    	query.append("WHERE  1=1								");
    	query.append("  AND errorlog_id = ?						");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,consultPgmErrorCommonDTO.getErrorLogId()
    	};
    
        ConsultPgmErrorDetailDTO consultPgmErrorDetailDTO = 
        		(ConsultPgmErrorDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new ConsultPgmErrorDetailDTO()));
        
        return consultPgmErrorDetailDTO;
        
    }
    
    public int updatePgmErrorDetail(ConsultPgmErrorDetailDTO consultPgmErrorDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAERRORLOG SET									");
    	query.append("	 check_yn			= ?								");
    	query.append("  ,check_date			= TO_DATE(?,'yyyymmddhh24miss')	");
    	query.append("	,remark				= ?								");
    	query.append("WHERE  errorlog_id	= ?								");
    	
    	Object[] objects = new Object[] {
    			consultPgmErrorDetailDTO.getCheckYnId()
    			,consultPgmErrorDetailDTO.getCheckDate()
    			,consultPgmErrorDetailDTO.getRemark()
    			,consultPgmErrorDetailDTO.getErrorLogId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}