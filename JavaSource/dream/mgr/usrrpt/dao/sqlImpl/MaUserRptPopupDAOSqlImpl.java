package dream.mgr.usrrpt.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.usrrpt.dao.MaUserRptPopupDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptDetailDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: MaUserRptListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maUserRptPopupDAOTarget"
 * @spring.txbn id="maUserRptPopupDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserRptPopupDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaUserRptPopupDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();

//        query.append(maUserRptCommonDTO.getScript());
        
        return getJdbcTemplate().queryForList(query.toString());
    }

	@Override
	public void makeLogForScript(MaUserRptCommonDTO maUserRptCommonDTO, MaUserRptDetailDTO detailDTO,String errMsg) {
		// TODO Auto-generated method stub
		
	}
}