package dream.mgr.usrrpt.dao;

import java.util.List;

import common.bean.User;
import common.util.QueryBuffer;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptDetailDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: MaUserRptListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaUserRptPopupDAO
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
    public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser);

	public void makeLogForScript(MaUserRptCommonDTO maUserRptCommonDTO, MaUserRptDetailDTO mcDTO, String errMsg);
}