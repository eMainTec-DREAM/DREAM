package dream.mgr.usrcd.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.usrcd.dto.MaCdUsrCommonDTO;

/**
 * 사용자코드관리 상세 목록
 * @author 
 * @version $Id: $
 * @since 1.0
 */
public interface MaCdUsrCdListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCommonDTO
     * @return 
     */
    public List findSheet(MaCdUsrCommonDTO maCdUsrCommonDTO,User user);
	
    /**
     * 선택된 하위 데이터 삭제 
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maCdUsrCdGridDTO
     * @return
     */
    public int deleteCdUsrCdList(String compNo, String cdUsrdId);
    
    public String findTotalCount(MaCdUsrCommonDTO maCdUsrCommonDTO, User user);
}