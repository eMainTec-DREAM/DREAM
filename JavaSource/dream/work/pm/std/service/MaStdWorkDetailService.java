package dream.work.pm.std.service;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkDetailDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;

/**
 * ǥ���׸� ����Ʈ - �� service
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface MaStdWorkDetailService
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
    public MaStdWorkDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdWorkListDTO maStdWorkListDTO, User loginUser)throws Exception;
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdWorkDetailDTO
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaStdWorkDetailDTO maStdWorkDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdWorkDetailDTO
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaStdWorkDetailDTO maStdWorkDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser) throws Exception;
}
