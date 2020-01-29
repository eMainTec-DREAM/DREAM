package dream.consult.comp.tracelog.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
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
public class ConsultCompTracelogListDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompTracelogListDAO
{
	public List findCompTracelogList(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
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
        query.getOrderByQuery("a.upd_date", consultCompTracelogCommonDTO.getOrderBy(), consultCompTracelogCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(consultCompTracelogCommonDTO.getIsLoadMaxCount(), consultCompTracelogCommonDTO.getFirstRow()));
        } 

	private String getWhere(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
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
            query.append("        WHERE file_name=a.file_name) IN (SELECT c_page_id AS pageId       ");
            query.append("                                                            FROM TAPGPAGE     ");
            query.append("                                                            START WITH page_id IN(SELECT page_id      ");
            query.append("                                                                                                FROM TAMENU c         ");
            query.append("                                                                                                WHERE c.service_type='WEB'        ");
            query.getAndQuery("c.menu_id", consultCompTracelogCommonDTO.getFilterMenuId());
            query.append("                                                                                                )     ");
            query.append("                                                            CONNECT BY PRIOR c_page_id=page_id        ");
            query.append("                                                            UNION ALL     ");
            query.append("                                                            SELECT page_id AS pageId      ");
            query.append("                                                            FROM TAMENU c         ");
            query.append("                                                            WHERE c.service_type='WEB'        ");
            query.getAndQuery("c.menu_id", consultCompTracelogCommonDTO.getFilterMenuId());
            query.append("                                                            )     ");
        }
        else if(!"".equals(consultCompTracelogCommonDTO.getFilterMenuDesc())) {
            query.append("AND (SELECT page_id       ");
            query.append("        FROM TAPAGE       ");
            query.append("        WHERE file_name=a.file_name) IN (SELECT c_page_id AS pageId       ");
            query.append("                                                            FROM TAPGPAGE     ");
            query.append("                                                            START WITH page_id IN(SELECT page_id      ");
            query.append("                                                                                                FROM TAMENU c         ");
            query.append("                                                                                                WHERE c.service_type='WEB'        ");
            query.append("                                                                                                AND c.key_no IN(SELECT key_no         ");
            query.append("                                                                                                                        FROM TALANG       ");
            query.append("                                                                                                                        WHERE key_type=c.key_type         ");
            query.append("                                                                                                                        AND lang='"+user.getLangId()+"'         ");
            query.getLikeQuery("key_name", consultCompTracelogCommonDTO.getFilterMenuDesc());
            query.append("                                                                                                                        )     ");
            query.append("                                                                                                )     ");
            query.append("                                                            CONNECT BY PRIOR c_page_id=page_id        ");
            query.append("                                                            UNION ALL     ");
            query.append("                                                            SELECT page_id AS pageId      ");
            query.append("                                                            FROM TAMENU c         ");
            query.append("                                                            WHERE c.service_type='WEB'        ");
            query.append("                                                            AND c.key_no IN(SELECT key_no         ");
            query.append("                                                                                    FROM TALANG       ");
            query.append("                                                                                    WHERE key_type=c.key_type         ");
            query.append("                                                                                    AND lang='"+user.getLangId()+"'         ");
            query.getLikeQuery("key_name", consultCompTracelogCommonDTO.getFilterMenuDesc());
            query.append("                                                                                    )     ");
            query.append("                                                            )     ");
        }
        
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
    public String findTotalCount(ConsultCompTracelogCommonDTO consultCompTracelogCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TASCRNTRACELOG a																	");
    	query.append("WHERE  1=1																		");
    	query.append(this.getWhere(consultCompTracelogCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}