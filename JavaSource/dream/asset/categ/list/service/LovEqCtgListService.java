package dream.asset.categ.list.service;

import java.util.List;

import common.bean.User;

import dream.asset.categ.list.dto.LovEqCtgListDTO;
import dream.asset.categ.list.form.LovEqCtgListForm;

/**
 * 설비종류팝업 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovEqCtgListService
{

    /**
     * 설비종류검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovEqCtgListDTO
     * @param loginUser
     * @return
     */
    List findEqCtgList(LovEqCtgListDTO lovEqCtgListDTO, User loginUser);

	List findEqCtgAcList(LovEqCtgListDTO lovEqCtgListDTO, User user, LovEqCtgListForm lovEqCtgListForm);
}