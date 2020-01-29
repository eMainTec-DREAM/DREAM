package dream.app.tracelog.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.app.tracelog.dao.AppTracelogListDAO;
import dream.app.tracelog.dto.AppTracelogCommonDTO;

/**
 * TraceLog - List DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="appTracelogListDAOTarget"
 * @spring.txbn id="appTracelogListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AppTracelogListDAOOraImpl extends BaseJdbcDaoSupportOra implements AppTracelogListDAO
{
	public List findCompTracelogList(AppTracelogCommonDTO appTracelogCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT                ");
        query.append("    ''      seqNo             ");
        query.append("    ,''     isDelCheck                ");
        query.append("    ,a.upd_date     updDate               ");
        query.append("    ,a.user_name    userName              ");
        query.append("    ,a.scrntracelog_id  scrntracelogId                ");
        query.append("FROM TASCRNTRACELOG a             ");
        query.append("WHERE 1=1     ");
    	query.append(this.getWhere(appTracelogCommonDTO, user));
        query.getOrderByQuery("a.upd_date DESC", appTracelogCommonDTO.getOrderBy(), appTracelogCommonDTO.getDirection());
        
        Object[] objects = new Object[] {
                appTracelogCommonDTO.getFilterFileName()
                ,appTracelogCommonDTO.getFilterCompNo()
                ,appTracelogCommonDTO.getFilterObjectId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(appTracelogCommonDTO.getIsLoadMaxCount(), appTracelogCommonDTO.getFirstRow()), objects);
    } 

	private String getWhere(AppTracelogCommonDTO appTracelogCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.append("AND a.file_name = ?     ");
        query.append("AND a.comp_no = ?     ");
        query.append("AND a.object_id = ?     ");
        
    	return query.toString();
    }

    public int deleteCompTracelogList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TASCRNTRACELOG			");
        query.append("WHERE  scrntracelog_id 		= ?		");
        
        Object[] objects = new Object[] {   
                id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(AppTracelogCommonDTO appTracelogCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TASCRNTRACELOG a																	");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(appTracelogCommonDTO, user));
    	
    	Object[] objects = new Object[] {
    	        appTracelogCommonDTO.getFilterFileName()
                ,appTracelogCommonDTO.getFilterCompNo()
                ,appTracelogCommonDTO.getFilterObjectId()
        };
        
        List resultList=  getJdbcTemplate().queryForList(query.toString(), objects);
        return QueryBuffer.listToString(resultList);
    }
}