package dream.consult.program.page.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.MaPgMngPageDetailDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageDetailDTO;

/**
 * 화면별 탭페이지 상세 dao
 * @author  kim21017
 * @version $Id: MaPgMngPageDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPgMngPageDetailDAOTarget"
 * @spring.txbn id="maPgMngPageDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPgMngPageDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPgMngPageDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPgMngPageDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param pgPageId
     * @return
     */
    public MaPgMngPageDetailDTO findDetail(String pageId, String pgPageId, String lang)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("SELECT														");
        query.append("       x.page_id						pgId					");
        query.append("       ,x.pgpage_id					pgPageId				");
        query.append("       ,(SELECT description									");
        query.append("       	FROM TAPAGE											");
        query.append("         WHERE page_id = x.page_id) 	pgDesc					");
        query.append("       ,(SELECT file_name										");
        query.append("       	FROM TAPAGE											");
        query.append("         WHERE page_id = x.page_id) 	fileName				");
        query.append("       ,x.c_page_id			pageId							");
        query.append("       ,(SELECT description									");
        query.append("       	FROM TAPAGE											");
        query.append("         WHERE page_id = x.c_page_id) pageDesc				");
        query.append("       ,(SELECT key_name										");
        query.append("       	FROM TALANG											");
        query.append("         WHERE key_type = x.key_type							");
        query.append("           AND key_no = x.key_no								");
        query.append("           AND lang = '"+lang+"') 	langDesc				");
        query.append("       ,x.key_type						keyType				");
        query.append("       ,x.key_no						keyNo					");
        query.append("       ,x.is_use						isUse					");
        query.append("       ,x.ord_no						ordNo					");
        query.append("       ,x.remark						remark					");
        query.append("FROM   TAPGPAGE x												");
        query.append("WHERE  x.page_id 		= ?							            ");
        query.append("  AND  x.pgpage_id 	= ?						                ");
        
        Object[] objects = new Object[]{
        	pageId
        	,pgPageId
        };
    
        MaPgMngPageDetailDTO maPgMngPageDetailDTO = 
        		(MaPgMngPageDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new MaPgMngPageDetailDTO()));
        
        return maPgMngPageDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPgMngPageDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngPageDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int updateDetail(MaPgMngPageDetailDTO maPgMngPageDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPGPAGE SET	");
    	query.append("	c_page_id	 = ?	");
    	query.append("	,key_type	 = ?	");
    	query.append("	,key_no		 = ?	");
    	query.append("	,is_use		 = ?	");
    	query.append("	,ord_no		 = ?	");
    	query.append("	,remark		 = ?	");
    	query.append("WHERE pgpage_id = ?	");
    	query.append("  AND page_id  = ?	");
    	
    	Object[] objects = new Object[] {
    			maPgMngPageDetailDTO.getPageId()
    			,maPgMngPageDetailDTO.getKeyType()
    			,maPgMngPageDetailDTO.getKeyNo()
    			,maPgMngPageDetailDTO.getIsUse()
    			,maPgMngPageDetailDTO.getOrdNo()
    			,maPgMngPageDetailDTO.getRemark()
    			,maPgMngPageDetailDTO.getPgPageId()
    			,maPgMngCommonDTO.getPageId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPgMngPageDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngPageDetailDTO
     * @param maPgMngCommonDTO
     * @return
     */
    public int insertDetail(MaPgMngPageDetailDTO maPgMngPageDetailDTO, MaPgMngCommonDTO maPgMngCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPGPAGE					");
    	query.append("	(page_id,		pgpage_id,			");
    	query.append("	 c_page_id,		ord_no,				");
    	query.append("	 key_type,	key_no,	is_use ,remark	");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,			?,?		");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			maPgMngCommonDTO.getPageId()
    			,maPgMngPageDetailDTO.getPgPageId()
    			,maPgMngPageDetailDTO.getPageId()
    			,maPgMngPageDetailDTO.getOrdNo()
    			,maPgMngPageDetailDTO.getKeyType()
    			,maPgMngPageDetailDTO.getKeyNo()
    			,maPgMngPageDetailDTO.getIsUse()
    			,maPgMngPageDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}