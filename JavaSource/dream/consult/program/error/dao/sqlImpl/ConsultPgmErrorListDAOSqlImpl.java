package dream.consult.program.error.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.error.dao.ConsultPgmErrorListDAO;
import dream.consult.program.error.dto.ConsultPgmErrorCommonDTO;

/**
 * Error Page - List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="consultPgmErrorListDAOTarget"
 * @spring.txbn id="consultPgmErrorListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultPgmErrorListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultPgmErrorListDAO
{
	public List findPgmErrorList(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                 			");
        query.append("      ''                 AS ISDELCHECK 			");
        query.append("    , ''                 AS SEQNO        			");
        query.append("    , x.errorlog_id      AS ERRORLOGID    		");
        query.append("    , x.errorlog_id      AS ERRORNO        		");
        query.append("    , CONVERT(varchar,x.err_date,20)   AS ERRDATE	");
        query.append("    , x.comp_no          AS COMPNO        		");
        query.append("    , (SELECT a.description FROM TACOMP a WHERE a.comp_no = x.comp_no)         	AS COMPDESC   ");
        query.append("    , x.user_no          AS USERNO        		");
        query.append("    , x.req_url          AS URL            		");
        query.append("    , dbo.SFACODE_TO_DESC( x.check_yn, 'IS_USE','SYS','', '"+user.getLangId()+"') AS CHECKYN    ");
        query.append("    , x.REMARK           AS REMARK        		");
        query.append("FROM TAERRORLOG x 								");
    	query.append("WHERE  1=1										");
    	query.append(this.getWhere(consultPgmErrorCommonDTO, user));
    	query.getOrderByQuery("x.comp_no","x.errorlog_id", consultPgmErrorCommonDTO.getOrderBy(), consultPgmErrorCommonDTO.getDirection());
        
        
    	return getJdbcTemplate().queryForList(query.toString(consultPgmErrorCommonDTO.getIsLoadMaxCount(), consultPgmErrorCommonDTO.getFirstRow()));
    } 

	private String getWhere(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        if(!"".equals(consultPgmErrorCommonDTO.getErrorLogId())){
        	query.getAndQuery("x.errorlog_id", consultPgmErrorCommonDTO.getErrorLogId());
        	return query.toString();
        }

        String startDate = consultPgmErrorCommonDTO.getFilterStartDate()+"000000";
        String endDate = consultPgmErrorCommonDTO.getFilterEndDate()+"235959";
        
        //일자
        query.getAndDateQuery("CONVERT(VARCHAR,x.err_date,20)", startDate, endDate);

        //확인여부
    	query.getSysCdQuery("x.check_yn", consultPgmErrorCommonDTO.getFilterIsCheckId(), consultPgmErrorCommonDTO.getFilterIsCheckDesc(), "IS_USE", "", user.getLangId());

    	return query.toString();
    }

    public int deletePgmErrorList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAERRORLOG		");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  errorlog_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(ConsultPgmErrorCommonDTO consultPgmErrorCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAERRORLOG x																	");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(consultPgmErrorCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}