package dream.work.pm.std.service;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWoTypeDetailDTO;
import dream.work.pm.std.dto.MaStdWoTypeListDTO;

/**
 * 표준항목 리스트 - 상세 service
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface MaStdWoTypeDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MaStdWoTypeDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdWoTypeListDTO maStdWoTypeListDTO, User loginUser)throws Exception;
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdWoTypeDetailDTO
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaStdWoTypeDetailDTO maStdWoTypeDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdWoTypeDetailDTO
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaStdWoTypeDetailDTO maStdWoTypeDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception;
}
