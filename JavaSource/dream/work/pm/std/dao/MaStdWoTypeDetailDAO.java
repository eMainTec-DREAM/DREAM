package dream.work.pm.std.dao;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWoTypeDetailDTO;
import dream.work.pm.std.dto.MaStdWoTypeListDTO;

/**
 * 표준항목 리스트 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 */
public interface MaStdWoTypeDetailDAO
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
    public MaStdWoTypeDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdWoTypeListDTO maStdWoTypeListDTO, User loginUser);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWoTypeDetailDTO
     * @return
     */
    public int insertDetail(MaStdWoTypeDetailDTO maStdWoTypeDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWoTypeDetailDTO
     * @return
     */
    public int updateDetail(MaStdWoTypeDetailDTO maStdWoTypeDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);
}