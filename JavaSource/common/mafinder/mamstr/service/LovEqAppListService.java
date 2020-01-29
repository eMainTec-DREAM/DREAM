package common.mafinder.mamstr.service;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dto.LovEqAppListDTO;

/**
 * 설비기안품의서 팝업 Service
 * @author  kim21017
 * @version $Id: LovEqAppListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovEqAppListService
{

    /**
     * 설비기안품의서 검색
     * @author  kim21017
     * @version $Id: LovEqAppListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovEqAppListDTO
     * @param loginUser
     * @return
     */
    List findEqAppList(LovEqAppListDTO lovEqAppListDTO, User loginUser);
}