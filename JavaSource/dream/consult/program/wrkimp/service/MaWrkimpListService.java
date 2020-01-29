package dream.consult.program.wrkimp.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;


/**
 * 목록 service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface MaWrkimpListService
{     
    /**
     *  grid find
     * @author  
     * @version $Id:  $
     * @param maWrkimpCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWrkimpList(MaWrkimpCommonDTO maWrkimpCommonDTO, User user);     
    public String findTotalCount(MaWrkimpCommonDTO maWrkimpCommonDTO, User user);     
   
    /**
     * delete List
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;   

}
