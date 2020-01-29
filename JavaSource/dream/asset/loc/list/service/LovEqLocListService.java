package dream.asset.loc.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.loc.list.dto.LovEqLocListDTO;
import dream.asset.loc.list.form.LovEqLocListForm;

/**
 * 위치분류팝업 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovEqLocListService
{

    /**
     * 위치분류검색
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
     * 위치분류 AC 검색
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