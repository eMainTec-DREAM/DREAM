package dream.rcm.system.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.system.dto.LovRcmListDTO;

/**
 * 자산검색 팝업
 * @author  kim21017
 * @version $Id: LovRcmListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovRcmListDAO
{
    /**
     * 자산 검색
     * @author  kim21017
     * @version $Id: LovRcmListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovRcmListDTO
     * @param loginUser
     * @return
     */
    public List findRcmList(LovRcmListDTO lovRcmListDTO, User loginUser);
}