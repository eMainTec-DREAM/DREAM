package dream.rcm.system.service;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 사원 - 목록 service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface RcmSysListService
{     
    /**
     *  grid find
     * @author  
     * @version $Id:  $
     * @param rcmSysCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws IOException 
     * @throws Exception
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, User user) throws IOException;     
   
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
    public int deleteList(String compNo, String[] deleteRows) throws Exception;

	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, User user);   

}
