package dream.mgr.usrcd.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.usrcd.dao.MaCdUsrCdDetailDAO;
import dream.mgr.usrcd.dto.MaCdUsrCdDetailDTO;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;

/**
 * 유형별 세부코드 dao
 * @author   
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maCdUsrCdDetailDAOTarget"
 * @spring.txbn id="maCdUsrCdDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaCdUsrCdDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaCdUsrCdDetailDAO
{
    /**
     * detail find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param cdUsrdId
     * @return
     */
    public MaCdUsrCdDetailDTO findDetail(MaCdUsrCommonDTO maCdUsrCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT												");
        query.append("       x.comp_no			          COMPNO			");
        query.append("       ,x.cdusrd_id		          CDUSRDID			");
        query.append("       ,x.cdusrm_id		          CDUSRMID			");
        query.append("       ,x.cdusrd_no                  CDUSRDNO		    ");
        query.append("       ,x.p_cdusrd_id	              PCDUSRDID             ");
        query.append("       ,(SELECT description						        ");
        query.append("         FROM   TACDUSRD								    ");
        query.append("         WHERE  comp_no  = x.comp_no                      ");
        query.append("           AND  cdusrd_id = x.p_cdusrd_id) PCDUSRDDESC    ");
        query.append("       ,x.description                DESCRIPTION		    ");
        query.append("       ,x.remark			          REMARK			    ");
        query.append("       ,x.ord_no			          ORDNO			        ");
        query.append("       ,x.is_use			          ISUSE 			    ");
        query.append("       ,x.mng_cd			          mngCd 			");
        query.append("       ,dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') ISUSEDESC ");
        query.append("FROM   TACDUSRD x										");
        query.append("WHERE  x.comp_no 		= ?					            ");
        query.append("  AND  x.cdusrd_id 	= ?				                ");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,maCdUsrCommonDTO.getCdUsrdId()
    	};
    
        MaCdUsrCdDetailDTO maCdUsrCdDetailDTO = 
        		(MaCdUsrCdDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaCdUsrCdDetailDTO()));
        
        return maCdUsrCdDetailDTO;
    }
    /**
     * detail insert
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCdDetailDTO
     * @return
     */
    public int insertDetail(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TACDUSRD(	                                    ");
    	query.append("	 comp_no,     cdusrd_id,   cdusrm_id, cdusrd_no,        ");
    	query.append("	 p_cdusrd_id, description, remark,    ord_no,    is_use ");
    	query.append("	 ,mng_cd                                                ");
    	query.append(") VALUES (							                    ");
    	query.append("	 ?,			  ?, 	       ?,         ?,                ");
    	query.append("	 ISNULL(?, 0),   ?, 	       ?,         ?,      ?         ");
    	query.append("	 ,?                                                     ");
    	query.append(")                                                         ");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,maCdUsrCdDetailDTO.getCdUsrdId()
    			,maCdUsrCdDetailDTO.getCdUsrmId()
    			,maCdUsrCdDetailDTO.getCdUsrdNo()
    			,"".equals(maCdUsrCdDetailDTO.getPcdUsrdId())?"0":maCdUsrCdDetailDTO.getPcdUsrdId()
    			,maCdUsrCdDetailDTO.getDescription()
    			,maCdUsrCdDetailDTO.getRemark()
    			,maCdUsrCdDetailDTO.getOrdNo()
    			,maCdUsrCdDetailDTO.getIsUse()
    			,maCdUsrCdDetailDTO.getMngCd()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    public int updateFullDesc(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("UPDATE TACDUSRD SET full_desc = ( SELECT   MAX(CASE WHEN LVL =8 THEN b.description + '-' ELSE '' END)    ");
    	query.append("                                            + MAX(CASE WHEN LVL =7 THEN b.description + '-' ELSE '' END) ");
    	query.append("                                            + MAX(CASE WHEN LVL =6 THEN b.description + '-' ELSE '' END) ");
    	query.append("                                            + MAX(CASE WHEN LVL =5 THEN b.description + '-' ELSE '' END) ");
    	query.append("                                            + MAX(CASE WHEN LVL =4 THEN b.description + '-' ELSE '' END) ");
    	query.append("                                            + MAX(CASE WHEN LVL =3 THEN b.description + '-' ELSE '' END) ");
    	query.append("                                            + MAX(CASE WHEN LVL =2 THEN b.description + '-' ELSE '' END) ");
    	query.append("                                            + MAX(CASE WHEN LVL =1 THEN b.description + '' ELSE '' END)  ");
    	query.append("                                     FROM dbo.SFACDUSRD_PALL(?,?,'') a INNER JOIN TACDUSRD b		");
    	query.append("                                     ON a.comp_no = b.comp_no 		");
    	query.append("                                     AND a.cdusrd_id = b.cdusrd_id                             		");

    	query.append("                                   )                                                                   ");
    	query.append("WHERE cdusrd_id = ?                                                                                     ");
    	query.append("    and comp_no = ?                                                                                    ");

    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,maCdUsrCdDetailDTO.getCdUsrdId()
    			,maCdUsrCdDetailDTO.getCdUsrdId()
    			,user.getCompNo()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int updateDirType(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("update a set a.dir_type = b.dir_type                         ");
    	query.append("from TACDUSRD a inner join TACDUSRM b                        ");
    	query.append("      on a.comp_no = b.comp_no and a.cdusrm_id = b.cdusrm_id ");
    	query.append("where 1=1                                                    ");
    	query.append("    and a.comp_no = ?                                        ");
    	query.append("    and a.cdusrd_id = ?                                      ");

    	
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,maCdUsrCdDetailDTO.getCdUsrdId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCdDetailDTO
     * @return
     */
    public int updateDetail(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TACDUSRD SET	        ");
    	query.append("	     p_cdusrd_id   	= ?	    ");
    	query.append("	     ,cdusrd_no     = ?	    ");
    	query.append("	     ,description	= ?	    ");
    	query.append("	     ,remark	    = ?	    ");
    	query.append("	     ,ord_no	    = ?	    ");
    	query.append("	     ,is_use	    = ?		");
    	query.append("	     ,mng_cd	    = ?		");
    	query.append("WHERE  comp_no        = ?	    ");
    	query.append("  AND  cdusrd_id      = ?	    ");
    	
    	Object[] objects = new Object[] {
    	        "".equals(maCdUsrCdDetailDTO.getPcdUsrdId())?"0":maCdUsrCdDetailDTO.getPcdUsrdId()
    			,maCdUsrCdDetailDTO.getCdUsrdNo()
    			,maCdUsrCdDetailDTO.getDescription()
    			,maCdUsrCdDetailDTO.getRemark()
    			,maCdUsrCdDetailDTO.getOrdNo()
    			,maCdUsrCdDetailDTO.getIsUse()
    			,maCdUsrCdDetailDTO.getMngCd()
    			,user.getCompNo()
    			,maCdUsrCdDetailDTO.getCdUsrdId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * valid code
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCdDetailDTO
     * @return
     */
    public String validCode(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user)
    {
        
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT COUNT(*)                           ");
        query.append("FROM   TACDUSRD x                         ");
        query.append("WHERE  cdusrm_id = (SELECT cdusrm_id FROM TACDUSRM WHERE comp_no = x.comp_no AND dir_type = ?)                 ");
        query.append("  AND  comp_no    = ?                     ");
        query.append("  AND  cdusrd_no  = ?                     ");
        
        Object[] objects = new Object[] {
                 maCdUsrCdDetailDTO.getDirType()
                ,user.getCompNo()
                ,maCdUsrCdDetailDTO.getCdUsrdNo()
        };
    	
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    
    }
}