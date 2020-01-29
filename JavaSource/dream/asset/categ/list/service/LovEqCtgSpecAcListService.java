package dream.asset.categ.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.categ.list.dto.LovEqCtgSpecAcListDTO;
import dream.asset.categ.list.form.LovEqCtgSpecAcListForm;

/**
 * 설비종류별제원 Service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovEqCtgSpecAcListService
{

    /**
     * 설비종류별제원
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param lovEqCtgSpecAcListDTO
     * @return
     */
    List findEqCtgSpecAcList(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovEqCtgSpecAcListDTO
	 * @param user
	 * @param lovEqCtgSpecAcListForm
	 * @return
	 * @throws Exception 
	 */
	List findEqCtgSpecAcAcList(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User user, LovEqCtgSpecAcListForm lovEqCtgSpecAcListForm) throws Exception;
    public String findTotalCount(LovEqCtgSpecAcListDTO lovEqCtgSpecAcListDTO, User loginUser, LovEqCtgSpecAcListForm lovEqCtgSpecAcListForm) throws Exception;

}