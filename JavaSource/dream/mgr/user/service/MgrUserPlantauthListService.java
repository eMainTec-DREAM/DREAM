package dream.mgr.user.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MgrUserPlantauthDetailDTO;

/**
 * 사용자 공장 권한 - 목록 service
 * @author ssong 
 * @version $Id:  $
 * @since   1.0
 */
public interface MgrUserPlantauthListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maUserCommonDTO
     * @param loginUser
     * @return 조회 결과 
     * @throws Exception
     */
    public List findUserList(MaUserCommonDTO maUserCommonDTO, User loginUser);   
    
    
    
    
    public String findTotalCount(MaUserCommonDTO maUserCommonDTO, User loginUser);
    /**
     * delete List
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;
    public void savePointList(List<Map> gridList, User user) throws Exception;
    public int insertList(MaUserCommonDTO maUserCommonDTO, MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User loginUser) throws Exception;
}
