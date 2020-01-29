package dream.part.rep.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rep.dao.MaPtRepAppListDAO;
import dream.part.rep.dto.MaPtRepAppListDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.service.MaPtRepAppListService;

/**
 * 수리기안 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtRepAppListServiceTarget"
 * @spring.txbn id="maPtRepAppListService"
 * @spring.property name="maPtRepAppListDAO" ref="maPtRepAppListDAO"
 */
public class MaPtRepAppListServiceImpl implements MaPtRepAppListService
{
    private MaPtRepAppListDAO maPtRepAppListDAO = null;

    public MaPtRepAppListDAO getMaPtRepAppListDAO() 
    {
        return maPtRepAppListDAO;
    }

    public void setMaPtRepAppListDAO(MaPtRepAppListDAO maPtRepAppListDAO) 
    {
        this.maPtRepAppListDAO = maPtRepAppListDAO;
    }

    public List findList(MaPtRepCommonDTO maPtRepCommonDTO, MaPtRepAppListDTO maPtRepAppListDTO, User loginUser)
    {      
        return maPtRepAppListDAO.findList(maPtRepCommonDTO,maPtRepAppListDTO, loginUser);
    }
    
    public int deleteList(String[] deleteRows, User loginUser) throws Exception
    {
        int result = 0;
        MaPtRepAppListDTO maPtRepAppListDTO = null;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            maPtRepAppListDTO = new MaPtRepAppListDTO();
            maPtRepAppListDTO.setCompNo(loginUser.getCompNo());
            maPtRepAppListDTO.setPtRprAppListId(deleteRows[i]);
            
            result = result + maPtRepAppListDAO.deleteList(maPtRepAppListDTO, loginUser);
        }
        
        return result;
    }

}