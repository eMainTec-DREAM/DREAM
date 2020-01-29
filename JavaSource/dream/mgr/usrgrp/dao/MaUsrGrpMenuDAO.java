package dream.mgr.usrgrp.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.dto.MaUsrGrpMenuDTO;

/**
 * 권한그룹 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaUsrGrpMenuDAO
{
    
    public List findMenuList(MaUsrGrpCommonDTO maUsrGrpCommonDTO);
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpMenuDTO
     * @return
     */
    public int insertUserGrpMenu(MaUsrGrpMenuDTO maUsrGrpMenuDTO);
       
    /**
     * 삭제
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param usrGrpId
     * @return
     */
    public int deleteUserGrpMenuLsit(String compNo, String usrGrpId);
    
    /**
     * 삭제
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpMenuDTO
     * @return
     */
    public int deleteUserGrpMenu(MaUsrGrpMenuDTO maUsrGrpMenuDTO);

    public List findMenuListForTree(String pMenuId, String usrGrpId, String compNo, User user);
}