package dream.consult.comp.warehouse.dao;

import java.util.List;

import common.bean.User;
import dream.consult.comp.warehouse.dto.LovWhToolListDTO;

/**
 * ���â�� �˾�
 * @author  kim21017
 * @version $Id: LovWhToolListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovWhToolListDAO
{
    /**
     * ���â�� �˻�
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