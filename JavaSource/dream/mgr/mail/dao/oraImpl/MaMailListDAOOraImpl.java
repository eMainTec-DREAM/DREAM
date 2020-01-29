package dream.mgr.mail.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.mail.dao.MaMailListDAO;
import dream.mgr.mail.dto.MaMailCommonDTO;

/**
 * 메일수신자설정 - 목록 dao
 * @author  kim21017
 * @version $Id: MaMailListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maMailListDAOTarget"
 * @spring.txbn id="maMailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaMailListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaMailListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaMailListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailCommonDTO
     * @return List
     */
    public List findMailList(MaMailCommonDTO maMailCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                   					");
        query.append("       '' AS seqNo,						");
        query.append("       '' AS isDelCheck,					");
        query.append("       x.mail_list_id AS mailListId,		");
        query.append("       x.mail_list_no AS mailListNo,		");
        query.append("       x.description  AS description,		");
        query.append("       x.remark		AS remark,			");
        query.append("       x.cycle		AS transCycle,		");
        query.append("       x.time_type	AS timeType,		");
        query.append("       x.method_type	AS methodType,		");
        query.append("       x.is_use		AS isUse,			");
        query.append("       x.mail_scope_type		AS mailScopeTypeId,	");
        query.append("		 SFACODE_TO_DESC(x.mail_scope_type,'MAIL_SCOPE_TYPE','SYS','','"+user.getLangId()+"') AS mailScopeTypeDesc,	");
        query.append("		(SELECT a.param1 													");
        query.append("			FROM TACDSYSD a													");
        query.append("			WHERE 1=1														");
        query.append("			AND a.list_type='MAIL_SCOPE_TYPE'								");
        query.append("			AND a.cdsysd_no = x.mail_scope_type) AS detailPage				");
        query.append("FROM   TAMAILLIST x        				");
        query.append("WHERE  1=1               					");
        query.append(this.getWhere(maMailCommonDTO,user));
        query.getOrderByQuery("x.mail_list_no", maMailCommonDTO.getOrderBy(), maMailCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maMailCommonDTO.getIsLoadMaxCount(), maMailCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaMailListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaMailCommonDTO maMailCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if (!"".equals(maMailCommonDTO.getMailListId()))
        {
            query.getAndQuery("x.mail_list_id", maMailCommonDTO.getMailListId());
            return query.toString();
        }
        query.getLikeQuery("x.description", maMailCommonDTO.getFilterMailDesc());
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaMailListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteMail(String id)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	String mailListId = id;
    	
    	query.append("DELETE FROM TAMAILLIST				");
    	query.append("WHERE mail_list_id = '"+mailListId+"'	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAMAILUSR					");
    	query.append("WHERE mail_list_id = '"+mailListId+"'	");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }

	@Override
	public String findTotalCount(MaMailCommonDTO maMailCommonDTO, User user)
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    ");
        query.append("       COUNT(1)                           ");
        query.append("FROM   TAMAILLIST x        				");
        query.append("WHERE  1=1               					");
        query.append(this.getWhere(maMailCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
}