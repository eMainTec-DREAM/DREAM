package mobile.dream.org.wrkgrp.dao;

import java.util.List;

import common.bean.User;
import mobile.dream.org.wrkgrp.dto.OrgWkCtrLovListDTO;

/**
 * �۾��׷� �˾�
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface OrgWkCtrLovListDAO
{
    /**
     * �۾��׷� �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param orgWkCtrLovListDTO
     * @return
     */
    public List findWkCtrList(OrgWkCtrLovListDTO orgWkCtrLovListDTO, User loginUser);
}