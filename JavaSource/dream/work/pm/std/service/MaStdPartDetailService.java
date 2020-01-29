package dream.work.pm.std.service;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartDetailDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;

/**
 * 표준항목 리스트 - 상세 service
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface MaStdPartDetailService
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
    public MaStdPartDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdPartListDTO maStdPartListDTO, User loginUser)throws Exception;
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdPartDetailDTO
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaStdPartDetailDTO maStdPartDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdPartDetailDTO
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaStdPartDetailDTO maStdPartDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception;
}
