package common.mafinder.mamstr.dao;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dto.LovEqAppListDTO;

/**
 * 설비기안품의서 팝업
 * @author  kim21017
 * @version $Id: LovEqAppListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 */
public interface LovEqAppListDAO
{
    /**
     * 설비기안품의서 검색
     * @author  kim21017
     * @version $Id: LovEqAppListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovEqAppListDTO
     * @param loginUser
     * @return
     */
    public List findEqAppList(LovEqAppListDTO lovEqAppListDTO, User loginUser);
}