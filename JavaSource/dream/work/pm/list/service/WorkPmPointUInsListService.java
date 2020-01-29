package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
/**
 * 사용량 항목 - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface WorkPmPointUInsListService {
	/**
	 * 사용량 항목 LIST
	 * @param workPmPointUInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
	/**
	 * DELETE 사용량 항목 LIST
	 * @param deleteRows
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int deleteList(String[] deleteRows, User user) throws Exception;
	/**
	 * 사용량 항목 LIST COUNT
	 * @param workPmPointUInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
}
