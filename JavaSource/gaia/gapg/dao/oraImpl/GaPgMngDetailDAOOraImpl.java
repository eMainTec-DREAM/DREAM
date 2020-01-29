package gaia.gapg.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import gaia.gapg.dao.GaPgMngDetailDAO;
import gaia.gapg.dto.GaPgMngDetailDTO;

/**
 * 화면 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: GaPgMngDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="gaPgMngDetailDAOTarget"
 * @spring.txbn id="gaPgMngDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class GaPgMngDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements GaPgMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: GaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public GaPgMngDetailDTO findDetail(String pageId, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("       x.page_id							pageId,		");
        query.append("       x.file_name						fileName,	");
        query.append("       x.description						pageDesc,	");
        query.append("       x.remark							remark,		");
        query.append("       x.is_use							isUse,	    ");
        query.append("       x.is_chkauth                       isChkauth,   ");
        query.append("		 x.page_type						pageTypeId,		");
        query.append("		 SFACODE_TO_DESC(x.page_type,'PAGE_TYPE','SYS','','"+user.getLangId()+"') pageTypeDesc");
        query.append("FROM   TAPAGE x										");
        query.append("WHERE  x.page_id = '"+pageId+"'						");
    
        GaPgMngDetailDTO gaPgMngDetailDTO = 
        		(GaPgMngDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new GaPgMngDetailDTO()));
        
        return gaPgMngDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: GaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngDetailDTO
     * @return
     */
    public int insertDetail(GaPgMngDetailDTO gaPgMngDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPAGE					");
    	query.append("	(page_id,		description,		");
    	query.append("	 remark,		is_use,				");
    	query.append("	 file_name,     is_chkauth,         ");
    	query.append("	 page_type                         ");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?                					");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			gaPgMngDetailDTO.getPageId(),
    			gaPgMngDetailDTO.getPageDesc(),
    			gaPgMngDetailDTO.getRemark(),
    			gaPgMngDetailDTO.getIsUse(),
    			gaPgMngDetailDTO.getFileName(),
    			gaPgMngDetailDTO.getIsChkauth(),
    			gaPgMngDetailDTO.getPageTypeId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: GaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param gaPgMngDetailDTO
     * @return
     */
    public int updateDetail(GaPgMngDetailDTO gaPgMngDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPAGE SET		");
    	query.append("	description	= ?,	");
    	query.append("	remark		= ?,	");
    	query.append("	file_name	= ?,	");
    	query.append("	is_use		= ?,	");
    	query.append("  is_chkauth  = ?,    ");
    	query.append("  page_type   = ?     ");
    	query.append("WHERE page_id = ?		");
    	
    	Object[] objects = new Object[] {
    			gaPgMngDetailDTO.getPageDesc(),
    			gaPgMngDetailDTO.getRemark(),
    			gaPgMngDetailDTO.getFileName(),
    			gaPgMngDetailDTO.getIsUse(),
    			gaPgMngDetailDTO.getIsChkauth(),
    			gaPgMngDetailDTO.getPageTypeId(),
    			gaPgMngDetailDTO.getPageId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}