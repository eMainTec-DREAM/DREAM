/**
 * 
 */
package dream.part.list.service;

import java.util.List;

import common.bean.User;
import dream.part.list.dto.PartListBinListDTO;
/**
 * 부품창고 보관위치 - List Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface PartListBinListService {
	/**
	 * 부품창고 보관위치 LIST
	 * @param partListBinListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findPtWhBinList(PartListBinListDTO partListBinListDTO, User user) throws Exception;
	/**
	 * DELETE 부품창고 보관위치 LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deletePtWhBinList(String[] deleteRows, User user) throws Exception;
	/**
	 * 부품창고 보관위치 LIST COUNT
	 * @param partListBinListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(PartListBinListDTO partListBinListDTO, User user) throws Exception;
}
