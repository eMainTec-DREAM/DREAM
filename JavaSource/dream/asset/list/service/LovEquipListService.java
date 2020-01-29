package dream.asset.list.service;

import java.util.List;

import org.json.simple.parser.ParseException;

import common.bean.User;
import dream.asset.list.dto.LovEquipListDTO;
import dream.asset.list.form.LovEquipListForm;

/**
 * 설비 Service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public interface LovEquipListService
{

    /**
     * 설비검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param LovEquipListDTO
     * @param loginUser
     * @return
     */
    List findEquipList(LovEquipListDTO lovEquipListDTO, User loginUser, LovEquipListForm lovEquipListForm);
    
    LovEquipListDTO setJsonParm(LovEquipListDTO lovEquipListDTO) throws ClassNotFoundException, ParseException;

	List findEquipAcList(LovEquipListDTO lovEquipListDTO, User user, LovEquipListForm lovEquipListForm);
	
	/**
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEquipListDTO
     * @return
     */
    public String findTotalCount(LovEquipListDTO lovEquipListDTO, User loginUser, LovEquipListForm lovEquipListForm) throws Exception;

}