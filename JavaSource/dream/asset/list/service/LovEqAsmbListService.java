package dream.asset.list.service;

import java.util.List;

import org.json.simple.parser.ParseException;

import common.bean.User;
import dream.asset.list.dto.LovEqAsmbListDTO;
import dream.asset.list.form.LovEqAsmbListForm;

/**
 * 설비부위팝업 Service
 * @author  hyosung
 * @version $Id: LovEqAsmbListService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
 * @since   1.0
 *
 */
public interface LovEqAsmbListService
{

    /**
     * 설비부위검색
     * @author  hyosung
     * @version $Id: LovEqAsmbListService.java,v 1.2 2014/01/28 07:49:27 hyosung Exp $
     * @since   1.0
     * 
     * @param LovEqAsmbListDTO
     * @param loginUser
     * @param lovEqAsmbListForm
     * @return
     */
   List findEqAsmbAcList(LovEqAsmbListDTO lovEqAsmbListDTO, User loginUser, LovEqAsmbListForm lovEqAsmbListForm);    
    
    LovEqAsmbListDTO setJsonParm(LovEqAsmbListDTO lovEqAsmbListDTO, LovEqAsmbListForm lovEqAsmbListForm) throws ClassNotFoundException, ParseException;

    List findEqAsmbByPmAcList(LovEqAsmbListDTO lovEqAsmbListDTO, User loginUser, LovEqAsmbListForm lovEqAsmbListForm);
}