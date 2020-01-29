package dream.work.pm.std.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointHdrDetailDTO;

/**
 * 표준항목 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 */
public interface MaStdPointHdrDetailDAO extends BaseJdbcDaoSupportIntf
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
    public MaStdPointHdrDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointHdrDetailDTO
     * @return
     */
    public int insertDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointHdrDetailDTO
     * @return
     */
    public int updateDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User loginUser);

    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointHdrDetailDTO
     * @return
     */
    public int confirmDetail(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO);
    public int insertRevisionHist(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User user, String histId);
	public int updateStdPointMstrLastVersion(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO, User user, String histId);
	
}