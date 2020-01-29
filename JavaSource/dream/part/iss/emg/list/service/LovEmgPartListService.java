package dream.part.iss.emg.list.service;

import java.util.List;

import common.bean.User;
import dream.part.iss.emg.list.dto.LovEmgPartListDTO;

/**
 * 긴급출고 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovEmgPartListService
{

    /**
     * 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovEmgPartListDTO
     * @param loginUser
     * @return
     */
    List findEmgPartList(LovEmgPartListDTO lovEmgPartListDTO, User loginUser);
	
    public String findTotalCount(LovEmgPartListDTO lovEmgPartListDTO, User user) throws Exception;

}