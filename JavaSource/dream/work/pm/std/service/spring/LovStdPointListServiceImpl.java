package dream.work.pm.std.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dao.LovStdPointListDAO;
import dream.work.pm.std.dto.LovStdPointListDTO;
import dream.work.pm.std.service.LovStdPointListService;

/**
 * 표준항목선택 ServiceImpl
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovStdPointListServiceTarget"
 * @spring.txbn id="lovStdPointListService"
 * @spring.property name="lovStdPointListDAO" ref="lovStdPointListDAO"
 */
public class LovStdPointListServiceImpl implements LovStdPointListService
{
    /** 표준항목선택 DAO */
    private LovStdPointListDAO lovStdPointListDAO = null;

    public LovStdPointListDAO getLovStdPointListDAO() 
    {
		return lovStdPointListDAO;
	}

	public void setLovStdPointListDAO(LovStdPointListDAO lovStdPointListDAO) 
	{
		this.lovStdPointListDAO = lovStdPointListDAO;
	}

	public List findStdPointList(LovStdPointListDTO lovStdPointListDTO, User loginUser)
    {
        List resultList = null;
        resultList = lovStdPointListDAO.findStdPointList(lovStdPointListDTO,loginUser);
        
        return resultList;
    }

	public void inputStdPoint(String[] deleteRows, User user,  LovStdPointListDTO lovStdPointListDTO) 
	{
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
            	if(id == "") continue;
            	
            	lovStdPointListDTO.setStWrkPointId(id);
            	lovStdPointListDAO.inputStdPoint(lovStdPointListDTO, user);
            }
	}
}