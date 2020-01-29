package dream.mgr.usrrpt.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptDetailDTO;


/**
 * 상세 service
 * 
 * @author kim21017
 * @version $Id: MaUserRptDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaUserRptDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaUserRptDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param menuId
     * @return
     * @throws Exception
     */
    public MaUserRptDetailDTO findDetail(String menuId, String lang)throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaUserRptDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maUserRptDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaUserRptDetailDTO maUserRptDetailDTO, User loginUser) throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaUserRptDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maUserRptDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaUserRptDetailDTO maUserRptDetailDTO, User loginUser) throws Exception;
    
	/**
	 * 조회
	 * @param maUserRptCommonDTO
	 * @param user
	 * @return
	 */
	public Map<String, List<Map>> makeReport(MaUserRptCommonDTO maUserRptCommonDTO, User user);
}
