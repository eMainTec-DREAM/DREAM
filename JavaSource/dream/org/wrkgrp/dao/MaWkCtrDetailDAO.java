package dream.org.wrkgrp.dao;

import dream.org.wrkgrp.dto.MaWkCtrDetailDTO;

/**
 * 작업그룹 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaWkCtrDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param wkCtrId
     * @return
     */
    public MaWkCtrDetailDTO findDetail(String compNo, String wkCtrId);
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWkCtrDetailDTO
     * @return
     */
    public int insertDetail(MaWkCtrDetailDTO maWkCtrDetailDTO);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWkCtrDetailDTO
     * @return
     */
    public int updateDetail(MaWkCtrDetailDTO maWkCtrDetailDTO);
    
    /**
     * valid wkCtrNo
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maWkCtrDetailDTO
     * @return
     */
    public String validWkCtrNo(MaWkCtrDetailDTO maWkCtrDetailDTO);
}