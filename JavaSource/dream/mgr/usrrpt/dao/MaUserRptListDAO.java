package dream.mgr.usrrpt.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: MaUserRptListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaUserRptListDAO 
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
    public List findMenuList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser);
    
	public int deleteRpt(String id);

	public int deleteTable(String id);

	public int deleteJoin(String id);

	public int deleteCol(String id);

	public int deleteParam(String id);

	public int deleteOrd(String id);
	
    public String findTotalCount(MaUserRptCommonDTO maUserRptCommonDTO, User user) throws Exception;
  
}