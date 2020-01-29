package dream.pers.priv.pgm.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.pers.priv.pgm.dao.MaGrdUsrDetailDAO;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;

/**
 * 칼럼 상세 dao
 * @author  jung7126
 * @version $Id: MaGrdUsrDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maGrdUsrDetailDAOTarget"
 * @spring.txbn id="maGrdUsrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaGrdUsrDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaGrdUsrDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrCommonDTO
     * @param grdColId
     * @param user 
     * @return
     */
    public MaGrdUsrDetailDTO findDetail(MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = user.getLangId();
        query.append("SELECT                                                ");
        query.append("        x.pggrid_id pgGridId,                         ");
        query.append("        '' usrPgGridId,                               ");
        query.append("        x.page_id pageId,                             ");
//        query.append("        (SELECT a.description                         ");
//        query.append("         FROM   TAPAGE a                              ");
//        query.append("         WHERE x.page_id = a.page_id ) pageDesc,      ");
        query.append("		  CASE (SELECT TOP 1 (SELECT key_name 			");
        query.append("		  					FROM TALANG 				");
        query.append("		  					WHERE key_type='MENU' 		");
        query.append("		  					AND key_no=a.key_no 		");
        query.getAndQuery("lang", lang);
        query.append("							  )							");
        query.append("		  		FROM   TAMENU a  						");
        query.append("		  		WHERE a.page_id = x.page_id)			");
        query.append("		  	WHEN '' THEN								");
        query.append("		  		(SELECT TOP 1 (SELECT key_name 			");
        query.append("		  				 FROM TALANG 					");
        query.append("		  				WHERE key_type=a.key_type 		");
        query.append("		  				AND key_no=a.key_no 			");
        query.getAndQuery("lang", lang);
        query.append("							  )							");
        query.append("		  		 FROM   TAPGPAGE a						");
        query.append("		 		 WHERE a.c_page_id = x.page_id)	 		");
        query.append("		  	ELSE 			 							");
        query.append("		  		(SELECT TOP 1 (SELECT key_name 			");
        query.append("		  				 FROM TALANG 					");
        query.append("		  				 WHERE key_type='MENU' 			");
        query.append("		  				 AND key_no=a.key_no 			");
        query.getAndQuery("lang", lang);
        query.append("							  )							");
        query.append("		  		FROM   TAMENU a							");
        query.append("		  		WHERE a.page_id = x.page_id)			");
        query.append("		  	END 		AS pageDesc,					");
        query.append("        x.grid_obj_id gridObjId,                      ");
        query.append("        x.description,                                ");
        query.append("        x.height                                      ");
        query.append(" FROM   TAPGGRID x                                    ");
        query.append(" WHERE  1 = 1                                         ");
        query.getAndQuery("(SELECT b.file_name FROM TAPAGE b WHERE x.page_id = b.page_id)", maGrdUsrCommonDTO.getPageId()); 
        query.getAndQuery("grid_obj_id", maGrdUsrCommonDTO.getGridObjId());
        
        MaGrdUsrDetailDTO maGrdUsrDetailDTO = 
        		(MaGrdUsrDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaGrdUsrDetailDTO()));
        
        return maGrdUsrDetailDTO;
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrDetailDTO
     * @param maGrdUsrCommonDTO
     * @return
     */
    public int updateDetail(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO,  User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAUSRPGGRID SET	");
    	query.append("  height           = ?    ");
    	query.append("WHERE usrpggrid_id = ?	");
    	
    	Object[] objects = new Object[] {
    	        maGrdUsrDetailDTO.getHeight(),
    	        maGrdUsrDetailDTO.getUsrPgGridId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrDetailDTO
     * @param maGrdUsrCommonDTO
     * @param user 
     * @return
     */
    public int insertDetail(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAUSRPGGRID			    ");
    	query.append("	(usrpggrid_id,	pggrid_id,   	    ");
    	query.append("	 comp_no,		user_id,     	    ");
    	query.append("	 height              			    ");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ? 									");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    	        maGrdUsrDetailDTO.getUsrPgGridId(),
    	        maGrdUsrDetailDTO.getPgGridId(),
    	        user.getCompNo(),
    	        user.getUserId(),
    	        maGrdUsrDetailDTO.getHeight()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * 유저 컬럼 정의 상세 화면
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param maGrdUsrCommonDTO
     * @param user
     * @return
     */
    public MaGrdUsrDetailDTO findUsrDetail(MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = user.getLangId();
        
        query.append("SELECT                                                                                ");
        query.append("        x.pggrid_id pgGridId,                         ");
        query.append("        y.usrpggrid_id usrPgGridId,                   ");
        query.append("        x.page_id pageId,                             ");
//        query.append("        (SELECT a.description                         ");
//        query.append("         FROM   TAPAGE a                              ");
//        query.append("         WHERE x.page_id = a.page_id ) pageDesc,      ");
        query.append("		  CASE (SELECT TOP 1 (SELECT key_name 			");
        query.append("		  					FROM TALANG 				");
        query.append("		  					WHERE key_type='MENU' 		");
        query.append("		  					AND key_no=a.key_no 		");
        query.getAndQuery("lang", lang);
        query.append("							  )							");
        query.append("		  		FROM   TAMENU a  						");
        query.append("		  		WHERE a.page_id = x.page_id)			");
        query.append("		  	WHEN '' THEN								");
        query.append("		  		(SELECT TOP 1 (SELECT key_name 			");
        query.append("		  				 FROM TALANG 					");
        query.append("		  				WHERE key_type=a.key_type 		");
        query.append("		  				AND key_no=a.key_no 			");
        query.getAndQuery("lang", lang);
        query.append("							  )							");
        query.append("		  		FROM   TAPGPAGE a						");
        query.append("		 		WHERE a.c_page_id = x.page_id)			");
        query.append("		  	ELSE			 							");
        query.append("		  		(SELECT TOP 1 (SELECT key_name 			");
        query.append("		  				 FROM TALANG 					");
        query.append("		  				 WHERE key_type='MENU' 			");
        query.append("		  				 AND key_no=a.key_no 			");
        query.getAndQuery("lang", lang);
        query.append("							  )							");
        query.append("		  		FROM   TAMENU a							");
        query.append("		  		WHERE a.page_id = x.page_id) 			");
        query.append("		  	END AS pageDesc,							");
        query.append("        x.grid_obj_id gridObjId,                      ");
        query.append("        x.description,                                ");
//        query.append("        CASE ISNULL(y.height,'') WHEN '' THEN x.height ELSE y.height END height	");
        query.append("        CASE WHEN ISNULL(y.height,'9999999') = '9999999' THEN x.height ELSE y.height END height       "); //0값에 대한 처리가 이루어지지 않아 변경 
        query.append(" FROM   TAPGGRID x LEFT OUTER JOIN TAUSRPGGRID y      ");
        query.append(" 		ON   x.pggrid_id = y.pggrid_id            		");
        query.append("   	AND  y.user_id 	 = '"+user.getUserId()+"'       ");
        query.append("   	AND  y.comp_no   = '"+user.getCompNo()+"'       ");
        query.append(" WHERE  1=1            								");
        query.getAndQuery("(SELECT b.file_name FROM TAPAGE b WHERE x.page_id = b.page_id)", maGrdUsrCommonDTO.getPageId());
        query.getAndQuery("x.grid_obj_id", maGrdUsrCommonDTO.getGridObjId());
        

        MaGrdUsrDetailDTO maGrdUsrDetailDTO = 
                (MaGrdUsrDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaGrdUsrDetailDTO()));
        
        return maGrdUsrDetailDTO;
    }

}