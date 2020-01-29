package dream.org.wrkgrp.service;

import java.util.List;

import common.bean.User;
import dream.org.wrkgrp.dto.LovWkCtrListDTO;
import dream.org.wrkgrp.form.LovWkCtrListForm;

/**
 * 작업그룹 팝업 Service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovWkCtrListService
{

    /**
     * 작업그룹 검색
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
     * 작업그룹 검색 AC LOV
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
     * 작업그룹 검색 AC LOV
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