package common.mafinder.mamstr.service;

import java.util.List;

import common.bean.User;
import common.mafinder.mamstr.dto.LovInvtListDTO;
import common.mafinder.mamstr.form.LovInvtListForm;

/**
 * ���� ��� �˾� Service
 * @author  js.lee
 * @version $Id: $
 * @since   1.0
 *
 */
public interface LovInvtListService
{

    /**
     * ���� ��� �˻�
     * @author  js.lee
     * @version $Id: $
     * @param lovInvtListForm 
     * @since   1.0
     * 
     * @param LovInvtListDTO
     * @param loginUser
     * @return
     */
    List findInvtList(LovInvtListDTO lovInvtListDTO, User user, LovInvtListForm lovInvtListForm);

}