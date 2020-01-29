package dream.work.pm.std.dao;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkDetailDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;

/**
 * 표준항목 리스트 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 */
public interface MaStdWorkDetailDAO
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
    public MaStdWorkDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdWorkListDTO maStdWorkListDTO, User loginUser);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWorkDetailDTO
     * @return
     */
    public int insertDetail(MaStdWorkDetailDTO maStdWorkDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdWorkDetailDTO
     * @return
     */
    public int updateDetail(MaStdWorkDetailDTO maStdWorkDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);
}