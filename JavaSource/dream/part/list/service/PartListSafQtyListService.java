/**
 * 
 */
package dream.part.list.service;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.PartListSafQtyListDTO;
/**
 * ��ǰâ�� ������ġ - List Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface PartListSafQtyListService {
	/**
	 * ��ǰâ�� ������ġ LIST
	 * @param partListSafQtyListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findPtWhEmpList(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception;
	/**
	 * DELETE ��ǰâ�� ������ġ LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deletePtWhEmpList(String[] deleteRows, User user) throws Exception;
	/**
	 * ��ǰâ�� ������ġ LIST COUNT
	 * @param partListSafQtyListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception;
}
