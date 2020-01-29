package dream.pers.priv.info.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.pers.priv.info.dao.MaLinkMenuDetailDAO;
import dream.pers.priv.info.dto.MaLinkMenuDetailDTO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;


/**
 * »ó¼¼ dao
 * @author  jung7126
 * @version $Id: MaLinkMenuDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maLinkMenuDetailDAOTarget"
 * @spring.txbn id="maLinkMenuDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaLinkMenuDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaLinkMenuDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaLinkMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     */
    public MaLinkMenuDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT                                                                                ");
        query.append("        (SELECT a.description                                                         ");
        query.append("         FROM   TAMENU a                                                              ");
        query.append("         WHERE  a.menu_id = x.menu_id) AS menuDesc                                    ");
        query.append("        ,(SELECT a.description                                                        ");
        query.append("         FROM   TAMENU a                                                              ");
        query.append("         WHERE  a.menu_id = x.linked_menu_id) AS linkedMenuDesc                       ");
        query.append("       ,x.menu_id menuId                                                              ");
        query.append("       ,x.linked_menu_id linkedMenuId                                                 ");
        query.append("       ,x.ord_no ordNo                                                                ");
        query.append("       ,x.linkedmenu_id linkMenuId                                                    ");
        query.append("FROM   TALINKEDMENU x                                                                 ");
        query.append("WHERE  x.linkedmenu_id = '"+maMyInfoCommonDTO.getLinkMenuId()+"' ");
    
        MaLinkMenuDetailDTO maLinkMenuDetailDTO = 
        		(MaLinkMenuDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaLinkMenuDetailDTO()));
        
        return maLinkMenuDetailDTO;
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaLinkMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maLinkMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int updateDetail(MaLinkMenuDetailDTO maLinkMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TALINKEDMENU SET	");
    	query.append("	menu_id	         = ?,	");
    	query.append("  linked_menu_id   = ?,   ");
    	query.append("	ord_no	         = ? 	");
    	query.append("WHERE linkedmenu_id= ?	");
    	
    	Object[] objects = new Object[] {
    	        maLinkMenuDetailDTO.getMenuId(),
    	        maLinkMenuDetailDTO.getLinkedMenuId(),
    	        maLinkMenuDetailDTO.getOrdNo(),
    	        maLinkMenuDetailDTO.getLinkMenuId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaLinkMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maLinkMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int insertDetail(MaLinkMenuDetailDTO maLinkMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TALINKEDMENU			    ");
    	query.append("	(linkedmenu_id,	comp_no,			");
    	query.append("	 user_id,		menu_id,		    ");
    	query.append("	 ord_no,        linked_menu_id      ");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("   ?,             ?                   ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    	        maLinkMenuDetailDTO.getLinkMenuId(),
    	        maLinkMenuDetailDTO.getCompNo(),
    	        maLinkMenuDetailDTO.getEnterBy(),
    	        maLinkMenuDetailDTO.getMenuId(),
    	        maLinkMenuDetailDTO.getOrdNo(),
    	        maLinkMenuDetailDTO.getLinkedMenuId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}