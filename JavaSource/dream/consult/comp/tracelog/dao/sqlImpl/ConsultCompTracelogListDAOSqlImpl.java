package dream.consult.comp.tracelog.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.tracelog.dao.ConsultCompTracelogListDAO;
import dream.consult.comp.tracelog.dto.ConsultCompTracelogCommonDTO;

/**
 * Screen Trace - List DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="consultCompTracelogListDAOTarget"
 * @spring.txbn id="consultCompTracelogListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompTracelogListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompTracelogListDAO
{
	public List findCompTracelogList(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT        ");
        query.append("    ''      seqNo     ");
        query.append("    ,''     isDelCheck        ");
        query.append("    ,a.comp_no  compNo        ");
        query.append("    ,(SELECT description FROM TACOMP WHERE comp_no=a.comp_no) compDesc        ");
        query.append("    ,a.file_name    fileName      ");
        query.append("    ,(SELECT page_id FROM TAPAGE WHERE file_name=a.file_name)     pageId        ");
        query.append("    ,(SELECT description FROM TAPAGE WHERE file_name=a.file_name)     pageDesc        ");
        query.append("    ,a.object_id    objectId      ");
        query.append("    ,a.upd_date     updDate       ");
        query.append("    ,a.user_name    userName      ");
        query.append("    ,a.scrntracelog_id  scrntracelogId        ");
        query.append("FROM TASCRNTRACELOG a     ");
        query.append("WHERE 1=1     ");
        query.append(this.getWhere(consultCompTracelogCommonDTO, user));
        query.getOrderByQuery("a.scrntracelog_id","a.upd_date", consultCompTracelogCommonDTO.getOrderBy(), consultCompTracelogCommonDTO.getDirection());
    	
    	return getJdbcTemplate().queryForList(query.toString(consultCompTracelogCommonDTO.getIsLoadMaxCount(), consultCompTracelogCommonDTO.getFirstRow()));
    } 

	private String getWhere(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		if(!"".equals(consultCompTracelogCommonDTO.getScrnTraceLogId())){
            query.getAndQuery("a.scrntracelog_id", consultCompTracelogCommonDTO.getScrnTraceLogId());
            return query.toString();
        }
        
        query.getCodeLikeQuery("a.comp_no", "(SELECT description FROM TACOMP WHERE comp_no=a.comp_no)", consultCompTracelogCommonDTO.getFilterCompNo(), consultCompTracelogCommonDTO.getFilterCompDesc());
        
        query.getCodeLikeQuery("a.file_name", "(SELECT description FROM TAPAGE WHERE file_name=a.file_name)", consultCompTracelogCommonDTO.getFilterFileName(), consultCompTracelogCommonDTO.getFilterPageDesc());
        
        query.getAndQuery("a.object_id", consultCompTracelogCommonDTO.getFilterObjectId());
        
        if(!"".equals(consultCompTracelogCommonDTO.getFilterMenuId())) {
            query.append("AND (SELECT page_id       ");
            query.append("        FROM TAPAGE       ");
            query.append("        WHERE file_name=a.file_name) IN (SELECT page_id       ");
            query.append("                                                      FROM dbo.SFAPGPAGEINMENU_ALL("+consultCompTracelogCommonDTO.getFilterMenuId()+")     ");
            query.append("                                                      )     ");
        }
        
    	return query.toString();
    }

    public int deleteCompTracelogList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("DELETE FROM TASCRNTRACELOG            ");
        query.append("WHERE  scrntracelog_id        = ?     ");
        
        Object[] objects = new Object[] {   
                id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TASCRNTRACELOG a                                                                 ");
        query.append("WHERE  1=1                                                                        ");
        query.append(this.getWhere(consultCompTracelogCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}