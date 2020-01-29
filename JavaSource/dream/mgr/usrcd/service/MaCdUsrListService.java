package dream.mgr.usrcd.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;

/**
 * 사용자코드관리
 * @author 
 * @version $Id: $
 * @since 1.0
 */
public interface MaCdUsrListService
{
    /**
     *  grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCommonDTO
     * @return
     */
    public List findSheet(MaCdUsrCommonDTO maCdUsrCommonDTO, User user);
    
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
    
    public String findTotalCount(MaCdUsrCommonDTO maCdUsrCommonDTO, User user);
    
}