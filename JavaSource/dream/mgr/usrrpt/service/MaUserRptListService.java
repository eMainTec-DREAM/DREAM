package dream.mgr.usrrpt.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;


/**
 * 사용자데이터 - 목록 service
 * @author  kim21017
 * @version $Id: MaUserRptListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaUserRptListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaUserRptListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maUserRptCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findMenuList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser);    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaUserRptListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteMenu(String[] deleteRows) throws Exception;

    public String findTotalCount(MaUserRptCommonDTO maUserRptCommonDTO, User user) throws Exception;

}
