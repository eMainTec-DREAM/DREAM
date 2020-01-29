package dream.rcm.funceq.service;

import java.util.List;

import common.bean.User;

import dream.rcm.funceq.dto.LovRcmEqCtgAsmbListDTO;

/**
 * 설비종류 작업부위팝업 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovRcmEqCtgAsmbListService
{

    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovRcmEqCtgAsmbListDTO
     * @param loginUser
     * @return
     */
    List findEqCtgAsmbList(LovRcmEqCtgAsmbListDTO lovRcmEqCtgAsmbListDTO, User loginUser);
}