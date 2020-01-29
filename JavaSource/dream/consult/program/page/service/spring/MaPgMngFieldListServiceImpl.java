package dream.consult.program.page.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dao.MaPgMngFieldListDAO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;
import dream.consult.program.page.service.MaPgMngFieldListService;

/**
 * 화면별 필드 목록    
 * @author kim21017
 * @version $Id: MaPgMngFieldListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPgMngFieldListServiceTarget"
 * @spring.txbn id="maPgMngFieldListService"
 * @spring.property name="maPgMngFieldListDAO" ref="maPgMngFieldListDAO"
 */
public class MaPgMngFieldListServiceImpl implements MaPgMngFieldListService
{
    private MaPgMngFieldListDAO maPgMngFieldListDAO = null;


	public List findFieldList(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {      	    
        return maPgMngFieldListDAO.findFieldList(maPgMngCommonDTO, maPgMngFieldListDTO);
    }
	public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO, MaPgMngFieldListDTO maPgMngFieldListDTO, User user) throws Exception
    {      
        return maPgMngFieldListDAO.findTotalCount(maPgMngCommonDTO, maPgMngFieldListDTO, user);
    }
	public MaPgMngFieldListDAO getMaPgMngFieldListDAO() {
		return maPgMngFieldListDAO;
	}

	public void setMaPgMngFieldListDAO(MaPgMngFieldListDAO maPgMngFieldListDAO) {
		this.maPgMngFieldListDAO = maPgMngFieldListDAO;
	}
	
	public int deleteFieldList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maPgMngFieldListDAO.deleteFieldList(id);
            }
        
        return result;
    }
	
	public int sysYColList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maPgMngFieldListDAO.sysYColList(id);
            }
        
        return result;
    }
    
    public int sysNColList(String[] deleteRows) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maPgMngFieldListDAO.sysNColList(id);
            }
        
        return result;
    }

}

