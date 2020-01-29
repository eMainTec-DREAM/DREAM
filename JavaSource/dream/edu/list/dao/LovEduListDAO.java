package dream.edu.list.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.edu.list.dto.LovEduListDTO;

/**
 * ±³À°°úÁ¤ ÆË¾÷
 * @author  hyosung
 * @version $Id: LovEduListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
 * @since   1.0
 */
public interface LovEduListDAO
{
    /**
     * ±³À°°úÁ¤ °Ë»ö
     * @author  hyosung
     * @version $Id: LovEduListDAO.java,v 1.0 2016/01/18 00:16:44 hyosung Exp $
     * @since   1.0
     * 
     * @param lovEduListDTO
     * @param loginUser
     * @param conditionMap
     * @return
     */
    public List findEduList(LovEduListDTO lovEduListDTO, User loginUser, Map<String , String > conditionMap);
}