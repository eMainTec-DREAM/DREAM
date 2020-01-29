package dream.work.pm.std.service;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointDetailDTO;
import dream.work.pm.std.dto.MaStdPointListDTO;

/**
 * 표준항목 리스트 - 상세 service
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface MaStdPointDetailService
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
    public MaStdPointDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdPointListDTO maStdPointListDTO, User loginUser)throws Exception;
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdPointDetailDTO
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaStdPointDetailDTO maStdPointDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdPointDetailDTO
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaStdPointDetailDTO maStdPointDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception;
}
