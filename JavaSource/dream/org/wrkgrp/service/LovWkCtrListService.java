package dream.org.wrkgrp.service;

import java.util.List;

import common.bean.User;
import dream.org.wrkgrp.dto.LovWkCtrListDTO;
import dream.org.wrkgrp.form.LovWkCtrListForm;

/**
 * �۾��׷� �˾� Service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovWkCtrListService
{

    /**
     * �۾��׷� �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovWkCtrListDTO
     * @param loginUser
     * @return
     */
    List findWkCtrList(LovWkCtrListDTO lovWkCtrListDTO, User loginUser);
    /**
     * �۾��׷� �˻� AC LOV
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWkCtrListForm
     * @param loginUser
     * @return
     */
    public List findWkCtrAcList(LovWkCtrListForm lovWkCtrListForm, User loginUser);
    /**
     * �۾��׷� �˻� AC LOV
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWkCtrListForm
     * @param loginUser
     * @return
     */
    public String findTotalCount(LovWkCtrListForm lovWkCtrListForm, User loginUser);
}