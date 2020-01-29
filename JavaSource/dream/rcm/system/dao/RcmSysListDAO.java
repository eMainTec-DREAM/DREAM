package dream.rcm.system.dao;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 사원 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface RcmSysListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param rcmSysCommonDTO
     * @return List
     * @throws IOException 
     */
    public List findList(RcmSysCommonDTO rcmSysCommonDTO, User user) throws IOException;
    
    /**
     * 삭제
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     */
    public int deleteList(String compNo, String Id);

	public String findTotalCount(RcmSysCommonDTO rcmSysCommonDTO, User user);
}