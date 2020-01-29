package dream.rcm.taskmap.service;

import java.util.List;

import common.bean.User;
import dream.rcm.taskmap.dto.LovTaskMapListDTO;
import dream.rcm.taskmap.form.LovTaskMapListForm;

/**
 * 질의 팝업 Service
 * @author  hyosung
 * @version $Id: LovTaskMapListService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
 * @since   1.0
 *
 */
public interface LovTaskMapListService
{

    /**
     * 질의검색
     * @author  hyosung
     * @version $Id: LovTaskMapListService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
     * @since   1.0
     * 
     * @param lovTaskMapListDTO
     * @param loginUser
     * @param lovTaskMapListForm
     * @return
     */
    List findTaskMapList(LovTaskMapListDTO lovTaskMapListDTO, User loginUser, LovTaskMapListForm lovTaskMapListForm);
}