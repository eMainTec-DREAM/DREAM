package dream.consult.program.page.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.page.dao.MaPgMngBtnListDAO;
import dream.consult.program.page.dto.MaPgMngBtnListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 버튼 목록 dao
 * @author  kim21017
 * @version $Id: MaPgMngBtnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPgMngBtnListDAOTarget"
 * @spring.txbn id="maPgMngBtnListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPgMngBtnListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPgMngBtnListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPgMngBtnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPgMngCommonDTO
     * @param maPgMngBtnListDTO
     * @return List
     */
    public List findBtnList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngBtnListDTO maPgMngBtnListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String lang = maPgMngCommonDTO.getUserLang();
        query.append("SELECT													");
        query.append("       '' seqNo,		 								    ");
        query.append("       '' isDelCheck,										");
        query.append("       x.page_id pageId,									");
        query.append("       x.pgbtn_id pgBtnId,								");
        query.append("       (SELECT button_no									");
        query.append("          FROM TABUTTON									");
        query.append("         WHERE button_id=x.button_id) buttonNo,			");
        query.append("       (SELECT description								");
        query.append("          FROM TABUTTON									");
        query.append("         WHERE button_id=x.button_id) buttonDesc,			");
        query.append("       (SELECT a.key_name									");
        query.append("        FROM   TALANG a									");
        query.append("        WHERE  a.key_no = x.key_no						");
        query.append("          AND  a.key_type = x.key_type					");
        query.append("          AND  a.lang = '"+lang+"' ) remark, 				");
        query.append("       dbo.SFACODE_TO_DESC(x.button_loc,'BUTTON_LOC','SYS','','"+lang+"') buttonLoc,");
        query.append("       x.ord_no ordNo,									");
        query.append("       x.is_use isUse,									");
        query.append("       x.is_chkauth isChkauth                             ");
        query.append("FROM   TAPGBTN x											");
        query.append("WHERE  1=1												");
        query.append(this.getWhere(maPgMngCommonDTO,maPgMngBtnListDTO));
        query.append("ORDER BY x.ord_no   										");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPgMngBtnListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteBtnList(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String pgBtnId=id;
    	
    	query.append("DELETE FROM TAPGBTN					");
    	query.append("WHERE  pgbtn_id 	= '"+pgBtnId+"'		");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngBtnListDTO maPgMngBtnListDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.page_id", maPgMngCommonDTO.getPageId());
    	if (!"".equals(maPgMngBtnListDTO.getPgBtnId()))
        {
            query.getAndQuery("x.pgbtn_id", maPgMngBtnListDTO.getPgBtnId());
            return query.toString();
        }
    	
    	return query.toString();
    }
}