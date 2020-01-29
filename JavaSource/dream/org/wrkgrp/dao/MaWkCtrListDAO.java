package dream.org.wrkgrp.dao;

import java.util.List;

import common.bean.User;
import dream.org.wrkgrp.dto.MaWkCtrCommonDTO;

/**
 * 작업그룹 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaWkCtrListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWkCtrCommonDTO
     * @return List
     */
    public List findWkCtrList(MaWkCtrCommonDTO maWkCtrCommonDTO, User user);
    
    /**
     * 삭제
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param wkCtrId
     * @return
     */
    public int deleteWkCtr(String compNo, String wkCtrId);
}