package dream.edu.list.service;

import java.util.List;

import common.bean.User;
import dream.edu.list.dto.LovEduListDTO;
import dream.edu.list.form.LovEduListForm;

/**
 * ±³À°°úÁ¤ Service
 * @author  hyosung
 * @version $Id: LovEduListService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
 * @since   1.0
 *
 */
public interface LovEduListService
{

    /**
     * ±³À°°úÁ¤ °Ë»ö
     * @author  hyosung
     * @version $Id: LovEduListService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
     * @since   1.0
     * 
     * @param LovEduListDTO
     * @param loginUser
     * @param lovEduListForm
     * @return
     */
    List findEduList(LovEduListDTO lovEduListDTO, User loginUser, LovEduListForm lovEduListForm);
}