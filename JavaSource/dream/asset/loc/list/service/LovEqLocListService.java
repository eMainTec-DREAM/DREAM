package dream.asset.loc.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.loc.list.dto.LovEqLocListDTO;
import dream.asset.loc.list.form.LovEqLocListForm;

/**
 * ��ġ�з��˾� Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovEqLocListService
{

    /**
     * ��ġ�з��˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovEqLocListDTO
     * @param loginUser
     * @return
     */
    List findEqLocList(LovEqLocListDTO lovEqLocListDTO, User loginUser);
    /**
     * ��ġ�з� AC �˻�
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqLocListForm
     * @param loginUser
     * @return
     */
    List findEqLocAcList(LovEqLocListForm lovEqLocListForm, User loginUser);
}