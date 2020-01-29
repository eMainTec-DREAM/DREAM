package dream.work.pm.std.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;

/**
 * ǥ���׸� ����Ʈ - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaStdWorkListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param maStdWorkListDTO
     * @param loginUser
     * @return List
     */
    public List findStdPointList(MaStdPointCommonDTO maStdPointCommonDTO, MaStdWorkListDTO maStdWorkListDTO, User loginUser);
    
    /**
     * ǥ���׸� ����
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param id
     * @param loginUser
     * @return
     */
    public int deleteList(String id, User loginUser);
}