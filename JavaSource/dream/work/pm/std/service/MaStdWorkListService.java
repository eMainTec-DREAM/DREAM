package dream.work.pm.std.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;

/**
 * 표준항목 리스트- 목록 service
 * @author kim21017 
 * @version $Id:  $
 * @since   1.0
 */
public interface MaStdWorkListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param maStdWorkListDTO
     * @param loginUser
     * @return 조회 결과 
     * @throws Exception
     */
    public List findStdPointList(MaStdPointCommonDTO maStdPointCommonDTO,MaStdWorkListDTO maStdWorkListDTO, User loginUser);    
    
    /**
     * delete List
     * @author kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;
}
