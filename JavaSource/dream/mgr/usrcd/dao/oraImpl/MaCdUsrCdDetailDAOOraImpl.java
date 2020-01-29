package dream.mgr.usrcd.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
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
public class MaCdUsrCdDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaCdUsrCdDetailDAO
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
        QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT												");
        query.append("       x.comp_no			          compNo			");
        query.append("       ,x.cdusrd_id		          cdUsrdId			");
        query.append("       ,x.cdusrm_id		          cdUsrmId			");
        query.append("       ,x.cdusrd_no                  cdUsrdNo		    ");
        query.append("       ,x.p_cdusrd_id	              pcdUsrdId        ");
        query.append("       ,(SELECT description						        ");
        query.append("         FROM   TACDUSRD								");
        query.append("         WHERE  comp_no  = x.comp_no                    ");
        query.append("           AND  cdusrd_id = x.p_cdusrd_id) pcdUsrdDesc ");
        query.append("       ,x.description                description		");
        query.append("       ,x.remark			          remark			");
        query.append("       ,x.ord_no			          ordNo			    ");
        query.append("       ,x.is_use			          isUse 			");
        query.append("       ,x.mng_cd			          mngCd 			");
        query.append("       ,SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isUseDesc ");
        query.append("FROM   TACDUSRD x										");
        query.append("WHERE  x.comp_no 		= ?					            ");
        query.append("  AND  x.cdusrd_id 	= ?				                ");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,maCdUsrCommonDTO.getCdUsrdId()
    	};
        
    
        MaCdUsrCdDetailDTO maCdUsrCdDetailDTO = 
        		(MaCdUsrCdDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaCdUsrCdDetailDTO()));
        
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
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TACDUSRD(	                                    ");
    	query.append("	 comp_no,     cdusrd_id,   cdusrm_id, cdusrd_no,        ");
    	query.append("	 p_cdusrd_id, description, remark,    ord_no,    is_use ");
    	query.append("	 ,mng_cd                                                ");
    	query.append(") VALUES (							                    ");
    	query.append("	 ?,			  ?, 	       ?,         ?,                ");
    	query.append("	 NVL(?, 0),   ?, 	       ?,         ?,      ?         ");
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
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int updateFullDesc(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("UPDATE TACDUSRD    SET                                                            ");
    	query.append("                full_desc = (                                                     ");
    	query.append("                SELECT                                                           ");
    	query.append("                        min(decode(lev,9,description||'-',''))||                 ");
    	query.append("                        min(decode(lev,8,description||'-',''))||                 ");
    	query.append("                        min(decode(lev,7,description||'-',''))||                 ");
    	query.append("                        min(decode(lev,6,description||'-',''))||                 ");
    	query.append("                        min(decode(lev,5,description||'-',''))||                 ");
    	query.append("                        min(decode(lev,4,description||'-',''))||                 ");
    	query.append("                        min(decode(lev,3,description||'-',''))||                 ");
    	query.append("                        min(decode(lev,2,description||'-',''))||                 ");
    	query.append("                        min(decode(lev,1,description,'')) a                       ");
    	query.append("                FROM (                                                            ");
    	query.append("                        SELECT description,LEVEL lev                              ");
    	query.append("                        FROM TACDUSRD                                             ");
    	query.append("                        WHERE comp_no=?                                           ");
    	query.append("                        START WITH cdusrd_id= ?                                   ");
    	query.append("                        CONNECT BY PRIOR  p_cdusrd_id = cdusrd_id                 ");
    	query.append("                    )                                                             ");
    	query.append("                )                                                                 ");
    	query.append("WHERE cdusrd_id = ?                                                               ");
    	query.append("    and comp_no = ?                                                               ");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,maCdUsrCdDetailDTO.getCdUsrdId()
    			,maCdUsrCdDetailDTO.getCdUsrdId()
    			,user.getCompNo()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    public int updateDirType(MaCdUsrCdDetailDTO maCdUsrCdDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("update TACDUSRD a set                                     ");
    	query.append("    a.dir_type = (select b.dir_type                       ");
    	query.append("                        from tacdusrm b                   ");
    	query.append("                        where a.comp_no = b.comp_no       ");
    	query.append("                            and a.cdusrm_id = b.cdusrm_id ");
    	query.append("                        )                                 ");
    	query.append("where 1=1                                                 ");
    	query.append("    and a.comp_no = ?                                     ");
    	query.append("    and a.cdusrd_id = ?                                   ");


    	
    	
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
    	QueryBuffer query = new QueryBuffer();

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
    	
    	return getJdbcTemplate().update(query.toString(), objects);
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
    	String compNo = maCdUsrCdDetailDTO.getCompNo();
    	String code = maCdUsrCdDetailDTO.getCdUsrdNo();
        
    	QueryBuffer query = new QueryBuffer();
        
    	query.append("SELECT COUNT(*)                           ");
        query.append("FROM   TACDUSRD x                         ");
    	query.append("WHERE  cdusrm_id = (SELECT cdusrm_id FROM TACDUSRM WHERE comp_no = x.comp_no AND dir_type = ?)                 ");
    	query.append("  AND  comp_no    = ?	                    ");
    	query.append("  AND  cdusrd_no  = ?	                    ");
    	
    	Object[] objects = new Object[] {
    	         maCdUsrCdDetailDTO.getDirType()
    			,user.getCompNo()
    			,maCdUsrCdDetailDTO.getCdUsrdNo()
    	};
    	
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
    
    }
}