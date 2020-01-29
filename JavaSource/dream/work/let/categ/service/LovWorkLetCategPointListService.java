package dream.work.let.categ.service;

import java.util.List;

import common.bean.User;
import dream.work.let.categ.dto.LovWorkLetCategPointListDTO;
import dream.work.let.categ.form.LovWorkLetCategPointListForm;

/**
 * 안전작업허가서 표준점검항목 Lov Service
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovWorkLetCategPointListService
{

	/**
	 * AC Find List
	 * @param lovWorkLetCategPointListDTO
	 * @param user
	 * @param lovWorkLetCategPointListForm
	 * @return
	 */
	public List findWorkLetCategPointAcList(LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO, User user, LovWorkLetCategPointListForm lovWorkLetCategPointListForm);

	/**
     * find Total Count
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWorkLetCategPointListDTO
	 * @param user
	 * @param lovWorkLetCategPointListForm
     * @return
     */
	public String findTotalCount(LovWorkLetCategPointListDTO lovWorkLetCategPointListDTO, User user, LovWorkLetCategPointListForm lovWorkLetCategPointListForm) throws Exception;
	
}