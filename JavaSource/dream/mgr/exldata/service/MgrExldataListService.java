/**
 * 
 */
package dream.mgr.exldata.service;

import java.util.List;

import common.bean.User;
import dream.mgr.exldata.dto.MgrExldataCommonDTO;
/**
 * Excel Data Upload - List Service
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 */
public interface MgrExldataListService {
	/**
	 * FIND LIST
	 * @param mgrExldataCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findExldataList(MgrExldataCommonDTO mgrExldataCommonDTO, User user) throws Exception;
	/**
	 * DELETE LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteExldataList(String table, String[] deleteRows, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param mgrExldataCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MgrExldataCommonDTO mgrExldataCommonDTO, User user) throws Exception;
    
    public List getDummyHeader(MgrExldataCommonDTO mgrExldataCommonDTO, User user) throws Exception;
    
    public List findExcelTemplateData(MgrExldataCommonDTO mgrExldataCommonDTO, User user) throws Exception;
    
    public void uploadData(MgrExldataCommonDTO mgrExldataCommonDTO, User user) throws Exception;
}
