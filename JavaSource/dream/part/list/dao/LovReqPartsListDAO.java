package dream.part.list.dao;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.LovReqPartsListDTO;

/**
 * 자재검색 팝업
 * @author  kim21017
 * @version $Id: LovReqPartsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovReqPartsListDAO
{
    /**
     * 자재 검색
     * @author  kim21017
     * @version $Id: LovReqPartsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovReqPartsListDTO
     * @param loginUser
     * @return
     */
    public List findPartsList(LovReqPartsListDTO lovReqPartsListDTO, User loginUser);
}