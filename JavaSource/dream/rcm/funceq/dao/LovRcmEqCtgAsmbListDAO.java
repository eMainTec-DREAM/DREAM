package dream.rcm.funceq.dao;

import java.util.List;

import common.bean.User;

import dream.rcm.funceq.dto.LovRcmEqCtgAsmbListDTO;

/**
 * 설비종류 작업부위 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovRcmEqCtgAsmbListDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovRcmEqCtgAsmbListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgAsmbList(LovRcmEqCtgAsmbListDTO lovRcmEqCtgAsmbListDTO, User loginUser);
}