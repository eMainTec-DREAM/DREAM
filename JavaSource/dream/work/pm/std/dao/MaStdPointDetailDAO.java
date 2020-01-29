package dream.work.pm.std.dao;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointDetailDTO;
import dream.work.pm.std.dto.MaStdPointListDTO;

/**
 * 표준항목 리스트 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 */
public interface MaStdPointDetailDAO
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
    public MaStdPointDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdPointListDTO maStdPointListDTO, User loginUser);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointDetailDTO
     * @return
     */
    public int insertDetail(MaStdPointDetailDTO maStdPointDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointDetailDTO
     * @return
     */
    public int updateDetail(MaStdPointDetailDTO maStdPointDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);
}