package gaia.gapg.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import gaia.gapg.dao.GaPgMngListDAO;
import gaia.gapg.dto.GaPgMngCommonDTO;

/**
 * 화면 - 목록 dao
 * @author  kim21017
 * @version $Id: GaPgMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="gaPgMngListDAOTarget"
 * @spring.txbn id="gaPgMngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class GaPgMngListDAOOraImpl extends BaseJdbcDaoSupportOra implements GaPgMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: GaPgMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngCommonDTO
     * @return List
     */
    public List findPgList(GaPgMngCommonDTO gaPgMngCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                   								");
        query.append("       '' seqNo,										");
        query.append("		 '' isDelCheck,									");
        query.append("       x.page_id pageId,        						");
        query.append("       x.file_name fileName,    						");
        query.append("       x.description pageDesc,    					");
        query.append("       x.remark remark,         						");
        query.append("       x.is_use isUse,								");
        query.append("       x.is_chkauth isChkauth                         ");
        query.append("FROM   TAPAGE x          								");
        query.append("WHERE  1=1               								");
        query.append(this.getWhere(gaPgMngCommonDTO));
        query.append("ORDER by file_name         							");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: GaPgMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngDTOList
     * @return
     */
    public int deletePage(String id)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	String pageId=id;
    	
    	query.append("DELETE FROM TAPAGE				");
    	query.append("WHERE page_id = '"+pageId+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPGBTN				");
    	query.append("WHERE page_id = '"+pageId+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAPGPAGE				");
    	query.append("WHERE page_id = '"+pageId+"'		");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: GaPgMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(GaPgMngCommonDTO gaPgMngCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        if (!"".equals(gaPgMngCommonDTO.getPageId()))
        {
            query.getAndQuery("x.page_id", gaPgMngCommonDTO.getPageId());
            return query.toString();
        }
        query.getLikeQuery("x.file_name", gaPgMngCommonDTO.getFilterFileName());
        query.getLikeQuery("x.description", gaPgMngCommonDTO.getFilterPageDesc());
        return query.toString();
    }
}