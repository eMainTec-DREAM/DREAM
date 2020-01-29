/**
 * 
 */
package dream.part.list.service;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.PartListSafQtyListDTO;
/**
 * 부품창고 보관위치 - List Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface PartListSafQtyListService {
	/**
	 * 부품창고 보관위치 LIST
	 * @param partListSafQtyListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findPtWhEmpList(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception;
	/**
	 * DELETE 부품창고 보관위치 LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deletePtWhEmpList(String[] deleteRows, User user) throws Exception;
	/**
	 * 부품창고 보관위치 LIST COUNT
	 * @param partListSafQtyListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception;
}
