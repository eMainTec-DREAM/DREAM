package dream.work.let.categ.service;

import java.util.List;

import common.bean.User;
import dream.work.let.categ.dto.LovWorkLetCategListDTO;
import dream.work.let.categ.form.LovWorkLetCategListForm;

/**
 * 안전작업유형 Lov Service
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovWorkLetCategListService
{

	/**
	 * AC Find List
	 * @param lovWorkLetCategListDTO
	 * @param user
	 * @param lovWorkLetCategListForm
	 * @return
	 */
	public List findWorkLetCategAcList(LovWorkLetCategListDTO lovWorkLetCategListDTO, User user, LovWorkLetCategListForm lovWorkLetCategListForm);

	/**
     * find Total Count
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWorkLetCategListDTO
	 * @param user
	 * @param lovWorkLetCategListForm
     * @return
     */
	public String findTotalCount(LovWorkLetCategListDTO lovWorkLetCategListDTO, User user, LovWorkLetCategListForm lovWorkLetCategListForm) throws Exception;
	
}