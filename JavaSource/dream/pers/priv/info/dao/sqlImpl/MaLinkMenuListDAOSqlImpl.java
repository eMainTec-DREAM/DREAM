package dream.pers.priv.info.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;
import dream.pers.priv.info.dao.MaLinkMenuListDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 *  ¸ñ·Ï dao
 * @author  jung7126
 * @version $Id: MaLinkMenuListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maLinkMenuListDAOTarget"
 * @spring.txbn id="maLinkMenuListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaLinkMenuListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaLinkMenuListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaLinkMenuListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @param user 
     * @return List
     */
    public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                ");
        query.append("       '' AS seqNo                                                                    ");
        query.append("       ,'' AS isDelCheck                                                              ");
        query.append("       ,(SELECT a.description                                                         ");
        query.append("         FROM   TAMENU a                                                              ");
        query.append("         WHERE  a.menu_id = x.menu_id) AS menuDesc                                    ");
        query.append("        ,(SELECT a.description                                                        ");
        query.append("         FROM   TAMENU a                                                              ");
        query.append("         WHERE  a.menu_id = x.linked_menu_id) AS linkedMenuDesc                       ");
        query.append("       ,x.ord_no ordNo                                                                ");
        query.append("       ,x.linkedmenu_id linkMenuId                                                    ");
        query.append("FROM   TALINKEDMENU x                                                                 ");
        query.append("WHERE  1=1							    ");
        query.append(this.getWhere(maMyInfoCommonDTO,loginUser));
        
        query.append("ORDER BY x.ord_no                 												    ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaLinkMenuListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteList(String linkMenuId)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TALINKEDMENU					    ");
    	query.append("WHERE  linkedmenu_id 	= '"+linkMenuId+"'   	");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaMyInfoCommonDTO maMyInfoCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.user_id", maMyInfoCommonDTO.getUserId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maMyInfoCommonDTO.getLinkMenuId()))
        {
            query.getAndQuery("x.linkedmenu_id", maMyInfoCommonDTO.getLinkMenuId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
}