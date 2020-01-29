package dream.consult.program.page.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.MaGrdMngDetailDAO;
import dream.consult.program.page.dto.MaGrdMngDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author jung7126
 * @version $Id: MaGrdMngDetailDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 * @spring.bean id="maGrdMngDetailDAOTarget"
 * @spring.txbn id="maGrdMngDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaGrdMngDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaGrdMngDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @return
     */
    public MaGrdMngDetailDTO findDetail(String pgGridId,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = user.getLangId();
        
        query.append("SELECT                                            ");
        query.append("        x.pggrid_id pgGridId,                     ");
        query.append("        x.page_id pageId,                         ");
        query.append("        (SELECT a.description                     ");
        query.append("         FROM   TAPAGE a                          ");
        query.append("         WHERE x.page_id = a.page_id ) pageDesc,  ");
//        query.append("		  DECODE((SELECT (SELECT key_name 				");
//        query.append("		  					FROM TALANG 				");
//        query.append("		  					WHERE key_type='MENU' 		");
//        query.append("		  					AND key_no=a.key_no 		");
//        query.getAndQuery("lang", lang);
//        query.append("							  )							");
//        query.append("		  		FROM   TAMENU a  						");
//        query.append("		  		WHERE a.page_id = x.page_id AND rownum=1 ),'',		");
//        query.append("		  		(SELECT (SELECT key_name 				");
//        query.append("		  				 FROM TALANG 					");
//        query.append("		  				WHERE key_type=a.key_type 		");
//        query.append("		  				AND key_no=a.key_no 			");
//        query.getAndQuery("lang", lang);
//        query.append("							  )							");
//        query.append("		  		FROM   TAPGPAGE a						");
//        query.append("		 		WHERE a.c_page_id = x.page_id	 		");
//        query.append("		  		AND ROWNUM=1) ,			 				");
//        query.append("		  		(SELECT (SELECT key_name 				");
//        query.append("		  				 FROM TALANG 					");
//        query.append("		  				 WHERE key_type='MENU' 			");
//        query.append("		  				 AND key_no=a.key_no 			");
//        query.getAndQuery("lang", lang);
//        query.append("							  )							");
//        query.append("		  		FROM   TAMENU a							");
//        query.append("		  		WHERE a.page_id = x.page_id AND rownum=1 )) pageDesc,");
        query.append("        grid_obj_id gridObjId,                    ");
        query.append("        description,                              ");
        query.append("        height                                    ");
        query.append("FROM   TAPGGRID x                                 ");
        query.append("WHERE  x.pggrid_id = '"+pgGridId+"'				");
    
        MaGrdMngDetailDTO maGrdMngDetailDTO = 
        		(MaGrdMngDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaGrdMngDetailDTO()));
        
        return maGrdMngDetailDTO;
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngDetailDTO
     * @return
     */
    public int insertDetail(MaGrdMngDetailDTO maGrdMngDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPGGRID					");
    	query.append("	(pggrid_id,		description,		");
    	query.append("	 page_id,		grid_obj_id,		");
    	query.append("   height                             ");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("   ?                                  ");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    	        maGrdMngDetailDTO.getPgGridId(),
    	        maGrdMngDetailDTO.getDescription(),
    	        maGrdMngDetailDTO.getPageId(),
    	        maGrdMngDetailDTO.getGridObjId(),
    	        maGrdMngDetailDTO.getHeight()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdMngDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngDetailDTO
     * @return
     */
    public int updateDetail(MaGrdMngDetailDTO maGrdMngDetailDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPGGRID SET		");
    	query.append("	description	    = ?,	");
    	query.append("	page_id		    = ?,	");
    	query.append("	grid_obj_id	    = ?, 	");
    	query.append("  height          = ?     ");
    	query.append("WHERE pggrid_id   = ?		");
    	
    	Object[] objects = new Object[] {
    			maGrdMngDetailDTO.getDescription(),
    			maGrdMngDetailDTO.getPageId(),
    			maGrdMngDetailDTO.getGridObjId(),
    			maGrdMngDetailDTO.getHeight(),
    			maGrdMngDetailDTO.getPgGridId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}