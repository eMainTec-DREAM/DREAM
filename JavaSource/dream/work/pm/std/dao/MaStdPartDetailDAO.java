package dream.work.pm.std.dao;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPartDetailDTO;
import dream.work.pm.std.dto.MaStdPartListDTO;

/**
 * 표준항목 리스트 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 */
public interface MaStdPartDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     */
    public MaStdPartDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdPartListDTO maStdPartListDTO, User loginUser);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPartDetailDTO
     * @return
     */
    public int insertDetail(MaStdPartDetailDTO maStdPartDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPartDetailDTO
     * @return
     */
    public int updateDetail(MaStdPartDetailDTO maStdPartDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);
}