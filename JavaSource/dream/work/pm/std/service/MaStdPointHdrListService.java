package dream.work.pm.std.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;

/**
 * ǥ���׸� - ��� service
 * @author kim21017 
 * @version $Id:  $
 * @since   1.0
 */
public interface MaStdPointHdrListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findStdPointHdrList(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);    
    
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
    
    public String findTotalCount(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception;
}
