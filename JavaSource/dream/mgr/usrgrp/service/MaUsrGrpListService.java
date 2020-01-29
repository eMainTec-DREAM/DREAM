package dream.mgr.usrgrp.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;

/**
 * 권한그룹 - 목록 service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface MaUsrGrpListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id:  $
     * @param maUsrGrpCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findUseGrpList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user);    
    
    /**
     * delete List
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;
    
    public String findTotalCount(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user);

}
