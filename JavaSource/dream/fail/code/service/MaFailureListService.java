package dream.fail.code.service;

import java.util.List;

import common.bean.User;
import dream.fail.code.dto.MaFailureCommonDTO;

/**
 * 고장코드 - 목록 service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaFailureListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id: $
     * @param maFailureCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaFailureCommonDTO maFailureCommonDTO, User user);    
   
    /**
     * delete List
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;
    
    public String findTotalCount(MaFailureCommonDTO maFailureCommonDTO, User user);

}
