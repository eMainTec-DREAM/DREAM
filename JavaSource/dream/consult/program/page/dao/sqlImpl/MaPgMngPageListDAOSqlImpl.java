package dream.consult.program.page.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.MaPgMngPageListDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageListDTO;

/**
 * 화면별 탭페이지 목록 dao
 * @author  kim21017
 * @version $Id: MaPgMngPageListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPgMngPageListDAOTarget"
 * @spring.txbn id="maPgMngPageListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPgMngPageListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPgMngPageListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPgMngPageListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param maPgMngPageListDTO
     * @return List
     */
    public List findPageList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngPageListDTO maPgMngPageListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT													");
        query.append("       '' 									seqNo,		");
        query.append("       '' 									isDelCheck,	");
        query.append("       x.page_id 								pageId,		");
        query.append("       x.pgpage_id 							pgPageId,	");
        query.append("       (SELECT file_name									");
        query.append("          FROM TAPAGE										");
        query.append("         WHERE page_id=x.page_id) 			fileName,	");
        query.append("       (SELECT description								");
        query.append("          FROM TAPAGE										");
        query.append("         WHERE page_id=x.page_id) 			pgDesc,		");
        query.append("       (SELECT file_name									");
        query.append("          FROM TAPAGE										");
        query.append("         WHERE page_id=x.c_page_id) 			cFileName,	");
        query.append("       (SELECT description								");
        query.append("          FROM TAPAGE										");
        query.append("         WHERE page_id=x.c_page_id) 			pageDesc,	");
        query.append("       x.ord_no 								ordNo		");
        query.append("       ,x.is_use 								ISUSE		");
        query.append("FROM   TAPGPAGE x											");
        query.append("WHERE  1=1												");
        query.append(this.getWhere(maPgMngCommonDTO,maPgMngPageListDTO));
        query.append("ORDER BY x.ord_no   										");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPgMngPageListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deletePageList(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String pgPageId=id;
    	
    	query.append("DELETE FROM TAPGPAGE						");
    	query.append("WHERE  pgpage_id 	= '"+pgPageId+"'		");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngPageListDTO maPgMngPageListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.page_id", maPgMngCommonDTO.getPageId());
    	if (!"".equals(maPgMngPageListDTO.getPgPageId()))
        {
            query.getAndQuery("x.pgpage_id", maPgMngPageListDTO.getPgPageId());
            return query.toString();
        }
    	
    	return query.toString();
    }
}