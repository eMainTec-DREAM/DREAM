package dream.work.pm.std.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointListDTO;

/**
 * ǥ���׸� ����Ʈ- ��� service
 * @author kim21017 
 * @version $Id:  $
 * @since   1.0
 */
public interface MaStdPointListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param maStdPointListDTO
     * @param loginUser
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findStdPointList(MaStdPointCommonDTO maStdPointCommonDTO,MaStdPointListDTO maStdPointListDTO, User loginUser);    
    
    /**
     * delete List
     * @author kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;
}
