package dream.pers.priv.info.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgAsmbListDTO;
import dream.pers.priv.info.dao.MaMyMenuListDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 *  ¸ñ·Ï dao
 * @author  jung7126
 * @version $Id: MaMyMenuListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maMyMenuListDAOTarget"
 * @spring.txbn id="maMyMenuListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaMyMenuListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaMyMenuListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaMyMenuListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param page_id
     * @param user 
     * @return List
     */
    public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                            ");
        query.append("       '' AS seqNo                                                                ");
        query.append("       ,'' AS isDelCheck                                                          ");
        query.append("       ,(SELECT a.description                                                     ");
        query.append("         FROM   TAMENU a                                                          ");
        query.append("         WHERE  a.menu_id = x.menu_id) AS menuDesc                                ");
        query.append("       ,x.ord_no ordNo                                                            ");
        query.append("       ,x.usrmenu_id usrMenuId                                                    ");
        query.append("FROM   TAUSRMENU x 																");
        query.append("WHERE  1=1																		");
        query.append(this.getWhere(maMyInfoCommonDTO,user));
        query.append("ORDER BY x.ord_no                 												");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaMyMenuListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteList(String usrmenuId)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAUSRMENU					    ");
    	query.append("WHERE  usrmenu_id 	= '"+usrmenuId+"' 	");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.user_id", maMyInfoCommonDTO.getUserId());
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	if (!"".equals(maMyInfoCommonDTO.getUsrMenuId()))
        {
            query.getAndQuery("x.usrmenu_id", maMyInfoCommonDTO.getUsrMenuId());
            return query.toString();
        }
    	
    	return query.toString();
    }
}