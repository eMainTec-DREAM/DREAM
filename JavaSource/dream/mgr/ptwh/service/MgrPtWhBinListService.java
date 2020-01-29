/**
 * 
 */
package dream.mgr.ptwh.service;

import java.util.List;

import common.bean.User;
import dream.mgr.ptwh.dto.MgrPtWhBinListDTO;
/**
 * ��ǰâ�� ������ġ - List Service
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public interface MgrPtWhBinListService {
	/**
	 * ��ǰâ�� ������ġ LIST
	 * @param mgrPtWhBinListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findPtWhEmpList(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) throws Exception;
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
	 * @param mgrPtWhBinListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrPtWhBinListDTO mgrPtWhBinListDTO, User user) throws Exception;
    public int insertQrList(String[] selectRows, String fileName, User user) throws Exception;
}
