package dream.org.wrkgrp.service;

import java.util.List;

import common.bean.User;
import dream.org.wrkgrp.dto.MaWkCtrCommonDTO;

/**
 * �۾��׷� - ��� service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaWkCtrListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: $
     * @param maWkCtrCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findWkCtrList(MaWkCtrCommonDTO maWkCtrCommonDTO,User user);    
   
    /**
     * delete List
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param deleteRows
     * @return
     * @throws Exception
     */
    public int deleteList(String compNo, String[] deleteRows) throws Exception;
}
