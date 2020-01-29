package dream.mgr.usrgrp.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;

/**
 * 권한그룹 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaUsrGrpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpCommonDTO
     * @return List
     */
    public List findUsrGrpList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user);
    
    /**
     * 삭제
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpGridDTO
     * @return
     */
    public int deleteUsrGrp(String compNo, String usrGrpId);
    
    /**
     * 메뉴 권한 삭제 
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUsrGrpGridDTO
     * @return
     */
    public int deleteUsrGrpMenu(String compNo, String usrGrpId);

    public String findTotalCount(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user);

}