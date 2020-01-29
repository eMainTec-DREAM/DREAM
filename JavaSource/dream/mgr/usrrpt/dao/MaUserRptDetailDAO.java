package dream.mgr.usrrpt.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptDetailDTO;


/**
 * 메뉴 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaUserRptDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaUserRptDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaUserRptDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     */
    public MaUserRptDetailDTO findDetail(String usrrpt_id, String lang);
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaUserRptDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int insertDetail(MaUserRptDetailDTO DetailDTO, User loginUser);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaUserRptDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param DetailDTO
     * @return
     */
    public int updateDetail(MaUserRptDetailDTO DetailDTO, User loginUser);
	public List<Map> findTableList(MaUserRptCommonDTO maUserRptCommonDTO, User user);
	public List<Map> findColList(MaUserRptCommonDTO maUserRptCommonDTO, User user);
	public List<Map> findParamList(MaUserRptCommonDTO maUserRptCommonDTO, User user);
	public List<Map> findOrdList(MaUserRptCommonDTO maUserRptCommonDTO, User user);
	public List<Map> findJoinList(MaUserRptCommonDTO maUserRptCommonDTO, User user);
	
	public List findReportData(String script);
}