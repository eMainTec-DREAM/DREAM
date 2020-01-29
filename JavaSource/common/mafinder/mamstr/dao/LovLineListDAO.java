package common.mafinder.mamstr.dao;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dto.LovLineListDTO;

/**
 * 무정지라인검색 팝업
 * @author  kim21017
 * @version $Id: LovLineListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovLineListDAO
{
    /**
     * 무정지라인 검색
     * @author  kim21017
     * @version $Id: LovLineListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovLineListDTO
     * @param loginUser
     * @return
     */
    public List findLineList(LovLineListDTO lovLineListDTO, User loginUser);
}