package dream.doc.manual.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.doc.manual.dao.DocManualListDAO;
import dream.doc.manual.dto.DocManualCommonDTO;

/**
 * 사용자매뉴얼 - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="docManualListDAOTarget"
 * @spring.txbn id="docManualListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class DocManualListDAOOraImpl extends BaseJdbcDaoSupportOra implements DocManualListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualCommonDTO
     * @return List
     */
    public List findHelpList(DocManualCommonDTO docManualCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                         ");
        query.append("    ''                                            ISDELCHECK   ");
        query.append("    ,''                                           SEQNO        ");
        query.append("    ,a.onlinehelp_id                              onlineHelpNo ");
        query.append("    ,a.description                                title        ");
        query.append("    ,a.upd_date                                   updDate      ");
        query.append("    ,(SELECT description FROM TADEPT                           ");
        query.append("      WHERE dept_id = (SELECT dept_id FROM TAEMP               ");
        query.append("                       WHERE emp_id = a.emp_id                 ");
        query.append("                       AND comp_no = a.comp_no )) updDept      ");
        query.append("    ,a.emp_name                                   updBy        ");
        query.append("    ,a.onlinehelp_id                              onlineHelpId ");
        query.append("FROM TAONLINEHELP a                                            ");
        query.append("WHERE 1=1                                                      ");
        query.append(this.getWhere(docManualCommonDTO, user));
        query.getOrderByQuery("a.onlinehelp_id", docManualCommonDTO.getOrderBy(), docManualCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(docManualCommonDTO.getIsLoadMaxCount(), docManualCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteHelp(String id, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TAONLINEHELP					");
    	query.append("WHERE onlinehelp_id = '"+id+"'			");
    	query.append(" AND comp_no = '"+user.getCompNo()+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param docManualCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(DocManualCommonDTO docManualCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        if (!"".equals(docManualCommonDTO.getOnlineHelpId()))
        {
            query.getAndQuery("a.onlinehelp_id", docManualCommonDTO.getOnlineHelpId());
            return query.toString();
        }
        query.getLikeQuery("a.description", docManualCommonDTO.getFilterTitle());
        
        if(!"".equals(docManualCommonDTO.getFilterMenuId()) || !"".equals(docManualCommonDTO.getFilterMenuDesc())) {
            query.append("AND a.file_name IN(SELECT file_name FROM TAPAGE       ");
            query.append("                          WHERE page_id IN(SELECT DISTINCT page_id FROM TAMENU        ");
            if(!"".equals(docManualCommonDTO.getFilterMenuId()))
                query.append("                                             START WITH menu_id="+docManualCommonDTO.getFilterMenuId()+"        ");
            else if(!"".equals(docManualCommonDTO.getFilterMenuDesc()))
                query.append("                                             START WITH description LIKE UPPER('%"+docManualCommonDTO.getFilterMenuDesc()+"%')        ");
            query.append("                                                 CONNECT BY PRIOR menu_id=p_menu_id        ");
            query.append("                                                 )     ");
            query.append("                          )     ");
        }
        
        return query.toString();
    }
    
    public String findTotalCount(DocManualCommonDTO docManualCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                        ");
        query.append("      COUNT(1)                ");
        query.append("FROM TAONLINEHELP a       ");
        query.append("WHERE  1=1                    ");
        query.append(this.getWhere(docManualCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}