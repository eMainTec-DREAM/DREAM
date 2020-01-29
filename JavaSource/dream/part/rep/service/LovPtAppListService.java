package dream.part.rep.service;

import java.util.List;

import common.bean.User;
import dream.part.rep.dto.LovPtAppListDTO;

/**
 * 수리기안품의서 팝업 Service
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 *
 */
public interface LovPtAppListService
{

    /**
     * 수리기안품의서 검색
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param LovPtAppListDTO
     * @param loginUser
     * @return
     */
    List findPtAppList(LovPtAppListDTO lovPtAppListDTO, User loginUser);
}