package dream.consult.program.page.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.program.page.dao.MaPgMngDetailDAO;
import dream.consult.program.page.dto.MaPgMngDetailDTO;

/**
 * 화면 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaPgMngDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maPgMngDetailDAOTarget"
 * @spring.txbn id="maPgMngDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPgMngDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPgMngDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaPgMngDetailDTO findDetail(String pageId, User user)
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
        query.append("		 SFACODE_TO_DESC(x.page_type,'PAGE_TYPE','SYS','','"+user.getLangId()+"') pageTypeDesc,");
        query.append("		 x.page_categ						pageCateg		");
        query.append("FROM   TAPAGE x										");
        query.append("WHERE  x.page_id = '"+pageId+"'						");
    
        MaPgMngDetailDTO maPgMngDetailDTO = 
        		(MaPgMngDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPgMngDetailDTO()));
        
        return maPgMngDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDetailDTO
     * @return
     */
    public int insertDetail(MaPgMngDetailDTO maPgMngDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPAGE					");
    	query.append("	(page_id,		description,		");
    	query.append("	 remark,		is_use,				");
    	query.append("	 file_name,     is_chkauth,         ");
    	query.append("	 page_type,     page_categ          ");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,             ?  					");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			maPgMngDetailDTO.getPageId(),
    			maPgMngDetailDTO.getPageDesc(),
    			maPgMngDetailDTO.getRemark(),
    			maPgMngDetailDTO.getIsUse(),
    			maPgMngDetailDTO.getFileName(),
    			maPgMngDetailDTO.getIsChkauth(),
    			maPgMngDetailDTO.getPageTypeId(),
    			maPgMngDetailDTO.getPageCateg()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngDetailDTO
     * @return
     */
    public int updateDetail(MaPgMngDetailDTO maPgMngDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPAGE SET		");
    	query.append("	description	= ?,	");
    	query.append("	remark		= ?,	");
    	query.append("	file_name	= ?,	");
    	query.append("	is_use		= ?,	");
    	query.append("  is_chkauth  = ?,    ");
    	query.append("  page_type   = ?,     ");
    	query.append("  page_categ  = ?     ");
    	query.append("WHERE page_id = ?		");
    	
    	Object[] objects = new Object[] {
    			maPgMngDetailDTO.getPageDesc(),
    			maPgMngDetailDTO.getRemark(),
    			maPgMngDetailDTO.getFileName(),
    			maPgMngDetailDTO.getIsUse(),
    			maPgMngDetailDTO.getIsChkauth(),
    			maPgMngDetailDTO.getPageTypeId(),
    			maPgMngDetailDTO.getPageCateg(),
    			maPgMngDetailDTO.getPageId()
    			
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}