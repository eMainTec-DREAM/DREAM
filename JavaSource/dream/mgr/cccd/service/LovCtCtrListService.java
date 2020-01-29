package dream.mgr.cccd.service;

import java.util.List;

import common.bean.User;
import dream.mgr.cccd.dto.LovCtCtrListDTO;
import dream.mgr.cccd.form.LovCtCtrListForm;

/**
 * CP�˾� Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovCtCtrListService
{

    /**
     * CP�˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovCtCtrListDTO
     * @param loginUser
     * @return
     */
    List findCtCtrList(LovCtCtrListDTO lovCtCtrListDTO, User loginUser);

    List findCtCtrACList(LovCtCtrListDTO lovCtCtrListDTO, User user, LovCtCtrListForm lovCtCtrListForm);
    
    public String findTotalCount(LovCtCtrListDTO lovCtCtrListDTO, User user, LovCtCtrListForm lovCtCtrListForm);
}