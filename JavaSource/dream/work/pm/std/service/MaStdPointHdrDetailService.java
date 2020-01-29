package dream.work.pm.std.service;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointHdrDetailDTO;

/**
 * 표준항목 - 상세 service
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface MaStdPointHdrDetailService
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
    public MaStdPointHdrDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)throws Exception;
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdPointHdrDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdPointHdrDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User loginUser) throws Exception;
    /**
     * detail confirm
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maStdPointHdrDetailDTO
     * @return
     * @throws Exception
     */
    public int confirmDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO) throws Exception;

    public int insertRevisionHistAndUpdateMstr(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User user);
}
