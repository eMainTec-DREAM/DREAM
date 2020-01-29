package dream.consult.program.error.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
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
public class ConsultPgmErrorDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultPgmErrorDetailDAO
{
	
    public ConsultPgmErrorDetailDTO findPgmErrorDetail(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT                                     					");
    	query.append("      x.errorlog_id         			AS errorLogId    		");
    	query.append("    , x.errorlog_id         			AS errorLogNo    		");
    	query.append("    , CONVERT(varchar,x.err_date,20)  AS errorDate    		");
    	query.append("    , x.comp_no +' / '+x.user_no      AS compUser        		");
    	query.append("    , x.req_url             			AS url    				");
    	query.append("    , x.check_yn            			AS checkYnId    		");
    	query.append("    , dbo.SFACODE_TO_DESC( x.check_yn, 'IS_USE','SYS','', ? ) AS checkYn    		");
    	query.append("    , CONVERT(varchar,x.check_date,20)AS checkDate    		");
    	query.append("    , x.REMARK             			AS REMARK        		");
    	query.append("    , CONVERT(nvarchar(MAX), x.stacktrace_clob)       		AS errorlog        	");
    	query.append("FROM TAERRORLOG x                            					");
    	query.append("WHERE  1=1													");
    	query.append("  AND errorlog_id = ?											");
        
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAERRORLOG SET								");
    	query.append("	 check_yn			= ?							");
    	query.append("  ,check_date			= case when ? = '' then null else convert(varchar, ?, 20) end	");
    	query.append("	,remark				= ?							");
    	query.append("WHERE  errorlog_id	= ?							");
    	
    	Object[] objects = new Object[] {
    			consultPgmErrorDetailDTO.getCheckYnId()
    			,consultPgmErrorDetailDTO.getCheckDate()
    			,CommonUtil.convertDateTime(consultPgmErrorDetailDTO.getCheckDate())
    			,consultPgmErrorDetailDTO.getRemark()
    			,consultPgmErrorDetailDTO.getErrorLogId()
		};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}