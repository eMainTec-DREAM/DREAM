package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.LovPmNoListDTO;
import dream.work.pm.list.form.LovPmNoListForm;

/**
 * ���������˾� Service
 * @author  kim21017
 * @version $Id: LovPmNoListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
 * @since   1.0
 *
 */
public interface LovPmNoListService
{

    /**
     * �������˰˻�
     * @author  kim21017
     * @version $Id: LovPmNoListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param LovPmNoListDTO
     * @param loginUser
     * @return
     */
    List findPmList(LovPmNoListDTO lovPmNoListDTO, User loginUser);
    /**
     * �������˰˻� AC LOV
     * @author  kim21017
     * @version $Id: LovPmNoListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPmNoListForm
     * @param loginUser
     * @return
     */
    List findPmAcList(LovPmNoListForm lovPmNoListForm, User loginUser);
    /**
     * �������˰˻� count
     * @author  kim21017
     * @version $Id: LovPmNoListService.java,v 1.2 2014/01/28 07:49:27 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovPmNoListForm
     * @param loginUser
     * @return
     */
    String findTotalCount(LovPmNoListForm lovPmNoListForm, User loginUser);
}