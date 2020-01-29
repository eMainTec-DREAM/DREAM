package dream.consult.comp.warehouse.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.warehouse.dto.LovWhToolListDTO;

/**
 * 사용창고 팝업
 * @author  kim21017
 * @version $Id: LovWhToolListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovWhToolListDAO
{
    /**
     * 사용창고 검색
     * @author  kim21017
     * @version $Id: LovWhToolListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovWhListDTO
     * @param loginUser
     * @return
     */
    public List findWhList(LovWhToolListDTO lovWhListDTO, User loginUser);
}