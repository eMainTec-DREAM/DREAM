package dream.mgr.usrgrp.dao;

import java.util.List;

import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.dto.MaUsrGrpDetailDTO;

/**
 * 권한그룹 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaUsrGrpDetailDAO
{
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpDetailDTO
     * @return
     */
    public int insertDetail(MaUsrGrpDetailDTO maUsrGrpDetailDTO);

    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpDetailDTO
     * @return
     */
    public int updateDetail(MaUsrGrpDetailDTO maUsrGrpDetailDTO);

    /**
     * valid userGroup
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maUsrGrpDetailDTO
     * @return
     */
    public String validUsrGrpNo(MaUsrGrpDetailDTO maUsrGrpDetailDTO);
    
    public List findMenuList(MaUsrGrpCommonDTO maUsrGrpCommonDTO);
}