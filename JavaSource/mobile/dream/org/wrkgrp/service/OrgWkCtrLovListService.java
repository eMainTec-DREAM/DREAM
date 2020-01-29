package mobile.dream.org.wrkgrp.service;

import java.util.List;

import common.bean.User;
import mobile.dream.org.wrkgrp.dto.OrgWkCtrLovListDTO;

/**
 * 작업그룹 팝업 Service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public interface OrgWkCtrLovListService
{

    /**
     * 작업그룹 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param OrgWkCtrLovListDTO
     * @param loginUser
     * @return
     */
    List findWkCtrList(OrgWkCtrLovListDTO orgWkCtrLovListDTO, User loginUser);
}