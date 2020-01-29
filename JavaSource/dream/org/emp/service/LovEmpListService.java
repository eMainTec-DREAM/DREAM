package dream.org.emp.service;

import java.util.List;

import org.json.simple.parser.ParseException;

import common.bean.User;
import dream.org.emp.dto.LovEmpListDTO;
import dream.org.emp.form.LovEmpListForm;

/**
 * ����˾� Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovEmpListService
{

    /**
     * ����˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @param LovEmpListDTO
     * @return
     */
    List findEmpList(LovEmpListDTO lovEmpListDTO, User loginUser);

	/**
	 * AC Find List
	 * @param lovEmpListDTO
	 * @param user
	 * @param lovEmpListForm
	 * @return
	 */
	List findEmpAcList(LovEmpListDTO lovEmpListDTO, User user, LovEmpListForm lovEmpListForm);
	
	public String findTotalCount(LovEmpListForm lovEmpListForm, User user);

	LovEmpListDTO setJsonParm(LovEmpListDTO lovEmpListDTO) throws ClassNotFoundException, ParseException;
}