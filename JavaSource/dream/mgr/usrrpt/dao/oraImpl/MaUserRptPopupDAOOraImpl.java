package dream.mgr.usrrpt.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.usrrpt.dao.MaUserRptPopupDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptDetailDTO;


/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: MaUserRptListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maUserRptPopupDAOTarget"
 * @spring.txbn id="maUserRptPopupDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptPopupDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUserRptPopupDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaUserRptListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maUserRptCommonDTO
     * @return List
     */
    public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

//        query.append(maUserRptCommonDTO.getScript());
        
        return getJdbcTemplate().queryForList(query.toString());
    }

	public void makeLogForScript(MaUserRptCommonDTO maUserRptCommonDTO,MaUserRptDetailDTO detailDTO, String errMsg) 
	{
		
		QueryBuffer query = new QueryBuffer();

		query.append("INSERT INTO TAUSRDATALOG(                                      			");
		query.append("   comp_no,     usrdatalog_id,   usrdata_id,    dept_id,    			");
		query.append("   cre_id,      cre_date,       script,       rtn_value           	");
		query.append(")VALUES(                                                    			");
		query.append("   ?,          SQAUSRDATALOG_ID.nextval,      ?,            ?,       	");
		query.append("   ?,          to_char(sysdate,'yyyymmdd'),  ?,            ?			");
		query.append(")                                                            			");

    	
    	Object[] objects = new Object[] {
    			detailDTO.getCompNo(),
//    			detailDTO.getUsrrptId(),
    			detailDTO.getCreDept(),
    			detailDTO.getCreBy(),
//    			maUserRptCommonDTO.getScript(),
    			errMsg
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
	}
}