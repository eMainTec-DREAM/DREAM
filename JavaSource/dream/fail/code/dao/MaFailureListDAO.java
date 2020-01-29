package dream.fail.code.dao;

import java.util.List;

import common.bean.User;
import dream.fail.code.dto.MaFailureCommonDTO;

/**
 * 고장코드 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaFailureListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maFailureCommonDTO
     * @return List
     */
    public List findList(MaFailureCommonDTO maFailureCommonDTO, User user);

    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param failureId
     * @return
     */
    public int deleteParts(String compNo, String failureId);

    public String findTotalCount(MaFailureCommonDTO maFailureCommonDTO, User user);

    public int deleteLangData(String compNo, String failureId);

}