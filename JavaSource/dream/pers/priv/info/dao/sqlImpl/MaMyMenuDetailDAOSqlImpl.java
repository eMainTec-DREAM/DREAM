package dream.pers.priv.info.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.pers.priv.info.dao.MaMyMenuDetailDAO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.MaMyMenuDetailDTO;


/**
 * »ó¼¼ dao
 * @author  jung7126
 * @version $Id: MaMyMenuDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maMyMenuDetailDAOTarget"
 * @spring.txbn id="maMyMenuDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaMyMenuDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaMyMenuDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaMyMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     */
    public MaMyMenuDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT                                                       ");
    	query.append("       x.menu_id menuId                                      ");
    	query.append("       ,(SELECT a.description                                ");
    	query.append("         FROM   TAMENU a                                     ");
    	query.append("         WHERE  a.menu_id = x.menu_id) AS menuDesc           ");
    	query.append("       ,x.ord_no ordNo                                       ");
    	query.append("       ,x.usrmenu_id usrMenuId                               ");
    	query.append("FROM   TAUSRMENU x                                           ");
        query.append("WHERE  x.usrmenu_id = '"+maMyInfoCommonDTO.getUsrMenuId()+"' ");
    
        MaMyMenuDetailDTO maMyMenuDetailDTO = 
        		(MaMyMenuDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaMyMenuDetailDTO()));
        
        return maMyMenuDetailDTO;
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaMyMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maMyMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int updateDetail(MaMyMenuDetailDTO maMyMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAUSRMENU SET	");
    	query.append("	menu_id	         = ?,	");
    	query.append("	ord_no	         = ? 	");
    	query.append("WHERE usrmenu_id   = ?	");
    	
    	Object[] objects = new Object[] {
    	        maMyMenuDetailDTO.getMenuId(),
    	        maMyMenuDetailDTO.getOrdNo(),
    	        maMyMenuDetailDTO.getUsrMenuId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaMyMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maMyMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int insertDetail(MaMyMenuDetailDTO maMyMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAUSRMENU			        ");
    	query.append("	(usrmenu_id,	comp_no,			");
    	query.append("	 user_id,		menu_id,		    ");
    	query.append("	 ord_no				                ");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("   ?                                  ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    	        maMyMenuDetailDTO.getUsrMenuId(),
    	        maMyMenuDetailDTO.getCompNo(),
    	        maMyMenuDetailDTO.getEnterBy(),
    	        maMyMenuDetailDTO.getMenuId(),
    	        maMyMenuDetailDTO.getOrdNo()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}