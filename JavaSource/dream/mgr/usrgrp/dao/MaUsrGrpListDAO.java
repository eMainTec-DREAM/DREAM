package dream.mgr.usrgrp.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;

/**
 * ���ѱ׷� - ��� dao
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
     * ����
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUsrGrpGridDTO
     * @return
     */
    public int deleteUsrGrp(String compNo, String usrGrpId);
    
    /**
     * �޴� ���� ���� 
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