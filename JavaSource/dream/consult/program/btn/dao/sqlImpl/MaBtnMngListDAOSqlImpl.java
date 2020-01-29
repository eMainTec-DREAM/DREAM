package dream.consult.program.btn.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.program.btn.dao.MaBtnMngListDAO;
import dream.consult.program.btn.dto.MaBtnMngCommonDTO;

/**
 * 버튼 - 목록 dao
 * @author  kim21017
 * @version $Id: MaBtnMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maBtnMngListDAOTarget"
 * @spring.txbn id="maBtnMngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaBtnMngListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaBtnMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaBtnMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngCommonDTO
     * @return List
     */
    public List findBtnList(MaBtnMngCommonDTO maBtnMngCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT                   					");
        query.append("       ''  seqNo,							");
        query.append("		 '' isDelCheck,						");
//        query.append("       x.button_id ID,                    ");
        query.append("       x.button_id buttonId,      		");
        query.append("       x.button_no buttonNo,      		");
        query.append("       x.description buttonDesc,    		");
        query.append("       x.btn_img btnImg,        			");
        query.append("       x.ord_no ordNo,        			");
        query.append("       x.is_use isUse						");
        query.append("FROM   TABUTTON x        					");
        query.append("WHERE  1=1               					");
        query.append(this.getWhere(maBtnMngCommonDTO));
        query.append("ORDER by x.ord_no       				   ");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaBtnMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maBtnMngCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaBtnMngCommonDTO maBtnMngCommonDTO)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(maBtnMngCommonDTO.getButtonId()))
        {
            query.getAndQuery("x.button_id", maBtnMngCommonDTO.getButtonId());
            return query.toString();
        }
        query.getLikeQuery("x.button_no", maBtnMngCommonDTO.getFilterButtonNo());
        query.getLikeQuery("x.description", maBtnMngCommonDTO.getFilterButtonDesc());
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaBtnMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteBtn(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String cdsysMId = id;
    	
    	query.append("DELETE FROM TABUTTON				");
    	query.append("WHERE button_id = '"+cdsysMId+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
}