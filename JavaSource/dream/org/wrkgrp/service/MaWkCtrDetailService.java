package dream.org.wrkgrp.service;

import dream.org.wrkgrp.dto.MaWkCtrDetailDTO;

/**
 * 작업그룹 - 상세 service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaWkCtrDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param wkCtrNo
     * @return
     * @throws Exception
     */
    public MaWkCtrDetailDTO findDetail(String compNo, String wkCtrNo)throws Exception;
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWkCtrDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaWkCtrDetailDTO maWkCtrDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWkCtrDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWkCtrDetailDTO maWkCtrDetailDTO) throws Exception;
    
    /**
     * validWkCtrNo
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWkCtrDetailDTO
     * @return
     * @throws Exception
     */
    public String validWkCtrNo(MaWkCtrDetailDTO maWkCtrDetailDTO) throws Exception;
}
