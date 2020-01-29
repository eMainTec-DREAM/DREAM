package dream.consult.program.page.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.program.page.dao.MaGrdMngColDetailDAO;
import dream.consult.program.page.dto.MaGrdMngColDetailDTO;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;

/**
 * Ä®·³ »ó¼¼ dao
 * @author  jung7126
 * @version $Id: MaGrdMngColDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maGrdMngColDetailDAOTarget"
 * @spring.txbn id="maGrdMngColDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaGrdMngColDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaGrdMngColDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdMngColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     */
    public MaGrdMngColDetailDTO findDetail(String pageId, String grdColId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT                                                                       ");
    	query.append("       '' seqNo,                                                             ");
    	query.append("       '' isDelCheck,                                                        ");
    	query.append("       (SELECT b.description FROM TAPGGRID a INNER JOIN TAPAGE b      ");
    	query.append("       ON a.page_id=b.page_id                                                  ");
    	query.append("       WHERE a.pggrid_id=x.pggrid_id) pageDesc,                        ");
    	query.append("       (SELECT a.grid_obj_id FROM TAPGGRID a                                 ");
        query.append("       WHERE a.pggrid_id=x.pggrid_id) gridObjId,                        ");
    	query.append("       column_id columnId,                                                   ");
    	query.append("       (SELECT a.key_name                                                    ");
    	query.append("        FROM   TALANG a                                                      ");
    	query.append("        WHERE  a.key_type = x.key_type                                       ");
    	query.append("          AND  a.lang = '"+user.getLangId()+"'                               ");
    	query.append("          AND a.key_no = x.key_no) columnDesc,                               ");
    	query.append("       x.key_no keyNo,                                                       ");
    	query.append("       x.type,                                                               ");
    	query.append("       SFACODE_TO_DESC(x.type,'COLUMN_TYPE','SYS','','"+user.getLangId()+"') typeDesc,                ");
    	query.append("       x.width,                                                              ");
    	query.append("       x.description,                                                        ");
    	query.append("       x.ord_no ordNo,                                                       ");
    	query.append("       x.hidden,                                                             ");
    	query.append("       x.display_yn  displayYn,                                              ");
    	query.append("       x.key_type keyType,                                                   ");
    	query.append("       x.align,                                                              ");
    	query.append("       x.system_col systemCol,                                               ");
    	query.append("       SFACODE_TO_DESC(x.align,'ALIGN_TYPE','SYS','','"+user.getLangId()+"') alignDesc,               ");
    	query.append("       x.pggridcol_id pggridcolId                                            ");
    	query.append("FROM   TAPGGRIDCOL x                                                         ");
        query.append("WHERE  x.pggridcol_id 		=   ?    							           ");
        
        Object[] objects = new Object[]{
        		grdColId
        };
    
        MaGrdMngColDetailDTO maGrdMngColDetailDTO = 
        		(MaGrdMngColDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaGrdMngColDetailDTO()));
        
        return maGrdMngColDetailDTO;
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdMngColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngColDetailDTO
     * @param maGrdMngCommonDTO
     * @return
     */
    public int updateDetail(MaGrdMngColDetailDTO maGrdMngColDetailDTO, MaGrdMngCommonDTO maGrdMngCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPGGRIDCOL SET	");
    	query.append("	column_id	     = ?	");
    	query.append("	,ord_no	         = ?	");
    	query.append("	,key_no		     = ?	");
    	query.append("  ,key_type        = ?    ");
    	query.append("	,width		     = ?	");
    	query.append("  ,align           = ?    ");
    	query.append("  ,system_col      = ?    ");
    	query.append("  ,hidden          = ?    ");
    	query.append("  ,display_yn      = ?    ");
    	query.append("	,type		     = ?	");
    	query.append("	,description     = ?	");
    	query.append("WHERE pggridcol_id = ?	");
    	
    	Object[] objects = new Object[] {
    	        maGrdMngColDetailDTO.getColumnId()
    	        ,maGrdMngColDetailDTO.getOrdNo()
    	        ,maGrdMngColDetailDTO.getKeyNo()
    	        ,maGrdMngColDetailDTO.getKeyType()
    	        ,maGrdMngColDetailDTO.getWidth()
    	        ,maGrdMngColDetailDTO.getAlign()
    	        ,maGrdMngColDetailDTO.getSystemCol()
    	        ,maGrdMngColDetailDTO.getHidden()
    	        ,maGrdMngColDetailDTO.getDisplayYn()
    	        ,maGrdMngColDetailDTO.getType()
    	        ,maGrdMngColDetailDTO.getDescription()
    	        ,maGrdMngColDetailDTO.getPgGridColId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdMngColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngColDetailDTO
     * @param maGrdMngCommonDTO
     * @return
     */
    public int insertDetail(MaGrdMngColDetailDTO maGrdMngColDetailDTO, MaGrdMngCommonDTO maGrdMngCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAPGGRIDCOL(			    ");
    	query.append("	 pggridcol_id,	pggrid_id,			");
    	query.append("	 column_id,		ord_no,			    ");
    	query.append("	 key_no,		width,				");
    	query.append("	 type,			align,			    ");
    	query.append("   sort,          hidden,             ");
    	query.append("   key_type,      system_col,         ");
    	query.append("   description,   display_yn          ");
    	query.append("	)	VALUES	(						");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("   ?,             ?,                  ");
    	query.append("	 ?,				?,					");
    	query.append("   ?,             ?,                  ");
    	query.append("   ?,             ?,                  ");
    	query.append("   ?,             ?                   ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    	        maGrdMngColDetailDTO.getPgGridColId()
    	        ,maGrdMngCommonDTO.getPgGridId()
    	        ,maGrdMngColDetailDTO.getColumnId()
    	        ,maGrdMngColDetailDTO.getOrdNo()
    	        ,maGrdMngColDetailDTO.getKeyNo()
    	        ,maGrdMngColDetailDTO.getWidth()
    	        ,maGrdMngColDetailDTO.getType()
    	        ,maGrdMngColDetailDTO.getAlign()
    	        ,"str"
    	        ,maGrdMngColDetailDTO.getHidden()
    	        ,maGrdMngColDetailDTO.getKeyType()
    	        ,maGrdMngColDetailDTO.getSystemCol()
    	        ,maGrdMngColDetailDTO.getDescription()
    	        ,maGrdMngColDetailDTO.getDisplayYn()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    @Override
    public String getPgGridId(String pageId, String gridObjId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("    pggrid_id     ");
        query.append("FROM TAPGGRID     ");
        query.append("WHERE page_id = ?     ");
        query.append("AND grid_obj_id = ?       ");
        
        Object[] objects = new Object[] {
                pageId
                ,gridObjId
        };
        
        List resultList = getJdbcTemplate().queryForList(query.toString(), objects);

        return QueryBuffer.listToString(resultList);
    }
}