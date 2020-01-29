package mobile.dream.org.wrkgrp.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.org.wrkgrp.dto.OrgWkCtrLovListDTO;

/**
 * 작업그룹 팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface OrgWkCtrLovListDAO
{
    /**
     * 작업그룹 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param orgWkCtrLovListDTO
     * @return
     */
    public List findWkCtrList(OrgWkCtrLovListDTO orgWkCtrLovListDTO, User loginUser);
}