package dream.mgr.usrgrp.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.dto.MaUsrGrpDetailDTO;

/**
 * 권한그룹 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaUsrGrpDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUsrGrpCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MaUsrGrpDetailDTO findDetail(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUsrGrpDetailDTO
     * @param menuIds
     * @return
     * @throws Exception
     */
    public int insertDetail(MaUsrGrpDetailDTO maUsrGrpDetailDTO, String[] menuIds) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUsrGrpDetailDTO
     * @param menuIds
     * @return
     * @throws Exception
     */
    public int updateDetail(MaUsrGrpDetailDTO maUsrGrpDetailDTO, String[] menuIds) throws Exception;
    
    /**
     * valid userGroup
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maUsrGrpDetailDTO
     * @return
     * @throws Exception
     */
    public String validUserGroup(MaUsrGrpDetailDTO maUsrGrpDetailDTO) throws Exception;

    /**
     *  menu find
     * @author  
     * @version $Id:  $
     * @param maUsrGrpCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findMenuList(MaUsrGrpCommonDTO maUsrGrpCommonDTO);

    public Map findMenuListForTree(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user);  
}
